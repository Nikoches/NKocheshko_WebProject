package ServletExample.Servlets;

import ServletExample.Logic.ValidateService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RemoveUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ValidateService validateService = ValidateService.getInstance();
       validateService.process(req,req.getParameter("key"));
        resp.sendRedirect("all");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ValidateService validateService = ValidateService.getInstance();
        validateService.process(req,req.getParameter("key"));
        resp.sendRedirect("all");
    }
}
