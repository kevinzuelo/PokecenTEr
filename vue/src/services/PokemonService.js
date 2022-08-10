import axios from 'axios';

export default {

  getPokemonByCollectionId(collectionId) {
    return axios.get(`/collections/${collectionId}`)
  }

}