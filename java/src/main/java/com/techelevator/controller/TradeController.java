package com.techelevator.controller;

import com.techelevator.dao.PokemonDao;
import com.techelevator.dao.TradeDao;
import com.techelevator.model.Collection;
import com.techelevator.model.Trade;
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

}
