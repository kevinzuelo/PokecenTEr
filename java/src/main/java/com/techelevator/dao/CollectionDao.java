package com.techelevator.dao;

import java.util.Collection;
import java.util.List;

public interface CollectionDao {

    public List<Collection> listByID(int user_id);

    public int createCollection(int user_id);

    List<com.techelevator.model.Collection> list();

    int createCollection(com.techelevator.model.Collection collection);
}
