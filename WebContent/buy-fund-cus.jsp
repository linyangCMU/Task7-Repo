       <%@page import="java.util.List"%>
       <%@page import="databeans.Fund"%>

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
	<div class="content">
          <h2>Buy Fund </h2>
      <p>Type in the dollar amount if you want to buy the specified fund. Remmember, it should be number.</p>
      Your Cash amount: $
      <%
      double cash = (double) request.getAttribute("cash");
      out.println(cash);%>
      <form method="post" action="buy.do">
        <table>
			 <tr>
			 <%
			 	 Fund[] fund = (Fund[]) request.getAttribute("Fund");
				 for(int i=0; i<fund.length; i++){
				 out.println(fund[i].getName());
			 	 out.println("<td><input type=\"text\" name=\"buyfund\" value=\"0\"/></td>");
			 }
		      %>
	         </tr>
            <td colspan="2" align="center"><input type="submit" name="button" value="submit"/></td>
          </tr>
        </table>
      </form>
      </div>
       <div id="aside">
      <h3> Welcome to CFS- Mutual Fund Management </h3>
      <p> Duis autem vel eum iriure dolor in hendrerit in vulputate velit esse molestie consequat, vel illum dolore eu feugiat nulla facilisis at vero eros et accumsan. </p>
    </div>
    <div id="footer"> Copyright © Mutual Fund Application by Team e-Motion | CMU MSIT ebusiness Task7 2013 </div>
  </div>
</div>
</body>
</html>