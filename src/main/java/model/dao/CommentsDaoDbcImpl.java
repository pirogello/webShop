package model.dao;

import model.Comment;
import model.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CommentsDaoDbcImpl implements CommentsDao {

    private final String SQL_SELECT_BY_ID = "SELECT * FROM comments WHERE id = ?";
    private final String SQL_SELECT_BY_PRODUCT_ID = "SELECT * FROM comments WHERE product_id = ?";
    private final String SQL_SELECT_ALL = "SELECT * FROM comments";
    private final String SQL_UPDATE_BY_ID = "UPDATE comments SET text = ? WHERE id = ?";
    private final String SQL_SAVE = "INSERT INTO comments (user_id, product_id, text) VALUES (?,?,?);";
    private final String SQL_DELETE = "DELETE FROM comments WHERE id= ?";
    private final String SQL_DELETE_BY_PRODUCT_ID = "DELETE FROM comments WHERE product_id = ?";

    private Connection connection;
    private UsersDao usersDao;
    private ProductsDao productsDao;

    public CommentsDaoDbcImpl() throws SQLException, ClassNotFoundException {
        connection = new DBConnection().getConnection();
        usersDao = new UsersDaoDdbcImpl();
        productsDao = new ProductsDaoDbcImpl();
    }

    @Override
    public Comment find(Integer id) {
        try {
            Comment returnComment = new Comment();
            PreparedStatement statement = connection.prepareStatement(SQL_SELECT_BY_ID);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                returnComment.setId(resultSet.getInt("id"));
                returnComment.setUser(usersDao.find(resultSet.getInt("user_id")));
                returnComment.setProduct(productsDao.find(resultSet.getInt("product_id")));
                returnComment.setText(resultSet.getString("text"));
            }
            return returnComment;
        } catch (SQLException e) {
            new IllegalStateException(e);
        }
        return null;
    }

    @Override
    public void save(Comment model) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SQL_SAVE);
        statement.setInt(1, model.getUser().getId());
        statement.setInt(2, model.getProduct().getId());
        statement.setString(3, model.getText());
        statement.executeUpdate();
    }

    @Override
    public void update(Comment model) {
        try {
            PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_BY_ID);
            statement.setString(1, (model.getText()));
            statement.setInt(2, (model.getId()));
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
    public List<Comment> findAll() {
        try {
            List<Comment> commentsList = new ArrayList<>();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int userId = resultSet.getInt("user_id");
                int productId = resultSet.getInt("product_id");
                String text = resultSet.getString("text");
                Comment comment = new Comment(id, usersDao.find(userId), productsDao.find(productId), text);
                commentsList.add(comment);
            }
            return commentsList;
        } catch (SQLException e) {
            new IllegalStateException(e);
        }
        return null;
    }

    @Override
    public List<Comment> findByProductId(int productId) {
        try {
            List<Comment> commentsList = new ArrayList<>();
            PreparedStatement statement = connection.prepareStatement(SQL_SELECT_BY_PRODUCT_ID);
            statement.setInt(1, productId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int userId = resultSet.getInt("user_id");
                int productIdFrDB = resultSet.getInt("product_id");
                String text = resultSet.getString("text");
                Comment comment = new Comment(id, usersDao.find(userId), productsDao.find(productId), text);
                commentsList.add(comment);
            }
            return commentsList;
        } catch (SQLException e) {
            new IllegalStateException(e);
        }
        return null;
    }

    @Override
    public void deleteByProductId(int productId) {
        try {
            PreparedStatement statement = connection.prepareStatement(SQL_DELETE_BY_PRODUCT_ID);
            statement.setInt(1, productId);
            statement.executeUpdate();
        } catch (SQLException e) {
            new IllegalStateException(e);
        }
    }
}
