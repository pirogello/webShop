package model.dao;

import model.User;

import java.util.List;

public interface UsersDao extends CrudDao<User> {
   User findAllByFirstName(String firstName);
}
