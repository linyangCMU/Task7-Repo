package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.Model;
import model.EmployeeDAO;
import model.MyDAOException;

import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import databeans.Employee;
import formbeans.EmployeeRegisterForm;

public class EmployeeRegisterAction extends Action {
	private FormBeanFactory<EmployeeRegisterForm> formBeanFactory = FormBeanFactory.getInstance(EmployeeRegisterForm.class);

	private EmployeeDAO employeeDAO;
	
	public EmployeeRegisterAction(Model model) {
		employeeDAO = model.getEmployeeDAO();
	}

	public String getName() { return "register1.do"; }

    public String perform(HttpServletRequest request) {
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors",errors);
        
        try {
        	EmployeeRegisterForm form = formBeanFactory.create(request);
	        request.setAttribute("form",form);
	
	        // If no params were passed, return with no errors so that the form will be
	        // presented (we assume for the first time).
	        if (!form.isPresent()) {
	            return "createaccountE.html";
	        }
	
	        // Any validation errors?
	        errors.addAll(form.getValidationErrors());
	        if (errors.size() != 0) {
	        	System.out.println(errors.toString());
	            return "createaccountE.html";
	        }
	        System.out.println("hahaha");
	        // Create the user bean
	        Employee user = new Employee();
	        user.setUsername(form.getUserName());
	        user.setFirstName(form.getFirstName());
	        user.setLastName(form.getLastName());
	        user.setPassword(form.getPassword());
        	employeeDAO.create(user);
        
			// Attach (this copy of) the user bean to the session
	        HttpSession session = request.getSession(false);
	        session.setAttribute("user",user);
	
	        // After successful registration (and login) send to...
	        String redirectTo = (String) session.getAttribute("redirectTo");
	        if (redirectTo != null) return redirectTo;
	        
	        // If redirectTo is null, redirect to the "manage" action
			String webapp = request.getContextPath();
			return webapp + "/viewCustomerList.html";
        } catch (MyDAOException e) {
        	errors.add(e.getMessage());
        	return "register.jsp";
        } catch (FormBeanException e) {
        	errors.add(e.getMessage());
        	return "register.jsp";
        }
    }
}
