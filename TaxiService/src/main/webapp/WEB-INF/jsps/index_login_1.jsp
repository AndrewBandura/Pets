<%@ page import="java.util.Date" %>
<%--
  Created by IntelliJ IDEA.
  User: al1
  Date: 24.06.15
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title></title>

</head>
<body>

<h1 onclick="fun(1,2,3)">Hello User from prof26</h1>
<button id="button">Black border</button>
<p>Para</p>
<a href="http://www.ya.ru">Link</a><br/>
<a href="http://www.yak.ru">Link</a><br/>
<%--<img src="duke.running.gif" width=""/>--%>
<q id="cit">Citata</q>

<ul class="ob">
    <li>Item 1</li>
    <li>Item 2</li>
    <li>Item 3</li>
</ul>

<ol class="ob">
    <li>Item 1</li>
    <li>Item 2</li>
    <li>Item 3</li>
</ol>

<table border="2">
    <thead>
    <tr>
        <th width="100%">Загол 1</th>
        <th>Загол 1sdfsdfsdf</th>
        <th>Загол 1</th>
    </tr>
    </thead>
    <tbody>
    <tr>
        <td colspan="3">1111111111111111111111111111111111</td>
        <%--<td rowspan="2">3</td>--%>
    </tr>
    </tbody>
    <tfoot>
    <tr>
        <td>3</td>
        <td>1</td>
        <td>1</td>
    </tr>
    </tfoot>
</table>

<table border="2">
    <thead>
    <tr>
        <th width="100%">Загол 1</th>
        <th>Загол 1sdfsdfsdf</th>
        <th>Загол 1</th>
    </tr>
    </thead>
</table>
</body>

<form action = "/server">
    <fieldset>
        <legend id="send">Регистрация</legend>
        Ваш логин:<input id="login" type="text" name="login" value=""/><br/>
        <%--<input type="submit" value="Send"/>--%>
        <button onclick="send()">Send</button>
    </fieldset>
</form>
</body>
</html>
