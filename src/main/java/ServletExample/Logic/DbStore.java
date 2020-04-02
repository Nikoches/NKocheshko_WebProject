package ServletExample.Logic;

import ServletExample.Model.User;
import org.apache.commons.dbcp2.BasicDataSource;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicInteger;

public class DbStore implements Store {
    private static final BasicDataSource SOURCE = new BasicDataSource();
    private static final DbStore INSTANCE = new DbStore();
    private volatile AtomicInteger counterId = new AtomicInteger(0);
    private Connection connection;

    public DbStore() {
        setConnection();
    }

    public static DbStore getInstance() {
        return INSTANCE;
    }

    private void setConnection() {
        try (InputStream in = DbStore.class.getClassLoader().getResourceAsStream("app.properties")) {
            Properties config = new Properties();
            config.load(in);
            SOURCE.setDriverClassName(config.getProperty("connection.driver_class"));
            SOURCE.setUrl(config.getProperty("connection.url"));
            SOURCE.setUsername(config.getProperty("connection.username"));
            SOURCE.setPassword(config.getProperty("connection.password"));
            connection = SOURCE.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean add(User user) {
        user.setId(getCounterId());
        String sqlc = ("insert into users (id, name, login, email, creation_date) values ('%s', '%s', '%s', '%s', '%s');");
        try (PreparedStatement preparedStatement = connection.prepareStatement(String.format(sqlc, user.getId(), user.getName(), user.getLogin(), user.getEmail(), user.getCreateDate()))) {
            preparedStatement.execute();
        } catch (Exception x) {
            x.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean update(User user, String id) {
        if (!existUser(id)) {
            return false;
        }
        String sqlc = "update users  SET login = '%s',email='%s',name='%s' where id=%s";
        try (PreparedStatement preparedStatement = connection.prepareStatement(String.format(sqlc, user.getLogin(),user.getEmail(),user.getName(),id))) {
            preparedStatement.execute();
        } catch (Exception x) {
            x.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean delete(String id) {
        if (!existUser(id)){
            return false;
        }
        String sqlc = "delete from  users  where id=%s;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(String.format(sqlc,id))) {
            preparedStatement.execute();
        } catch (Exception x) {
            x.printStackTrace();
        }

        return false;
    }

    @Override
    public List<User> findlAll() {
        List<User> userList = new ArrayList<>();
        String sqlc = "select * from users order by id;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlc);ResultSet resultSet = preparedStatement.executeQuery()) {
            while(resultSet.next()) {
                User user = new User(resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getString(5));
                user.setId(resultSet.getInt(1));
                userList.add(user);
            }
        } catch (Exception x) {
            x.printStackTrace();
        }

        return userList;
    }

    @Override
    public User findById(String id) {
        User user = null;
        if (!existUser(id)) {
            return user;
        }
        try (PreparedStatement preparedStatement = connection.prepareStatement(String.format(" select * from users where id=%s;", id)); ResultSet resultSet = preparedStatement.executeQuery()) {
            resultSet.next();
            user = new User(resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5));
            user.setId(resultSet.getInt(1));
        } catch (Exception x) {
            x.printStackTrace();
        }
        return user;
    }

    @Override
    public void removeAll() {
        try (PreparedStatement preparedStatement = connection.prepareStatement("TRUNCATE users;")) {
            preparedStatement.execute();
        } catch (Exception x) {
            x.printStackTrace();
        }
    }

    @Override
    public int getCounterId() {
        return counterId.getAndIncrement();
    }

    private boolean existUser(String id) {
        String request = "select * from users where id=%s";
        try (PreparedStatement preparedStatement = connection.prepareStatement(String.format(request, id)); ResultSet resultSet = preparedStatement.executeQuery()) {
            if (!resultSet.next()) {
                return false;
            }
        } catch (Exception x) {
            x.printStackTrace();
        }
        return true;
    }
}
