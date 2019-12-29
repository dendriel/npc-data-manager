<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page import="com.rozsa.model.PassiveNpcData" %>
<%@ page import="com.rozsa.model.PassiveNpcsDataHolder" %>
<%@ page import="com.rozsa.model.InteractionData" %>
<%@ page import="java.util.List" %>

<html>
<head>
    <title>Edit NPC Data</title>
    <!-- TODO: move to its own css file -->
    <style>
        #numberInput {
            width:60px;
        }

        legend {
            font-size: 20px;
        }
    </style>
</head>
<body>

<jsp:include page="feedback.jsp" />

<%
    Integer npcId = Integer.parseInt(request.getParameter("id"));
    PassiveNpcsDataHolder dataHolder = (PassiveNpcsDataHolder)session.getAttribute("data");
    PassiveNpcData npc = dataHolder.getNpcById(npcId);
    session.setAttribute("npcData", npc);
    session.setAttribute("npcSpriteData", npc.getSpriteData());
    session.setAttribute("npcStatusData", npc.getStatus());

    List<InteractionData> interactionData = npc.getInteractionData();
    session.setAttribute("npcInteractions", interactionData);
%>
<form action="/listAll.jsp" method="POST" style="display:inline;">
    <input type="submit" value="Back" style="width:100px;height:100px; margin-right:10px;">
</form>
<form action="/editNpc" method="POST" style="display:inline;">
    <input type="submit" name="updateNpcDataButton" value="Update" style="width:100px;height:100px; margin-right:10px">
<fieldset>
    <legend>${npcData.getName()}</legend>

    <div>
        <fieldset style="background-color:#ffddb0; width:25%; float:left; height:140px">
            <legend>General</legend>
            ID: <input type="text" name="id" readonly value="${npcData.getId()}"><br />
            Name: <input type="text" name="name" value="${npcData.getName()}"><br />
            Behavior Id: <input type="number" name="behaviorId" value="${npcData.getBehaviorId()}"><br />
            Is Facing Right: <input type="checkbox" name="isFacingRight" ${npcData.isFacingRight() ? "checked": ""}>
        </fieldset>
        <fieldset style="background-color:lightyellow; width:25%; float:left; height:140px">
            <legend>Sprite</legend>
            Enabled: <input type="checkbox" name="spriteData_enabled" ${npcSpriteData.getEnabledAsText()}>
            <br />
            Order: <input type="number" id="numberInput" name="spriteData_order" value="${npcSpriteData.getOrder()}">
            <br />
            <span>
            Offset
            X: <input type="number" id="numberInput" name="spriteData_offset_x" value="${npcSpriteData.getOffset().getX()}">
            Y: <input type="number" id="numberInput" name="spriteData_offset_y" value="${npcSpriteData.getOffset().getY()}">
            </span>
            <span>
            Scale:
            W: <input type="number" id="numberInput" step="0.001" name="spriteData_scale_width" value="${npcSpriteData.getScale().getWidth()}">
            H: <input type="number" id="numberInput" step="0.001" name="spriteData_scale_height" value="${npcSpriteData.getScale().getHeight()}">
            </span><br />
            Image File: <input type="text" name="spriteData_imageFile" value="${npcSpriteData.getImageFile()}">
        </fieldset>
        <fieldset style="background-color:lightblue; width:25%; height:140px">
            <legend>Status</legend>
            <div style="width:32%;">
                <div  align="right">Strength: <input type="number" id="numberInput" name="status_strength" value="${npcStatusData.getStrength()}"></div>
                <div  align="right">Intelligence: <input type="number" id="numberInput" name="status_intelligence" value="${npcStatusData.getIntelligence()}"></div>
                <div  align="right">Dexterity: <input type="number" id="numberInput" name="status_dexterity" value="${npcStatusData.getDexterity()}"></div>
                <div  align="right">Accuracy: <input type="number" id="numberInput" name="status_accuracy" value="${npcStatusData.getAccuracy()}"></div>
                <div  align="right">Life: <input type="number" id="numberInput" name="status_life" value="${npcStatusData.getLife()}"></div>
                <div  align="right">Mana: <input type="number" id="numberInput" name="status_mana" value="${npcStatusData.getMana()}"></div>
            </div>
        </fieldset>
    </div>
    <fieldset style="background-color:lightgreen">
        <legend>Interactions</legend>
        Interaction Order:<input type="text" name="interactionOrder" value="${npcData.getInteractionOrderAsText()}"><br />

        <c:forEach var="inter" items="${npcInteractions}">
        <fieldset style="background-color:#bde7a5">
            <legend>${inter.getType()}(${inter.getId()})</legend>
            <input type="submit" name="removeNpcInteractionButton${inter.getId()}" value="Remove Interaction" style="height:30px; margin-bottom:10px; background-color:coral;"><br/>
            Id: <input type="number" id="numberInput" readonly name="interactions_interaction_id" value="${inter.getId()}">
            Type: <input type="text" name="interactions_interactiontype${inter.getId()}" value="${inter.getType()}"><br />
            <span style="float:left">Required Events State:</span> <textarea name="interactions_interaction_requiredeventstate${inter.getId()}" rows="4" cols="100">${inter.getRequireEventsStateAsText()}</textarea>Ex.: {"key":"event_key01", "value":false}, {"key":"event_key02", "value":true}<br />
            <span style="float:left">Update Events State:</span> <textarea name="interactions_interaction_updateeventstate${inter.getId()}" rows="4" cols="100">${inter.getUpdateEventsStateAsText()}</textarea> <br />
            Price Multiplier: <input type="number" step="0.01" id="numberInput" name="interactions_interaction_pricemultiplier${inter.getId()}" value="${inter.getPriceMultiplier()}"><br />
            <span style="float:left">Items:</span> <textarea name="interactions_interaction_items${inter.getId()}" align="TOP" rows="3" cols="50">${inter.getItemsAsText()}</textarea> <br />
            Teleport Target Id: <input type="number" step="1" id="numberInput" name="interactions_interaction_teleportTargetId${inter.getId()}" value="${inter.getTargetId()}">
            Pos - X: <input type="number" step="1" id="numberInput" name="interactions_interaction_teleportPosX${inter.getId()}" value="${inter.getToPos().getX()}">
            Y: <input type="number" step="1" id="numberInput" name="interactions_interaction_teleportPosY${inter.getId()}"  value="${inter.getToPos().getY()}"><br />
            Target Data: <input type="text" name="interactions_interaction_targetData${inter.getId()}" value="${inter.getTargetData()}">
            <br />

            <c:forEach var="msg" items="${inter.getMessages()}">
                <fieldset style="background-color:#afbf7f">
                <legend>Message (${msg.getId()})</legend>
                    <div style="float:left; width:73%">
                        <legend>Title</legend>
                        <div style="background-color:#5cb1af">
                            <div style="float:left;"><textarea name="interactions_messages_title${msg.getId()}_text" rows="4" cols="100">${msg.getTitle().getText()}</textarea></div>
                            <div>
                                Font: <input type="text" name="interactions_messages_title${msg.getId()}_fonttype" value="${msg.getTitle().getFont()}">
                                Style: <input type="number" id="numberInput" name="interactions_messages_title${msg.getId()}_fontstyle" value="${msg.getTitle().getStyle()}">
                                Size: <input type="number" id="numberInput" name="interactions_messages_title${msg.getId()}_size" value="${msg.getTitle().getSize()}">
                                Color: Text: <input type="color" name="interactions_messages_title${msg.getId()}_color" value="${msg.getTitle().getColorAsHex()}"><br />
                                Horizontal Align: <input type="number" id="numberInput" name="interactions_messages_title${msg.getId()}_horizontalAlignment" value="${msg.getTitle().getHorizontalAlignment()}">
                                Vertical Align: <input type="number" id="numberInput" name="interactions_messages_title${msg.getId()}_verticalAlignment" value="${msg.getTitle().getVerticalAlignment()}">
                            <br />
                                <span>
                                    Rect
                                    X: <input type="number" id="numberInput" name="interactions_messages_title${msg.getId()}_rect_X" value="${msg.getTitle().getRect().getX()}">
                                    Y: <input type="number" id="numberInput" name="interactions_messages_title${msg.getId()}_rect_Y" value="${msg.getTitle().getRect().getY()}">
                                    W: <input type="number" id="numberInput" name="interactions_messages_title${msg.getId()}_rect_W" value="${msg.getTitle().getRect().getWidth()}">
                                    H: <input type="number" id="numberInput" name="interactions_messages_title${msg.getId()}_rect_H" value="${msg.getTitle().getRect().getHeight()}">
                                </span>
                            </div>
                        </div>
                        <legend>Text</legend>
                        <div style="background-color:#afbf7f">
                            <div style="float:left;"><textarea name="interactions_messages_text${msg.getId()}_text" rows="4" cols="100">${msg.getText().getText()}</textarea> <br /></div>
                            <div>
                                Font: <input type="text" name="interactions_messages_text${msg.getId()}_fonttype" value="${msg.getText().getFont()}">
                                Style: <input type="number" id="numberInput" name="interactions_messages_text${msg.getId()}_fontstyle" value="${msg.getText().getStyle()}">
                                Size: <input type="number" id="numberInput" name="interactions_messages_text${msg.getId()}_size" value="${msg.getText().getSize()}">
                                Color: Text: <input type="color" name="interactions_messages_text${msg.getId()}_color" value="${msg.getText().getColorAsHex()}"><br />
                                Horizontal Align: <input type="number" id="numberInput" name="interactions_messages_text${msg.getId()}_horizontalAlignment" value="${msg.getText().getHorizontalAlignment()}">
                                Vertical Align: <input type="number" id="numberInput" name="interactions_messages_text${msg.getId()}_verticalAlignment" value="${msg.getText().getVerticalAlignment()}">
                                <br />
                                <span>
                                Rect
                                X: <input type="number" id="numberInput" name="interactions_messages_text${msg.getId()}_rect_X" value="${msg.getText().getRect().getX()}">
                                Y: <input type="number" id="numberInput" name="interactions_messages_text${msg.getId()}_rect_Y" value="${msg.getText().getRect().getY()}">
                                W: <input type="number" id="numberInput" name="interactions_messages_text${msg.getId()}_rect_W" value="${msg.getText().getRect().getWidth()}">
                                H: <input type="number" id="numberInput" name="interactions_messages_text${msg.getId()}_rect_H" value="${msg.getText().getRect().getHeight()}">
                                </span>
                            </div>
                        </div>
                    </div>

                    <input type="submit" name="removeMessageButton${msg.getId()}" value="Remove Message" style="height:30px; margin-bottom:10px; background-color:lightcoral; float:right"><br/>
                </fieldset>
            </c:forEach>
            <input type="submit" name="addMessageButton${inter.getId()}" value="Add Message" style="height:40px; margin-bottom:5px"><br/>
        </fieldset>
        </c:forEach>
        <input type="submit" name="addInteractionButton" value="Add Interaction" style="width:150px;height:50px; margin-right:10px; margin-top:10px">
    </fieldset>
</fieldset>
</form>
</body>
</html>
