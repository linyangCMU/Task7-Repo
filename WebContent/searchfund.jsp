<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title> Mutual Fund Management </title>
    <link rel="stylesheet" type="text/css" href="style/main.css" />
    <link rel="stylesheet" type="text/css" href="style/table.css" />
    
    <script >
        function showFund(str) {
            var xmlhttp;    
            if (str=="") {
                document.getElementById("txtHint").innerHTML="";
                return;
            }
            if (window.XMLHttpRequest) {
                // code for IE7+, Firefox, Chrome, Opera, Safari
                xmlhttp=new XMLHttpRequest();
            }
            else {
                // code for IE6, IE5
                xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
                }
            xmlhttp.onreadystatechange=function() {
                if (xmlhttp.readyState==4 && xmlhttp.status==200) {
                    document.getElementById("txtHint").innerHTML=xmlhttp.responseText;
                }
            }
            xmlhttp.open("GET","getfund.jsp?fund="+str,true);
            xmlhttp.send();
        }
    </script>
<%
	Connection con;
	ResultSet rs = null;
	String url = "jdbc:mysql://localhost:3306/task7";
	
	String query = (String) request.getParameter("fund");
	Class.forName("com.mysql.jdbc.Driver").newInstance();
	con = DriverManager.getConnection(url, "", ""); 
%>    
</head>

<body>
    <div id="container">
        
        <div id="header">
            <h1>Carnegie Financial Service - Mutual Fund Management</h1>
        </div>
        
        <div id="navigation">
            <ul>
                <li><a href="#">Home</a></li>
                <li><a href="#">About</a></li>
                <li><a href="#">Services</a></li>
                <li><a href="#">Contact us</a></li>
            </ul>
        </div>
        
        <div id="content-container">
            <div id="section-navigation">
                <ul>
                    
                </ul>
            </div>
            
            <div id="content">
                <h2>Mutual Fund Search</h2>
                
                <form action=""> 
                    <input type="text" name="fundid" onkeyup="showFund(this.value)" onchange="showFund(this.value)"/>
                </form>
                <br/>
	            <table class='tableone'>
	                <thead>
	                    <tr>
	                        <th class="th1"> ID </th>
	                        <th class="th2"> Fund Name </th>
	                        <th class="th3"> Fund Symbol </th>
	                        <th class="th4"> Date </th>
	                        <th class="th5"> Price </th>
	                    </tr>
	                </thead>
	                <%
	                
	                 
	                PreparedStatement pstmt = con.prepareStatement("select max(price_date) from task7_history");
	                rs = pstmt.executeQuery();
	                if (rs.next()){
	                    out.println("<tfoot>");
	                    out.println("   <tr>");
	                    out.println("   <td colspan=\"5\"> LAST UPDATE DATE: " + rs.getDate("max(price_date)") + "</td>");
	                    out.println("   </tr>");
	                    out.println("</tfoot>");
	                }
	                
	                %>
	                
	                <tbody>
                    <tr><td colspan="5">
                    <div class="innerb">
                    <table class="tabletwo" id="txtHint">
	                
	                </table>
	                </div>
	                </td></tr>
	                </tbody>
	            
	            </table>
            </div>    
            
            <div id="footer">
                Copyright Â© Mutual Fund Application by Team
                e-Motion | CMU MSIT ebusiness Task7 2013
            </div>
        </div>
    </div>
</body>
</html>
