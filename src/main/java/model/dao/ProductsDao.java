package model.dao;

import model.Product;

public interface ProductsDao extends CrudDao<Product>{

    public Product findByTitle (String title);
}
