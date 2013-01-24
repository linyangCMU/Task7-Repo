<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title> Mutual Fund Management </title>
    <link rel="stylesheet" type="text/css" href="style/main.css" />
    <link rel="stylesheet" type="text/css" href="style/table.css" />
    <script type="text/javascript" src="scripts/submit.js"></script>
    
    <script >
        function showCustomer(str) {
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
            xmlhttp.open("GET","getcustomers.do?query="+str,true);
            xmlhttp.send();
        }
    </script>
    
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
            
            <jsp:include page="template-section-navigation-emp.jsp" />
            
            <div id="content">
                <h2>Search for Customers</h2>
                
                <form action=""> 
				<input class="searchinput" type="text" name="customer" placeholder="Type in username OR first name OR last name to search"
				    onkeyup="showCustomer(this.value)" onchange="showCustomer(this.value)"/>
				</form>
				<br/>
				<table class="tableone">
                    <thead>
                        <tr>
                            <th class="th3"> Username </th>
                            <th class="th3"> First Name </th>
                            <th class="th3"> Last Name </th> 
                            <th class="th3"> City </th>
                            <th class="th3"> State </th>          
                        </tr>
                    </thead>
                    <tfoot>
                    <tr><td colspan="5">Click ROW to view details for each account.</td></tr>
                    </tfoot>
                    
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
            
            <jsp:include page="template-footer.jsp" />
            
        </div>
    </div>
</body>
</html>
