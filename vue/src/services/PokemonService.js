import axios from 'axios';

export default {



  getPokemonByCollectionId(collectionId) {
    return axios.get(`/collections/${collectionId}`)
  },

  addPokemon(newPokemon) {
    return axios.post(`/collections/${newPokemon.collectionId}`, newPokemon);

  },

  deletePokemon(pokemonId) {
    return axios.delete(`/pokemon/${pokemonId}`);
  }

}