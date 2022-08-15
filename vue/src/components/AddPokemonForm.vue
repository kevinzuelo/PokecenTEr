<template>
<div id="add-pokemon-page">
  <div id="add-pokemon-form" v-if="showForm">
   
    <div id="addPokemon">
    <form class="form-add-pokemon" >
      <h1 class="h3 mb-3 font-weight-normal">Add Pokemon</h1>
      <div id="error-message" class="alert alert-danger" role="alert" v-if="registrationErrors">
        {{ registrationErrorMsg }}
      </div>
     
     
      <label for="species" >Species</label>
      <input
        type="text"
        id="species"
        class="form-control"
        placeholder="Species"
        v-model="newPokemon.species"
        v-on:keyup="similarPokemonNames(newPokemon.species)"
        required
        autofocus
      />
      
      <label for="level" >Level</label>
      <p class='invalid-message' v-if='newPokemon.level < 1 || newPokemon.level > 100'>Pokemon level must be between 1 and 100</p>
      <input
        type="number"
        id="level"
        min="1"
        max="100"
        class="form-control"
        v-model="newPokemon.level"
        required
      />

    <div id="shiny-checkbox">
        <label for="isShiny" >Shiny? </label>
      <input
        type="checkbox"
        id="isShiny"
        class="form-control"
        v-model="newPokemon.isShiny"
        v-on:change="checkPokemonExist(newPokemon.species)"
       
      
      />
    </div>

      <label for="notes">Notes</label>
      <textarea
        
        id="notes"
        placeholder="Notes"
        maxlength="100"
        v-model="newPokemon.notes"
        />
      

      <div class="buttons">
      <button
        v-on:click.prevent="addPokemon()"
        class="btn btn-lg btn-primary btn-block"
        type="submit"
        v-bind:disabled="!requiredFieldsValid"
        v-bind:class="{ 'dead-button': !requiredFieldsValid }"
      >ADD POKEMON</button>
      <button v-on:click="goToCollection()" class="btn">CANCEL</button>
      </div>

    </form>
    </div>
    <div id="add-pokemon-preview">
    <p>{{ this.pokemonFeedback }}</p>
    <img v-bind:src="pokemonUrl" v-if="validPokemon" width="200px" />
    <img v-else src="https://i.gifer.com/origin/28/2860d2d8c3a1e402e0fc8913cd92cd7a_w200.gif" />
    </div>
  </div>
  <div id="add-more-pokemon" v-else>
    <h2>{{ newPokemon.species }} Successfully Added!!</h2>
    <img v-bind:src="pokemonUrl" />
    <button v-on:click.prevent="resetForm()">Add Another Pokemon</button>
    <button v-on:click="goToCollection()">Go to Collection</button>
  </div>
  <div v-if="this.giveSuggestions && !this.validPokemon" id="instructions">
    <h5> Similar pokemon names:</h5>
    <h5 v-for="suggestion in this.suggestions" v-bind:key="suggestion">{{ suggestion }},</h5>
  </div>
</div>

</template>

<script>

import pokemonService from '@/services/PokemonService.js'
import PokeAPIService from '@/services/PokeAPIService.js'
import PokedexService from '@/services/PokedexService.js'


export default {
  name: "add-pokemon-form",
  data() {
    return {
     
      newPokemon: {
        species: "",
        level: 1,
        isShiny: false,
        notes: "",
        collectionId: "",
      },
      showForm: true,
      registrationErrors: false,
      registrationErrorMsg: "There were problems adding the pokemon.",
      pokemonFeedback: "Enter Valid Species",
      validPokemon: false,
      pokemonUrl: "",
      giveSuggestions: false,
      suggestions: []
    };
  },
  computed: {
    requiredFieldsValid() {
      let isValid = (this.validPokemon && this.newPokemon.level >=1 && this.newPokemon.level <=100);
      return isValid;
    }
  },
  methods: {
  addPokemon() {
      pokemonService.addPokemon(this.newPokemon)
                    .then( (response)=> {
                       
                        if (response.status === 201) {
                          this.showForm = false;
        }
      })
      .catch((error) => {
        const response = error.response;
        this.registrationErrors = true;
          
         
         
        if (response.status == 400) {
         
          this.registrationErrorMsg = response.data.message;
        }
      });
                  
    },
    goToCollection() {
      this.$router.push({ name: 'collection', params: {id: this.$route.params.id}});
    },
    clearErrors() {
      this.registrationErrors = false;
      this.registrationErrorMsg = "There were problems registering this user.";
    },
    checkPokemonExist(pokeSpecies){
      this.validPokemon = false;
      PokeAPIService.getPokemon(pokeSpecies.toLowerCase()).then(response =>{
        this.pokemonFeedback = "Preview";
        this.validPokemon = true;
        if(this.newPokemon.isShiny){
          this.pokemonUrl = response.data.sprites.front_shiny;
        }else{
          this.pokemonUrl = response.data.sprites.front_default;
        }
      }).catch((error) => {
        console.log(error);
        // if(pokeSpecies.toLowerCase()=="meowstic"){
        //     this.validPokemon = false;
        //     this.pokemonFeedback = "Enter Valid Species";
        //   }else if(pokeSpecies.toLowerCase()=="nidoran"){
        //     this.validPokemon = false;
        //     this.pokemonFeedback = "Enter Valid Species";
        //   }else{
        //     this.pokemonFeedback = "Enter Valid Species";
        //     this.validPokemon = false;
        //   }
      })
    },
    resetForm() {
        this.newPokemon.species = "",
        this.newPokemon.level = "",
        this.newPokemon.isShiny = "",
        this.newPokemon.notes = "",
        this.showForm = true;
    },
    similarPokemonNames() {
      if(this.newPokemon.species.length>0){
        this.giveSuggestions = true;
        if(this.newPokemon.species.length>11){
          this.suggestions = ["Try with a shorter name."];
          return;
        }
        PokedexService.getSimilarPokemonNames(this.newPokemon.species).then(response => {
          this.suggestions = response.data;
        });
        this.checkPokemonExist(this.newPokemon.species);
      }
    }
  },
  created() {
      this.newPokemon.collectionId = this.$route.params.id;
  }
};




</script>

<style scoped>

.invalid-message {
  color: red;
}

#error-message {
  width: 300px;
  color: red;
  text-align: center;
  padding: 5px;
  border-radius: 10px;
  font-weight: bold;
}

.dead-button {
  background-color: grey;

}

.dead-button:hover {
  cursor: default;
}

#add-pokemon-page {
  display: flex;
  flex-direction: column;
  align-items: center;
}

#add-more-pokemon {
  display: flex;
  align-items: center;
  flex-direction: column;
  background-color: rgba(0,0,0,0.5);
  color: yellow;
  font-weight: bold;
  padding: 20px;
  border-radius: 20px;
  width: 500px;
  
}

#add-pokemon-form {
  display: flex;
  align-items: center;
  gap: 50px;
  justify-content: center;
  
}

#add-pokemon-preview {
  display: flex;
  flex-direction: column;
  background-color: rgba(0,0,0,0.5);
  color: yellow;
  font-weight: bold;
  padding: 20px;
  border-radius: 20px;
  align-items: center;
  width: 150px;
}

#shiny-checkbox {
    display: flex;
    align-items: center;
}

.form-add-pokemon {
  display: flex;
  flex-direction: column;
  min-height: 500px;

  background-color:rgba(0,0,0,0.5);
  padding: 20px 40px 50px 40px;
  border-radius: 20px;
  height: 50%;
  color: yellow;
  justify-content: space-evenly;
  gap: 5px;
}



#addPokemon {
  display: flex;
  justify-content: center;
  align-items: center;
  }



input, select {
  height: 1.5em;
}

h1{
  text-align: center;
  font-size: 1.75em;
}

label {
  font-weight: bold;
}

.icon-menu {
  display: flex;
}
.icon-choice{
  display: flex;
  flex-direction: column;
  align-items: center;
}
  .top-img {
    text-align: center;
    height: 100px;
    width: 500px;
    position: static;
    top: 30px;
    margin: 30px;
  }

#register-page {
  display: flex;
  flex-direction: column;
  align-items: center;
}

#instructions {
  color: yellow;
  text-align: center;
  display: flex;
}
#instructions>h5 {
  padding: 3px;
}
</style>
