package com.techelevator.dao;
import com.techelevator.exception.MaximumPokemonExceededException;
import com.techelevator.model.Pokemon;

import java.util.List;
import java.util.Map;

public interface PokemonDao {

    List<Pokemon> getAllPokemonByCollectionId(int collectionId);

    Pokemon getPokemonById(int pokemonId);

    boolean addPokemon(Pokemon poke, int collectionId) throws MaximumPokemonExceededException;

    boolean releasePokemon(int pokemon_id);

    Integer getTotalPokemonByUserId(int userId);

    Map<String, Integer> getTypesByCollectionId(int collectionId);

    Map<String, Integer> getTypesByUserId(int userId);

    Integer getShinyByCollectionId(int collectionId);

    Integer getShinyByUserId(int userId);

    Integer getTotalPokemonByCollectionId(int collectionId);
}
