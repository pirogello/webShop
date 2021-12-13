package model.dao;

import model.Role;

import java.util.List;

public interface RolesDao  extends CrudDao<Role> {
    public List<Role> findByRole(String role);
}
