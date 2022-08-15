package com.techelevator.dao;

import com.techelevator.model.Collection;

import java.util.List;

public interface CollectionDao {

    List<Collection> listAllCollections();

    List<Collection> listByID(int user_id);

    int createCollection(Collection collection);

    boolean deleteCollection(int collectionId);

    List<Collection> listPublicCollections();

    List<Collection> listRecentPublicCollections();

    Collection getRandomCollection();

    Collection getCollectionByCollectionId(int collectionId);

    void updateCollection(int collectionId, Collection collection);

    String getLinkKeyByCollectionId(int collectionId);
}
