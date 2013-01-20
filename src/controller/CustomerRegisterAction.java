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
import formbeans.CustomerRegisterForm;

public class CustomerRegisterAction extends Action {
	private FormBeanFactory<CustomerRegisterForm> formBeanFactory = FormBeanFactory.getInstance(CustomerRegisterForm.class);

	private CustomerDAO CustomerDAO;
	
	public CustomerRegisterAction(Model model) {
		CustomerDAO = model.getCustomerDAO();
	}

	public String getName() { return "login2.do"; }

    public String perform(HttpServletRequest request) {
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors",errors);
        
        try {
        	CustomerRegisterForm form = formBeanFactory.create(request);
	        request.setAttribute("form",form);
	
	        // If no params were passed, return with no errors so that the form will be
	        // presented (we assume for the first time).
	        if (!form.isPresent()) {
	            return "createaccountA.html";
	        }
	
	        // Any validation errors?
	        errors.addAll(form.getValidationErrors());
	        if (errors.size() != 0) {
	        	System.out.println(errors.toString());
	            return "createaccountA.html";
	        }
	        System.out.println("hahaha");
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
	        
        	CustomerDAO.create(user);
        
			// Attach (this copy of) the user bean to the session
	        HttpSession session = request.getSession(false);
	        session.setAttribute("user",user);
	
	        // After successful registration (and login) send to...
	        String redirectTo = (String) session.getAttribute("redirectTo");
	        if (redirectTo != null) return redirectTo;
	        
	        // If redirectTo is null, redirect to the "manage" action
			String webapp = request.getContextPath();
			return webapp + "/viewPortafolio.html";
        } catch (MyDAOException e) {
        	errors.add(e.getMessage());
        	return "register.jsp";
        } catch (FormBeanException e) {
        	errors.add(e.getMessage());
        	return "register.jsp";
        }
    }
}
