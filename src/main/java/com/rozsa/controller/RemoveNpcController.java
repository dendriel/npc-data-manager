package com.rozsa.controller;

import com.rozsa.business.AddNpc;
import com.rozsa.business.RemoveNpc;
import com.rozsa.model.PassiveNpcsDataHolder;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

import static com.rozsa.reader.DataGetter.getInteger;

@WebServlet("/removeNpc")
public class RemoveNpcController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();
        PassiveNpcsDataHolder dataHolder = (PassiveNpcsDataHolder)session.getAttribute("data");

        int id = getInteger(req, "id");
        RemoveNpc remove = new RemoveNpc(dataHolder, id);
        remove.execute();

        resp.sendRedirect("/listAll.jsp");
    }
}
