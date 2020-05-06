package ServletExample.Servlets.TestInstance;

import ServletExample.Logic.Store;
import ServletExample.Model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public class DbStoreTest implements Store {
    @Override
    public boolean add(User user) {
        return false;
    }

    @Override
    public boolean update(User user, String id) {
        return false;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }

    @Override
    public List<User> findlAll() {
        return null;
    }

    @Override
    public User findById(String id) {
        return null;
    }

    @Override
    public void removeAll() {

    }

    @Override
    public int getCounterId() {
        return 0;
    }
    public int getCredentials(String login, String pwd) {
        if(!login.equals("admin") || !pwd.equals("0000")){
            return 0;
        }
        return 1;
    }
}
