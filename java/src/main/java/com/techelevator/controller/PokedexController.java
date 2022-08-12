package com.techelevator.controller;

import com.techelevator.dao.CollectionDao;

import com.techelevator.dao.PokedexDao;
import com.techelevator.dao.PokemonDao;
import com.techelevator.model.Collection;
import com.techelevator.model.Pokemon;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
public class PokedexController {
    private PokedexDao pokedexDao;

    public PokedexController(PokedexDao pokedexDao){
        this.pokedexDao = pokedexDao;
    }

    @RequestMapping(path = "/pokedex/{prompt}", method = RequestMethod.GET)
    public List<String> similarPokemonNames(@PathVariable("prompt") String prompt){
        return pokedexDao.similarPokemonNames(prompt);
    }
}
