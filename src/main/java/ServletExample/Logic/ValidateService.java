package ServletExample.Logic;

import ServletExample.Model.User;
import org.apache.commons.fileupload.FileItem;


import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

/*
    Service
 */
public class ValidateService implements Validate {
    private static final Validate validateService = new ValidateService();
    private final Store usersStorage = DbStore.getInstance();
    Map<String, Function<User, Optional>> dispatch = new HashMap<>();

    private ValidateService() {
        dispatch.put("add", add());
        dispatch.put("update", update());
        dispatch.put("delete", delete());
        dispatch.put("findbyid", findById());
        dispatch.put("removeAll", removeAll());
        dispatch.put("findAll", findAll());
    }

    public static Validate getInstance() {
        return validateService;
    }

    /*
    Apply action from dicpatcher with multi-part data;
     */
    public Optional process(List<FileItem> items, String action) {
        return dispatch.get(action).apply(processUser(items));
    }

    /*
    Apply action from dispatcher with simple HTTP request;
     */
    public Optional process(HttpServletRequest request, String action) {
        return dispatch.get(action).apply(processUser(request));
    }

    /*
        Add user
        @return boolean
    */
    private Function<User, Optional> add() {
        return key -> Optional.of(usersStorage.add(key));
    }

    /*
        Update user
        @return boolean
     */
    private Function<User, Optional> update() {
        return key -> Optional.of(usersStorage.update(key, String.valueOf(key.getId())));
    }

    /*
        Remove user by id
        @return boolean
     */
    private Function<User, Optional> delete() {
        return key -> Optional.of(usersStorage.delete(String.valueOf(key.getId())));
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
}
