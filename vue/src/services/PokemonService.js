import axios from 'axios';

export default {

  getPokemonByCollectionId(collectionId) {
    return axios.get(`/collections/${collectionId}`)
  },

  getNumberOfPokemonByCollectionId(collectionId) {
    return axios.get(`/users/collections/${collectionId}/totalpokemon`)
  },

  getPokemonByPokemonId(pokemonId) {
    return axios.get(`/pokemon/${pokemonId}`)
  },

  addPokemon(newPokemon) {
    return axios.post(`/collections/${newPokemon.collectionId}`, newPokemon);

  },

  deletePokemon(pokemonId) {
    return axios.delete(`/pokemon/${pokemonId}`);
  },

  getCollectionTypeStatistics(collectionId) {
    return axios.get(`/collections/${collectionId}/typeStats`);
  },
  
  updatePokemon(updatedPokemon) {
    return axios.put(`/pokemon/${updatedPokemon.pokemonId}`, updatedPokemon);
  },
  
  getAllPokemonByUserId(userId) {
    return axios.get(`/users/${userId}/totalpokemon`);
  }

}
