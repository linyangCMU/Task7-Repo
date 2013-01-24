<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

      
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
              

						<li><span class="menu-item"><a href="cus_changePwd.do">Change password</a></span></li>
						<li><span class="menu-item"><a href="buyFund.do">Buy Fund</a></span></li>
						<li><span class="menu-item"><a href="sellFund.do">Sell Fund</a></span></li>
						<li><span class="menu-item"><a href="RequestCheck.do">Request Check</a></span></li>
						<li><span class="menu-item"><a href="transactionhistory.do">Transaction History</a></span></li>
						<li><span class="menu-item"><a href="viewPortafolio.do">View Account</a></span></li>
						<li><span class="menu-item"><a href="researchfund.do">Research Fund</a></span></li>
						<li><span class="menu-item"><a href="logout.do">Logout</a></span></li>
			
		</ul>
    </div>
         <h2> Change Password </h2>
          <p>
      <form method="post" action="cus_changePwd.do">
        <table>
        
          <tr>
            <td> New Password: </td>
            <td><input type="password" name="newPassword" value=""/></td>
          </tr>
          <tr>
            <td> Confirm New Password: </td>
            <td><input type="password" name="confirmPassword" value=""/></td>
          </tr>
          <tr>
            <td colspan="2" align="center"><input type="submit" name="button" value="Submit"/></td>
          </tr>
        </table>
      </form>
      </p>
       <div id="aside">
      <h3> Welcome to CFS- Mutual Fund Management </h3>
      <p> Duis autem vel eum iriure dolor in hendrerit in vulputate velit esse molestie consequat, vel illum dolore eu feugiat nulla facilisis at vero eros et accumsan. </p>
    </div>
    <div id="footer"> Copyright © Mutual Fund Application by Team e-Motion | CMU MSIT ebusiness Task7 2013 </div>
  </div>
</div>
</body>
</html>