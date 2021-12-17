package com.teamred.dao;

import java.util.List;

public interface Dao<T> {

    public List<T> getItems();

    public T get(Integer itemId);

    public T add(T item);

    public T update(T item);

    public T delete(Integer itemId);
    
}
