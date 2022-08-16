package com.techelevator.controller;

import com.techelevator.dao.PokemonDao;
import com.techelevator.dao.TradeDao;
import com.techelevator.model.Collection;
import com.techelevator.model.Trade;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class TradeController {

    private TradeDao tradeDao;

    public TradeController(TradeDao tradeDao) {
        this.tradeDao = tradeDao;
    }

    @RequestMapping(path = "/users/{id}/trades", method = RequestMethod.GET)
    public List<Trade> getAllTradesByUserId(@PathVariable("id") int userId){
        return tradeDao.getAllTradesByUserId(userId);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "/trades", method =  RequestMethod.POST)
    public int createTrade(@RequestBody int[] pokemonIds){
        return tradeDao.createTrade(pokemonIds[0],pokemonIds[1]);
    }

    @RequestMapping(path = "/trades/{id}", method = RequestMethod.PUT)
    public void updateTrade(@PathVariable("id") int tradeId, @RequestBody String tradeStatus)  {
        System.out.println(tradeStatus);
        tradeDao.updateTrade(tradeId, tradeStatus);
    }

    @RequestMapping(path = "/trades/{id}", method = RequestMethod.GET)
    public Trade getTradeByTradeId(@PathVariable("id") int tradeId) {
        return tradeDao.getTradeByTradeId(tradeId);
    }

    @RequestMapping(path = "/trades/{id}", method = RequestMethod.DELETE)
    public void deleteTradeByTradeId(@PathVariable("id") int tradeId) {
        tradeDao.deleteTrade(tradeId);
    }
}
