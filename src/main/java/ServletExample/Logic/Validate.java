package ServletExample.Logic;

import ServletExample.Model.User;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.util.Streams;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

public interface Validate {
//    default User processUser(HttpServletRequest stringUser)  {
//        User user = new User(stringUser.getParameter("name"), stringUser.getParameter("login"), stringUser.getParameter("email"), "30-03-2020");
//        if(stringUser.getParameter("id") != null) {
//            user.setId(Integer.parseInt(stringUser.getParameter("id")));
//        }
//        return user;
//    }
      //User processUser(List<FileItem> items) ;
//        HashMap<String,String> params = new HashMap<>();
//        params.put("imageName","default.jpg");
//        try {
//            for (FileItem item : items) {
//                if (item.isFormField()) {
//                    String secondParam = Streams.asString(item.getInputStream());
//                    params.put(item.getFieldName(), secondParam);
//                } else {
//                    params.put("imageName", item.getName());
//                }
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        User user = new User(params.get("name"), params.get("login"), params.get("email"), "2020-03-12");
//        user.setImageName(params.get("imageName"));
//        user.setPassword(params.get("password"));
//        user.setRoleId(Integer.parseInt(params.get("role")));
//        return user;

     Optional process(HttpServletRequest request, String action);
     Optional process(List<FileItem> items,String action);
}
