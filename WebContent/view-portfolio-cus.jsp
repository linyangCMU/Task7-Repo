
<%@page import="databeans.Portfolio" %>
<%@page import="databeans.Customer" %>
<%@page import="databeans.Transaction" %>
<%@ page import="java.util.*" %>
<%@ page import="java.sql.Date" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Customer template</title>
<link rel="stylesheet" type="text/css" href="style/main.css">
</head>
<body>

<div id="container">
  <div id="header">
    <h1> Carnegie Financial Service - Mutual Fund Management </h1>
  </div>
  <div id="navigation">
    <ul>
      <li><a href="viewPortafolio.do">Home</a></li>
      <li><a href="#">About</a></li>
      <li><a href="#">Services</a></li>
      <li><a href="#">Contact us</a></li>
    </ul>
  </div>
  <div id="content-container">
     <div id="section-navigation">
    	<ul>
              

						<li><span class="menu-item"><a href="login.do">Change password</a></span></li>
						<li><span class="menu-item"><a href="buyFund.do">Buy Fund</a></span></li>
						<li><span class="menu-item"><a href="sellFund.do">sell Fund</a></span></li>
						<li><span class="menu-item"><a href="RequestCheck.do">Request Check</a></span></li>
						<li><span class="menu-item"><a href="transactionhistory.do">Transaction History</a></span></li>
						<li><span class="menu-item"><a href="viewPortafolio.do">View Account</a></span></li>
						<li><span class="menu-item"><a href="researchfund.do">Research Fund</a></span></li>
						<li><span class="menu-item"><a href="logout.do">Logout</a></span></li>
			
		</ul>
     <h2> Customer Account </h2>
      <p>
      <form method="post" action="viewPortafolio.do">
        <table>
          <tr>
            <td> <b>Name</b> </td>
            <td> {$Customer.firstname} {$Customer.lastname} </td>
          </tr>
          <tr>
            <td> <b>Address</b> </td>
            <td>{$Customer.addr_line1}</td>
            <td>{$Customer.addr_line2}</td>
            <td>{$Customer.city}</td>
            <td>{$Customer.state}</td>
            <td>{$Customer.zip}</td>
          </tr>
          <tr>
            <td> <b>Last Trading Date</b> </td>
            <td>
            <%
            	Date lastDate = (Date) request.getAttribute("lastExecuteDate");
            	out.println(lastDate);
            
            %>
            </td>
          </tr>
          <tr>
            <td> <b>Cash Balance</b> </td>
            <td> {$Customer.cash}</td>
          </tr>
        </table>
      </form>
      <form method="post" action="viewPortafolio.do">
        <table>
          <tr>
            <td> <b>Fund Name</b> </td>
            <td> <b>Share Amount</b> </td>
            <td> <b>Value per Share</b> </td>
            <td> <b>Total Value</b> </td>
          </tr>
          <%
          ArrayList<Portfolio> portfolios = (ArrayList<Portfolio>) request.getAttribute("portfolios");
          if(portfolios != null){
        	  for(Portfolio portfolio:portfolios){
            	  out.println("<td>");
            	  out.println(portfolio.getFundName());
            	  out.println("</td><td>");
            	  out.println(portfolio.getShares());
            	  out.println("</td><td>");
            	  out.println(portfolio.getPrice());
            	  out.println("</td><td>");
            	  out.println(portfolio.getTotal());
            	  out.println("</td>");	
        	  }
          }
          %>
          </tr>
        </table>
      </form>
             <div id="aside">
      <h3> Welcome to CFS- Mutual Fund Management </h3>
      <p> Duis autem vel eum iriure dolor in hendrerit in vulputate velit esse molestie consequat, vel illum dolore eu feugiat nulla facilisis at vero eros et accumsan. </p>
    </div>
    <div id="footer"> Copyright © Mutual Fund Application by Team e-Motion | CMU MSIT ebusiness Task7 2013 </div>
  </div>
</div>
</body>
</html>