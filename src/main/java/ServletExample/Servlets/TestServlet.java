package ServletExample.Servlets;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TestServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("Views/Test.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            ServletFileUpload upload = new ServletFileUpload();
           // resp.setContentType("text/plain");
            FileItemIterator iterator = upload.getItemIterator(req);
            while (iterator.hasNext()) {
                FileItemStream item = iterator.next();
                if (item.isFormField()) {
                    System.out.println("Got a form field: " + item.getFieldName()  + " value=== " + Streams.asString(item.openStream()));
                } else{
                    System.out.println("Got an uploaded file: " + item.getFieldName() +
                            ", name = " + item.getName());
                }
            }
        } catch (FileUploadException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        req.getRequestDispatcher("Views/Test2.jsp").forward(req, resp);
    }
}
