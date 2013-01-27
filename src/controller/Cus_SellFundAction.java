package controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import model.CustomerDAO;
import model.FundDAO;
import model.Model;
import model.TransactionDAO;

import org.mybeans.form.FormBeanFactory;

import databeans.Customer;
import databeans.Transaction;

import formbeans.Cus_SellFundForm;

public class Cus_SellFundAction extends Action{
	private FormBeanFactory<Cus_SellFundForm> formBeanFactory = FormBeanFactory
			.getInstance(Cus_SellFundForm.class);
	
	private TransactionDAO transactionDAO;

	private CustomerDAO customerDAO;
	public Cus_SellFundAction(Model model) {
		transactionDAO = model.getTransactionDAO();

		customerDAO = model.getCustomerDAO();
	}
	public String getName() {
		
		return "cus_sellFund.do";
	}
	public String perform(HttpServletRequest request) {
		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);

		try {
			Cus_SellFundForm form = formBeanFactory.create(request);
			request.setAttribute("form", form);
			// If no params were passed, return with no errors so that the form
			// will be presented (we assume for the first time).
			if (!form.isPresent()) {
				return "buy-fund-cus.jsp";
			}

			// Any validation errors?
			errors.addAll(form.getValidationErrors());
			if (errors.size() != 0) {
				System.out.println(errors.toString());
				return "buy-fund-cus.jsp";
			}
			Customer customer = (Customer) request.getSession().getAttribute("customer");
			int customer_id = customerDAO.lookup(customer.getUsername()).getCustomerID();
			Transaction t = new Transaction();
			t.setCustomer_id(customer_id);
			
			int fund_id = form.getFundId();
			
			
			t.setFund_id(fund_id);
			
			Date date = new Date();
			t.setDate(date);
			
			t.setTransaction_type("Sell");
			t.setShares(form.getShares());
			t.setStatus("Pending");
			transactionDAO.create(t); 
			
	        return "viewportfolio.do";
	  } catch (Exception e) {
      	errors.add(e.toString());
      	return "error.jsp";
      }
	}

	
	
}
