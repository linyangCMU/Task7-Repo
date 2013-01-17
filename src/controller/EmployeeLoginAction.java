package controller;

import javax.servlet.http.HttpServletRequest;

import model.Model;

public class EmployeeLoginAction extends Action {	

	public EmployeeLoginAction(Model model) {
		
	}
	
	public String getName() { return "login.do"; }
	
    public String perform(HttpServletRequest request) {
		
    	return null;	
    }
}
