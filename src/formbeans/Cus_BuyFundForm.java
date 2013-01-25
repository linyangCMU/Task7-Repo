package formbeans;
import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;
public class Cus_BuyFundForm extends FormBean{
	private String fundName;
	private String amount;
	public String getFundName() {
		return fundName;
	}
	public void setFundName(String fundName) {
		this.fundName = fundName;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	
	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();
		
		if (fundName == null || fundName.length() == 0) {
			errors.add("No such fund !");
		}
		
		if (amount == null || amount.length() == 0) {
			errors.add("Amount is required");
		}
		
		if (errors.size() > 0) {
			return errors;
		}
		
		

		return errors;
	}
}
