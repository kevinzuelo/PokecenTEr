<template>
    <div id="viewContainer">
      <pokemon-details v-bind:pokemon="this.pokemon"/>
            <div id="middleIcons">
                <div id="moveTo">
                    <i  class="fa-solid fa-arrow-right move"></i>
                    <p>Select a new Collection</p>
                </div>           
                <div id="back" v-on:click="goToCollection">
                    <i  class="fa-solid fa-arrow-left"></i>
                    <p>Go Back</p>
                </div>           
            </div>
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
  methods: {
      goToCollection() {
      this.$router.push({ name: 'collection', params: {id: this.pokemon.collectionId}});
    }
  }
}

</script>

<style >
#viewContainer {
    display: flex;
    width: 100%;
    align-items: center;
}

#middleIcons {
    display: flex;
    flex-direction: column;
    gap: 15px;
    margin: 30px;
    padding: 10px;
}



#moveTo {
    font-size: 50px;
    color: yellow;
    background-color: rgba(0,0,0,0.5);
    height: auto;
    width: 80px;
    text-align: center;
    padding: 30px;
    border-radius: 10px;
    display: flex;
    flex-direction: column-reverse;
}

#moveTo p {
    font-size: 20px;
    
}

#back {
    display: flex;
    align-items: center;
    justify-content: space-evenly;
    justify-items: center;
    background-color: rgba(135,206,250,0.3);
    border-radius: 10px;
    color: white;
    font-size: 20px;
}   

#back:hover {
    cursor: pointer;
    background-color: rgba(135,206,250, 0.8);
}



</style>