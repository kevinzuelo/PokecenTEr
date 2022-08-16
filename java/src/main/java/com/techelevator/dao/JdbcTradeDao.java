package com.techelevator.dao;

import com.techelevator.model.Collection;
import com.techelevator.model.Pokemon;
import com.techelevator.model.Trade;
import com.techelevator.model.User;
import com.techelevator.utility.PokeAPICaller;
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
                    "WHERE requested_pokemon_owner = ? OR offered_pokemon_owner = ? ;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId, userId);


        while(results.next()){

            Trade trade = mapRowToTrade(results);

            trades.add(trade);

        }
        return trades;
    }

    @Override
    public int createTrade(int requestedPokemonId, int offeredPokemonId){

        int requestedOwner = collectionDao.getCollectionByCollectionId(pokemonDao.getPokemonById(requestedPokemonId).getCollectionId()).getUserId();
        int offeredOwner = collectionDao.getCollectionByCollectionId(pokemonDao.getPokemonById(offeredPokemonId).getCollectionId()).getUserId();


        String sql = "INSERT INTO trades (requested_pokemon, offered_pokemon, trade_status, requested_pokemon_owner, offered_pokemon_owner) " +
                    "VALUES ( ? , ? , 'Pending' , ? , ? ) " +
                    "RETURNING trade_id; ";

        int tradeId = jdbcTemplate.queryForObject(sql, Integer.class, requestedPokemonId, offeredPokemonId, requestedOwner, offeredOwner);

        return tradeId;
    };


    @Override
    public Trade getTradeByTradeId(int tradeId) {
        Trade trade = null;

        String sql = "SELECT * FROM trades " +
                     "WHERE trade_id = ?; ";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, tradeId);
        if(results.next()) {
            trade = mapRowToTrade(results);
            return trade;
        }
        return trade;
    }

    @Override
    public void updateTrade(int tradeId, String tradeStatus) {
        Trade trade = getTradeByTradeId(tradeId);
        int requestorCollectionId = trade.getRequestedPokemon().getCollectionId();
        int receiverCollectionId = trade.getOfferedPokemon().getCollectionId();

        String sql = "UPDATE trades " +
                     "SET trade_status = ? " +
                     "WHERE trade_id = ?; ";

        jdbcTemplate.update(sql, tradeStatus, tradeId);

        if (tradeStatus.equalsIgnoreCase("approved")) {
            trade.getRequestedPokemon().setCollectionId(receiverCollectionId);
            trade.getOfferedPokemon().setCollectionId(requestorCollectionId);

            pokemonDao.updatePokemon(trade.getRequestedPokemon(), trade.getRequestedPokemon().getPokemonId());
            pokemonDao.updatePokemon(trade.getOfferedPokemon(), trade.getOfferedPokemon().getPokemonId());
        }
    }

    @Override
    public void deleteTrade(int tradeId) {
        Trade trade = getTradeByTradeId(tradeId);

        String sql = "DELETE FROM trades WHERE trade_id = ?;" ;

        jdbcTemplate.update(sql, tradeId);
    }

    private Trade mapRowToTrade(SqlRowSet results){
        Trade trade = new Trade();

        trade.setTradeId(results.getInt("trade_id"));

        int requestedPokemon = results.getInt("requested_pokemon");
        int offeredPokemon = results.getInt("offered_pokemon");

        trade.setTradeStatus(results.getString("trade_status"));

        trade.setRequestedPokemon(pokemonDao.getPokemonById(requestedPokemon));
        trade.setOfferedPokemon(pokemonDao.getPokemonById(offeredPokemon));

        trade.setTradeInitiator(userDao.getUserById(results.getInt("offered_pokemon_owner")));
        trade.setTradeReceiver(userDao.getUserById(results.getInt("requested_pokemon_owner")));

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