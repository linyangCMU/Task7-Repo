package controller;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.http.*;

import model.Model;

import databeans.*;


public class Controller extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void init() throws ServletException {
		Model model = new Model(getServletConfig());
		Action.add(new CustomerLoginAction(model));
		Action.add(new CustomerLogoutAction(model));
		Action.add(new EmployeeLoginAction(model));
		Action.add(new EmployeeLogoutAction(model));
		Action.add(new EmployeeRegisterAction(model));
	}

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nextPage = performTheAction(request);
        sendToNextPage(nextPage,request,response);
    }
    
	private String performTheAction(HttpServletRequest request) {
		HttpSession session     = request.getSession(true);
    	String      servletPath = request.getServletPath();
        String      action = getActionName(servletPath);
        
        if (action.equals("login1.do")) {
        	// Allow these actions without logging in
			return Action.perform(action,request);
        }
        
        if (action.equals("register1.do")) {
        	// Allow these actions without logging in
        	System.out.println("Action: register1.do");
			return Action.perform(action,request);
        }
        
        // Let the logged in user run his chosen action
		return Action.perform(action,request);
	}
    
    private void sendToNextPage(String nextPage, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    	// System.out.println("nextPage="+nextPage);
    	
    	if (nextPage == null) {
    		response.sendError(HttpServletResponse.SC_NOT_FOUND,request.getServletPath());
    		return;
    	}
    	
    	if (nextPage.charAt(0) == '/') {
			String host  = request.getServerName();
			String port  = ":"+String.valueOf(request.getServerPort());
			if (port.equals(":80")) port = "";
			response.sendRedirect("http://"+host+port+nextPage);
			return;
    	}
    	
   		RequestDispatcher d = request.getRequestDispatcher("/"+nextPage);
   		d.forward(request,response);
    }
    
    private String getActionName(String path) {
        int slash = path.lastIndexOf('/');
        return path.substring(slash+1);
    }
}
