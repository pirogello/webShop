package model.dao;

import java.sql.SQLException;
import java.util.List;

public interface CrudDao <T>{
    T find(Integer id);
    void save(T model) throws SQLException;
    void update(T model);
    void delete(Integer id);

    List<T> findAll();
}
