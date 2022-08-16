<template>
<div id ="trade-container">
 <pokemon-details v-bind:pokemon="this.pokemon"/>
    <div id="all-pokemon">
        <h1>Trade</h1>
        <h3>Select a pokemon to offer for trade</h3>
       
        <div id ="pokemon-container">

 <img v-for="pokemon in usersPokemon" v-bind:key="pokemon.pokemonId" v-bind:src="pokemon.imgSprite" 
     v-on:click="select(pokemon)" v-bind:class="{selected: offeredPokemon == pokemon}"/>
        </div>
                        <div id="send" class="btn" v-on:click="requestTrade">
                
                <p>Request Trade</p>
                <i  class="fa-solid fa-arrow-right"></i>
            </div> 
                <div id="back" class="btn" v-on:click="goToCollection">
                <i  class="fa-solid fa-arrow-left"></i>
                <p>Go Back</p>
            </div>  

       
        
    </div>
<pokemon-details v-bind:pokemon="this.offeredPokemon" v-if="this.offeredPokemon"/>
</div>

 
</template>

<script>
import PokemonDetails from '../components/PokemonDetails.vue'
import PokemonService from '../services/PokemonService.js'
import TradeService from '../services/TradeService.js'
export default {
    data() {
        return {
            name:"trade-view",
            pokemon: {},
            offeredPokemon: '',
            usersPokemon: []
        }
    },
     components: { PokemonDetails },
    created() {
        PokemonService.getPokemonByPokemonId(this.$route.params.id).then((response) => {
            this.pokemon = response.data
        }),
        PokemonService.getAllPokemonByUserId(this.$store.state.user.id).then((response) => {
            this.usersPokemon = response.data
        })
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
        TradeService.createTrade(this.pokemon.pokemonId, this.offeredPokemon.pokemonId);
        this.$router.push({ name: 'my-trades', params: {id: this.$store.state.user.id}});
        window.location.reload();
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
    border-radius:0 ;
    width: 350px;
    height:500px;
    overflow-y: auto;
    padding-top: 10px;
    justify-content: space-between;
}

#pokemon-container::-webkit-scrollbar {
    display: none;
}

#pokemon-container > img:hover{
    outline-style: solid;
    outline-color: #ffe019;
    outline-offset: -3px;
    cursor: pointer;
    border-radius: 5px
}
#pokemon-container > img {
    margin: 5px;
}

.selected {
    outline-style: solid;
    outline-color:#ffe019;
    outline-offset: -3px;
    outline-width: 3px;
    cursor: pointer;
    border-radius: 5px
}

.btn {
    display: flex;
    align-items: center;
    justify-content: space-evenly;
    justify-items: center;
    background-color: rgba(135,206,250,0.3);
    border-radius: 0px 0px 20px 20px;
    color: white;
    font-size: 20px;
    margin: 0px !important;
    font-family: 'Silkscreen', cursive;
}   

.btn:hover {
    cursor: pointer;
    background-color: rgba(135,206,250, 0.8);
}

#send {
    border-radius: 0px;
    background-color: #25c81fca;
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



</style>