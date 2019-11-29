package com.rozsa.controller;

import com.rozsa.business.RemoveMessage;
import com.rozsa.business.UpdateNpc;
import com.rozsa.model.PassiveNpcData;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/removeMessage")
public class RemoveMessageController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        PassiveNpcData npc = (PassiveNpcData)session.getAttribute("npcData");

        int targetInteractionMessageId = getInteractionMessageId(req, npc);
        if (targetInteractionMessageId < 0) {
            System.out.println("Failed to find target message id!");
            RequestDispatcher rd = req.getRequestDispatcher("listAll.jsp");
            rd.forward(req, resp);
            return;
        }

        UpdateNpc updateNpc = new UpdateNpc(req, npc);
        updateNpc.execute();

        RemoveMessage removeMessage = new RemoveMessage(npc, targetInteractionMessageId);
        removeMessage.execute();

        RequestDispatcher rd = req.getRequestDispatcher("edit.jsp");
        rd.forward(req, resp);
    }

    public static int getInteractionMessageId(HttpServletRequest req, PassiveNpcData npc) {
        return npc
                .getInteractionMessagesIds()
                .stream()
                .filter(i -> req.getParameter("removeMessageButton"+i) != null)
                .findFirst()
                .orElse(-1);
    }
}
