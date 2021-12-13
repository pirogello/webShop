package model.dao;

import model.Order;

import java.util.List;

public interface OrderDao extends CrudDao<Order> {
    public List findAllByUserId(int userId);
    public boolean deleteByUserId(int userId);
    public Order findByUserIdAndProductId(int productId, int userId);
}
