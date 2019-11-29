package com.rozsa.controller;

import com.rozsa.business.SaveNpcs;
import com.rozsa.model.PassiveNpcsDataHolder;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/saveNpcs")
public class SaveNpcsController extends HttpServlet
{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException
    {
        HttpSession session = req.getSession();
        String dataFilePath = (String)session.getAttribute("dataFilePath");
        PassiveNpcsDataHolder dataHolder = (PassiveNpcsDataHolder)session.getAttribute("data");

        SaveNpcs save = new SaveNpcs(dataFilePath, dataHolder);
        save.execute();
    }
}
