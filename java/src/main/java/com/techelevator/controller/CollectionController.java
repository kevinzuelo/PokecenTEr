package com.techelevator.controller;

import com.techelevator.dao.CollectionDao;

import com.techelevator.dao.PokemonDao;
import com.techelevator.model.Collection;
import com.techelevator.model.Pokemon;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class CollectionController {


    private CollectionDao collectionDao;
    private PokemonDao pokemonDao;

    public CollectionController( CollectionDao collectionDao, PokemonDao pokemonDao) {
        this.collectionDao = collectionDao;
        this.pokemonDao = pokemonDao;
    }

    @RequestMapping(path = "/collections/{id}", method = RequestMethod.GET)
    public List<Pokemon> getPokemonList (@PathVariable("id") int id) {

        return pokemonDao.getAllPokemonByCollectionId(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "/collections", method = RequestMethod.POST)
    public void addCollection(@RequestBody Collection collection) {
        collectionDao.createCollection(collection);
    }



}

