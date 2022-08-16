<template>
  <div id="bulk-add-container">
      <form id="import-file-form">
          <label for="bulk-pokemon-input">Select .csv File To Import</label>
          <input type="file" name="bulk-pokemon-input" id="bulk-pokemon-input" accept=".csv" />
      </form>
      <textarea id="feedback" name="feedback" rows="20" cols="50" readonly />
      <button v-if="!isLoading" v-on:click.prevent="bulkAddPokemon()">Add Pokemon</button>
      <button id="back-home-button" v-on:click="returnHome()" v-else>Return To Collection</button>
  </div>
</template>

<script>
import PokemonService from '@/services/PokemonService.js'
import CollectionService from '@/services/CollectionService.js'

export default {
    created(){
        CollectionService.getCollectionByCollectionId(this.$route.params.id).then(response =>{
            this.currentCollection = response.data;
            console.log(this.$store.state.user.id);
            console.log(this.currentCollection.userId);
            if(this.$store.state.user.id != this.currentCollection.userId){
            this.$router.push({name: 'home'});
        }
        });
    },
    data() {
        return{
            isLoading: false,
            singlePokemon: {},
            currentCollection: {},
            collectionToAddId: Number(this.$route.params.id)
        }
    },
    methods: {
        bulkAddPokemon(){
            let idParam = this.$route.params.id;
            this.isLoading = true;
            let fr = new FileReader();
            let file = document.getElementById("bulk-pokemon-input").files[0];
            let textArea = document.getElementById("feedback");

            fr.addEventListener("loadend", function() {
                let fileContentArray = fr.result.split(/\r\n|\n/);
                for(let i=0; i<fileContentArray.length; i++){
                    if(i==0){
                        continue;
                    }
                    let pokemonFields = fileContentArray[i].split(',');
                    this.singlePokemon = {
                        species: pokemonFields[0],
                        level: pokemonFields[1],
                        isShiny: pokemonFields[2],
                        notes: pokemonFields[3],
                        collectionId: idParam
                    };
                    console.log(this.singlePokemon);
                    
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
        },
        returnHome(){
            this.$router.push({name: 'collection', params: {id: this.$route.params.id}})
        }
    }
}
</script>

<style scoped>
    textarea {
        overflow-y: scroll;
        height: 100px;
        resize: none;
    }
    #feedback {
        grid-area: "feedback";
    }
    #import-file-form {
        grid-area: "import-file-form";
        display: flex;
        flex-direction: column;
        align-items: center;
    }
    #back-home-button {
        grid-area: "back-home-button"
    }
    #bulk-add-container {
        display: flex;
        flex-direction: column;
        justify-content: center;
        align-items: center;
        background-color: rgba(0,0,0,0.5);
        color: #ffe019;
        width: 25%;
        border-radius: 30px;
        margin: auto;
    }
</style>