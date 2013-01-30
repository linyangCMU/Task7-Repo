package formbeans;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class Cus_RegisterForm extends FormBean {
	private String firstName = "";
	private String lastName = "";
	private String userName = "";
	private String password = "";
	private String confirmPassword = "";
	private String addr1 = "";
	private String addr2 = "";
	private String city = "";
	private String state = "";
	private String zip = "";
	
	public String getFirstName() { return firstName; }
	public String getLastName()  { return lastName;  }
	public String getUserName()  { return userName;  }
	public String getPassword()  { return password;  }
	public String getConfirm()   { return confirmPassword;   }
	public String getAddr1()    { return addr1;    }
	public String getAddr2()    { return addr2;    }
	public String getCity()      { return city;      }
	public String getState()     { return state;     }
	public String getZip()       { return zip;       }
	
	public void setFirstName(String s) { firstName = trimAndConvert(s,"<>\"");  }
	public void setLastName(String s)  { lastName  = trimAndConvert(s,"<>\"");  }
	public void setUserName(String s)  { userName  = trimAndConvert(s,"<>\"");  }
	public void setPassword(String s)  { password  = s.trim();                  }
	public void setConfirmPassword(String s)   { confirmPassword   = s.trim();                  }
	
	public void setAddr1(String addr1) {
		this.addr1 = addr1.trim();
	}
	public void setAddr2(String addr2) {
		this.addr2 = addr2.trim();
	}
	public void setCity(String city) {
		this.city = city.trim();
	}
	public void setState(String state) {
		this.state = state.trim();
	}
	public void setZip(String zip) {
		this.zip = zip.trim();
	}


	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();

		if (firstName == null || firstName.length() == 0) {
			errors.add("First Name is required");
		}

		if (lastName == null || lastName.length() == 0) {
			errors.add("Last Name is required");
		}

		if (userName == null || userName.length() == 0) {
			errors.add("User Name is required");
		}

		if (password == null || password.length() == 0) {
			errors.add("Password is required");
		}

		if (confirmPassword == null || confirmPassword.length() == 0) {
			errors.add("Confirm Password is required");
		}
		
		if (errors.size() > 0) {
			return errors;
		}
		
		if (!password.equals(confirmPassword)) {
			errors.add("Passwords are not the same");
		}
		
		return errors;
	}
}
