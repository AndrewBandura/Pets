<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Register client</title>
</head>

<body>
<header1>Registration form</header1>

<form action="/registerClient" method="post">
    <p id="message">${message}</p>
    <p id="error">${error}</p>
    <style>
        #error{color:red}
        #message{color:green}
    </style>

    <table>
        <tr>
            <td>Name:</td>
            <td><input id="name" type="text" name="name"/></td>
        </tr>

        <tr>
            <td>Surname:</td>
            <td><input id="surname" type="text" name="surname"/></td>
        </tr>

        <tr>
            <td>Phone:</td>
            <td><input id="phone" type="text" name="phone"/></td>
        </tr>

        <tr>
            <td>Address:</td>
            <td><input id="address" type="text" name="address"/></td>
        </tr>

        <tr>
            <td> <input type="submit" value="Register" size=50 /></td>
        </tr>
    </table>

</form>


<table>
    <tr>
        <form  action="/clients" method="get">
            <td><input type="submit" value="Cancel" size=50/></td>
        </form>
    </tr>
</table>
</body>

</html>

