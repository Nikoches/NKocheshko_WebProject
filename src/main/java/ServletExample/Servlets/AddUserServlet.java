package ServletExample.Servlets;

import ServletExample.Logic.Validate;
import ServletExample.Logic.ValidateService;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class AddUserServlet extends HttpServlet {
    //TODO Добавить readme
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        resp.setContentType("text/html");
        resp.sendRedirect("Views/AddUser.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        Validate validateService = ValidateService.getInstance();
        if (req.getContentType() != null && req.getContentType().equals("multipart/form-data")) {
            DiskFileItemFactory factory = new DiskFileItemFactory();
            ServletContext servletContext = getServletContext();
            File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
            factory.setRepository(repository);
            ServletFileUpload upload = new ServletFileUpload(factory);
            try {
                List<FileItem> items = upload.parseRequest(req);
                File folder = new File("images");
                if (!folder.exists()) {
                    folder.mkdir();
                }
                for (FileItem item : items) {
                    if (!item.isFormField()) {
                        InputStream picName = item.getInputStream();
                        if (picName.available() > 0) {
                            File file = new File(folder + File.separator + item.getName());
                            try (FileOutputStream out = new FileOutputStream(file)) {
                                out.write(picName.readAllBytes());
                            } catch (IOException error) {
                                error.printStackTrace();
                            }
                        }
                    }
                }
                validateService.process(items, "add");
            } catch (FileUploadException e) {
                e.printStackTrace();
            }
        } else {
            validateService.process(req, "add");
        }
        resp.sendRedirect("all");
    }
}
