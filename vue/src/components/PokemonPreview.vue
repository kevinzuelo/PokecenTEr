<template>
    <div class="pokemon-preview">
      <router-link :to="{ name: 'detail', params: { id: pokemon.pokemonId } }">
      <div>
        <h3 class="species-name">{{pokemon.species}}</h3>
        <h4 class="level">Lvl {{pokemon.level}}</h4>
        <img v-bind:src=pokemon.imgSprite />
        <br>
        <img class="type-image" v-for="type in typeArray" v-bind:key="type" v-bind:src="type" />
      </div>
      </router-link>
       
        <pokemon-modify-buttons v-bind:pokemon="this.pokemon" v-if="isMine" ></pokemon-modify-buttons>
        <trade-button v-else-if="!isMine && hasPokemonToTrade" v-bind:pokemon="this.pokemon"/>
     
    </div>

<!-- v-if="ownerId === $store.state.currentUser.userId" -->
</template>
<script>
import PokemonModifyButtons from './PokemonModifyButtons.vue';
import CollectionService from '@/services/CollectionService.js'
import TradeButton from './TradeButton.vue';
import PokemonService from '@/services/PokemonService.js'
export default {
  components: { PokemonModifyButtons, TradeButton },
    data() {
    return {
        typeArray: [],
        ownerId: "",
        hasPokemonToTrade: false
    }
    },
  name: "pokemon-preview",
  props: ["pokemon"],
  created() {
      this.typeArray = this.pokemon.type.split(" ")
      this.typeArray.pop();
      
      for(let i = 0; i<this.typeArray.length; i++){
        if(this.typeArray[i]=="bug"){
          this.typeArray[i]="https://static.wikia.nocookie.net/pokemon/images/6/64/Type_Bug.gif"
        }else if(this.typeArray[i]=="dark"){
          this.typeArray[i]="https://static.wikia.nocookie.net/pokemon/images/0/0d/Type_Dark.gif"
        }else if(this.typeArray[i]=="dragon"){
          this.typeArray[i]="https://static.wikia.nocookie.net/pokemon/images/2/26/Type_Dragon.gif"
        }else if(this.typeArray[i]=="electric"){
          this.typeArray[i]="https://static.wikia.nocookie.net/pokemon/images/a/aa/Type_Electric.gif"
        }else if(this.typeArray[i]=="fairy"){
          this.typeArray[i]="https://static.wikia.nocookie.net/pokemon/images/7/74/Type_Fairy.gif"
        }else if(this.typeArray[i]=="fighting"){
          this.typeArray[i]="https://static.wikia.nocookie.net/pokemon/images/6/6b/Type_Fighting.gif"
        }else if(this.typeArray[i]=="fire"){
          this.typeArray[i]="https://static.wikia.nocookie.net/pokemon/images/4/4d/Type_Fire.gif"
        }else if(this.typeArray[i]=="flying"){
          this.typeArray[i]="https://static.wikia.nocookie.net/pokemon/images/4/4b/Type_Flying.gif"
        }else if(this.typeArray[i]=="ghost"){
          this.typeArray[i]="https://static.wikia.nocookie.net/pokemon/images/f/f2/Type_Ghost.gif"
        }else if(this.typeArray[i]=="grass"){
          this.typeArray[i]="https://static.wikia.nocookie.net/pokemon/images/4/46/Type_Grass.gif"
        }else if(this.typeArray[i]=="ground"){
          this.typeArray[i]="https://static.wikia.nocookie.net/pokemon/images/1/1d/Type_Ground.gif"
        }else if(this.typeArray[i]=="ice"){
          this.typeArray[i]="https://static.wikia.nocookie.net/pokemon/images/8/84/Type_Ice.gif"
        }else if(this.typeArray[i]=="normal"){
          this.typeArray[i]="https://static.wikia.nocookie.net/pokemon/images/6/61/Type_Normal.gif"
        }else if(this.typeArray[i]=="poison"){
          this.typeArray[i]="https://static.wikia.nocookie.net/pokemon/images/8/82/Type_Poison.gif"
        }else if(this.typeArray[i]=="psychic"){
          this.typeArray[i]="https://static.wikia.nocookie.net/pokemon/images/6/65/Type_Psychic.gif"
        }else if(this.typeArray[i]=="rock"){
          this.typeArray[i]="https://static.wikia.nocookie.net/pokemon/images/b/b3/Type_Rock.gif"
        }else if(this.typeArray[i]=="steel"){
          this.typeArray[i]="https://static.wikia.nocookie.net/pokemon/images/a/ab/Type_Steel.gif"
        }else if(this.typeArray[i]=="water"){
          this.typeArray[i]="https://static.wikia.nocookie.net/pokemon/images/e/ed/Type_Water.gif"
        }
      }
      if (this.typeArray.length > 2) {
        this.typeArray.shift();
      }

    let collectionId = this.pokemon.collectionId;

    CollectionService.getCollectionByCollectionId(collectionId)
                      .then( (response) => {
                        
                        if(response.status === 200) {
                          console.log(response.data.userId)
                          this.ownerId = response.data.userId;
                        }
                      });
      PokemonService.getAllPokemonCountByUserId(this.$store.state.user.id).then((response) => {
        if(response.data) {
          this.hasPokemonToTrade = true;
        }
      })
    },


    computed: {
      
      isMine() {
        
        return this.$store.state.token != "" && (this.ownerId === this.$store.state.user.id);
      },


    },


  methods: {

    }
  }


</script>

<style scoped>
  .pokemon-preview {
    display: inline-block;
    background-color: lightskyblue;
    padding: 10px;
    text-align: center;
    border-radius: 4px;
    min-width: 115px;
  }

   .pokemon-preview:hover {
    transform: scale(1.025);  
  }



  .type-image {
    width: 50px;
    margin: 2px;
  }
  .species-name {
    font-size: .875em;
    text-transform: capitalize;
    background-color: #030b42;
    color:white;
    padding: 2px;
    border-radius: 30px;
    box-shadow: 0px 0px 7px red;
    font-family: 'Silkscreen', cursive;
  }

.level {
  font-size: .875em;
}

</style>