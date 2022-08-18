<template>
    <div id="viewContainer">
      <pokemon-details v-bind:pokemon="this.pokemon"/>
            <div id="middleIcons">
                <h1>Move Pokemon</h1>
                <div id="gif">
                    <img src="../images/pikachu-running.gif">
                </div>
                <div id="moveTo">
                    <i id="right" class="fa-solid fa-arrow-right move"></i>
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

<style scoped>

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
    border-radius: 20px;
    background-color: rgba(0,0,0,0.3);
    
}

h1 {
    background-color: rgba(0,0,0,0.5);
    padding: 20px;
    border-radius: 20px 20px 0px 0px;
    margin: 0px;
}
#middleIcons > div {
 margin: auto;
 
}

#gif {
    width: 140px;
}

#gif > img{
    width: 100%;
}



#moveTo {
    font-size: 50px;
    color: #ffe019;
    background-color: rgba(0,0,0,0.4);
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
    border-radius: 0px 0px 20px 20px;
    color: white;
    font-size: 20px;
    margin: 0px !important;
    font-family: 'Silkscreen', cursive;
}   

#back:hover {
    cursor: pointer;
    background-color: rgba(135,206,250, 0.8);
}

@media only screen and (max-width: 600px){
    #viewContainer{
        display: flex;
        flex-direction: column;
    }

    #right{
        transform: rotate(90deg);
    }
}



</style>