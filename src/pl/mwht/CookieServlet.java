package pl.mwht;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "CookieServlet")
public class CookieServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession(true);

        response.setContentType("text/html; charset=utf-8");

        Boolean loggedIn = (Boolean) session.getAttribute("zalogowany");
        try {
            if (loggedIn == null || !loggedIn) { // not logged in
                loggedIn = false;
                out.println("<form method=\"GET\">\n" +
                        "<input type=\"text\" name=\"user\" />\n" +
                        "<input type=\"password\" name=\"pass\" />\n" +
                        "<input type=\"submit\" value=\"zaloguj\" />\n" +
                        "</form>\n");

                String user, pass;
                user = request.getParameter("user");
                pass = request.getParameter("pass");

                if(user != null && pass != null) {
                    if(user.equals("admin") && pass.equals("admin1")) {
                        loggedIn = true;
                        session.setAttribute("zalogowany", loggedIn);
                    }
                }
            } else { // logged in
                // TODO: logged in
            }
        } finally {
            out.close();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
}
