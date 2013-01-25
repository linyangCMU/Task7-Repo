package controller;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import model.Model;
import model.CustomerDAO;
import model.TransactionDAO;
import model.FundDAO;
import javax.servlet.http.HttpServletRequest;
import org.mybeans.form.FormBeanFactory;

import databeans.Customer;
import databeans.Transaction;

import formbeans.Cus_BuyFundForm;
import formbeans.Cus_ChangePwdForm;


public class Cus_BuyFundAction extends Action{
	private FormBeanFactory<Cus_BuyFundForm> formBeanFactory = FormBeanFactory
			.getInstance(Cus_BuyFundForm.class);
	
	private TransactionDAO transactionDAO;
	private FundDAO fundDAO;
	private CustomerDAO customerDAO;
	public Cus_BuyFundAction(Model model) {
		transactionDAO = model.getTransactionDAO();
		fundDAO = model.getFundDAO();
		customerDAO = model.getCustomerDAO();
	}
	
	public String getName() {
		return "cus_buyFund.do";
	}
	public String perform(HttpServletRequest request) {
		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);

		try {
			Cus_BuyFundForm form = formBeanFactory.create(request);
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
			
			String fundname = form.getFundName();
			int fund_id = fundDAO.lookup(fundname,null).getId();
			
			t.setFund_id(fund_id);
			
			Date date = new Date();
			t.setDate(date);
			
			t.setTransaction_type("Pending");
			t.setAmount(Double.parseDouble(form.getAmount()));
			
			transactionDAO.create(t); 
			
	        return "viewportfolio.do";
	  } catch (Exception e) {
      	errors.add(e.toString());
      	return "error.jsp";
      }
	}
}
