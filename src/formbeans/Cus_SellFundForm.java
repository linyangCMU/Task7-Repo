package formbeans;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class Cus_SellFundForm extends FormBean{
	private String fundId;
	private String shares;
	public int getFundId() {
		return Integer.parseInt(fundId);
	}
	public void setFundName(String fundId) {
		this.fundId = fundId;
	}
	public double getShares() {
		return Double.parseDouble(shares);
	}
	public void setShares(String shares) {
		this.shares = shares;
	}
	
	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();
		
		if (fundId == null || fundId.length() == 0) {
			errors.add("No such fund !");
		}
		
		if (shares == null || shares.length() == 0) {
			errors.add("Shares is required");
		}
		
		if (errors.size() > 0) {
			return errors;
		}
		
		

		return errors;
	}
}
