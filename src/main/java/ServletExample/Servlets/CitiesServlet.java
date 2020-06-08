package ServletExample.Servlets;

import ServletExample.Logic.Validate;
import ServletExample.Logic.ValidateService;

import javax.json.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/city")
public class CitiesServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Validate validateService = ValidateService.getInstance();
        List<String> ss = (ArrayList<String>) validateService.process(req, "city").get();
        resp.setContentType("text/plain");
        resp.setCharacterEncoding("UTF-8");
        resp.setHeader("Access-Control-Allow-Origin", "*");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        JsonArrayBuilder json = Json.createArrayBuilder(ss);
        JsonArray sss = json.build();
        writer.write(sss.toString());
        writer.flush();
    }
}
