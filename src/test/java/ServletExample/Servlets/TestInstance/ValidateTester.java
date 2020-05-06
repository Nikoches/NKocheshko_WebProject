package ServletExample.Servlets.TestInstance;

import ServletExample.Logic.Validate;
import ServletExample.Model.User;
import ServletExample.Servlets.ServletExample;
import org.apache.commons.fileupload.FileItem;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

public class ValidateTester implements Validate {
    private final Map<Integer, User> store = new HashMap<>();
    private int id = 0;
    @Override
    public Optional process(List<FileItem> items, String action) {
        return dispatch.get(action).apply(processUser(items));
    }

    @Override
    public Optional process(HttpServletRequest request, String action) {
        return dispatch.get(action).apply(processUser(request));
    }

}
