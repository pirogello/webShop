package model.dao;

import model.DBConnection;
import model.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDaoDbcImp implements OrderDao{


    private final String SQL_SELECT_BY_USER_ID = "SELECT * FROM orders WHERE user_id = ?";
    private final String SQL_SAVE = "INSERT INTO orders (user_id, product_id,quantity_products) VALUES (?,?,?);";
    private final String SQL_DELETE = "DELETE FROM orders WHERE id= ?";
    private final String SQL_DELETE_BY_USER_ID = "DELETE FROM orders WHERE user_id= ?";
    private final String SQL_SELECT_BY_USER_ID_BY_PRODUCT_ID = "SELECT * FROM orders WHERE user_id = ? AND product_id = ?";
    private final String SQL_UPDATE_BY_USER_ID_AND_PRODUCT_ID = "UPDATE orders SET quantity_products = ? WHERE user_id = ? AND product_id = ?";

    private Connection connection;

    public OrderDaoDbcImp () throws SQLException, ClassNotFoundException {
        connection = new DBConnection().getConnection();
    }

    @Override
    public Order find(Integer id) {
        return null;
    }

    @Override
    public void save(Order model) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SQL_SAVE);
        statement.setInt(1,model.getUserId());
        statement.setInt(2,model.getProductId());
        statement.setInt(3,model.getQuantityProducts());
        statement.executeUpdate();
    }

    @Override
    public void update(Order model) {
        try {
            PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_BY_USER_ID_AND_PRODUCT_ID);
            statement.setInt(1, (model.getQuantityProducts()));
            statement.setInt(2, model.getUserId());
            statement.setInt(3, model.getProductId());
            statement.executeUpdate();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    @Override
    public void delete(Integer id) {
        try {
            PreparedStatement prepared_statement = connection.prepareStatement(SQL_DELETE);
            prepared_statement.setInt(1, id);
            prepared_statement.executeUpdate();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    @Override
    public List findAll() {
        return null;
    }

    @Override
    public List findAllByUserId(int userId) {

        try {
            List<Order> orders = new ArrayList<>();
            PreparedStatement statement = connection.prepareStatement(SQL_SELECT_BY_USER_ID);
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                Order order = new Order();
                order.setId(resultSet.getInt("id"));
                order.setUserId(resultSet.getInt("user_id"));
                order.setProductId(resultSet.getInt("product_id"));
                order.setQuantityProducts(resultSet.getInt("quantity_products"));
                orders.add(order);
            }
            return orders;
        } catch (SQLException e) {
            new IllegalStateException(e);
        }
        return null;
    }

    @Override
    public boolean deleteByUserId(int userId) {
        try {
            PreparedStatement prepared_statement = connection.prepareStatement(SQL_DELETE_BY_USER_ID);
            prepared_statement.setInt(1, userId);
            prepared_statement.executeUpdate();
            return true;
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return false;
    }


    @Override
    public Order findByUserIdAndProductId(int productId, int userId) {
        try {
            PreparedStatement prepared_statement = connection.prepareStatement(SQL_SELECT_BY_USER_ID_BY_PRODUCT_ID);
            prepared_statement.setInt(1, userId);
            prepared_statement.setInt(2, productId);
            ResultSet resultSet =  prepared_statement.executeQuery();
            if(resultSet.next())
                return new Order(resultSet.getInt(1),
                                resultSet.getInt(2),
                                resultSet.getInt(3),
                                resultSet.getInt(4));
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return null;
    }
}
