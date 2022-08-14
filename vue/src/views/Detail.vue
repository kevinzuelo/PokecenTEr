<template>
  <div>
    <div class="flexing">
    <pokemon-details v-bind:pokemon="pokemon" />
    <back-to-collection-button v-bind:pokemon="pokemon" v-bind:ownerId="ownerId" />
  </div>
  </div>
</template>


<script>
import BackToCollectionButton from "../components/BackToCollectionButton.vue";
import PokemonDetails from "../components/PokemonDetails.vue";
import PokemonService from "../services/PokemonService.js";

export default {
  
  data() {
    return {
      pokemon: {},
     
    };
  },
  name: "detail",
  components: {
    PokemonDetails,
    BackToCollectionButton,
  },
  created() {
    PokemonService.getPokemonByPokemonId(this.$route.params.id).then(
      (response) => {
        this.pokemon = response.data;
        console.log(this.pokemon);

      

      }
    );

    
  },
};
</script>



<style scoped>

.flexing {
display: flex;
justify-content: center;
align-items: center;
margin: 50px;
}

</style>
