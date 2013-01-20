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

import formbeans.ChangePwdForm;
import formbeans.EmployeeRegisterForm;

public class changepwdAAction extends Action {
	private FormBeanFactory<ChangePwdForm> formBeanFactory = FormBeanFactory
			.getInstance(ChangePwdForm.class);

	private CustomerDAO customerDAO;

	public changepwdAAction(Model model) {
		customerDAO = model.getCustomerDAO();
	}

	public String getName() {
		return "login.do";
	}

	public String perform(HttpServletRequest request) {
		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);

		try {
			ChangePwdForm form = formBeanFactory.create(request);
			request.setAttribute("form", form);
			// If no params were passed, return with no errors so that the form
			// will be
			// presented (we assume for the first time).
			if (!form.isPresent()) {
				return "changepwdA.html";
			}

			// Any validation errors?
			errors.addAll(form.getValidationErrors());
			if (errors.size() != 0) {
				System.out.println(errors.toString());
				return "changepwdA.html";
			}
			System.out.println("hahaha");

			Customer customer = (Customer) request.getSession().getAttribute("customer");
			
			// Change the password
			customerDAO.setPassword(customer.getCustomerID(), form.getNewPassword());

			request.setAttribute("message","Password changed for "+customer.getCustomerID());
	        return "viewPortafolio.jsp";
	  } catch (Exception e) {
      	errors.add(e.toString());
      	return "viewPortafolio.jsp";
      }
	}
}
