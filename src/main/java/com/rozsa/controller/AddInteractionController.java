package com.rozsa.controller;

import com.rozsa.business.AddInteraction;
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

@WebServlet("/addInteraction")
public class AddInteractionController extends HttpServlet
{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        HttpSession session = req.getSession();
        PassiveNpcsDataHolder dataHolder = (PassiveNpcsDataHolder)session.getAttribute("data");
        Integer npcId = getInteger(req, "id");
        PassiveNpcData npc = dataHolder.getNpcById(npcId);

        // update so we don't lose any unsaved changes.
        UpdateNpc updateNpc = new UpdateNpc(req, npc);
        updateNpc.execute();

        AddInteraction addInteraction = new AddInteraction(npc);
        addInteraction.execute();

        session.setAttribute("feedback", "New interaction added!");
        session.setAttribute("isBadFeedback", false);

        RequestDispatcher rd = req.getRequestDispatcher("edit.jsp");
        rd.forward(req, resp);
    }
}
