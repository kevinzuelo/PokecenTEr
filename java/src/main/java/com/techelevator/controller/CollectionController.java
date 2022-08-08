package com.techelevator.controller;

import com.techelevator.dao.CollectionDao;
import com.techelevator.dao.PokemonDao;
import com.techelevator.model.Collection;
import com.techelevator.model.Pokemon;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class CollectionController {

    private PokemonDao pokemonDao;
    private CollectionDao collectionDao;

    public CollectionController(PokemonDao pokemonDao, CollectionDao collectionDao) {
        this.collectionDao = collectionDao;
        this.pokemonDao = pokemonDao;
    }

    @RequestMapping(path = "/addPokemon", method = RequestMethod.POST)
    public void addPokemon(@RequestBody Pokemon pokemon) {
        pokemonDao.addPokemon(pokemon);
    }

    @RequestMapping(path = "/collection/{id}", method = RequestMethod.GET)
    public List<Pokemon> getPokemonById (@PathVariable("id") int id) {
            return pokemonDao.getPokemonByCollectionId(id);
    }


}

