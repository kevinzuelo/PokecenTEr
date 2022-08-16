package com.techelevator.model;

public class Trade {

    private int tradeId;
    private Pokemon requestedPokemon;
    private Pokemon offeredPokemon;
    private User tradeInitiator;
    private User tradeReceiver;
    private String tradeStatus;

    public int getTradeId() {
        return tradeId;
    }

    public void setTradeId(int tradeId) {
        this.tradeId = tradeId;
    }

    public Pokemon getRequestedPokemon() {
        return requestedPokemon;
    }

    public void setRequestedPokemon(Pokemon requestedPokemon) {
        this.requestedPokemon = requestedPokemon;
    }

    public Pokemon getOfferedPokemon() {
        return offeredPokemon;
    }

    public void setOfferedPokemon(Pokemon offeredPokemon) {
        this.offeredPokemon = offeredPokemon;
    }

    public User getTradeInitiator() {
        return tradeInitiator;
    }

    public void setTradeInitiator(User tradeInitiator) {
        this.tradeInitiator = tradeInitiator;
    }

    public User getTradeReceiver() {
        return tradeReceiver;
    }

    public void setTradeReceiver(User tradeReceiver) {
        this.tradeReceiver = tradeReceiver;
    }


    public String getTradeStatus() {
        return tradeStatus;
    }

    public void setTradeStatus(String tradeStatus) {
        this.tradeStatus = tradeStatus;
    }
}
