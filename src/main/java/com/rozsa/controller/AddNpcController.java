package com.rozsa.controller;

import com.rozsa.business.AddNpc;
import com.rozsa.model.PassiveNpcsDataHolder;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/addNpc")
public class AddNpcController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();
        PassiveNpcsDataHolder dataHolder = (PassiveNpcsDataHolder)session.getAttribute("data");

        AddNpc add = new AddNpc(dataHolder);
        add.execute();

        session.setAttribute("feedback", "NPC added!");
        session.setAttribute("isBadFeedback", false);

        resp.sendRedirect("/listAll.jsp");
    }
}
