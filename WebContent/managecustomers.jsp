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
            xmlhttp.open("GET","getcustomer.jsp?username="+str,true);
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
            <div id="section-navigation">
                <ul>
                    <li><a href="#">Login</a></li>
                    <li><a href="#">Change Password</a></li>
                    <li><a href="#">Create Employee Account</a></li>
                    <li><a href="#">Create Customer Account</a></li>
                    <li><a href="#">Reset Customer Password</a></li>
                    <li><a href="#">View Customer Account</a></li>
                    <li><a href="#">View Customer Transaction History</a></li>
                    <li><a href="#">Deposit Check</a></li>
                    <li><a href="#">Create Fund</a></li>
                    <li><a href="#">Transition Day</a></li>
                    <li><a href="#">Logout</a></li>
                </ul>
            </div>
            
            <div id="content">
                <h2>Search for Customers</h2>
                
                <form action=""> 
				<input type="text" name="customer" onkeyup="showCustomer(this.value)" onchange="showCustomer(this.value)"/>
				</form>
				<br/>
				<table class="tableone">
                    <thead>
                        <tr>
                            <th class="th3"> Username </th>
                            <th class="th3"> First Name </th>
                            <th class="th3"> Last Name </th> 
                            <th class="th3"> First Name </th>
                            <th class="th3"> Last Name </th>          
                        </tr>
                    </thead>
                    
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
