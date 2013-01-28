package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import databeans.Customer;
import databeans.Transaction;
import formbeans.Cus_RequestCheckForm;
import formbeans.Emp_DepositCheckForm;

import model.Model;
import model.TransactionDAO;
import model.CustomerDAO;
import model.MyDAOException;


public class Cus_RequestCheckAction extends Action {   
    private FormBeanFactory<Cus_RequestCheckForm> formBeanFactory = FormBeanFactory.getInstance(Cus_RequestCheckForm.class);
    private CustomerDAO customerDAO;
    
    public Cus_RequestCheckAction(Model model) {
        customerDAO = model.getCustomerDAO();
    }
    
    public String getName() { return "requestcheck.do"; }
    
    public String perform(HttpServletRequest request) {
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors",errors);
        
        try {
            Cus_RequestCheckForm form = formBeanFactory.create(request);
            request.setAttribute("form",form);
            
            // Look up the customer
            Customer customer = (Customer) request.getSession(false).getAttribute("customer");
            
            if (customer == null) {
                return "login-cus.jsp";
            }
            
            double balance = customerDAO.getAvailableCash(customer.getCustomerID());
            request.setAttribute("cash", balance);
            
            if (!form.isPresent()) {
                return "request-check-cus.jsp";
            }
            
            
            // Any validation errors?
            errors.addAll(form.getValidationErrors());
            
            double withdrawAmount = Double.parseDouble(form.getWithdraw());
            
            if (withdrawAmount > balance) {
                errors.add("Withdraw amount cannot be greater than your current balance!");
            }
            
            if (errors.size() != 0) {
                return "request-check-cus.jsp";
            }
            
            balance = balance - withdrawAmount;
            customer.setAvailableCash(balance);
            
            customerDAO.update(customer);
            
            request.setAttribute("cash", balance);
            
            String webapp = request.getContextPath();
            return webapp + "/requestcheck.do";
            
        } catch (MyDAOException e) {
            errors.add(e.getMessage());
            return "error.jsp";
        } catch (FormBeanException e) {
            errors.add(e.getMessage());
            return "error.jsp";
        }
    }
}
