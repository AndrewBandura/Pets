<%@ page import="hw8.taxi.domain.Client" %>
<%@ page import="java.util.List" %>
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
            <a href="/dashboard" color:blue> Home </a>
        </td>
    </tr>
    <tr>
        <td>
            <a href="/registerClient" color:blue> New </a>
        </td>
        <td>
            <a href="/clients" color:blue> Show all </a>
        </td>
        <td>
            <a href="/clientFilter" color:blue> Filter by amount </a>
        </td>
        <td>
            <a href="/clientsFilteredByOrders" color:blue> Filter by orders </a>
        </td>

    </tr>
</table>

<body>
<table border="1">
    <thead>
        <th>Id</th>
        <th>Name id</th>
        <th>Surname</th>
        <th>Phone</th>
        <th>Address</th>
    </thead>
    <tbody>
    <% List<Client> clientList= (List<Client>)request.getAttribute("clientList");
        for (Client client: clientList)
        {
    %>
    <tr>
        <td align="left"><%=client.getId()%></td>
        <td align="left"><%=client.getName()%></td>
        <td align="left"><%=client.getSurname()%></td>
        <td align="left"><%=client.getPhone()%></td>
        <td align="left"><%=client.getAddress()%></td>
    </tr>
    <%}%>
    </tbody>
</table>
</body>

</body>
</html>
