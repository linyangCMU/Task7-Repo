<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div id="section-navigation">
    <ul>
<% 
	if (session.getAttribute("customer") == null) {
%>
        <li><span class="menu-item"><a href="customer-login.do">Login</a></span></li>
<%   
    } else {
%>
		<li><span class="menu-item"><a href="login.do">Change password</a></span></li>
		<li><span class="menu-item"><a href="buyFund.do">Buy Fund</a></span></li>
		<li><span class="menu-item"><a href="sellFund.do">sell Fund</a></span></li>
		<li><span class="menu-item"><a href="RequestCheck.do">Request Check</a></span></li>
		<li><span class="menu-item"><a href="transactionhistory.do">Transaction History</a></span></li>
		<li><span class="menu-item"><a href="viewPortafolio.do">View Account</a></span></li>
		<li><span class="menu-item"><a href="researchfund.do">Research Fund</a></span></li>
		<li><span class="menu-item"><a href="logout.do">Logout</a></span></li>
<%
    }
%>
    </ul>
</div>