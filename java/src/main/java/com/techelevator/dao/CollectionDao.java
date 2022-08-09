package com.techelevator.dao;

import com.techelevator.model.Collection;

import java.util.List;

public interface CollectionDao {

    public List<Collection> listByID(int user_id);

    public int createCollection(Collection collection);
}
