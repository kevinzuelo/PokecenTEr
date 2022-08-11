<template>
<div id="add-pokemon-page">
  <div id="add-pokemon-form" v-if="showForm">
   
    <div id="addPokemon">
    <form class="form-add-pokemon" >
      <h1 class="h3 mb-3 font-weight-normal">Add Pokemon</h1>
      <div class="alert alert-danger" role="alert" v-if="registrationErrors">
        {{ registrationErrorMsg }}
      </div>
     
     
      <label for="species" >Species</label>
      <input
        type="text"
        id="species"
        class="form-control"
        placeholder="Species"
        v-model="newPokemon.species"
        v-on:blur="checkPokemonExist(newPokemon.species)"
        required
        autofocus
      />
      
      <label for="level" >Level</label>
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
        v-bind:checked="newPokemon.isShiny"
      
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
    <div id="add-pokemon-preview" v-if="newPokemon.species != ''">
    <p>{{ this.pokemonFeedback }}</p>
    <img v-bind:src="pokemonUrl" v-if="validPokemon" />
    </div>
  </div>
  <div id="add-more-pokemon" v-else>
    <h2>{{ newPokemon.species }} Successfully Added!!</h2>
    <img v-bind:src="pokemonUrl" />
    <button v-on:click.prevent="resetForm()">Add Another Pokemon</button>
    <button v-on:click="goToCollection()">Go to Collection</button>
  </div>

</div>

</template>

<script>

import pokemonService from '@/services/PokemonService.js'
import PokeAPIService from '@/services/PokeAPIService.js'


export default {
  name: "add-pokemon-form",
  data() {
    return {
      newPokemon: {
        species: "",
        level: "",
        isShiny: "",
        notes: "",
        collectionId: "",
      },
      showForm: true,
      registrationErrors: false,
      registrationErrorMsg: "There were problems adding the pokemon.",
      pokemonFeedback: "Invalid Pokemon",
      validPokemon: false,
      pokemonUrl: ""
    };
  },
  computed: {
    requiredFieldsValid() {
      let isValid = (this.validPokemon && this.newPokemon.level);
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
          this.registrationErrorMsg = "Bad Request: Validation Errors";
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
      PokeAPIService.getPokemon(pokeSpecies.toLowerCase()).then(response =>{
        this.pokemonFeedback = "Valid Pokemon";
        this.validPokemon = true;
        this.pokemonUrl = response.data.sprites.front_default;
      }).catch((error) => {
        console.log(error);
        if(pokeSpecies.toLowerCase()=="meowstic"){
            this.validPokemon = false;
            this.pokemonFeedback = "For Meowstic please indicate gender i.e. Mewostic-male or Meowstic-female";
          }else if(pokeSpecies.toLowerCase()=="nidoran"){
            this.validPokemon = false;
            this.pokemonFeedback = "For Nidoran please indicate gender i.e. Nidoran-m or Nidoran-f";
          }else{
            this.pokemonFeedback = "Invalid Pokemon";
            this.validPokemon = false;
          }
      })
    },
    resetForm() {
        this.newPokemon.species = "",
        this.newPokemon.level = "",
        this.newPokemon.isShiny = "",
        this.newPokemon.notes = "",
        this.showForm = true;
    }
  },
  created() {
      this.newPokemon.collectionId = this.$route.params.id;
  }
};




</script>

<style scoped>

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
  background-color: rgba(0,0,0,0.5);
  color: yellow;
  font-weight: bold;
  padding: 20px;
  border-radius: 20px;

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

</style>
