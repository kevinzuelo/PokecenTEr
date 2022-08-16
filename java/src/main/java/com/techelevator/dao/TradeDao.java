package com.techelevator.dao;

import com.techelevator.model.Trade;

import java.util.List;

public interface TradeDao {

    List<Trade> getAllTradesByUserId(int userId);

    int createTrade(int requestedPokemonId, int offeredPokemonId);

    void updateTrade(int tradeId, String tradeStatus);

    Trade getTradeByTradeId(int tradeId);

    void deleteTrade(int tradeId);

}
