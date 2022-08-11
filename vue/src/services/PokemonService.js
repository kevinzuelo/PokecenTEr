import axios from 'axios';

export default {



  getPokemonByCollectionId(collectionId) {
    return axios.get(`/collections/${collectionId}`)
  },

  getPokemonByPokemonId(pokemonId) {
    return axios.get(`/pokemon/${pokemonId}`)
  },

  addPokemon(newPokemon) {
    return axios.post(`/collections/${newPokemon.collectionId}`, newPokemon);

  }

}