package formbeans;
import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;
public class Cus_BuyFundForm extends FormBean{
	private String fundId;
	private String amount;
	public String getFundId() {
		return fundId;
	}
	public void setFundId(String fundId) {
		this.fundId = fundId;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	
	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();
		
		if (fundId == null || fundId.length() == 0) {
			errors.add("No such fund !");
		}
		
		if (amount == null || amount.length() == 0) {
			errors.add("Amount is required");
		}
		
		
		try {
    		if (Double.parseDouble(amount) < 0.01) {
    		    errors.add("Amount must be greater than 0.01"); 
            }
		}
		catch (NumberFormatException e) {
		    errors.add("Amount must be a valid number");
		}
		
		if (errors.size() > 0) {
			return errors;
		}
		
		

		return errors;
	}
}
