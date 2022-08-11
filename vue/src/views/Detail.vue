<template>
<pokemon-details v-bind:pokemon="this.pokemon" />
</template>


<script>
import PokemonDetails from '../components/PokemonDetails.vue'
import PokemonService from '../services/PokemonService.js'

export default {
    data() {
    return {
      pokemon: {}
    }
  },
  name: "detail",
  components: {
    PokemonDetails,
  },
      created() {
      PokemonService.getPokemonByPokemonId(this.$route.params.id).then((response) => {
        this.pokemon = response.data;
        console.log(this.pokemon);
      })
    }
};
</script>



<style scoped>
</style>
