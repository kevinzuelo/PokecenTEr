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
    <add-pokemon class="add" />
  </div>
  <display-collection-statistics id="stats" v-bind:collectionId="this.$route.params.id" />
  <button id="delete-button">
            <i class="fa-solid fa-trash-can"></i>
            <h3>Delete Collection</h3>
  </button>
  <button id="privacy-button" v-on:click.prevent="togglePrivacy()">
    <img v-bind:src="privacyImage"/>
    <p>{{ collection.isPrivate ? "PRIVATE" : "PUBLIC"}}</p>
  </button>
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
}

#name {
  grid-area: name;
}

#delete-button {
  grid-area: delete-button;
  width: 60%;
  min-width: 150px;
}
#collection-filter {
  grid-area: filter;
  text-align: left;
  background-color: rgba(0,0,0,0.5);
  color: yellow;
  padding: 10px;
  max-height: 50px;
}

#privacy-button {
  grid-area: privacy-button;
  background-color: rgb(0, 128, 122);
  display:flex;
  justify-content: center;
  align-items: center;
  gap: 20px;
  color: yellow;
}

#privacy-button:hover {
  background-color: rgba(0, 128, 85, 0.548);
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
  color: yellow;
  font-size: 1.5em;
  background-color: rgba(0,0,0,0.5);
}

#edit {
  color: white;
}


  

</style>