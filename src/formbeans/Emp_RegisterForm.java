package formbeans;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class Emp_RegisterForm extends FormBean {
	private String fundName;
	private String fundSymbol;
	
	public String getFundName() {
        return fundName;
    }
    public void setFundName(String fundName) {
        this.fundName = trimAndConvert(fundName,"<>\"");
    }
    public String getFundSymbol() {
        return fundSymbol;
    }
    public void setFundSymbol(String fundSymbol) {
        this.fundSymbol = trimAndConvert(fundSymbol,"<>\"");
    }

	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();

		if (fundName == null || fundName.length() == 0) {
			errors.add("Fund name is required");
		}

		if (fundSymbol == null || fundSymbol.length() == 0) {
			errors.add("Fund symbol (ticker) is required");
		}
		
		return errors;
	}
}
