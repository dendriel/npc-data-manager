package com.rozsa.controller;

import com.rozsa.business.AddMessage;
import com.rozsa.business.UpdateNpc;
import com.rozsa.model.PassiveNpcData;
import com.rozsa.model.PassiveNpcsDataHolder;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

import static com.rozsa.reader.DataGetter.getInteger;

@WebServlet("/addMessage")
public class AddMessageController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        PassiveNpcsDataHolder dataHolder = (PassiveNpcsDataHolder)session.getAttribute("data");
        Integer npcId = getInteger(req, "id");
        PassiveNpcData npc = dataHolder.getNpcById(npcId);

        UpdateNpc updateNpc = new UpdateNpc(req, npc);
        updateNpc.execute();

        int interactionId = getInteractionId(req, npc);
        AddMessage addMessage = new AddMessage(npc, interactionId);
        addMessage.execute();

        session.setAttribute("feedback", "New message added!");
        session.setAttribute("isBadFeedback", false);

        RequestDispatcher rd = req.getRequestDispatcher("edit.jsp");
        rd.forward(req, resp);
    }

    public static int getInteractionId(HttpServletRequest req, PassiveNpcData npc) {
        return npc
                .getInteractionOrder()
                .stream()
                .filter(i -> req.getParameter("addMessageButton"+i) != null)
                .findFirst()
                .orElse(-1);
    }
}
