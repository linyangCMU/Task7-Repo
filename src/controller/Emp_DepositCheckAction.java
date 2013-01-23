package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import databeans.Customer;
import formbeans.Emp_DepositCheckForm;

import model.Model;
import model.TransactionDAO;
import model.CustomerDAO;
import model.MyDAOException;


public class Emp_DepositCheckAction extends Action {   
    private FormBeanFactory<Emp_DepositCheckForm> formBeanFactory = FormBeanFactory.getInstance(Emp_DepositCheckForm.class);
    private CustomerDAO customerDAO;
    private TransactionDAO transactionDAO;
    
    public Emp_DepositCheckAction(Model model) {
        customerDAO = model.getCustomerDAO();
        transactionDAO = model.getTransactionDAO();
    }
    
    public String getName() { return "depositcheck.do"; }
    
    public String perform(HttpServletRequest request) {
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors",errors);
        
        try {
            Emp_DepositCheckForm form = formBeanFactory.create(request);
            request.setAttribute("form",form);

            
            if (!form.isPresent()) {
                return "deposit-check.jsp";
            }
            
            // Any validation errors?
            errors.addAll(form.getValidationErrors());
            if (errors.size() != 0) {
                return "deposit-check.jsp";
            }
            
            // Look up the fund
            Customer customer = customerDAO.lookup(form.getUserName());
            customer.setCash(customer.getCash() + Double.parseDouble(form.getDeposit()));
            
            customerDAO.updateCash(customer);
            // Attach (this copy of) the funds object to the session
            HttpSession session = request.getSession();
            session.setAttribute("Customer",customer);
            
            String webapp = request.getContextPath();
            return webapp + "/view-customer-acct.jsp";
        } catch (MyDAOException e) {
            System.out.println("DAO error");
            errors.add(e.getMessage());
            return "error.jsp";
        } catch (FormBeanException e) {
            System.out.println("FormBean error");
            errors.add(e.getMessage());
            return "error.jsp";
        }
    }
}