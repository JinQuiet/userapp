package com.teamred.dao;

import java.util.List;

public interface Dao<T> {

    public List<T> getAll();

    public T get(Integer userId);

    public T add(T user);

    public T update(T user);

    public T delete(Integer userId);    
    
}
