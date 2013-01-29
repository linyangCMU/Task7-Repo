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
		
		try {
            if (Double.parseDouble(shares) < 0.01) {
                errors.add("Amount must be greater than $ 0.01"); 
            }
        }
        catch (Exception e) {
            errors.add("Amount must be a valid number");
        }
		
		if (errors.size() > 0) {
			return errors;
		}
		
		

		return errors;
	}


	

	
}
