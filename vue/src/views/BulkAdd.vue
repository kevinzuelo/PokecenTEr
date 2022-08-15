<template>
  <div>
      <form>
          <label for="bulk-pokemon-input">Select .csv file for bulk add:</label>
          <input type="file" name="bulk-pokemon-input" id="bulk-pokemon-input" accept=".csv" />
          <button v-if="!isLoading" v-on:click.prevent="bulkAddPokemon()">Add Pokemon</button>
      </form>
      <textarea id="feedback" name="feedback" rows="20" cols="50" readonly />
      <button v-if="isLoading">Return Home</button>
  </div>
</template>

<script>
import PokemonService from '@/services/PokemonService.js'

export default {
    data() {
        return{
            isLoading: false,
            singlePokemon: {}
        }
    },
    methods: {
        bulkAddPokemon(){
            this.isLoading = true;
            let fr = new FileReader();
            let file = document.getElementById("bulk-pokemon-input").files[0];
            let textArea = document.getElementById("feedback");

            fr.addEventListener("loadend", function() {
                let fileContentArray = fr.result.split(/\r\n|\n/);
                for(let i=0; i<fileContentArray.length; i++){
                    let pokemonFields = fileContentArray[i].split(',');
                    this.singlePokemon = {
                        species: pokemonFields[0],
                        level: pokemonFields[1],
                        isShiny: pokemonFields[2],
                        notes: pokemonFields[3],
                        collectionId: 10
                    };

                    
                    PokemonService.addPokemon(this.singlePokemon).then(response => {
                        if(response.status == 201){
                            console.log(response.status);
                            textArea.value = textArea.value + "Success \r\n";
                        }
                    }).catch((error) =>{
                        textArea.value = textArea.value + "Error: " + error.response.data.message + "\r\n";
                    });
                }
            });

            fr.readAsText(file);
        }
    }
}
</script>

<style>
    textarea {
        overflow-y: scroll;
        height: 100px;
        resize: none;
    }
</style>