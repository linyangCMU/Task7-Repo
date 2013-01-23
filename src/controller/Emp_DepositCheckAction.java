package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import databeans.Customer;
import databeans.Transaction;
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
                return "deposit-check-emp.jsp";
            }
            
            // Any validation errors?
            errors.addAll(form.getValidationErrors());
            if (errors.size() != 0) {
                return "deposit-check-emp.jsp";
            }
            
            // Look up the customer
            Customer customer = customerDAO.lookup(form.getUserName());
            double amount = Double.parseDouble(form.getDeposit());
            customer.setCash(customer.getCash() + amount);
            
            customerDAO.updateCash(customer);
            /*
            Transaction transaction = new Transaction();
            transaction.setCustomer_id(customer.getCustomerID());
            transaction.setAmount((int)amount*1000);
            transaction.setDate(execute_date);
            transaction.set
            transactionDAO.create(transaction);
            */
            // Attach (this copy of) the customer object to the session
            HttpSession session = request.getSession();
            session.setAttribute("Customer",customer);
            
            String webapp = request.getContextPath();
            return webapp + "/view-customer-acct-emp.jsp";
        } catch (MyDAOException e) {
            errors.add(e.getMessage());
            return "error.jsp";
        } catch (FormBeanException e) {
            errors.add(e.getMessage());
            return "error.jsp";
        }
    }
}
