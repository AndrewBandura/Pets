<%--
  Created by IntelliJ IDEA.
  User: Andrew
  Date: 15.10.2015
  Time: 0:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Welcome</title>
</head>
<body>
<p id="message">${message}</p>
<p id="error">${error}</p>
<style>
    #message{color:green}
    #error{color:red}
</style>
<table>
    <tr>
        <td>
            <a href="/index"> Login </a>
        </td>
    </tr>
    <tr>
        <td>
            <a href="/drvOrders"> Orders </a>
        </td>
    </tr>
</table>

</body>
</html>

