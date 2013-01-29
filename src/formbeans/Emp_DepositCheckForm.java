package formbeans;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class Emp_DepositCheckForm extends FormBean {
	private String userName;
	private String deposit;
	
	public String getUserName()  { return userName;  }
	public String getDeposit()   { return deposit;   }
	
	public void setUserName(String s)  { userName  = trimAndConvert(s,"<>\"");  }
	public void setDeposit(String s)   { deposit  = s.trim();                   }

	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();

		if (userName == null || userName.length() == 0) {
			errors.add("Username is required");
		}

		if (deposit == null || deposit.length() == 0) {
			errors.add("Deposit amount is required");
		}

		try {
            if (Double.parseDouble(deposit) <= 0) {
                errors.add("Amount must be positive number"); 
            }
        }
        catch (NumberFormatException e) {
            errors.add("Amount must be a valid number");
        }
		
		// missing validation for cash
		
		if (errors.size() > 0) {
			return errors;
		}
		
		return errors;
	}
}
