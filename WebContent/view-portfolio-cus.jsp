<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="databeans.Portfolio" %>
<%@page import="databeans.Customer" %>
<%@page import="databeans.Transaction" %>
<%@ page import="java.util.*" %>
<%@ page import="java.sql.Date" %>
<%@ page import="java.text.DecimalFormat" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Customer Portfolio</title>
<link rel="stylesheet" type="text/css" href="style/main.css">
</head>
<body>

<div id="container">
    <jsp:include page="template-header-navigation.jsp" />
    <div id="content-container">
    
        <jsp:include page="template-section-navigation-cus.jsp" />
        
        <div class="content">
            <h2> Customer Account </h2>
            <jsp:include page="error-list.jsp" />
            
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
            
            <p>&nbsp</p>
            
            <table>
				<tr>
					<td> <b>Fund Name</b> </td>
					<td> <b>Share Amount</b> </td>
					<td> <b>Value per Share</b> </td>
					<td> <b>Total Value</b> </td>
				</tr>
<%
DecimalFormat nf = new DecimalFormat("#0.00");
nf.setMaximumFractionDigits(2);
nf.setMinimumFractionDigits(2);
	ArrayList<Portfolio> portfolios = (ArrayList<Portfolio>) request.getAttribute("portfolios");
	if(portfolios != null){
	    for(Portfolio portfolio:portfolios){
%>
                <tr>
                    <td> <%=portfolio.getFundName() %> </td>
                    <td align="right"> <%=nf.format(portfolio.getShares()) %> </td>
                    <td align="right"> $ <%=nf.format(portfolio.getPrice()) %> </td>
                    <td align="right"> $ <%=nf.format(portfolio.getTotal()) %> </td>
                    <td><a href="cus_sellFund.do?fundName=<%=portfolio.getFundName() %>">Sell</td>
                </tr>
<%
        }
	}
%>
            </table>
        </div>
        
        <jsp:include page="template-footer.jsp" />
    </div>
</div>
</body>
</html>