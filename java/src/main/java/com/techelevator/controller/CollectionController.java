package com.techelevator.controller;

import com.techelevator.dao.CollectionDao;

import com.techelevator.dao.PokemonDao;
import com.techelevator.model.Collection;
import com.techelevator.model.Pokemon;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
public class CollectionController {


    private CollectionDao collectionDao;
    private PokemonDao pokemonDao;

    public CollectionController( CollectionDao collectionDao, PokemonDao pokemonDao) {
        this.collectionDao = collectionDao;
        this.pokemonDao = pokemonDao;
    }


    @RequestMapping(path = "/collections", method = RequestMethod.GET)
    public List<Collection> getAllCollections(){
        return collectionDao.listAllCollections();
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

    @RequestMapping(path = "/collections/{collectionId}", method = RequestMethod.DELETE)
    public void deleteCollection(@PathVariable("collectionId") int collectionId) {
        collectionDao.deleteCollection(collectionId);
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

    @RequestMapping(path = "/collections/{id}/typeStats", method = RequestMethod.GET)
    public Map<String,Integer> getTypesByCollectionId(@PathVariable("id") int collectionId){
        return pokemonDao.getTypesByCollectionId(collectionId);
    }

    @RequestMapping(path = "/user/{id}/typeStats", method = RequestMethod.GET)
    public Map<String, Integer> getTypesByUserId(@PathVariable("id") int userId){
        return pokemonDao.getTypesByUserId(userId);
    }

    @RequestMapping(path = "/collections/{id}/shinyStats", method = RequestMethod.GET)
    public Integer getShinyByCollectionId(@PathVariable("id") int collectionId){
        return pokemonDao.getShinyByCollectionId(collectionId);
    }

    @RequestMapping(path = "/user/{id}/shinyStats", method = RequestMethod.GET)
    public Integer getShinyByUserId(@PathVariable("id") int userId){
        return pokemonDao.getShinyByUserId(userId);
    }

    @RequestMapping(path = "/collections/{id}", method = RequestMethod.PUT)
    public void updateCollection(@PathVariable("id") int updatedCollectionId, @RequestBody Collection updatedCollection){

        collectionDao.updateCollection(updatedCollectionId, updatedCollection);
    }


}

