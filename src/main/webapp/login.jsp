<%--
  Created by IntelliJ IDEA.
  User: Vitor Rozsa
  Date: 29/11/2019
  Time: 07:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login to NPC Data Manager</title>
</head>
<body>
<form action="/login" method="POST">
    Username:<input type="text" name="username"><br />
    Password:<input type="password" name="password">
    <input type="submit">
</form>

</body>
</html>
