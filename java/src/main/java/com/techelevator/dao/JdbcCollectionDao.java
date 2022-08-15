package com.techelevator.dao;

import com.techelevator.model.Collection;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import java.util.Random;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class JdbcCollectionDao implements CollectionDao{
    private JdbcTemplate jdbcTemplate;



    public JdbcCollectionDao(JdbcTemplate jdbcTemplate) {
            this.jdbcTemplate = jdbcTemplate;
        }


    @Override
    public List<Collection> listByID(int user_id) {
        List<Collection> lists = new ArrayList<>();
        String sql = "SELECT collection_id, collection_name, user_id, is_private " +
                "FROM collections WHERE user_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, user_id);
        while (results.next()) {
            Collection collection = mapRowToCollection(results);
            lists.add(collection);

        }
        return lists;
    }

    @Override
    public List<Collection> listAllCollections() {
        List<Collection> collections = new ArrayList<>();
        String sql = "SELECT collection_id, collection_name, user_id, is_private " +
                    "FROM collections; ";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while (results.next()) {
            Collection collection = mapRowToCollection(results);
            collections.add(collection);

        }

        return collections;
    }

    @Override
    public int createCollection(Collection collection){

        UUID randomUUID = UUID.randomUUID();

        String linkKey = randomUUID.toString().replace("-","");


        String sql = "INSERT INTO collections (collection_name, user_id, is_private, link_key) " +
                "VALUES (?,?,?,?) RETURNING collection_id;";

        Integer collectionId = jdbcTemplate.queryForObject(sql, Integer.class, collection.getName(),
                collection.getUserId(), collection.getIsPrivate(), linkKey);
        return collectionId;
    }

    public boolean deleteCollection(int collectionId) {
        String sql = "DELETE FROM pokemon WHERE collection_id = ?;" +
                "DELETE FROM collections WHERE collection_id = ?;";
        int numberOfRowsDeleted = jdbcTemplate.update(sql, collectionId, collectionId);

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
        String sql = "SELECT collection_id, collection_name, user_id, is_private FROM collections "+
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

    @Override
    public void updateCollection(int collectionId, Collection collection){
        String sql =    "UPDATE collections SET collection_name = ? , is_private = ? " +
                        "WHERE collection_id = ? ;";

        jdbcTemplate.update(sql, collection.getName(), collection.getIsPrivate(), collectionId);


    }

    @Override
    public String getLinkKeyByCollectionId(int collectionId){
        String sql = "SELECT link_key " +
                    "FROM collections " +
                    "WHERE collection_id = ?;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, collectionId);

        String linkKey = "";
        if(results.next()) {
            linkKey = results.getString("link_key");
        }

        return linkKey;
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


