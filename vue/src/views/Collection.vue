<template>
  <div class="collection-container">
    <pokemon-preview v-for="poke in pokemon" v-bind:key="poke.pokemonId" v-bind:pokemon="poke" />
  </div>
</template>

<script>
import PokemonService from '../services/PokemonService.js'
import PokemonPreview from '../components/PokemonPreview.vue'

export default {
  data() {
    return {
      pokemon: []
    }
  },
  components: {
    PokemonPreview
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