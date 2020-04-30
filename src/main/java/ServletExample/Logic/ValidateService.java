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

public class ValidateService {
    private static final ValidateService validateService = new ValidateService();
    private final Map<String, Function<User, Optional>> dispatch = new HashMap<>();
    private final Store usersStorage = DbStore.getInstance();
    private ValidateService() {
        dispatch.put("add", add());
        dispatch.put("update", update());
        dispatch.put("delete", delete());
        dispatch.put("findbyid", findById());
        dispatch.put("removeAll", removeAll());
        dispatch.put("findAll", findAll());
    }

    public static ValidateService getInstance() {
        return validateService;
    }

    public Optional process(List<FileItem> items,String action) {
        return dispatch.get(action).apply(processUser(items));
    }
    public Optional process(HttpServletRequest request, String action) {
        return dispatch.get(action).apply(processUser(request));
    }
    /*
        Add user
        @return boolean
    */
    private Function<User, Optional> add() {
        return key-> Optional.of(usersStorage.add(key));
    }
    /*
        Update user
        @return boolean
     */
    private Function<User, Optional> update() {
        return key-> Optional.of(usersStorage.update(key,String.valueOf(key.getId())));
    }
    /*
        Remove user by id
        @return boolean
     */
    private Function<User, Optional> delete() {
        return key-> Optional.of(usersStorage.delete(String.valueOf(key.getId())));
    }
    /*
        Remove all users
        @return boolean
     */
    private Function<User, Optional> removeAll() {
        return user -> {
            usersStorage.removeAll();
            return Optional.of(true);
        };
    }
    /*
        Get all users
        @return List
     */
    private Function<User, Optional> findAll() {
        return user -> Optional.of(usersStorage.findlAll());
    }
    /*
        Return user by id
        @return User;
     */
    private Function<User, Optional> findById() {
        return key -> Optional.of(usersStorage.findById(String.valueOf(key.getId())));
    }
    private User processUser(List<FileItem> items)  {
        Map<String, String> params = getParams(items);
        User user = new User(params.get("name"), params.get("login"), params.get("email"), "2020-03-12");
        user.setImageName(params.get("imageName"));
        user.setPassword(params.get("password"));
        user.setRoleId(Integer.parseInt(params.get("role")));
        return user;
    }
    private Map<String,String> getParams(List<FileItem> items) {
        HashMap<String,String> params = new HashMap<>();
        params.put("imageName","default.jpg");
        try {
            for (FileItem item : items) {
                if (item.isFormField()) {
                    String secondParam = Streams.asString(item.getInputStream());
                    params.put(item.getFieldName(), secondParam);
                } else {
                    params.put("imageName", item.getName());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return params;
    }
    private User processUser(HttpServletRequest stringUser)  {
        return new User(stringUser.getParameter("name"), stringUser.getParameter("login"), stringUser.getParameter("email"), "30-03-2020");
    }

}
