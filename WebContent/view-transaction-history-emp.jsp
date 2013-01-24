<%@page import="databeans.Transaction"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Customer template</title>
<link rel="stylesheet" type="text/css" href="style/main.css">
</head>
<body>
	<div id="container">
		<jsp:include page="template-header-navigation.jsp" />
		<div id="content-container">
			<jsp:include page="template-section-navigation-emp.jsp" />

			<div class="content">
				<h2>Transaction History</h2>

				<jsp:include page="error-list.jsp" />
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
						
<%
ArrayList<Transaction> transactions = (ArrayList<Transaction>)request.getAttribute("Transaction");
if(transactions!=null) {
    for(Transaction transaction : transactions){
%>
			            <tr>
							<td><div align="center"> <%=transaction.getExecute_date() %></div></td>
							<td><div align="center"> <%=transaction.getTransaction_type() %></div></td>
							<td><div align="center"> <%=transaction.getFundName() %></div></td>
							<td><div align="center"> <%=transaction.getShares() %></div></td>
							<td><div align="center"> <%=transaction.getFundPrice() %></div></td>
	   						<td><div align="center"> <%=transaction.getAmount() %></div></td>
			            </tr>
<%
    }
}
%>	
					</table>
				</form>
			</div>
		</div>
	</div>
</body>
</html>