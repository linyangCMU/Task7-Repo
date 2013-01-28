package formbeans;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class Cus_SellFundForm extends FormBean{
	private String fundName;
	private String shares;
	
	
	public void setShares(String shares) {
		this.shares = shares;
	}


	public String getFundName() {
		return fundName;
	}


	public void setFundName(String fundName) {
		this.fundName = fundName;
	}
	public String getShares() {
		return shares;
	}


	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();
		
		if (fundName == null || fundName.length() == 0) {
			errors.add("No such fund !");
		}
		
		
		
		if (errors.size() > 0) {
			return errors;
		}
		
		

		return errors;
	}


	

	
}
