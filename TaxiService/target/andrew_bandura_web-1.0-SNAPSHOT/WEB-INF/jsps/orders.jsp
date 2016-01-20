<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="hw8.taxi.domain.Order" %>
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
            <a href="/order" color:blue> New </a>
        </td>
        <td>
            <a href="/orders" color:blue> Show all </a>
        </td>
        <td>
            <a href="/orderFilter" color:blue> Filter by.. </a>
        </td>
    </tr>
</table>

<body>
<table border="1">
    <thead>
        <th>Date</th>
        <th>Order id</th>
        <th>Client</th>
        <th>Amount</th>
        <th>Address from</th>
        <th>Address to</th>
    </thead>
    <tbody>
    <% List<Order> orderList= (List<Order>)request.getAttribute("orderList");
        for (Order order: orderList)
        {
    %>
    <tr>
        <td align="left"><%=order.getDate()%></td>


        <td align="left"><%=order.getId()%></td>
        <td align="left"><%=order.getClient().getName()%></td>
        <td align="left"><%=order.getAmount()%></td>
        <td align="left"><%=order.getAddressFrom()%></td>
        <td align="left"><%=order.getAddressTo()%></td>
        <td>
                <a href="/editOrder" color:blue> edit</a>
        </td>
    </tr>
    <%}%>
    </tbody>
</table>
</body>

