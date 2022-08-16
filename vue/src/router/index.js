import Vue from 'vue'
import Router from 'vue-router'
import Home from '../views/Home.vue'
import Login from '../views/Login.vue'
import Logout from '../views/Logout.vue'
import Register from '../views/Register.vue'
import store from '../store/index'
import AddCollection from "@/views/AddCollection"
import Collection from "@/views/Collection.vue"
import AddPokemon from "@/views/AddPokemon.vue"
import Detail from '../views/Detail.vue'
import collectionService from '@/services/CollectionService.js'
import pokemonService from '@/services/PokemonService.js'
import Redirect from '@/views/Redirect.vue'
import NotFound from '@/views/NotFound.vue'
import UpdatePokemon from '../views/UpdatePokemon.vue'
import MovePokemon from '../views/MovePokemon.vue'
import Browse from '@/views/Browse.vue'
import BulkAdd from '@/views/BulkAdd.vue'
import Trade from '@/views/Trade.vue'


Vue.use(Router)

/**
 * The Vue Router is used to "direct" the browser to render a specific view component
 * inside of App.vue depending on the URL.
 *
 * It also is used to detect whether or not a route requires the user to have first authenticated.
 * If the user has not yet authenticated (and needs to) they are redirected to /login
 * If they have (or don't need to) they're allowed to go about their way.
 */

const router = new Router({
  mode: 'history',
  base: process.env.BASE_URL,
  routes: [
    {
      path: '/',
      name: 'home',
      component: Home,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/login",
      name: "login",
      component: Login,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/redirect",
      name: "redirect",
      component: Redirect,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/logout",
      name: "logout",
      component: Logout,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/register",
      name: "register",
      component: Register,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/addCollection",
      name: "AddCollection",
      component: AddCollection,
      meta: {
        requiresAuth: true
      }
    },
    
      {
        path: "/collection/:id",
        name: "collection",
        component: Collection,
        beforeEnter(to, from, next) {
          
          let collectionId = to.params.id;

          let collection = {}
          collectionService.getCollectionByCollectionId(collectionId)
          .then( (response) => {

            collection = response.data;
            
            if(!collection.isPrivate  || store.state.token != '' || (store.state.currentCollection && store.state.currentCollection.collectionId === collectionId)){
              next();
            }
            else if(to.query.key){
              collectionService.getLinkAuthorization(collectionId, to.query.key)
                            .then( (response) => {
                              if(response.data == 'Authorized'){
                                store.commit('SET_CURRENT_COLLECTION', collection)
                                next();
                              }
                              else{
                                next("/redirect");
                              }
                            });
            }
            else{
              next("/redirect");
            }

          });
        }
          
        
      },

    {
      path: "/collection/:id/addPokemon",
      name: "add-pokemon",
      component: AddPokemon,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/bulkAdd",
      name: "bulk-add",
      component: BulkAdd,
      meta: {
        requiresAuth: true
      }
    },
    
    {
      path: "/pokemon/:id",
      name: "detail",
      component: Detail,
      beforeEnter(to, from, next) {

        let pokemonId = to.params.id;
        let pokemon = {};

        pokemonService.getPokemonByPokemonId(pokemonId)
            .then( (response) => {
              pokemon = response.data;
            
 
        let collectionId = pokemon.collectionId;

        let collection = {}
        collectionService.getCollectionByCollectionId(collectionId)
        .then( (response) => {

          collection = response.data;
          if(!collection.isPrivate || store.state.token != '' || store.state.currentCollection.collectionId === collectionId){
            next();
          }
          else {
            next("/redirect");
          }

        })
});
        
      }
    },
    {
      path: "/pokemon/:id/edit",
      name: "edit",
      component: UpdatePokemon,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/pokemon/:id/move",
      name: "move",
      component: MovePokemon,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/browse",
      name: "browse",
      component: Browse,
      meta: {
        requiresAuth: false
      }
    },
    
    {
    path: '*',
    name: "not-found", 
    component: NotFound
    },
    {
      path: "/pokemon/:id/trade",
      name: "trade",
      component: Trade
    }
  ]
})

router.beforeEach((to, from, next) => {
  // Determine if the route requires Authentication
  const requiresAuth = to.matched.some(x => x.meta.requiresAuth);

  // If it does and they are not logged in, send the user to "/login"
  if (requiresAuth && store.state.token === '') {
    next("/login");
  } else {
    // Else let them go to their next destination
    next();
  }
});

export default router;
