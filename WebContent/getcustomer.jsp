<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<%@ page import="model.CustomerDAO" %>
<%@ page import="model.Model" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="style/table.css"/>
</head>
<body>

<%
	Connection con;
	ResultSet rs = null;
	String url = "jdbc:mysql://localhost:3306/task7";
	
	String username = (String) request.getParameter("username");
	String[] querys = username.split(" ");
	
	int max = 20;
	try {
	    max = Integer.parseInt(request.getParameter("max"));
	} catch (NumberFormatException e){
	    max = 20;
	}
	
	Class.forName("com.mysql.jdbc.Driver").newInstance();
	con = DriverManager.getConnection(url, "", ""); 
	
	PreparedStatement pstmt = con.prepareStatement("SELECT * FROM task7_custormer " + 
	        " WHERE username REGEXP ? " +
	        " OR firstname REGEXP ? " +
	        " OR lastname REGEXP ? ");
	pstmt.setString(1,username);
	pstmt.setString(2,username);
	pstmt.setString(3,username);
	
	rs = pstmt.executeQuery();
	
	
	while (rs.next()) {
	    out.println("<form method='get' id=\""+rs.getString("customer_id")+"\" action=\"login.do\">");
	    out.println("<tr value='"+rs.getString("customer_id")+"' name='id' onclick=\"document.forms['"+rs.getString("customer_id")+"'].submit();\">");
	    out.println("  <td class=\"td3\">"+rs.getString("username") + "</td>");
	    out.println("  <td class=\"td3\">"+rs.getString("firstname") + "</td>");
	    out.println("  <td class=\"td3\">"+rs.getString("lastname") + "</td>");
	    out.println("  <td class=\"td3\">"+rs.getString("username") + "</td>");
        out.println("  <td class=\"td3\">"+rs.getString("firstname") + "</td>");
	    out.println("</tr>");
	    out.println("</form>");
	}
	
	rs.close();
	
	con.close();


%>

</body>
</html>