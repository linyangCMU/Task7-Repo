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
  <div id="header">
    <h1> Carnegie Financial Service - Mutual Fund Management </h1>
  </div>
  <div id="navigation">
    <ul>
      <li><a href="viewPortafolio.do">Home</a></li>
      <li><a href="#">About</a></li>
      <li><a href="#">Services</a></li>
      <li><a href="#">Contact us</a></li>
    </ul>
  </div>
  <div id="content-container">
    <div id="content">
      <h2> Customer Login </h2>
      <form method="post" action="customer-login.do">
        <table>
          <tr>
            <td> Username: </td>
            <td><input type="text" name="userName" value=""/></td>
          </tr>
          <tr>
            <td> Password: </td>
            <td><input type="password" name="password" value=""/></td>
          </tr>
          <tr>
            <td colspan="2" align="center"><input type="submit" name="button" value="Login"/></td>
          </tr>
        </table>
      </form>
      </div>
       <div id="aside">
       
       
      <h3> Welcome to CFS- Mutual Fund Management </h3>
    </div>
</div>
</body>
</html>