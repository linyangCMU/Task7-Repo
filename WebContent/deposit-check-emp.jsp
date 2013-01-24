<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Deposit Check</title>
<link rel="stylesheet" type="text/css" href="style/main.css">
</head>
<body>

<div id="container">
    <jsp:include page="template-header-navigation.jsp" />
    <div id="content-container">
        <jsp:include page="template-section-navigation-cus.jsp" />
        
        <div class="content">
            <h2> Deposit Check </h2>
            
            <jsp:include page="error-list.jsp" />
            
			<form method="post" action="depositcheck.do">
				<table>
					<tr>
						<td> UserName: </td>
						<td><input type="text" name="userName" value="${form.userName}"/></td>
					</tr>
					<tr>
						<td> Deposit Amount: </td>
						<td><input type="text" name="deposit" value=""/></td>
					</tr>
					<tr>  
						<td colspan="2" align="center">
							<input type="submit" name="button" value="Deposit Check"/>
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