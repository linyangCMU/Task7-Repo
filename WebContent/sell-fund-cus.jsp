       <%@page import="java.util.List"%>
       <%@page import="databeans.Fund"%>
       <%@page import="databeans.Position"%>
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
         <h2>Sell Fund </h2>
      <p>Type in the number of shares you want to sell. Remmember, it could not be larger than the number you have.</p>
      <hr />
          <form method="post" action="sell.do">
        <table>
		  <tr>
            <td> <div align="center">FundName:  </div></td>
			<td> <div align="center">Shares you own now
			  </div>
			<td> <div align="center">Shares you will sell</div></td>
          </tr>
          <tr>
			 <%
			 	 Fund[] fund = (Fund[]) request.getAttribute("fund");
			 	 Position[] position=(Position[]) request.getAttribute("position");
				 for(int i=0; i<fund.length; i++){
					out.println("<td>");
				 	out.println(fund[i].getName());
				 	for(int j=0; j<position.length;j++){
				 		if(fund[i].getId()==position[j].getFund_id()){
				 			out.println(":$ </td><td>");
				 			out.println(position[j].getShares());
				 			out.println("</td>");
			 	 			out.println("<td><input type=\"text\" name=\"sellfund\" value=\"0\"/></td>");
				 		}
				 	}
			 }
		      %>
          
          <tr>
            <td colspan="3" align="center"><input type="submit" name="button" value="submit"/></td>
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