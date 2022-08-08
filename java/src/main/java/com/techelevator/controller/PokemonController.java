package com.techelevator.controller;

import com.techelevator.dao.PokemonDao;
import com.techelevator.model.Pokemon;
import org.springframework.web.bind.annotation.*;

import java.util.List;


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

    @RequestMapping(path = "/collections/{id}", method = RequestMethod.POST)
    public void addPokemon(@RequestBody Pokemon pokemon, @PathVariable("id") int id) {
        pokemonDao.addPokemon(pokemon, id);
    }




}

