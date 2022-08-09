package com.techelevator.dao;
import com.techelevator.model.Pokemon;

import java.util.List;

public interface PokemonDao {

    List<Pokemon> getAllPokemonByCollectionId(int collectionId);

    Pokemon getPokemonById(int pokemonId);

    boolean addPokemon(Pokemon poke, int collectionId);
}
