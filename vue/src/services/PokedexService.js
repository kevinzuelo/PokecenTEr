import axios from 'axios';

export default {

  getSimilarPokemonNames(prompt) {
    return axios.get(`/pokedex/${prompt}`)
  },

}