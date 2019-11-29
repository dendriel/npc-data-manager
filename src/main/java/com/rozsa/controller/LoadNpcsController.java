package com.rozsa.controller;

import com.rozsa.business.LoadNpcs;
import com.rozsa.model.PassiveNpcsDataHolder;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/loadNpcs")
public class LoadNpcsController extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String dataFilePath = req.getParameter("dataFilePath");

        LoadNpcs loadNpcs = new LoadNpcs(dataFilePath);
        PassiveNpcsDataHolder data = loadNpcs.execute();

        HttpSession session = req.getSession();
        session.setAttribute("dataFilePath", dataFilePath);
        session.setAttribute("data", data);

        resp.sendRedirect("listAll.jsp");
    }
}
