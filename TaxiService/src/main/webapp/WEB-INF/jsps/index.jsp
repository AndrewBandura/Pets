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
<header1>Authorization form</header1>

<form:form id="index" method="post" action="index" modelAttribute="user">
    <p id="message">${message}</p>
    <p id="error">${error}</p>
    <style>
        #message{color:green}
        #error{color:red}
    </style>

   <table>
        <tr>
            <th><small>
                <form:label path="name">login</form:label>
            </small>
            <th><small>
                <form:input id="name" name="name" path="name" /><br>
            </small>
        </tr>
        <tr>
            <th><small>
                <form:label path="name">password</form:label>
            </small>
            <th><small>
                <form:password id="password" name="password" path="password" /><br>
            </small>
        </tr>

       <tr>

           <th>
               <form id="index" method="post" action="index" modelAttribute="user">
                 <input type="submit" value="Submit" size=40 />
               </form>

           <th>
           <form:form id="index" method="get" action="register" modelAttribute="user">
               <input type="submit" value="Register" size=40 />
           </form:form>
       </th>
       </tr>

   </table>

</form:form>


<table>

<%--<form:form id="index" method="get" action="register" modelAttribute="user">--%>
    <%--<input type="submit" value="Register" size=40 />--%>
<%--</form:form>--%>
    <%--</tr>--%>
</table>
</body>

</html>

