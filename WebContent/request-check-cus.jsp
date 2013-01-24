<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Employee Login</title>
<link rel="stylesheet" type="text/css" href="style/main.css">
</head>
<body>

<div id="container">
    <jsp:include page="template-header-navigation.jsp" />
    <div id="content-container">
        <jsp:include page="template-section-navigation-cus.jsp" />
        
        <div class="content">
            <h2> Request Check</h2>
            
            <jsp:include page="error-list.jsp" />
            
            <form method="post" action="requestcheck.do">
                <table>
                    <tr>
                        <td> Current Balance: $</td>
                        <td>    
                        <%
	                        Double cash = (Double) request.getAttribute("cash");
	                        if (cash!=null)
	                            out.println(cash);
                        %>
                        </td>
                    </tr>
                    <tr>
                        <td> Withdraw Amount: </td>
                        <td><input type="text" name="withdraw" value=""/></td>
                    </tr>
                    <tr>
                        <td colspan="2" align="center"><input type="submit" name="button" value="Submit"/></td>
                    </tr>
                </table>
            </form>
        </div>
        <jsp:include page="template-footer.jsp" />
    </div>
</div>
</body>
</html>