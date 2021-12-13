package model.dao;

import model.DBConnection;
import model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductsDaoDbcImpl implements ProductsDao{

    private final String SQL_SELECT_BY_TITLE = "SELECT * FROM products WHERE title = ?";
    private final String SQL_SELECT_BY_ID = "SELECT * FROM products WHERE id = ?";
    private final String SQL_SELECT_ALL = "SELECT * FROM products";
    private final String SQL_UPDATE_BY_ID = "UPDATE products SET title = ?,quantity = ?,description = ?,image = ?,price = ?,user_id = ?,highlighting = ? ,priority_in_list = ?  WHERE id = ?";
    private final String SQL_SAVE = "INSERT INTO products (title, quantity,description, image, price, user_id, highlighting, priority_in_list) VALUES (?,?,?,?,?,?,?,?);";
    private final String SQL_DELETE = "DELETE FROM products WHERE id= ?";

    private Connection connection;
    private UsersDao usersDao;

    public ProductsDaoDbcImpl () throws SQLException, ClassNotFoundException {
        connection = new DBConnection().getConnection();
        usersDao = new UsersDaoDdbcImpl();
    }

    @Override
    public Product find(Integer id) {
        try {
            Product returnProduct = new Product();
            PreparedStatement statement = connection.prepareStatement(SQL_SELECT_BY_ID);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                returnProduct.setId(resultSet.getInt("id"));
                returnProduct.setTitle(resultSet.getString("title"));
                returnProduct.setPrice(resultSet.getInt("price"));
                returnProduct.setQuantity(resultSet.getInt("quantity"));
                returnProduct.setImageHref(resultSet.getString("image"));
                returnProduct.setDescription(resultSet.getString("description"));
                returnProduct.setUser(usersDao.find(resultSet.getInt("user_id")));
                returnProduct.setHighlighting(resultSet.getBoolean("highlighting"));
                returnProduct.setPriorityInList(resultSet.getInt("priority_in_list"));
            }
            return returnProduct;
        } catch (SQLException e) {
            new IllegalStateException(e);
        }
        return null;
    }

    @Override
    public void save(Product model) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SQL_SAVE);
        Product product = model;
        statement.setString(1, product.getTitle());
        statement.setInt(2, product.getQuantity());
        statement.setString(3, product.getDescription());
        statement.setString(4, product.getImageHref());
        statement.setInt(5, product.getPrice());
        statement.setInt(6, product.getUser().getId());
        statement.setBoolean(7, product.isHighlighting());
        statement.setInt(8, product.getPriorityInList());
        statement.executeUpdate();
    }

    @Override
    public void update(Product model) {
        try {

            PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_BY_ID);
            statement.setString(1, (model.getTitle()));
            statement.setInt(2, (model.getQuantity()));
            statement.setString(3, (model.getDescription()));
            statement.setString(4, (model.getImageHref()));
            statement.setInt(5, model.getPrice());
            statement.setInt(6, model.getUser().getId());
            statement.setBoolean(7, model.isHighlighting());
            statement.setInt(8, model.getPriorityInList());
            statement.setInt(9, model.getId());
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
        try {
            List<Product> productsList = new ArrayList<>();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL);
            while(resultSet.next()){
                int id = resultSet.getInt("id");
                String title = resultSet.getString("title");
                int quantity = resultSet.getInt("quantity");
                int price = resultSet.getInt("price");
                String description = resultSet.getString("description");
                String imageHref = resultSet.getString("image");
                int user_id = resultSet.getInt("user_id");
                boolean highlighting = resultSet.getBoolean("highlighting");
                int priority_in_list = resultSet.getInt("priority_in_list");
               // System.out.println(usersDao.find(1));
                Product product = new Product(id, title, quantity, price, imageHref,description, usersDao.find(user_id), highlighting, priority_in_list);
                productsList.add(product);
            }
            return productsList;
        } catch (SQLException e) {
            new IllegalStateException(e);
        }
        return null;
    }

    @Override
    public Product findByTitle(String title) {
        try {
            Product returnProduct = new Product();
            PreparedStatement statement = connection.prepareStatement(SQL_SELECT_BY_TITLE);
            statement.setString(1, title);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                returnProduct.setId(resultSet.getInt("id"));
                returnProduct.setTitle(resultSet.getString("title"));
                returnProduct.setPrice(resultSet.getInt("amount"));
                returnProduct.setQuantity(resultSet.getInt("quantity"));
                returnProduct.setImageHref(resultSet.getString("image"));
                returnProduct.setDescription(resultSet.getString("description"));
                returnProduct.setUser(usersDao.find(resultSet.getInt("user_id")));
                returnProduct.setHighlighting(resultSet.getBoolean("highlighting"));
                returnProduct.setPriorityInList(resultSet.getInt("priority_in_list"));
            }
            return returnProduct;
        } catch (SQLException e) {
            new IllegalStateException(e);
        }
        return null;
    }
}
