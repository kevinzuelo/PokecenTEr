package com.techelevator.dao;
import com.techelevator.model.Pokemon;

import java.util.List;

public interface PokemonDao {

    List<Pokemon> getPokemonByCollectionId(int collectionId);

    boolean addPokemon(Pokemon poke);
}
