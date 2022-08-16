<template>
  <div id="view-export">
      <label>Export File</label>
      <textarea v-show="isLoaded" id="exported-collection" rows="20" cols="50" readonly></textarea>
      <button id="back-to-collection" v-on:click="goToCollection()" class="btn">Back to Collection</button>
  </div>
  
</template>

<script>
import PokemonService from '../services/PokemonService.js'

export default {
    data() {
        return {
            pokemon: [],
            isLoaded: false
        }
    },
    created() {
        PokemonService.getPokemonByCollectionId(this.$route.params.id).then(response => {
            this.pokemon = response.data;
            let exportText = document.getElementById("exported-collection")
            exportText.value = exportText.value + "Species,Level,Shiny,Notes \r\n";
            
            for(let i = 0; i<this.pokemon.length; i++){
                exportText.value = exportText.value + this.pokemon[i].species+","+this.pokemon[i].level+","+this.pokemon[i].isShiny+","+this.pokemon[i].notes+"\r\n";
            }
            this.isLoaded = true;
        })
    },
    methods: {
        goToCollection() {
            this.$router.push({name: "collection"})
        }
    }
}
</script>

<style scoped>
    label {
        color: yellow;
        font-size: 50px;
    }

    textarea {
        display: flex;
        align-items: center;
        margin: 50px;
        overflow-y: scroll;
        height: 100px;
        resize: none;
        width: 500px;
        height: 200px;
    }
    #back-to-collection {
        display: flex;
        align-items: center;
        margin: 50px;
    }

    #view-export {
        display: flex;
        align-items: center;
        flex-direction: column;
    }
</style>