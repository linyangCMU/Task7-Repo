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

	        // If the servlet path isn't "/login.do", the controller sent a request here
	        // because the user needed to login.  We want to support redirect back to the
	        // original request only if the user is just trying to view an image.
	        // So, only set up redirect back to original request path="/view.do".
	        if (request.getServletPath().equals("/view.do")) {
	        	String redirectTo = request.getRequestURI()+"?"+request.getQueryString();
	        	HttpSession session = request.getSession();
	        	session.setAttribute("redirectTo",redirectTo);
	        }

	        // If no params were passed, return with no errors so that the form will be
	        // presented (we assume for the first time).
	        if (!form.isPresent()) {
	            return "login.jsp";
	        }

	        // Any validation errors?
	        errors.addAll(form.getValidationErrors());
	        if (errors.size() != 0) {
	            return "login.jsp";
	        }

	        // Look up the user
	        Customer user = customerDAO.lookup(form.getUserName());
	        
	        if (user == null) {
	            errors.add("User Name not found");
	            return "login.jsp";
	        }

	        // Check the password
	        if (!user.checkPassword(form.getPassword())) {
	            errors.add("Incorrect password");
	            return "login.jsp";
	        }
	
	        // Attach (this copy of) the user bean to the session
	        HttpSession session = request.getSession();
	        session.setAttribute("user",user);
	
	        // After successful login send to...
	        String redirectTo = (String) session.getAttribute("redirectTo");
	        if (redirectTo != null) return redirectTo;
	        
	        
	        // If redirectTo is null, redirect to the "manage" action
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
