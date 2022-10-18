<%@ page import="example.SessionCreatedListener" %>
<%@ page import="java.util.LinkedList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<style><%@include file="/WEB-INF/jsp/style.css" %></style>
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
<div class="container" id="container">
    <div class="form-container sign-in-container">
        <form action="homepage/chat/room" method="get">
        <h1>Присоединиться к чату</h1>
            <input type="text" name="id" placeholder="введите ID чата" />
            <button>вход</button>
        </form>
    </div>

    <div class="overlay-container">
        <% String id = (String) request.getServletContext().getAttribute("chatId"); %>
        <form action="" method="post">
            <div class="overlay">
                <div class="overlay-panel">
                    <h1>Сгенерировать ID</h1>
                    <button class="ghost" id="signUp" type="submit">сгенерировать</button>
                    <% if (id != null) { %>
                    <%= id %>
                    <% } %>
                </div>
            </div>
        </form>
    </div>
</div>


</body>
</html>