package model.dao;


import model.DBConnection;
import model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsersDaoDdbcImpl implements UsersDao{

    private final String SQL_SELECT_ALL = "SELECT * FROM users";
    private final String SQL_SELECT_BY_FIRST_NAME = "SELECT * FROM users WHERE first_name = ?";
    private final String SQL_SELECT_BY_ID = "SELECT * FROM users WHERE id = ?";
    private final String SQL_SAVE_NEW_USER = "INSERT INTO users (first_name, second_name,password, role_id) VALUES (?,?,?,?);";

    private Connection connection;
    private RolesDao roleDao;

    public UsersDaoDdbcImpl () throws SQLException, ClassNotFoundException {
        connection = new DBConnection().getConnection();
        roleDao = new RolesDaoDbcImpl();
    }

    @Override
    public User find(Integer id) {
        try {
            PreparedStatement statement = connection.prepareStatement(SQL_SELECT_BY_ID);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                String fName = resultSet.getString("first_name");
                String secondName = resultSet.getString("second_name");
                String password = resultSet.getString("password");
                int role_id = resultSet.getInt("role_id");

                return new User(id, fName, secondName, password, roleDao.find(role_id));
            }
        } catch (SQLException e) {
            new IllegalStateException(e);
        }
        return null;
    }

    @Override
    public void save(User model) {
        try {
            PreparedStatement statement = connection.prepareStatement(SQL_SAVE_NEW_USER);
            statement.setString(1, model.getFirstName());
            statement.setString(2, model.getSecondName());
            statement.setString(3, model.getPassword());
            statement.setInt(4, model.getRole().getId());
            statement.executeUpdate();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

    }

    @Override
    public void update(User model) {

    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public List<User> findAll() {
        try {
            List<User> userList = new ArrayList<>();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL);
            while(resultSet.next()){
                Integer id = resultSet.getInt("id");
                String firstName = resultSet.getString("first_name");
                String secondName = resultSet.getString("second_name");
                String password = resultSet.getString("password");
                int role_id = resultSet.getInt("role_id");

                User user = new User(id, firstName, secondName, password, roleDao.find(role_id));
                userList.add(user);
            }
            return userList;
        } catch (SQLException e) {
            new IllegalStateException(e);
        }
        return null;
    }

    @Override
    public User findAllByFirstName(String firstName) {
        try {
            PreparedStatement statement = connection.prepareStatement(SQL_SELECT_BY_FIRST_NAME);
            statement.setString(1, firstName);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                Integer id = resultSet.getInt("id");
                String fName = resultSet.getString("first_name");
                String secondName = resultSet.getString("second_name");
                String password = resultSet.getString("password");
                int role_id = resultSet.getInt("role_id");
                return new User(id,fName, secondName, password, roleDao.find(role_id));
            }
        } catch (SQLException e) {
            new IllegalStateException(e);
        }
        return null;
    }
}
