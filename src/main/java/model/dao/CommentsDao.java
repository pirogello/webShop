package model.dao;

import model.Comment;

import java.util.List;

public interface CommentsDao extends CrudDao<Comment> {
    List<Comment> findByProductId(int productId);
    void deleteByProductId(int productId);
}
