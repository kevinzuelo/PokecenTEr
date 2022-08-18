<template>
<div id ="trade-container">
 <pokemon-details id="request" v-bind:pokemon="this.pokemon" v-if="!isMobile"/>
 <div id="mobile-cards" v-else>
     <pokemon-preview class= "preview" v-if="this.loaded" v-bind:pokemon="this.pokemon" />
     <pokemon-preview class="preview" v-if="this.offeredPokemon" v-bind:pokemon="this.offeredPokemon" />
 </div>
    <div id="all-pokemon">
        <h1>Trade</h1>
        <h3>Select a pokemon to offer for trade</h3>
       
        <div id ="pokemon-container">

 <img v-for="pokemon in usersPokemon" v-bind:key="pokemon.pokemonId" v-bind:src="pokemon.imgSprite" 
     v-on:click="select(pokemon)" v-bind:class="{selected: offeredPokemon == pokemon}"/>
        </div>
                        <button id="send" class="btn" v-on:click="requestTrade"
                         v-bind:disabled="!this.offeredPokemon"
                         v-bind:class="{ 'dead-button': !this.offeredPokemon}"> 
                
                <p>Request Trade</p>
                <i  class="fa-solid fa-arrow-right"></i>
            </button> 
                <div id="back" class="btn" v-on:click="goToCollection">
                <i  class="fa-solid fa-arrow-left"></i>
                <p>Go Back</p>
            </div>  

       
        
    </div>
<pokemon-details id="offer" v-bind:pokemon="this.offeredPokemon" v-if="this.offeredPokemon && !this.isMobile" />
</div>

 
</template>

<script>
import PokemonDetails from '../components/PokemonDetails.vue'
import PokemonPreview from '../components/PokemonPreview.vue'
import PokemonService from '../services/PokemonService.js'
import TradeService from '../services/TradeService.js'
export default {
    data() {
        return {
            name:"trade-view",
            pokemon: {},
            offeredPokemon: '',
            usersPokemon: [],
            isMobile: false,
            loaded: false
        }
  },
     components: { PokemonDetails, PokemonPreview },
    created() {
        
        PokemonService.getPokemonByPokemonId(this.$route.params.id).then((response) => {
            this.pokemon = response.data
            this.loaded = true;
        }),
        PokemonService.getAllPokemonByUserId(this.$store.state.user.id).then((response) => {
            this.usersPokemon = response.data
        })
        window.addEventListener("resize", this.resizeBrowserHandler);
        if(window.innerWidth <= 500) {
            this.isMobile = true
        }

},

methods: {
      goToCollection() {
      this.$router.push({ name: 'collection', params: {id: this.pokemon.collectionId}});
    },
     select(pokemon) {
         if(this.offeredPokemon == pokemon) {
             this.offeredPokemon = '';
         }
         else{
            this.offeredPokemon = pokemon
         }
      
    },
    requestTrade() {
        TradeService.createTrade(this.pokemon.pokemonId, this.offeredPokemon.pokemonId).then(response =>{
            console.log(response);
            this.$router.push({ name: 'my-trades', params: {id: this.$store.state.user.id}});
        });
        
    },
    resizeBrowserHandler () {
    
    if (window.innerWidth <= 500) {
        this.isMobile = true;
    } else {
        this.isMobile = false;
    }
}
}
}
</script>

<style scoped>
#all-pokemon {
  text-align: center;
  display: flex;
  flex-direction: column;
}

#trade-container {
    display: flex;
    
}

#pokemon-container {
  background-color: rgba(146, 146, 146, 0.733);
  border-radius: 0;
  width: 350px;
  height: 500px;
  overflow-y: auto;
  padding-top: 10px;
  justify-content: space-between;
}

#pokemon-container::-webkit-scrollbar {
  display: none;
}

#pokemon-container > img:hover {
  outline-style: solid;
  outline-color: #ffe019;
  outline-offset: -3px;
  cursor: pointer;
  border-radius: 5px;
}
#pokemon-container > img {
  margin: 5px;
}

.selected {
  outline-style: solid;
  outline-color: #ffe019;
  outline-offset: -3px;
  outline-width: 3px;
  cursor: pointer;
  border-radius: 5px;
}
.dead-button {
    background-color: rgba(128, 128, 128, 0.753) !important;
    cursor: default !important;
    color: rgba(255, 253, 253, 0.527) !important;


}


.btn {
  display: flex;
  align-items: center;
  justify-content: space-evenly;
  justify-items: center;
  background-color: rgba(135, 206, 250, 0.3);
  border-radius: 0px 0px 20px 20px;
  color: white;
  font-size: 20px;
  margin: 0px !important;
  font-family: "Silkscreen", cursive;
  max-width: 100%
  ;

}

.btn:hover {
  cursor: pointer;
  background-color: rgba(135, 206, 250, 0.8);
}

#send {
    border-radius: 0px;
    background-color: #25c81fca;
    border: 0px;
    
}
#send:hover {
  background-color: #77cc74df;
}


#headers {
  color: white;
  background-color: rgba(0, 0, 0, 0.5);
  border-radius: 20px 20px 0 0;
}

h1 {
  background-color: rgba(0, 0, 0, 0.5);
  margin: 0px;
  padding: 20px;
  border-radius: 20px 20px 0 0;
}

h3 {
  background-color: #474747c4;
  margin: 0px;
  padding: 10px;
  color: white;
}

 @media (max-width: 600px) {

     #trade-container {
         flex-direction: column;
         gap: 20px;
         padding-bottom: 20px;
     }

     .preview {
         width: 150px
     }

     #mobile-cards {
         display: flex;
         justify-content: space-evenly;
         width: 100%;
     }

     #request {
        order: 1 ;
        display: inline-block;
        flex-direction: column;
     }
     #offer {
         order: 1 ;
     }

     #all-pokemon {
         order: 3;
         height: 500px;
         width: 100%
     }

     #pokemon-container {
         width: 100%
     }

     #back {
         height: 90px
     }
    }



</style>