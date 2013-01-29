package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.Model;
import model.CustomerDAO;
import model.MyDAOException;

import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import databeans.Customer;
import formbeans.Cus_RegisterForm;

public class Cus_RegisterAction extends Action {
	private FormBeanFactory<Cus_RegisterForm> formBeanFactory = FormBeanFactory.getInstance(Cus_RegisterForm.class);

	private CustomerDAO customerDAO;
	
	public Cus_RegisterAction(Model model) {
		customerDAO = model.getCustomerDAO();
	}

	public String getName() { return "create-customer-acct.do"; }
    public String perform(HttpServletRequest request) {
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors",errors);
        
        try {
            Customer customer = (Customer) request.getSession(false).getAttribute("customer");
            
            if(customer == null) {
                return "login-cus.jsp";
            }
            
            customer = customerDAO.lookup(customer.getCustomerID());
            request.getSession(false).setAttribute("customer", customer);
            
            Cus_RegisterForm form = formBeanFactory.create(request);
	        request.setAttribute("form",form);
	
	        // If no params were passed, return with no errors so that the form will be
	        // presented (we assume for the first time).
	        if (!form.isPresent()) {
	            return "create-acct-cus.jsp";
	        }
	
	        // Any validation errors?
	        errors.addAll(form.getValidationErrors());
	        if (errors.size() != 0) {
	        	System.out.println(errors.toString());
	            return "create-acct-cus.jsp";
	        }
	        // Create the user bean
	        Customer user = new Customer();
	        user.setUsername(form.getUserName());
	        user.setFirstName(form.getFirstName());
	        user.setLastName(form.getLastName());
	        user.setPassword(form.getPassword());
	        user.setAddr1(form.getAddr1());
	        user.setAddr2(form.getAddr2());
	        user.setCity(form.getCity());
	        user.setState(form.getState());
	        user.setZip(form.getZip());
	        
        	customerDAO.create(user);
        
			// Attach (this copy of) the user bean to the session
	        HttpSession session = request.getSession(false);
	        session.setAttribute("user",user);
	
	        // After successful registration (and login) send to...
	        String redirectTo = (String) session.getAttribute("redirectTo");
	        if (redirectTo != null) return redirectTo;
	        
	        // If redirectTo is null, redirect to the "manage" action
			return "manage-customers-emp.jsp";        
		} catch (MyDAOException e) {
        	errors.add(e.getMessage());
        	return "error.jsp";
        } catch (FormBeanException e) {
        	errors.add(e.getMessage());
        	return "error.jsp";
        }
    }
}
