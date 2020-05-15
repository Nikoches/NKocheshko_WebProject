package ServletExample.Servlets.TestInstance;

import ServletExample.Logic.Validate;
import ServletExample.Model.User;
import ServletExample.Servlets.ServletExample;
import org.apache.commons.fileupload.FileItem;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.function.Function;

public class ValidateTester implements Validate {
    private LinkedList<User> users = new LinkedList<>();
    Map<String, Function<User, Optional>> dispatch = new HashMap<>();
    int counter= 0;
    public ValidateTester(){
        dispatch.put("add", add());
        dispatch.put("all", all());
        dispatch.put("removeAll", removeAll());
        dispatch.put("update", update());
        dispatch.put("findbyid", findById());
    }
    @Override
    public Optional process(List<FileItem> items, String action) {
        return dispatch.get(action).apply(processUser(items));
    }

    @Override
    public Optional process(HttpServletRequest request, String action) {
        return dispatch.get(action).apply(processUser(request));
    }
    private Function<User, Optional> add() {
        return user -> {
            user.setId(counter++);
            return  Optional.of(users.add(user));
        };
    }
    private Function<User, Optional> all() {
        return user -> Optional.of(users);
    }
    private Function<User, Optional> removeAll() {
        return user -> {
           users.clear();
           return Optional.of(users);
        };
    }
    private Function<User, Optional> update() {
        return user -> {
            User user1 = users.getFirst();
            user1.setName(user.getName());
            user1.setLogin(user.getLogin());
            user1.setEmail(user.getEmail());
            return Optional.of(users);
        };
    }
    private Function<User, Optional> findById() {
        return user -> {
            User returned = null;
            for (User x:users) {
                if(user.getId()==x.getId()){
                    returned = x;
                }
            }
            return Optional.of(returned);
        };
    }
}
