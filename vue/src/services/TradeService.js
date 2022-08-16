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

  updateTrade(tradeId, tradeStatus) {


    let approval = { approvalStatus: tradeStatus }
    return axios.put(`/trades/${tradeId}`, approval);
  },

  deleteTrade(tradeId) {
    return axios.delete(`/trades/${tradeId}`);
  }


}