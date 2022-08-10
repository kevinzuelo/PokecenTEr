package com.techelevator.controller;

import com.techelevator.dao.CollectionDao;

import com.techelevator.dao.PokemonDao;
import com.techelevator.model.Collection;
import com.techelevator.model.Pokemon;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class CollectionController {


    private CollectionDao collectionDao;
    private PokemonDao pokemonDao;

    public CollectionController( CollectionDao collectionDao, PokemonDao pokemonDao) {
        this.collectionDao = collectionDao;
        this.pokemonDao = pokemonDao;
    }

    @RequestMapping(path = "/collections/id/{id}", method = RequestMethod.GET)
    public Collection getCollectionByCollectionId(@PathVariable("id") int id){
        return collectionDao.getCollectionByCollectionId(id);
    }

    @RequestMapping(path = "/collections/random", method = RequestMethod.GET)
    public Collection getRandomCollection(){
        return collectionDao.getRandomCollection();
    }

    @RequestMapping(path = "/collections/public", method = RequestMethod.GET)
    public List<Collection> getPublicCollections() {
        return collectionDao.listPublicCollections();
    }

    @RequestMapping(path = "/collections/public/recent", method = RequestMethod.GET)
    public List<Collection> getRecentPublicCollections() {
        return collectionDao.listRecentPublicCollections();
    }

    @RequestMapping(path = "/collections/{id}", method = RequestMethod.GET)
    public List<Pokemon> getPokemonList(@PathVariable("id") int id) {

        return pokemonDao.getAllPokemonByCollectionId(id);
    }

    @RequestMapping(path = "/user/{userId}/collections/{collectionId}", method = RequestMethod.DELETE)
    public boolean deleteCollection(@PathVariable("id") int userId, @PathVariable("collectionId") int collectionId) {
        return collectionDao.deleteCollection(userId, collectionId);
    }

    @RequestMapping(path = "/user/{id}/collections", method = RequestMethod.GET)
    public List<Collection> getCollectionsbyUserId(@PathVariable("id") int user_id) {
        return collectionDao.listByID(user_id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "/collections", method = RequestMethod.POST)
    public void addCollection(@RequestBody Collection collection) {
        collectionDao.createCollection(collection);
    }



}

