<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Reset Customer Password</title>
<link rel="stylesheet" type="text/css" href="style/main.css">
</head>
<body>

<div id="container">
    <jsp:include page="template-header-navigation.jsp" />
    <div id="content-container">
        <jsp:include page="template-section-navigation-emp.jsp" />
        
        <div class="content">
            <h2> Reset Customer Password </h2>
            
            <jsp:include page="error-list.jsp" />
            
			<form method="POST" action="emp_resetPwd.do">
				<table>
					<tr>
						<td> Customer UserName: </td>
						<td><input type="text" name="userName" value=""/></td>
					</tr>
					
					<tr>
						<td colspan="2" align="center">
							<input type="submit" name="action" value="Reset Password"/>
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