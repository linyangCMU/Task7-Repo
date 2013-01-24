
<%@page import="databeans.Transaction" %> 
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
        </div>
        <div id="content">
          <h2>Transaction History </h2>
       <p>
      <form method="post" action="history.do">
        <table width="auto" border="1">
		 <tr>
		  <th width="auto">Date</th>
          <th width="auto">Operation</th>
          <th width="auto">Fund Name</th>
          <th width="auto">Number of Share</th>
          <th width="auto">Price</th>
          <th width="auto">Dollar Amount</th>
         </tr>
         <tr>
   		 <%
   		 Transaction[] transaction = (Transaction[])request.getAttribute("Transaction");
   		 for(int i=0;i<transaction.length;i++){
   			 out.println("<td><div align=\"center\">");
      		 out.println(transaction[i].getExecute_date());
      		 out.println("</div></td>");
      		 out.println("<td><div align=\"center\">");
      		 out.println(transaction[i].getTransaction_type());
      		 out.println("</div></td>");
      		 out.println("<td><div align=\"center\">");
      		 out.println(transaction[i].getFundName());
      		 out.println("</div></td>");
      		 out.println("<td><div align=\"center\">");
      		 out.println(transaction[i].getShares());
      		 out.println("</div></td>");
      		 out.println("<td><div align=\"center\">");
      		 out.println(transaction[i].getFundPrice());
      		 out.println("</div></td>");
      		 out.println("<td><div align=\"center\">");
      		 out.println(transaction[i].getAmount());
      		 out.println("</div></td>");
   		 }
   		 
    	  %>
    	  </tr>
    	  </table>
    	  </form>
    	  </div>
    	  
       <div id="aside">
      <h3> Welcome to CFS- Mutual Fund Management </h3>
      <p> Duis autem vel eum iriure dolor in hendrerit in vulputate velit esse molestie consequat, vel illum dolore eu feugiat nulla facilisis at vero eros et accumsan. </p>
    </div>
    <div id="footer"> Copyright � Mutual Fund Application by Team e-Motion | CMU MSIT ebusiness Task7 2013 </div>
  </div>
</div>
</body>
</html>