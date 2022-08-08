package com.techelevator.dao;

import com.techelevator.model.Collection;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcCollectionDao implements CollectionDao{
    private JdbcTemplate jdbcTemplate;



    public JdbcCollectionDao(JdbcTemplate jdbcTemplate) {
            this.jdbcTemplate = jdbcTemplate;
        }

    @Override
    public List<java.util.Collection> listByID(int user_id) {
        return null;
    }

    @Override
    public int createCollection(int user_id) {
        return 0;
    }

    @Override
        public List<Collection> list() {
            List<Collection> lists = new ArrayList<>();
            String sql = "SELECT collection_id, collection_name, user_id, is_private, game " +
                    "FROM collections";
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            while (results.next()) {
                Collection collection = mapRowToCollection(results);
                lists.add(collection);

            }
            return lists;
        }


    @Override
    public int createCollection(Collection collection){
        String sql = "INSERT INTO collection (collection_id, collection_name, user_id, is_private, game) " +
                "VALUES (?,?,?,?,?) RETURNING collection_id;";

        Integer collectionId = jdbcTemplate.queryForObject(sql, Integer.class, collection.getCollectionId(), collection.getName(),
                collection.getUserId(), collection.isPrivate(), collection.getGame());
        return collectionId;
    }

    private Collection mapRowToCollection(SqlRowSet collectionMap) {
        Collection collection = new Collection();
        collection.setCollectionId(collectionMap.getInt("collection_id"));
        collection.setName(collectionMap.getString("collection_name"));
        collection.setUserId(collectionMap.getInt("user_id"));
        collection.setPrivate(collectionMap.getBoolean("is_private"));
        collection.setGame(collectionMap.getString("game"));
        return collection;
    }

}


