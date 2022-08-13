<template>
<div id="collection-grid">
  <div id="name">
    <input
      v-if="editingName"
      id="edit-name"
      type="text"
      v-model="collection.name"
      v-on:blur.prevent="editName()"
      v-on:keyup.enter="editName()"
      />
  
    <div id="collection-name" v-else>
      <h1>{{ collection.name }}</h1>
      <i id="edit" class="fa-solid fa-pen-to-square" v-on:click="editingName=true" ></i>
    </div>
  </div>

  <table id="collection-filter">
      <thead>
        <tr>
          <th>Pokemon</th>
          <th>Type</th>
          <th>Shiny?</th>
        </tr>
      </thead>
      <tbody>
        <tr>
          <td>
            <input type="text" v-model="filter.species" id="speciesFilter"  />
          </td>
          <td>
            <select v-model="filter.type" id="typeFilter">
                <option value="">All</option>
                <option value="bug">Bug</option>
                <option value="dark">Dark</option>
                <option value="dragon">Dragon</option>
                <option value="electric">Electric</option>
                <option value="fairy">Fairy</option>
                <option value="fighting">Fighting</option>
                <option value="fire">Fire</option>
                <option value="flying">Flying</option>
                <option value="ghost">Ghost</option>
                <option value="grass">Grass</option>
                <option value="ground">Ground</option>
                <option value="ice">Ice</option>
                <option value="normal">Normal</option>
                <option value="poison">Poison</option>
                <option value="psychic">Psychic</option>
                <option value="rock">Rock</option>
                <option value="steel">Steel</option>
                <option value="water">Water</option>
            </select>
          </td>
          <td>
            <select v-model="filter.shiny">
              <option value="">All</option>
              <option value="shiny">Shiny</option>
              <option value="notShiny">Not Shiny</option>
            </select>
          </td>
        </tr>
      </tbody>
  </table>






  <div id="collection-container">
    <pokemon-preview v-for="poke in filteredPokemon" v-bind:key="poke.pokemonId" v-bind:pokemon="poke" />
    <add-pokemon class="add" v-if="isMine" />
  </div>
  <display-collection-statistics id="stats" v-bind:collectionId="this.$route.params.id" />
  <button id="delete-button" v-on:click="deleteAlert = true" v-if="isMine" >
            <i class="fa-solid fa-trash-can"></i>
            <h3>Delete Collection</h3>
  </button>
  <router-link v-bind:to="{name: 'home'}" v-else>
    <button>Go to Login</button>
  </router-link>
  <button id="privacy-button" v-on:click.prevent="togglePrivacy()" v-if="isMine" >
    <img v-bind:src="privacyImage"/>
    <p>{{ collection.isPrivate ? "PRIVATE! click to make public" : "PUBLIC! click to make private"}}</p>
  </button>
  
  <button v-else>Browse Collections</button>
  
  <div id="delete-collection-alert" v-if="deleteAlert">
          <h3>Are you sure you want to delete this collection? All pokemon in collection will be released.</h3>
          <div id="alert-buttons">
            <button id ="deleteButton" v-on:click.prevent="deleteCollection" title="Delete">Yes</button>
            <button v-on:click="deleteAlert = false">No</button>
          </div>
      </div>
</div>

</template>

<script>
import PokemonService from '../services/PokemonService.js'
import PokemonPreview from '../components/PokemonPreview.vue'
import AddPokemon from '../components/AddPokemon.vue'
import DisplayCollectionStatistics from '../components/DisplayCollectionStatistics.vue'
import CollectionService from '@/services/CollectionService.js'


export default {
  data() {

    return {
      deleteAlert: false,
      filter: {
        species: "",
        type: "",
        shiny: ""
      },
      editingName: false,
      showCollection: false,
      pokemon: [],
      collection: {}
    }
  },
  components: {
    PokemonPreview,
    AddPokemon,
    DisplayCollectionStatistics
   
  },
  name: "collection",
  computed: {
    isMine(){
        return this.$store.state.token != "" && (this.collection.userId === this.$store.state.user.id);
    },

    filteredPokemon(){
      let filtered = this.pokemon;

      if(this.filter.species != ""){
        filtered = filtered.filter( (pokemon) => {
          return pokemon.species.startsWith(this.filter.species)
        });
      }

      if(this.filter.type != ""){
        filtered = filtered.filter( (pokemon) => {
          return pokemon.type.includes(this.filter.type);
        });
      }

      if(this.filter.shiny != ""){
        filtered = filtered.filter( (pokemon) => {
          
         let shininess = "";

         if(pokemon.isShiny){
           shininess = "shiny";
         }
         else{
           shininess = "notShiny";
         }

         return shininess === this.filter.shiny;

        });
      }
      
      return filtered;
    },
    privacyImage(){
      if(this.collection.isPrivate){
        return 'https://i.gifer.com/origin/28/2860d2d8c3a1e402e0fc8913cd92cd7a_w200.gif';
      }
      else{
        return this.$store.state.user.iconUrl;
      }
    }
  },

    methods: {
    
      editName() {

        CollectionService.updateCollection(this.collection)
                  .then( (response) => {
            alert(response.status)
            if(response.status === 200){
              this.editingName = false;
            }
                  
          });
      },

      togglePrivacy() {

        this.collection.isPrivate = !this.collection.isPrivate;


        CollectionService.updateCollection(this.collection)
                  .then( () => {

        });
      },

      deleteCollection(){
        CollectionService.deleteCollection(this.collection.collectionId)
                          .then( response => {
                            if(response.status === 200){
                              this.$router.push( { name: 'home'})
                            }
                          })
                            .catch( (error) => {
                              if(error){
                              this.errorMessage = "There was a problem deleting the collection."
                              }
                            });
                          }
      },
    

  created() {

      PokemonService.getPokemonByCollectionId(this.$route.params.id).then((response) => {
        this.pokemon = response.data;
        console.log(this.pokemon);
      });

      CollectionService.getCollectionByCollectionId(this.$route.params.id).then((response) => {
        this.collection = response.data;
      })
    }
  }
</script>

<style scoped>




#collection-grid {
  display: grid;
  
  grid-template-areas:
    "name filter stats"
    "collection-container collection-container stats"
    "delete-button privacy-button stats";
  grid-template-columns: 2fr 2fr 1fr;
  margin: 60px 30px 30px 30px;
  gap: 25px;
  width: 95%;
}

#collection-container {
  
  grid-area: collection-container;
  background-color:rgba(0,0,0,0.5);
  padding: 30px;
  display: flex;
  flex-wrap: wrap;
  gap: 20px;
  width: auto;
  height:auto;
  align-items: center;
}

#name {
  grid-area: name;
}

#delete-button {
  grid-area: delete-button;
  width: 30%;
  height: 125px;
  min-width: 175px;
  display: flex;
  flex-direction: column;
  align-items: center;
  border: 3px solid darkred;
}
.fa-trash-can {
  font-size: 1.5em;
}
#collection-filter {
  grid-area: filter;
  text-align: left;
  background-color: rgba(0,0,0,0.5);
  color: #ffe019;
  padding: 10px;
  max-height: 50px;
}

#privacy-button {
  grid-area: privacy-button;
  background-color: #030b42;
  display:flex;
  justify-content: center;
  align-items: center;
  gap: 20px;
  color: white;
  max-width: 400px;
  font-size: 1.5em;
  height: 125px;
  justify-self: right;
  border: 3px solid #000835;
}

#privacy-button:hover {
  background-color: rgba(135,206,250, 0.7);
  border: 3px solid rgba(135,206,250, 0.7);
}

#privacy-button img {
  height: 100px;
}
#stats {
  grid-area: stats;
}

#collection-name {
  display: flex;
  align-items: center;
  gap: 10px;
  font-size: 1.5em;
}

#edit-name {
  color: #ffe019;
  font-size: 1.5em;
  background-color: rgba(0,0,0,0.5);
}

#edit {
  color: white;
}

#delete-collection-alert {
  text-align: center;
  box-shadow: 0px 0px 20px 5px rgba(255, 0, 0, 0.5);
  border-radius: 10px;
  background-color: red;
  height: 200px;
  width: 400px;
  position: absolute;
  left: 0;
  right: 0;
  top: 0;
  bottom: 0;
  margin: auto;
}

div#alertbuttons {
  display: flex;
  justify-content: center;
}

  

</style>