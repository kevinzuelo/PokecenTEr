package com.techelevator.dao;

import com.techelevator.model.Pokemon;
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
        pokemon.setName(result.getString("pokemon_name"));
        pokemon.setSpecies(result.getString("species"));
        pokemon.setType(result.getString("type"));
        pokemon.setCollectionId(result.getInt("collection_id"));
        pokemon.setLevel(result.getInt("pokemon_level"));
        pokemon.setShiny(result.getBoolean("is_shiny"));
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
        String sql = "SELECT * FROM pokemon WHERE pokemon_id = ?";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, pokemonId);
        Pokemon pokemon = mapRowToPokemon(results);

        return pokemon;
    }



    @Override
    public boolean addPokemon(Pokemon poke, int collectionId) {
        String sql = "INSERT INTO pokemon (pokemon_name, species, type, collection_id, pokemon_level, is_shiny, notes, image_url, image_sprite) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?) RETURNING pokemon_id";

        Integer newPokemonId =0;

            newPokemonId = jdbcTemplate.queryForObject(sql, Integer.class, poke.getPokemonId(), poke.getName(), poke.getSpecies(), poke.getType(),
                    collectionId, poke.getLevel(), poke.isShiny(), poke.getNotes(), poke.getImgMain(), poke.getImgSprite());
        return newPokemonId == poke.getPokemonId();
    }


}
