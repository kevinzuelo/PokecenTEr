<template>
  <div>
      <textarea v-show="isLoaded" id="exported-collection" rows="20" cols="50" readonly></textarea>
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
    }
}
</script>

<style scoped>
    textarea {
        overflow-y: scroll;
        height: 100px;
        resize: none;
    }
</style>