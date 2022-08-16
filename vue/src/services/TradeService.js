import axios from 'axios';

export default {

  getAllTradesByUserId(userId) {
    return axios.get(`/users/${userId}/trades`)
  },

  createTrade(requestedPokemonId, offeredPokemonId){
        let pokemon = [requestedPokemonId, offeredPokemonId];

        return axios.post(`/trades`, pokemon);
  }

}