package com.techelevator.dao;

import com.techelevator.model.Trade;

import java.util.List;

public interface TradeDao {

    List<Trade> getAllTradesByUserId(int userId);

    int createTrade(int requestedPokemon, int offeredPokemon);


}
