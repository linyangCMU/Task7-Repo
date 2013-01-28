package controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import model.CustomerDAO;
import model.FundDAO;
import model.Model;
import model.PositionDAO;
import model.TransactionDAO;

import org.mybeans.form.FormBeanFactory;

import databeans.Customer;
import databeans.Fund;
import databeans.Position;
import databeans.Transaction;

import formbeans.Cus_SellFundForm;

public class Cus_SellFundAction extends Action{
	private FormBeanFactory<Cus_SellFundForm> formBeanFactory = FormBeanFactory
			.getInstance(Cus_SellFundForm.class);
	
	private TransactionDAO transactionDAO;
	private PositionDAO positionDAO;
	private CustomerDAO customerDAO;
	private FundDAO fundDAO;
	public Cus_SellFundAction(Model model) {
		transactionDAO = model.getTransactionDAO();
		positionDAO = model.getPositionDAO();
		customerDAO = model.getCustomerDAO();
		fundDAO = model.getFundDAO();
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
			
			Customer customer = (Customer) request.getSession().getAttribute("customer");
			int customer_id = customerDAO.lookup(customer.getUsername()).getCustomerID();
			String fundName = form.getFundName();
			String shares = form.getShares();
			Fund fund = fundDAO.lookup(fundName, null);
			if (shares == null||shares.length() == 0) {			
				
				fund.setShares(positionDAO.lookup(customer_id, fund.getId()).getShares());
				request.setAttribute("fund", fund);					
				return "sell-fund-cus.jsp";
			}
			

			// Any validation errors?
			errors.addAll(form.getValidationErrors());
			if (errors.size() != 0) {
				System.out.println(errors.toString());
				return "sell-fund-cus.jsp";
			}
			
			Transaction t = new Transaction();
			t.setCustomer_id(customer_id);
			
			int fund_id = fund.getId();
			
			
			t.setFund_id(fund_id);
			
			t.setDate(null);
			
			
			t.setTransaction_type("SELL");
			t.setShares(Double.parseDouble(form.getShares()));
			t.setStatus("Pending");
			transactionDAO.create(t); 
			
	        return "viewportfolio.do";
	  } catch (Exception e) {
      	errors.add(e.toString());
      	return "error.jsp";
      }
	}

	
	
}
