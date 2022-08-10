import axios from 'axios';

export default {

  addPokemon(newCollection) {
    return axios.post('/collections', newCollection)
  },

  getPokemonByCollectionId(collectionId) {
    return axios.get(`/collections/${collectionId}`)
  },

  getRecentCollections() {
    return axios.get('/collections/public/recent')
  }

}