package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import databeans.Employee;
import formbeans.Emp_LoginForm;

import model.Model;
import model.EmployeeDAO;
import model.MyDAOException;


public class Emp_LoginAction extends Action {	
	private FormBeanFactory<Emp_LoginForm> formBeanFactory = FormBeanFactory.getInstance(Emp_LoginForm.class);
	private EmployeeDAO employeeDAO;
	
	public Emp_LoginAction(Model model) {
		employeeDAO = model.getEmployeeDAO();
	}
	
	public String getName() { return "login1.do"; }
	
    public String perform(HttpServletRequest request) {
    	List<String> errors = new ArrayList<String>();
        request.setAttribute("errors",errors);
        
        try {
	    	Emp_LoginForm form = formBeanFactory.create(request);
	        request.setAttribute("form",form);

	        
	        if (!form.isPresent()) {
	        	System.out.println("haha1");
	            return "login1.html";
	        }

	        // Any validation errors?
	        errors.addAll(form.getValidationErrors());
	        if (errors.size() != 0) {
	        	System.out.println(errors.toString());
	            return "login1.html";
	        }
	        
	        // Look up the user
	        Employee user = employeeDAO.lookup(form.getUserName());
	        System.out.println("haha");
	        
	        if (user == null) {
	            errors.add("User Name not found");
	            System.out.println("not found");
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
			return webapp + "/viewCustomerList.html";
        } catch (MyDAOException e) {
        	errors.add(e.getMessage());
        	return "error.jsp";
        } catch (FormBeanException e) {
        	errors.add(e.getMessage());
        	return "error.jsp";
        }
    }
}
