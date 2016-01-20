<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Filter</title>
</head>

<body>
<header1>Filter</header1>

<form action="/ordersFiltered" method="post">
    <p id="message">${message}</p>
    <p id="error">${error}</p>
    <style>
        #error{color:red}
        #message{color:green}
    </style>

    <table>
        <tr>
            <td>Amount from:</td>
            <td><input id="amountFrom" type="text" name="amountFrom"/></td>

            <td> to:</td>
            <td><input id="amountTo" type="text" name="amountTo"/></td>
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
