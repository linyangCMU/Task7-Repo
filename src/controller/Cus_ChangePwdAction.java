package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.mybeans.form.FormBeanFactory;

import model.CustomerDAO;
import model.EmployeeDAO;
import model.Model;
import model.MyDAOException;

import databeans.Customer;
import databeans.Employee;

import formbeans.Cus_ChangePwdForm;
import formbeans.Emp_RegisterForm;

public class Cus_ChangePwdAction extends Action {
	private FormBeanFactory<Cus_ChangePwdForm> formBeanFactory = FormBeanFactory
			.getInstance(Cus_ChangePwdForm.class);

	private CustomerDAO customerDAO;

	public Cus_ChangePwdAction(Model model) {
		customerDAO = model.getCustomerDAO();
	}

	public String getName() {
		return "login.do";
	}

	public String perform(HttpServletRequest request) {
		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);

		try {
			Cus_ChangePwdForm form = formBeanFactory.create(request);
			request.setAttribute("form", form);
			// If no params were passed, return with no errors so that the form
			// will be
			// presented (we assume for the first time).
			if (!form.isPresent()) {
				return "change-pwd-cus.jsp";
			}

			// Any validation errors?
			errors.addAll(form.getValidationErrors());
			if (errors.size() != 0) {
				System.out.println(errors.toString());
				return "change-pwd-cus.jsp";
			}

			Customer customer = (Customer) request.getSession().getAttribute("customer");
			
			// Change the password
			customerDAO.setPassword(customer.getUsername(), form.getNewPassword());

			request.setAttribute("message","Password changed for "+customer.getCustomerID());
	        return "viewPortafolio.jsp";
	  } catch (Exception e) {
      	errors.add(e.toString());
      	return "viewPortafolio.jsp";
      }
	}
}
