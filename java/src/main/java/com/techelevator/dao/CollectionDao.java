package com.techelevator.dao;

import com.techelevator.model.Collection;

import java.util.List;

public interface CollectionDao {

    public List<Collection> listByID(int user_id);

    public int createCollection(Collection collection);

    public boolean deleteCollection(int collectionId);

    public List<Collection> listPublicCollections();

    public List<Collection> listRecentPublicCollections();

    public Collection getRandomCollection();

    public Collection getCollectionByCollectionId(int collectionId);

    public void updateCollection(int collectionId, Collection collection);
}
