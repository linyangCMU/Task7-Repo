package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import databeans.Customer;
import formbeans.CustomerLoginForm;

import model.Model;
import model.CustomerDAO;
import model.MyDAOException;


public class CustomerLoginAction extends Action {	
	private FormBeanFactory<CustomerLoginForm> formBeanFactory = FormBeanFactory.getInstance(CustomerLoginForm.class);
	private CustomerDAO customerDAO;
	
	public CustomerLoginAction(Model model) {
		customerDAO = model.getCustomerDAO();
	}
	
	public String getName() { return "login1.do"; }
	
    public String perform(HttpServletRequest request) {
    	List<String> errors = new ArrayList<String>();
        request.setAttribute("errors",errors);
        
        try {
	    	CustomerLoginForm form = formBeanFactory.create(request);
	        request.setAttribute("form",form);

	        
	        if (!form.isPresent()) {
	            return "login1.html";
	        }

	        // Any validation errors?
	        errors.addAll(form.getValidationErrors());
	        if (errors.size() != 0) {
	            return "login1.html";
	        }

	        // Look up the user
	        Customer user = customerDAO.lookup(form.getUserName());
	        
	        if (user == null) {
	            errors.add("User Name not found");
	            return "login1.html";
	        }

	        // Check the password
	        if (!user.checkPassword(form.getPassword())) {
	            errors.add("Incorrect password");
	            return "login1.html";
	        }
	
	        // Attach (this copy of) the user bean to the session
	        HttpSession session = request.getSession();
	        session.setAttribute("user",user);
	
	      
			String webapp = request.getContextPath();
			return webapp + "/manage.do";
        } catch (MyDAOException e) {
        	errors.add(e.getMessage());
        	return "error.jsp";
        } catch (FormBeanException e) {
        	errors.add(e.getMessage());
        	return "error.jsp";
        }
    }
}
