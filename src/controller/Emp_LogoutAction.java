package controller;

import javax.servlet.http.HttpServletRequest;

import model.Model;

public class Emp_LogoutAction extends Action {	

	public Emp_LogoutAction(Model model) {
		
	}
	
	public String getName() { return "login.do"; }
	
    public String perform(HttpServletRequest request) {
		
    	return null;	
    }
}
