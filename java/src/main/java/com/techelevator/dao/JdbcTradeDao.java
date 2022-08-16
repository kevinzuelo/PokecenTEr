package com.techelevator.dao;

import com.techelevator.model.Collection;
import com.techelevator.model.Pokemon;
import com.techelevator.model.Trade;
import com.techelevator.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class JdbcTradeDao implements TradeDao{


    private JdbcTemplate jdbcTemplate;
    private JdbcUserDao userDao;
    private JdbcPokemonDao pokemonDao;
    private JdbcCollectionDao collectionDao;


    public JdbcTradeDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.userDao = new JdbcUserDao(jdbcTemplate);
        this.pokemonDao = new JdbcPokemonDao(jdbcTemplate);
        this.collectionDao = new JdbcCollectionDao(jdbcTemplate);
    }


    @Override
    public List<Trade> getAllTradesByUserId(int userId) {
        List<Trade> trades = new ArrayList<>();

        String sql = "SELECT * FROM trades " +
                    "WHERE from_pokemon IN (SELECT pokemon_id FROM pokemon WHERE collection_id IN (SELECT collection_id FROM collections WHERE user_id = ? )) OR " +
                    "to_pokemon IN (SELECT pokemon_id FROM pokemon WHERE collection_id IN (SELECT collection_id FROM collections WHERE user_id = ? ))";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId, userId);

        System.out.println("Test");
        while(results.next()){

            Trade trade = mapRowToTrade(results);

            trades.add(trade);

        }


        return trades;
    }

    @Override
    public int createTrade(int requestedPokemon, int offeredPokemon){

        String sql = "INSERT INTO trades (from_pokemon, to_pokemon, trade_status) " +
                    "VALUES ( ? , ? , 'Pending' ) " +
                    "RETURNING trade_id; ";

        int tradeId = jdbcTemplate.queryForObject(sql, Integer.class, offeredPokemon, requestedPokemon);

        return tradeId;
    };

    private Trade mapRowToTrade(SqlRowSet results){

        Trade trade = new Trade();

        trade.setTradeId(results.getInt("trade_id"));

        int fromPokemonId = results.getInt("from_pokemon");
        int toPokemonId = results.getInt("to_pokemon");

        trade.setTradeStatus(results.getString("trade_status"));

        trade.setRequestedPokemon(pokemonDao.getPokemonById(toPokemonId));
        trade.setOfferedPokemon(pokemonDao.getPokemonById(fromPokemonId));

        trade.setReceiverCollection(collectionDao.getCollectionByCollectionId(trade.getRequestedPokemon().getCollectionId()));
        trade.setRequestorCollection(collectionDao.getCollectionByCollectionId(trade.getOfferedPokemon().getCollectionId()));

        trade.setTradeInitiator(userDao.getUserById(trade.getRequestorCollection().getUserId()));
        trade.setTradeReceiver(userDao.getUserById(trade.getReceiverCollection().getUserId()));

        return trade;


    }

    private User mapRowToUser(SqlRowSet rs) {
        User user = new User();
        user.setId(rs.getInt("user_id"));
        user.setUsername(rs.getString("username"));
        user.setPassword(rs.getString("password_hash"));
        user.setAuthorities(Objects.requireNonNull(rs.getString("role")));
        user.setActivated(true);
        user.setEmail(rs.getString("email"));
        user.setContinent(rs.getString("continent"));
        user.setIconUrl(rs.getString("icon"));
        return user;
    }

    private Collection mapRowToCollection(SqlRowSet collectionMap) {
        Collection collection = new Collection();
        collection.setCollectionId(collectionMap.getInt("collection_id"));
        collection.setName(collectionMap.getString("collection_name"));
        collection.setUserId(collectionMap.getInt("user_id"));
        collection.setPrivate(collectionMap.getBoolean("is_private"));
        return collection;
    }

    private Pokemon mapRowToPokemon(SqlRowSet result){
        Pokemon pokemon = new Pokemon();
        pokemon.setPokemonId(result.getInt("pokemon_id"));
        pokemon.setSpecies(result.getString("species"));
        pokemon.setType(result.getString("type"));
        pokemon.setCollectionId(result.getInt("collection_id"));
        pokemon.setLevel(result.getInt("pokemon_level"));
        pokemon.setIsShiny(result.getBoolean("is_shiny"));
        pokemon.setNotes(result.getString("notes"));
        pokemon.setImgMain(result.getString("image_url"));
        pokemon.setImgSprite(result.getString("image_sprite"));

        return pokemon;
    }



}


//    @Override
//    public Pokemon getPokemonById(int pokemonId) {
//        Pokemon pokemon = null;
//        String sql = "SELECT * FROM pokemon WHERE pokemon_id = ?";
//
//        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, pokemonId);
//        if(results.next()) {
//            pokemon = mapRowToPokemon(results);
//            return pokemon;
//        }
//        return pokemon;
//    }