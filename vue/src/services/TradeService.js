import axios from 'axios';

export default {

  getAllTradesByUserId(userId) {
    return axios.get(`/users/${userId}/trades`)
  },

  getTradeByTradeId(tradeId) {
    return axios.get(`/trades/${tradeId}`);
  },

  createTrade(requestedPokemonId, offeredPokemonId){
        let pokemon = [requestedPokemonId, offeredPokemonId];

        return axios.post(`/trades`, pokemon);
  },

  updateTrade(updatedTrade) {
    return axios.put(`/trades/${updatedTrade.tradeId}`, updatedTrade);
  },

  deleteTrade(tradeId) {
    return axios.delete(`/trades/${tradeId}`);
  }


}