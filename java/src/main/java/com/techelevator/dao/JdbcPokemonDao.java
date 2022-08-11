package com.techelevator.dao;

import com.techelevator.model.Pokemon;
import com.techelevator.utility.PokeAPICaller;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcPokemonDao implements PokemonDao {

    private JdbcTemplate jdbcTemplate;

    public JdbcPokemonDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
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
    public boolean addPokemon(Pokemon poke, int collectionId) {
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
    public Integer getTotalPokemonByUserId(int userId){
        String sql = "SELECT COUNT(*) AS total_pokemon " +
                    "FROM users " +
                    "JOIN collections ON users.user_id = collections.user_id " +
                    "JOIN pokemon ON collections.collection_id = pokemon.collection_id " +
                    "WHERE users.user_id = ?; ";

        Integer totalPokemon = 0;

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql,userId);
        while(results.next()){
            totalPokemon = results.getInt("total_pokemon");
        }



        return totalPokemon;
    }


}
