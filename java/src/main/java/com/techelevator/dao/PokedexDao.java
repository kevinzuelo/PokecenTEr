package com.techelevator.dao;

import java.util.List;

public interface PokedexDao {

    public List<String> similarPokemonNames(String prompt);
}
