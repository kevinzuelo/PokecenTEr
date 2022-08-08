package com.techelevator.dao;

import com.techelevator.model.Pokemon;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcPokemon implements PokemonDao {

    private JdbcTemplate jdbcTemplate;

    public JdbcPokemon(JdbcTemplate jdbcTemplate) {
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
    public List<Pokemon> getPokemonByCollectionId(int collectionId) {
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
    public boolean addPokemon(Pokemon poke) {
        String sql = "INSERT INTO pokemon (pokemon_id, pokemon_name, species, type, collection_id, pokemon_level, is_shiny, notes, image_url, image_sprite) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?) RETURNING pokemon_id";

        Integer newPokemonId =0;

            newPokemonId = jdbcTemplate.queryForObject(sql, Integer.class, poke.getPokemonId(), poke.getName(), poke.getSpecies(), poke.getType(),
                    poke.getCollectionId(), poke.getLevel(), poke.isShiny(), poke.getNotes(), poke.getImgMain(), poke.getImgSprite());
        return newPokemonId == poke.getPokemonId();
    }


}
