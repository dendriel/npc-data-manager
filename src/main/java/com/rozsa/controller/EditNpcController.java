package com.rozsa.controller;

import com.rozsa.model.PassiveNpcData;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/editNpc")
public class EditNpcController extends HttpServlet
{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        if (req.getParameter("updateNpcDataButton") != null) {
            RequestDispatcher rd = req.getRequestDispatcher("/updateNpc");
            rd.forward(req, resp);
        }
        else if (req.getParameter("addInteractionButton") != null) {
            RequestDispatcher rd = req.getRequestDispatcher("/addInteraction");
            rd.forward(req, resp);
        }
        else if (isRemoveInteraction(req)) {
            RequestDispatcher rd = req.getRequestDispatcher("/removeNpcInteraction");
            rd.forward(req, resp);
        }
        else if (isAddMessage(req)) {
            RequestDispatcher rd = req.getRequestDispatcher("/addMessage");
            rd.forward(req, resp);
        }
        else {
            RequestDispatcher rd = req.getRequestDispatcher("/removeMessage");
            rd.forward(req, resp);
        }
    }

    private boolean isRemoveInteraction(HttpServletRequest req) {
        HttpSession session = req.getSession();
        PassiveNpcData npc = (PassiveNpcData)session.getAttribute("npcData");

        return RemoveInteractionController.getInteractionId(req, npc) != -1;
    }

    private boolean isAddMessage(HttpServletRequest req) {
        HttpSession session = req.getSession();
        PassiveNpcData npc = (PassiveNpcData)session.getAttribute("npcData");

        return AddMessageController.getInteractionId(req, npc) != -1;
    }
}
