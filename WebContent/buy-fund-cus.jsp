       <%@page import="java.util.List"%>
       <%@page import="databeans.Fund"%>
       <%@ page import="java.util.*" %>

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
    <jsp:include page="template-header-navigation.jsp" />
    	<div id="content-container">
        	<jsp:include page="template-section-navigation-cus.jsp" />
       			 <div class="content">
          			<h2>Buy Fund </h2>
      				<tr><p>Type in the dollar amount if you want to buy the specified fund. Remmember, it should be number.</p></tr>
    				<tr> Your Cash amount: $
      				<%=(Double) request.getAttribute("cash")%>
      				</tr>
      				<form method="post" action="buy.do">
        			<table>
					  <tr>
			 			<td>
			 <%
			 	 ArrayList<Fund> funds = (ArrayList<Fund>) request.getAttribute("funds");
			 	 if(funds != null){
			 		 for(Fund fund : funds){
			 			 out.println(fund.getName());
			 		 }
				 }
		      %>
		      			</td>
		      			<td><input type="text" name="buyfund" value="0"/></td>
	         		</tr>
           			<td colspan="2" align="center"><input type="submit" name="button" value="submit"/></td>
        
        			</table>
      				</form>
      			</div>
        <jsp:include page="template-footer.jsp" />
    </div>
</div>
</body>
</html>