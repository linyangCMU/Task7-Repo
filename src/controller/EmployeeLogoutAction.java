package controller;

import javax.servlet.http.HttpServletRequest;

import model.Model;

public class EmployeeLogoutAction extends Action {	

	public EmployeeLogoutAction(Model model) {
		
	}
	
	public String getName() { return "login.do"; }
	
    public String perform(HttpServletRequest request) {
		
    	return null;	
    }
}
