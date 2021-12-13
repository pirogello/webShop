package model.dao;

import model.DBConnection;
import model.Role;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RolesDaoDbcImpl implements RolesDao{

    private final String SQL_SELECT_BY_ROLE = "SELECT * FROM roles WHERE role = ?";
    private final String SQL_SELECT_BY_ID = "SELECT * FROM roles  WHERE id = ?";

    private Connection connection;

    public RolesDaoDbcImpl () throws SQLException, ClassNotFoundException {
        connection = new DBConnection().getConnection();
    }

    @Override
    public Role find(Integer id) {
        try {
            Role returnROle = new Role();
            PreparedStatement statement = connection.prepareStatement(SQL_SELECT_BY_ID);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                returnROle.setId(resultSet.getInt("id"));
                returnROle.setTitle(resultSet.getString("role"));
            }
            return returnROle;
        } catch (SQLException e) {
            new IllegalStateException(e);
        }
        return null;
    }

    @Override
    public void save(Role model) {

    }

    @Override
    public void update(Role model) {

    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public List<Role> findAll() {
        return null;
    }

    @Override
    public List<Role> findByRole(String role) {
        try {
            List<Role> rolesList = new ArrayList<>();
            PreparedStatement statement = connection.prepareStatement(SQL_SELECT_BY_ROLE);
            statement.setString(1, role);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                Integer id = resultSet.getInt("id");
                String roleTitle = resultSet.getString("role");
                rolesList.add(new Role(id,roleTitle));
            }
            return rolesList;
        } catch (SQLException e) {
            new IllegalStateException(e);
        }
        return null;
    }
}
