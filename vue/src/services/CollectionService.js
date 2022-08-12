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

  getPokemonTypesByUserId(userId) {
    return axios.get(`/user/${userId}/typeStats`)
  }

}
