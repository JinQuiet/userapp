package com.teamred.dao;

import com.teamred.model.User;

import java.sql.SQLException;
import java.util.List;

public interface Dao<T> {

    public List<T> getAll() throws SQLException;

    public T get(Integer userId) throws SQLException;

    public T add(User user) throws SQLException;

    public T update(User user) throws SQLException;

    public T delete(Integer userId) throws SQLException;
    
}
