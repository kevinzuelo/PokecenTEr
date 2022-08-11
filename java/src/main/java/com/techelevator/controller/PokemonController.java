package com.techelevator.controller;

import com.techelevator.dao.PokemonDao;
import com.techelevator.exception.MaximumPokemonExceededException;
import com.techelevator.model.Pokemon;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.MalformedParameterizedTypeException;
import java.util.List;

@CrossOrigin
@RestController
public class PokemonController {

    private PokemonDao pokemonDao;

    public PokemonController(PokemonDao pokemonDao) {
        this.pokemonDao = pokemonDao;
    }

    @RequestMapping(path = "/pokemon/{id}", method = RequestMethod.GET)
    public Pokemon getPokemon (@PathVariable("id") int id) {

        return pokemonDao.getPokemonById(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "/collections/{id}", method = RequestMethod.POST)
    public void addPokemon(@RequestBody Pokemon pokemon, @PathVariable("id") int id) throws MaximumPokemonExceededException {
        pokemonDao.addPokemon(pokemon, id);
    }

    @RequestMapping(path = "/pokemon/{id}", method = RequestMethod.DELETE)
    public void releasePokemon(@PathVariable("id") int pokemon_id) {
        pokemonDao.releasePokemon(pokemon_id);
    }

    @RequestMapping(path = "users/{id}/totalpokemon", method = RequestMethod.GET)
    public Integer getTotalPokemonByUserId(@PathVariable("id") int userId) {return pokemonDao.getTotalPokemonByUserId(userId);}

    @RequestMapping(path = "users/collections/{id}/totalpokemon", method = RequestMethod.GET)
    public Integer getTotalPokemonByCollectionId(@PathVariable("id") int collectionId) {return pokemonDao.getTotalPokemonByCollectionId(collectionId);}

}

