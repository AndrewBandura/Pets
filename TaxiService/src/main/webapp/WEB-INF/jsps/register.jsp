<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Login</title>
</head>

<body>
<header1>Registration form</header1>

<form action="/register" method="post">
    <p id="message">${message}</p>
    <p id="error">${error}</p>
    <style>
        #error{color:red}
        #message{color:green}
    </style>

    <table>
        <tr>
            <td>Login:</td>
            <td><input id="name" type="text" name="name"/></td>
        </tr>

        <tr>
            <td>Password:</td>
            <td><input id="password" type="password" name="password"/></td>
        </tr>

        <tr>
            <td>Confirm password:</td>
            <td><input id="passwordConfirm" type="password" name="passwordConfirm"/></td>
        </tr>

        <tr>
            <td>ID:</td>
            <td><input id="personalId" type="text" name="personalId"/></td>
        </tr>

        <tr>
            <td>Role:</td>
            <td><input id="role" type="text" name="role"/></td>
        </tr>
        <tr>
        <td> <input type="submit" value="Register" size="50px" /></td>
        </tr>
    </table>

</form>


<table>
    <tr>
        <form  action="/dashboard" method="get">
            <td><input type="submit" value="Cancel" size=50px/></td>
        </form>
    </tr>
</table>
</body>

</html>
