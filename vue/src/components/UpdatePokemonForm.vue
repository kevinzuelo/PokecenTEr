<template>
<div id="add-pokemon-page">
   <pokemon-details v-bind:pokemon="updatedPokemon"/>
  <div id="add-pokemon-form" v-if="showForm">
   
    <div id="addPokemon">
    <form class="form-add-pokemon" >
      <h1 class="h3 mb-3 font-weight-normal">Update Pokemon</h1>
      <div id="error-message" class="alert alert-danger" role="alert" v-if="registrationErrors">
        {{ registrationErrorMsg }}
      </div>
     
      <label for="level" >Level</label>
      <p class='invalid-message' v-if='updatedPokemon.level < 1 || updatedPokemon.level > 100'>Pokemon level must be between 1 and 100</p>
      <input
        type="number"
        id="level"
        min="1"
        max="100"
        class="form-control"
        v-model="updatedPokemon.level"
      />

    <div id="shiny-checkbox">
        <label for="isShiny" >Shiny? </label>
      <input
        type="checkbox"
        id="isShiny"
        class="form-control"
        v-model="updatedPokemon.isShiny"
      
      />
    </div>

      <label for="notes">Notes</label>
      <textarea
        
        id="notes"
        placeholder="Notes"
        maxlength="100"
        v-model="updatedPokemon.notes"
        />      

      <div class="buttons">
      <button
        v-on:click.prevent="addPokemon()"
        class="btn btn-lg btn-primary btn-block"
        type="submit"
        v-bind:disabled="!requiredFieldsValid"
        v-bind:class="{ 'dead-button': !requiredFieldsValid }"
      >UPDATE POKEMON</button>
      <button v-on:click.prevent="goToCollection" class="btn">CANCEL</button>
      </div>

    </form>
    </div>
  </div>

</div>

</template>

<script>

import PokemonService from '../services/PokemonService';
import PokemonDetails from './PokemonDetails.vue';

export default {
  components: { PokemonDetails },
  name: "update-pokemon-form",
  data() {
    return {
        
        updatedPokemon: {
        level: 1,
        isShiny: '',
        notes: '',
        collectionId: 1
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
      if (this.updatedPokemon.level >= 1 && this.updatedPokemon.level <= 100) {
        
        return true;
      }
      return  false;
    }
  },
  created() {
        PokemonService.getPokemonByPokemonId(this.$route.params.id).then((response) => {
            this.updatedPokemon = response.data;
        })
  },

  methods: {
  addPokemon() {
      PokemonService.updatePokemon(this.updatedPokemon)
                    .then( (response)=> {
                       
                        if (response.status === 200) {
                          this.showForm = false;
                          this.goToCollection();
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
      this.$router.push({ name: 'collection', params: {id: this.updatedPokemon.collectionId}});
    },
    clearErrors() {
      this.registrationErrors = false;
      this.registrationErrorMsg = "There were problems updating this pokemon.";
    },
    resetForm() {
        this.newPokemon.level = "",
        this.newPokemon.isShiny = "",
        this.newPokemon.notes = "",
        this.showForm = true;
    }
  }
}




</script>

<style scoped>

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

.invalid-message {
  color: red;
}

#add-pokemon-page {
  display: flex;
  flex-direction: row;
  width: 100%;
  justify-content: center;
}

#add-more-pokemon {
  display: flex;
  align-items: center;
  flex-direction: column;
  background-color: rgba(0,0,0,0.5);
  color: #ffe019;
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
  color: #ffe019;
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
  color: #ffe019;
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

@media (max-width: 600px) {
  #add-pokemon-page {
    display: flex;
    flex-direction: column;
  }
}

</style>
