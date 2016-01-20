<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Create/edit order </title>
</head>

<body>
<header1>Order form</header1>

<form action="/createEditOrder" method="post">
    <p id="message">${message}</p>
    <p id="error">${error}</p>
    <style>
        #error{color:red}
        #message{color:green}
    </style>

    <table>
        <tr>
            <td>Id:</td>
            <td><input id="orderId" type="text" name="orderId"/></td>
        </tr>

        <tr>
            <td>Client:</td>
            <td><input id="clientId" type="text" name="clientId"/></td>
        </tr>

        <tr>
            <td>Address from:</td>
            <td><input id="addressFrom" type="text" name="addressFrom"/></td>
        </tr>

        <tr>
            <td>Address to:</td>
            <td><input id="addressTo" type="text" name="addressTo"/></td>
        </tr>

        <tr>
            <td>Amount:</td>
            <td><input id="amount" type="text" name="amount"/></td>
        </tr>

        <tr>
            <td> <input type="submit" value="Ok" size=50 /></td>
        </tr>
    </table>

</form>


<table>
    <tr>
        <form  action="/orders" method="get">
            <td><input type="submit" value="Cancel" size=50/></td>
        </form>
    </tr>
</table>
</body>

</html>
