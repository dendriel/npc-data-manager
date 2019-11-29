package com.rozsa.controller;

import com.rozsa.business.UpdateNpc;
import com.rozsa.model.*;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.rozsa.reader.DataGetter.getInteger;

@WebServlet("/updateNpc")
public class UpdateNpcsController extends HttpServlet
{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException
    {
        HttpSession session = req.getSession();
        PassiveNpcsDataHolder dataHolder = (PassiveNpcsDataHolder)session.getAttribute("data");
        Integer npcId = getInteger(req, "id");
        PassiveNpcData npc = dataHolder.getNpcById(npcId);

        UpdateNpc updateNpc = new UpdateNpc(req, npc);
        updateNpc.execute();

        resp.sendRedirect("listAll.jsp");
    }
}
