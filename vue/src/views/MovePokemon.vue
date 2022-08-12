<template>
    <div id="viewContainer">
      <pokemon-details v-bind:pokemon="this.pokemon"/>
            <i id="moveTo" class="fa-solid fa-arrow-right move"></i>
      <move-pokemon-form/>
    </div>
</template>

<script>
import MovePokemonForm from '../components/MovePokemonForm.vue'
import PokemonDetails from '../components/PokemonDetails.vue'
import PokemonService from '../services/PokemonService.js'

export default {
    data() {
        return {
            pokemon: {}
        }

    },
    components: {MovePokemonForm, PokemonDetails, },
    name: 'move-pokemon',
      created() {
        PokemonService.getPokemonByPokemonId(this.$route.params.id).then((response) => {
            this.pokemon = response.data;
        })
  },
}

</script>

<style >
#viewContainer {
    display: flex;
    width: 100%;
    justify-content: space-around;
    align-items: center;
}

#moveArrow {
    color: yellow;
    background-color: rgba(0,0,0,0.5);
    width: 170px;
    height: 170px;
    border-radius: 10px;
    display: flex;
    justify-content: center;
    align-content: center;
}

i#moveTo {
    font-size: 80px;
    color: yellow;
    background-color: rgba(0,0,0,0.5);
    height: 80px;
    width: 80px;
    text-align: center;
    padding: 30px;
    border-radius: 10px;

}



</style>