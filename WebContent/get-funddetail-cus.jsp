<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="databeans.History" %>
<%@ page import="databeans.Fund" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Mutual Fund Management</title>
<link rel="stylesheet" type="text/css" href="style/main.css">
<link rel="stylesheet" type="text/css" href="style/table.css">
<script type="text/javascript" src="scripts/jquery.js"></script>

<script>
var financeData = [];
var fundName = "No Fund";
<%
ArrayList<History> histories = (ArrayList<History>) request.getAttribute("histories");
Fund fund = (Fund) request.getAttribute("fund");
out.println("financeData = [ ");
if (histories!=null) {
    for (History history : histories) {
        out.println("["+history.getDate().getTime()+","+ history.getPrice() + "],");   
    }
}
out.println("];");
if(fund!=null)
    out.println("fundName = '" + fund.getName() + "';");
%>
</script>

<script type="text/javascript" src="scripts/draw.js"></script>

</head>

<body>
<div id="container">
    <div id="header">
        <h1> Carnegie Financial Service - Mutual Fund Management </h1>
    </div>
    <div id="navigation">
        <ul>
            <li><a href="viewPortafolio.html">Home</a></li>
            <li><a href="#">About</a></li>
            <li><a href="#">Services</a></li>
            <li><a href="#">Contact us</a></li>
        </ul>
    </div>
    <div id="content-container">
        <div id="section-navigation">
            <ul>
                <li><a href="changepwdA.html">Change Password</a></li>
		        <li><a href="viewCustomerAccount.html">View Account</a><a href="#"></a></li>
		        <li><a href="BuyFund.html">Buy Fund</a></li>
		        <li><a href="SellFund.html">sell Fund</a></li>
		        <li><a href="RequestCheck.html">Request Check</a></li>
		        <li><a href="transactionhistory.html">Transaction History</a></li>
		        <li><a href="researchFund.html">Research Fund</a></li>
		        <li><a href="Index.html">Logout</a></li>
            </ul>
        </div>
	    <div id="content">
		    
	      
	    </div>
    
        <div id="footer"> Copyright ? Mutual Fund Application by Team e-Motion | CMU MSIT ebusiness Task7 2013 </div>
    </div>

<script src="scripts/highstock.js"></script>

</div>

</body>
</html>
