<%@ page import="java.util.List" %>
<%@ page import="example.SessionAttributesChangedListener" %>
<%@ page import="example.SessionCreatedListener" %>
<%@ page import="java.util.LinkedList" %>
<%@ page import="example.Message" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<style><%@include file="/WEB-INF/jsp/style2.css" %></style>
<head>
    <title>Chat</title>
    <link rel="stylesheet" href="style2.css">
</head>
<body>
<div class="container">
    <h1>Сообщения</h1>
        <div>
            <ul class="list-group">
            <% for (String s : (List<String>) request.getAttribute("messageHistory")) { %>
                    <li class="group"> <%= s %></li>
            <% } %>
            </ul>
        </div>

        <form class="form__input"  action="" method="post" >
            <input class="input__text" type="text" placeholder="введите сообщение" name="message">
            <button class="button__text" type="submit">отправить сообщение</button>
        </form>
</div>
</body>
</html>

