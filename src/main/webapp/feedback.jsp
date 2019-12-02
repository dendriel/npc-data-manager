<%@ page import="java.io.PrintWriter" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    String feedback = (String)session.getAttribute("feedback");
    if (feedback == null || feedback.isEmpty()) {
        return;
    }

    try (PrintWriter outr = response.getWriter()) {
        outr.print("<div style=\"background-color: lightgreen; padding:20px; font-size:24px\">" +
                feedback +
                "</div>"
                );
    }
%>
