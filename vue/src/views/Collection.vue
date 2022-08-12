<template>
<div id="grid">
  
  <div class="collection-container">
  <pokemon-preview v-for="poke in pokemon" v-bind:key="poke.pokemonId" v-bind:pokemon="poke" />
  </div>
  <display-collection-statistics v-bind:collectionId="this.$route.params.id" />
  <add-pokemon class="add" />
</div>

</template>

<script>
import PokemonService from '../services/PokemonService.js'
import PokemonPreview from '../components/PokemonPreview.vue'
import AddPokemon from '../components/AddPokemon.vue'
import DisplayCollectionStatistics from '../components/DisplayCollectionStatistics.vue'

export default {
  data() {
    return {
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
    created() {

      PokemonService.getPokemonByCollectionId(this.$route.params.id).then((response) => {
        this.pokemon = response.data;
        console.log(this.pokemon);
      })
    }
}
</script>

<style scoped>
.collection-container {
  background-color:rgba(0,0,0,0.5);
  padding: 30px;
  display: flex;
  flex-wrap: wrap;
  gap: 20px;
  width: 50%;
}

#grid {
  display: flex;
  align-items: center;
  margin: 60px 30px 30px 30px;
  gap: 25px;
}

</style>