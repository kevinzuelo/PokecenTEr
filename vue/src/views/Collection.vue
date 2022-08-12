<template>
<div id="grid">
  <add-pokemon class="add" />
    <div class="collection-container">
    <pokemon-preview v-for="poke in pokemon" v-bind:key="poke.pokemonId" v-bind:pokemon="poke" />
  </div>
  
</div>

</template>

<script>
import PokemonService from '../services/PokemonService.js'
import PokemonPreview from '../components/PokemonPreview.vue'
import AddPokemon from '../components/AddPokemon.vue'

export default {
  data() {
    return {
      pokemon: []
    }
  },
  components: {
    PokemonPreview,
    AddPokemon
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

</style>