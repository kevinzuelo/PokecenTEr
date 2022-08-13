import axios from 'axios';

export default {

  createCollection(newCollection) {
    return axios.post('/collections', newCollection)
  },

  getCollectionsByUserId(userId) {
    return axios.get(`/user/${userId}/collections`)
  },

  getRecentCollections() {
    return axios.get('/collections/public/recent')
  },

  getCollectionByCollectionId(collectionId) {
    return axios.get(`/collections/id/${collectionId}`)
  },

  getPokemonTypesByUserId(userId) {
    return axios.get(`/user/${userId}/typeStats`)
  },
  
  getShinyPokemonByUserId(userId) {
    return axios.get(`/user/${userId}/shinyStats`)
  },

  getShinyPokemonByCollectionId(collectionId) {
    return axios.get(`/collections/${collectionId}/shinyStats`)
  },
  
  updateCollection(collectionId, collection) {
    return axios.get(`/collections/${collectionId}`, collection)
  }

}
