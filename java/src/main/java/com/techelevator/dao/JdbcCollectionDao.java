package com.techelevator.dao;

import com.techelevator.model.Collection;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import java.util.Random;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcCollectionDao implements CollectionDao{
    private JdbcTemplate jdbcTemplate;



    public JdbcCollectionDao(JdbcTemplate jdbcTemplate) {
            this.jdbcTemplate = jdbcTemplate;
        }

    @Override
    public List<Collection> listByID(int user_id) {
        List<Collection> lists = new ArrayList<>();
        String sql = "SELECT collection_id, collection_name, user_id, is_private" +
                "FROM collections WHERE user_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, user_id);
        while (results.next()) {
            Collection collection = mapRowToCollection(results);
            lists.add(collection);

        }
        return lists;
    }

    @Override
    public int createCollection(Collection collection){
        String sql = "INSERT INTO collections (collection_name, user_id, is_private) " +
                "VALUES (?,?,?) RETURNING collection_id;";

        Integer collectionId = jdbcTemplate.queryForObject(sql, Integer.class, collection.getName(),
                collection.getUserId(), collection.getIsPrivate());
        return collectionId;
    }

    public boolean deleteCollection(int userId, int collectionId) {
        String sql = "DELETE FROM collections WHERE user_id = ? && collection_id = ?;";
        int numberOfRowsDeleted = jdbcTemplate.update(sql, userId, collectionId);

        return numberOfRowsDeleted != 0;
    }

    @Override
    public List<Collection> listPublicCollections() {
        List<Collection> pubCollections = new ArrayList<>();
        String sql = "SELECT collection_id, collection_name, user_id, is_private FROM collections "+
                "WHERE is_private IS NOT true;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while(results.next()){
            Collection collToAdd = mapRowToCollection(results);
            pubCollections.add(collToAdd);
        }
        return pubCollections;
    }

    @Override
    public List<Collection> listRecentPublicCollections() {
        List<Collection> recentPubCollections = new ArrayList<>();
        String sql = "SELECT collection_id, collection_name, user_id, is_private, FROM collections "+
                "WHERE is_private IS NOT true "+
                "ORDER BY collection_id DESC "+
                "LIMIT 5;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while(results.next()){
            Collection collToAdd = mapRowToCollection(results);
            recentPubCollections.add(collToAdd);
        }
        return recentPubCollections;
    }

    @Override
    public Collection getRandomCollection() {
        Random rand = new Random();
        List<Collection> publicCollections = listPublicCollections();
        int index = rand.nextInt(publicCollections.size());
        return publicCollections.get(index);
    }

    @Override
    public Collection getCollectionByCollectionId(int collectionId) {
        Collection collToReturn = null;
        String sql = "SELECT collection_id, collection_name, user_id, is_private FROM collections "+
                "WHERE collection_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, collectionId);
        while(results.next()){
            collToReturn = mapRowToCollection(results);
        }
        return collToReturn;
    }

    private Collection mapRowToCollection(SqlRowSet collectionMap) {
        Collection collection = new Collection();
        collection.setCollectionId(collectionMap.getInt("collection_id"));
        collection.setName(collectionMap.getString("collection_name"));
        collection.setUserId(collectionMap.getInt("user_id"));
        collection.setPrivate(collectionMap.getBoolean("is_private"));
        return collection;
    }

}


