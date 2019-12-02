package com.rozsa.controller;

import com.rozsa.business.Login;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class LoginController extends HttpServlet {
    /** This login procedure doesn't look good to me (not that I know something about security), but is not
     * my intention to learn a great deal about security with this project =].
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        Login login = new Login(
                username,
                password,
                this::onLoginSuccess,
                this::onLoginFailure,
                new LoginData(req, resp));
        login.execute();
    }

    private void onLoginSuccess(Object state) {
        LoginData loginData = (LoginData)state;
        HttpServletRequest req = loginData.getRequest();
        HttpSession session = req.getSession();
        session.setAttribute("authenticated", true);

        HttpServletResponse resp = loginData.getResponse();
        try {
            resp.sendRedirect("index.jsp");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void onLoginFailure(Object state) {
        LoginData loginData = (LoginData)state;
        HttpServletRequest req = loginData.getRequest();
        HttpSession session = req.getSession();
        session.setAttribute("authenticated", false);

        session.setAttribute("feedback", "Invalid username or password!");
        session.setAttribute("isBadFeedback", true);

        HttpServletResponse resp = loginData.getResponse();
        try {
            resp.sendRedirect("login.jsp");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private class LoginData {
        private final HttpServletRequest request;

        private final HttpServletResponse response;

        public LoginData(HttpServletRequest request, HttpServletResponse response) {
            this.request = request;
            this.response = response;
        }

        public HttpServletRequest getRequest() {
            return request;
        }

        public HttpServletResponse getResponse() {
            return response;
        }
    }
}
