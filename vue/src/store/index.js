import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'

Vue.use(Vuex)

/*
 * The authorization header is set for axios when you login but what happens when you come back or
 * the page is refreshed. When that happens you need to check for the token in local storage and if it
 * exists you should set the header so that it will be attached to each request
 */
const currentToken = localStorage.getItem('token')
const currentUser = JSON.parse(localStorage.getItem('user'));

if(currentToken != null) {
  axios.defaults.headers.common['Authorization'] = `Bearer ${currentToken}`;
}

export default new Vuex.Store({
  state: {
    token: currentToken || '',
    user: currentUser || {},
    images: {
      "bug": "https://static.wikia.nocookie.net/pokemon/images/6/64/Type_Bug.gif",
      "dark": "https://static.wikia.nocookie.net/pokemon/images/0/0d/Type_Dark.gif",
      "dragon": "https://static.wikia.nocookie.net/pokemon/images/2/26/Type_Dragon.gif",
      "electric": "https://static.wikia.nocookie.net/pokemon/images/a/aa/Type_Electric.gif",
      "fairy": "https://static.wikia.nocookie.net/pokemon/images/7/74/Type_Fairy.gif",
      "fighting": "https://static.wikia.nocookie.net/pokemon/images/6/6b/Type_Fighting.gif",
      "fire": "https://static.wikia.nocookie.net/pokemon/images/4/4d/Type_Fire.gif",
      "flying": "https://static.wikia.nocookie.net/pokemon/images/4/4b/Type_Flying.gif",
      "ghost": "https://static.wikia.nocookie.net/pokemon/images/f/f2/Type_Ghost.gif",
      "grass": "https://static.wikia.nocookie.net/pokemon/images/4/46/Type_Grass.gif",
      "ground": "https://static.wikia.nocookie.net/pokemon/images/1/1d/Type_Ground.gif",
      "ice": "https://static.wikia.nocookie.net/pokemon/images/8/84/Type_Ice.gif",
      "normal": "https://static.wikia.nocookie.net/pokemon/images/6/61/Type_Normal.gif",
      "poison": "https://static.wikia.nocookie.net/pokemon/images/8/82/Type_Poison.gif",
      "psychic" : "https://static.wikia.nocookie.net/pokemon/images/6/65/Type_Psychic.gif",
      "rock": "https://static.wikia.nocookie.net/pokemon/images/b/b3/Type_Rock.gif",
      "steel": "https://static.wikia.nocookie.net/pokemon/images/a/ab/Type_Steel.gif",
      "water": "https://static.wikia.nocookie.net/pokemon/images/e/ed/Type_Water.gif",
    }
  },
  mutations: {
    SET_AUTH_TOKEN(state, token) {
      state.token = token;
      localStorage.setItem('token', token);
      axios.defaults.headers.common['Authorization'] = `Bearer ${token}`
    },
    SET_USER(state, user) {
      state.user = user;
      localStorage.setItem('user',JSON.stringify(user));
    },
    LOGOUT(state) {
      localStorage.removeItem('token');
      localStorage.removeItem('user');
      state.token = '';
      state.user = {};
      axios.defaults.headers.common = {};
    }
  }
})
