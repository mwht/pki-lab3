package pl.mwht;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "CounterServlet")
public class CounterServlet extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();

        try {

            Cookie[] cookies = request.getCookies();
            Cookie licznik = null;
            if (cookies != null) {
                for (int i = 0; i < cookies.length; i++) {
                    if (cookies[i].getName().equals("licznik")) {
                        licznik = cookies[i];
                        break;
                    }
                }
            }

            if (licznik == null) {
                licznik = new Cookie("licznik", "0");
            } else {
                Integer v = Integer.parseInt(licznik.getValue());
                v++;
                licznik.setValue(v + "");
            }

            licznik.setMaxAge(-1);
            response.addCookie(licznik);

            out.println(licznik.getValue());
        } finally {
            out.flush();
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
