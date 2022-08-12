package com.techelevator.dao;

import com.techelevator.exception.MaximumPokemonExceededException;
import com.techelevator.model.Authority;
import com.techelevator.model.Pokemon;
import com.techelevator.model.User;
import com.techelevator.utility.PokeAPICaller;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class JdbcPokemonDao implements PokemonDao {

    private JdbcTemplate jdbcTemplate;
    private JdbcUserDao userDao;

    public JdbcPokemonDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.userDao = new JdbcUserDao(jdbcTemplate);
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

    @Override
    public List<Pokemon> getAllPokemonByCollectionId(int collectionId) {
        List<Pokemon> pokemonList = new ArrayList<>();

        String sql = "SELECT * FROM pokemon WHERE collection_id = ?";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, collectionId);
        while(results.next()) {
            Pokemon pokemon = mapRowToPokemon(results);
            pokemonList.add(pokemon);
        }
        return pokemonList;

    }

    @Override
    public Pokemon getPokemonById(int pokemonId) {
        Pokemon pokemon = null;
        String sql = "SELECT * FROM pokemon WHERE pokemon_id = ?";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, pokemonId);
        if(results.next()) {
            pokemon = mapRowToPokemon(results);
            return pokemon;
        }
        return pokemon;
    }

    @Override
    public boolean addPokemon(Pokemon poke, int collectionId) throws MaximumPokemonExceededException {

        int totalPokemon = getTotalPokemonByCollectionId(collectionId);

        User user = userDao.getUserByCollectionId(collectionId);

        boolean isPremium = false;

        for (Authority authority : user.getAuthorities()) {
            if(authority.getName().equals("ROLE_PREMIUM_USER")){
                isPremium = true;
            }
        }

        if (!isPremium && getTotalPokemonByUserId(user.getId()) >= 50){
            throw new MaximumPokemonExceededException("Could not add pokemon. Must upgrade to premium to have more than 50 pokemon.");
        }



        String sql = "INSERT INTO pokemon (species, type, collection_id, pokemon_level, is_shiny, notes, image_url, image_sprite) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?) RETURNING pokemon_id";

        poke.setImgMain(PokeAPICaller.getPokemonImageUrl(poke));
        poke.setImgSprite(PokeAPICaller.getPokemonSpriteUrl(poke));
        poke.setType(PokeAPICaller.getPokemonType(poke));

        Integer newPokemonId =0;

            newPokemonId = jdbcTemplate.queryForObject(sql, Integer.class, poke.getSpecies(), poke.getType(),
                    collectionId, poke.getLevel(), poke.getIsShiny(), poke.getNotes(), poke.getImgMain(), poke.getImgSprite());
        return newPokemonId == poke.getPokemonId();
    }

    @Override
    public boolean releasePokemon(int pokemon_id) {
        String sql = "DELETE FROM pokemon WHERE pokemon_id = ?";

        int numberOfRowsDeleted = jdbcTemplate.update(sql, pokemon_id);

        return numberOfRowsDeleted != 0;
    }

    @Override
    public int updatePokemon(Pokemon poke, int pokemon_id) {
        String sql = "UPDATE pokemon SET pokemon_level = ?, is_shiny = ?, notes = ?, collection_id = ?, image_sprite = ? " +
                "WHERE pokemon_id = ?";
        poke.setImgSprite(PokeAPICaller.getPokemonSpriteUrl(poke));
        return jdbcTemplate.update(sql, poke.getLevel(), poke.getIsShiny(), poke.getNotes(), poke.getCollectionId(), poke.getImgSprite(), pokemon_id);

    }


    @Override
    public Integer getTotalPokemonByUserId(int userId){
        String sql = "SELECT COUNT(*) AS total_pokemon " +
                    "FROM users " +
                    "JOIN collections ON users.user_id = collections.user_id " +
                    "JOIN pokemon ON collections.collection_id = pokemon.collection_id " +
                    "WHERE users.user_id = ? ; ";

        Integer totalPokemon = 0;

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql,userId);
        while(results.next()){
            totalPokemon = results.getInt("total_pokemon");
        }

        return totalPokemon;
    }

    @Override
    public Integer getTotalPokemonByCollectionId(int collectionId){
        String sql = "SELECT COUNT(*) AS total_pokemon " +
                "FROM collections " +
                "JOIN users ON users.user_id = collections.user_id " +
                "JOIN pokemon ON collections.collection_id = pokemon.collection_id " +
                "WHERE collections.collection_id = ? ; ";

        Integer totalPokemon = 0;

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql,collectionId);
        while(results.next()){
            totalPokemon = results.getInt("total_pokemon");
        }

        return totalPokemon;
    }

    @Override
    public Map<String, Integer> getTypesByCollectionId(int collectionId) {
        Map<String, Integer> typeMap = new HashMap<>();

        String[] typeList = new String[]{"bug","dark","dragon","electric","fairy","fighting","fire","flying","ghost","grass","ground","ice","normal","poison","psychic","rock","steel","water"};
        for(int i =0; i<typeList.length;i++){
            typeMap.put(typeList[i], 0);
        }

        String sql = "SELECT SPLIT_PART(type, ' ', 1) as first_type, COUNT(*) " +
                "FROM pokemon " +
                "WHERE collection_id = ? " +
                "GROUP BY first_type;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, collectionId);

        while(results.next()){
            typeMap.put(results.getString("first_type"),results.getInt("count"));
        }

        return typeMap;
    }

    @Override
    public Map<String, Integer> getTypesByUserId(int userId) {
        Map<String, Integer> typeMap = new HashMap<>();

        String[] typeList = new String[]{"bug","dark","dragon","electric","fairy","fighting","fire","flying","ghost","grass","ground","ice","normal","poison","psychic","rock","steel","water"};
        for(int i =0; i<typeList.length;i++){
            typeMap.put(typeList[i], 0);
        }

        String sql = "SELECT SPLIT_PART(type, ' ', 1) as first_type, COUNT(*) " +
                "FROM pokemon " +
                "JOIN collections ON pokemon.collection_id = collections.collection_id " +
                "JOIN users ON collections.user_id = users.user_id " +
                "WHERE users.user_id = ? " +
                "GROUP BY first_type;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);

        while(results.next()){
            typeMap.put(results.getString("first_type"),results.getInt("count"));
        }

        return typeMap;
    }

    @Override
    public Integer getShinyByCollectionId(int collectionId) {
        Integer returnInt = 0;

        String sql = "SELECT COUNT(*) " +
                "FROM pokemon " +
                "WHERE collection_id = ? AND is_shiny = true;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, collectionId);

        while(results.next()){
            returnInt = results.getInt("count");
        }

        return returnInt;
    }

    @Override
    public Integer getShinyByUserId(int userId) {
        Integer returnInt = 0;

        String sql = "SELECT COUNT(*) " +
                "FROM pokemon " +
                "JOIN collections ON pokemon.collection_id = collections.collection_id " +
                "JOIN users ON collections.user_id = users.user_id " +
                "WHERE users.user_id = ? AND is_shiny = true;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);

        while(results.next()){
            returnInt = results.getInt("count");
        }

        return returnInt;
    }


}
