package controller;

import java.sql.Date;
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
import databeans.Employee;
import databeans.Fund;
import databeans.Portfolio;
import databeans.Position;
import databeans.Transaction;
import formbeans.Cus_RegisterForm;

public class Cus_RegisterAction extends Action {
	private FormBeanFactory<Cus_RegisterForm> formBeanFactory = FormBeanFactory
			.getInstance(Cus_RegisterForm.class);

	private CustomerDAO CustomerDAO;

	public Cus_RegisterAction(Model model) {
		CustomerDAO = model.getCustomerDAO();
	}

	public String getName() {
		return "create-customer-acct.do";
	}

	public String perform(HttpServletRequest request) {
		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);

		try {
			Cus_RegisterForm form = formBeanFactory.create(request);
			request.setAttribute("form", form);
			Employee employee = (Employee) request.getSession(false).getAttribute("employee");

			// If no params were passed, return with no errors so that the form
			// will be
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

			CustomerDAO.create(user);
			
			// Look up the customer
			Customer customer = (Customer) request.getAttribute("customer");

			if (customer == null)
				customer = CustomerDAO.lookup(form.getUserName());

			if (customer == null)
				customer = (Customer) request.getSession().getAttribute("cus");

			if (customer == null) {
				errors.add("User not found");
				return "create-acct-cus.jsp";
			}

			// Attach (this copy of) the user bean to the session
			HttpSession session = request.getSession();
			session.setAttribute("cus", customer);

			// If redirectTo is null, redirect to the "manage" action
			// return "manage-customers-emp.jsp";
			return "feedback-create-acct-cus.jsp";
		} catch (MyDAOException e) {
			errors.add(e.getMessage());
			return "error.jsp";
		} catch (FormBeanException e) {
			errors.add(e.getMessage());
			return "error.jsp";
		}
	}
}
