package com.rozsa.controller;

import com.rozsa.business.RemoveInteraction;
import com.rozsa.model.PassiveNpcData;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/removeNpcInteraction")
public class RemoveInteractionController extends HttpServlet
{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        HttpSession session = req.getSession();
        PassiveNpcData npc = (PassiveNpcData)session.getAttribute("npcData");

        int targetInteractionId = getInteractionId(req, npc);

        RemoveInteraction removeInteraction = new RemoveInteraction(npc, targetInteractionId);
        removeInteraction.execute();

        RequestDispatcher rd = req.getRequestDispatcher("edit.jsp");
        rd.forward(req, resp);
    }

    public static int getInteractionId(HttpServletRequest req, PassiveNpcData npc) {
        return npc
                .getInteractionOrder()
                .stream()
                .filter(i -> req.getParameter("removeNpcInteractionButton"+i) != null)
                .findFirst()
                .orElse(-1);
    }
}
