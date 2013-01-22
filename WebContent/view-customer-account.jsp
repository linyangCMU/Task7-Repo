<%@page import="java.util.List"%>
<jsp:include page="template-top.jsp" />
<jsp:include page="error-list.jsp" />
<jsp:include page="template-left.jsp" />


<html>
    <head>
        <title>View Customer Account</title>
    </head>
    
    <body>
        
<%
		List<String> errors =(List<String>) request.getAttribute("errors");
		if (errors != null) {
			for (String error : errors) {
%>		
				<h4 style="color:red"> <%= error %> </h4>
<%
			} 
		}
%>	
	<h3>Customer Account</h3>
		<table> 
			<tr>
				<td>Name</td>
				<td>${customer.lastName}  ${customer.firstName}</td>
			</tr>
			<tr>
				<td>Address</td>
				<td>${customer.addr_line1}  ${customer.addr_line2}</td>
			</tr>
			<tr>
				<td>Last Trading Date</td>
				<td>${transaction.date}</td>
			</tr>
			<tr>
				<td>Current Balance</td>
				<td>${customer.cash}</td>
			</tr>
		</table>
		<table width="auto" border="1">
			<tr>
				<td>Fund Name</td>
				<td>Share Amount</td>
				<td>Value per Share</td>
				<td>Total Value</td>
			</tr>
			
<% 			ArrayList<FundInfo> fundInfos =(ArrayList<FundInfo>) request.getAttribute("fundInfos");
			for(FundInfo fundInfo: fundInfos)
%>
           	<tr>
        		<td>${fundInfo.name}</td>
        		<td>${fundInfo.shares}</td>
        		<td>${fundInfo.price}</td>
        		<td>${fundInfo.value}</td>
   			</tr>
		</table>
    </body>
</html>
<jsp:include page="template-bottom.jsp" />