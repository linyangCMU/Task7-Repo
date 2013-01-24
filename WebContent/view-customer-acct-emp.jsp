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
        <jsp:include page="section-navigation-emp.jsp" />
        <div class="content">
            <h2> Customer Account </h2>
<%
    Customer customer = (Customer) session.getAttribute("customer");
    if(customer==null){
        return;
    }
%>
            <table>
                <tr>
                    <td> <b>Name</b> </td>
                    <td><%=customer.getFirstName()%> <%=customer.getLastName()%></td>
                </tr>
                <tr>
                    <td> <b>Address</b> </td>
                    <td><%=customer.getAddrL1()%></td>
                    <td><%=customer.getAddrL2()%></td>
                    <td><%=customer.getCity()%></td>
                    <td><%=customer.getState()%> </td>
                    <td><%=customer.getZip()%></td>
                </tr>
                <tr>
                    <td> <b>Last Trading Date</b> </td>
                    <td><%= (Date) request.getAttribute("lastExecuteDate") %></td>
                </tr>
                <tr>
                    <td> <b>Cash Balance</b> </td>
                    <td> <%=customer.getCash()%></td>
                </tr>
            </table>
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
%>
                <tr>
                    <td> <%=portfolio.getFundName() %> </td>
                    <td> <%=portfolio.getShares() %> </td>
                    <td> <%=portfolio.getPrice() %> </td>
                    <td> <%=portfolio.getTotal() %> </td>
                </tr>
<%
        }
    }
%>
            </table>
        </div>
        
        <div id="footer"> Copyright � Mutual Fund Application by Team e-Motion | CMU MSIT ebusiness Task7 2013 </div>
    </div>
</div>
</body>
</html>