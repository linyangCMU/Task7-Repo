<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<div id="section-navigation">
    <ul>
<% 
if (session.getAttribute("employee") == null) {
%>
        <li><span class="menu-item"><a href="employee-login.do">Login</a></span></li>
<%   
} else {
%>
        <li><a href="emp_changePwd.do">Change Password</a></li>
        <li><a href="#">Create Employee Account</a></li>
        <li><a href="#">Create Customer Account</a></li>
        <li><a href="createfund.do">Create Fund</a></li>
        <li><a href="transitionday.do">Transition Day</a></li>
        <li><a href=".do">Search Customer</a></li>
        <li><a href="logout-emp.do">Logout</a></li>
        
        <br/>
        <br/>
<%
	if (request.getAttribute("customer") != null) {
%>
        <li>Actions for this Customer</li>
        <li><a href="#">Reset Password</a></li>
        <li><a href="#">View Account</a></li>
        <li><a href="#">Transaction History</a></li>
        <li><a href="depositcheck.do">Deposit Check</a></li>        
<%
    }
}
%>
    </ul>
</div>
