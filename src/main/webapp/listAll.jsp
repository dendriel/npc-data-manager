<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>All NPCs Data</title>
    <!-- TODO: move to its own css file -->
    <style>
    </style>
</head>
<body>

<form action="/saveNpcs" method="POST" style="float:left">
    <input type="submit" value="Save to File" style="width:100px;height:100px; margin-right:10px">
</form>

<form action="/addNpc">
    <input type="submit" value="Add NPC" style="width:200px;height:100px; margin-right:10px">
</form>

<c:forEach var="npc" items="${data.getData()}">
    <fieldset>
        <legend style="font-size: 24px;">${npc.getName()}</legend>
        <div style="float:left; width:300px">
            <form action="/edit.jsp">
                <input type="text" hidden="true" name="id" value="${npc.getId()}">
                <input type="submit" value="Edit" style="float:left;width:50px;height:70px; margin-right:10px">
            </form>
            ID: ${npc.getId()}<br />
            Name: ${npc.getName()}<br />
            Behavior ID: ${npc.getBehaviorId()}
        </div>
        <div style="float:right">
            <form action="/removeNpc">
                <input type="submit" value="Remove" style="width:70px;height:50px; margin-left:10px">
                <input hidden name="id" value="${npc.getId()}">
            </form>
        </div>
    </fieldset>
</c:forEach>


</body>
</html>
