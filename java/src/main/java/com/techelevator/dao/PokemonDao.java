package com.techelevator.dao;
import com.techelevator.model.Pokemon;

import java.util.List;
import java.util.Map;

public interface PokemonDao {

    List<Pokemon> getAllPokemonByCollectionId(int collectionId);

    Pokemon getPokemonById(int pokemonId);

    boolean addPokemon(Pokemon poke, int collectionId);

    boolean releasePokemon(int pokemon_id);

    Integer getTotalPokemonByUserId(int userId);

    Map<String, Integer> getTypesByCollectionId(int collectionId);

    Map<String, Integer> getTypesByUserId(int userId);

    Integer getShinyByCollectionId(int collectionId);

    Integer getShinyByUserId(int userId);
}
