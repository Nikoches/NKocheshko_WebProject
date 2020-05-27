package ServletExample.Servlets;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class ServletExample extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        res.setContentType("text/plain");
        res.setCharacterEncoding("UTF-8");
       // String name = req.getParameter("name");
        PrintWriter writer = new PrintWriter(res.getOutputStream());
        writer.println("Nice to meet you, " + "name");
        writer.flush();
    }
}
