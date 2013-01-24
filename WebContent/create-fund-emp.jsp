<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create Fund</title>
<link rel="stylesheet" type="text/css" href="style/main.css">
</head>
<body>

<div id="container">
    <jsp:include page="template-header-navigation.jsp" />
    <div id="content-container">
        <jsp:include page="template-section-navigation-cus.jsp" />
        
        <div class="content">
            <h2> Create Fund </h2>
            
            <jsp:include page="error-list.jsp" />

			<form method="post" action="create-fund.do">
				<table>
					<tr>
						<td> Fund Name: </td>
						<td><input type="text" name="userName" value="${fund.name}"/></td>
					</tr>
					<tr>
						<td> Ticker: </td>
						<td><input type="text" name="deposit" value="${fund.symbol})"/></td>
					</tr>
					<tr>  
						<td colspan="2" align="center">
							<input type="submit" name="button" value="Create Fund"/>
						</td>
					</tr>
				</table>
			</form>
        </div>
        <jsp:include page="template-footer.jsp" />
    </div>
</div>
</body>
</html>