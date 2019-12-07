<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    String feedback = (String)session.getAttribute("feedback");
    if (feedback == null || feedback.isEmpty()) {
        return;
    }

    Boolean isBadFeedback = (Boolean)session.getAttribute("isBadFeedback");
    String color = isBadFeedback ? "lightcoral" : "lightgreen";

    session.setAttribute("feedback", "");
    out.print("<div style=\"background-color:" + color + "; padding:20px; font-size:24px;border-style: groove\">" +
            feedback +
            "</div>"
            );
%>