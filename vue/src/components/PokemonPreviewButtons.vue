<template>
  <div id="modify">
      <router-link :to="{ name: 'edit', params: { id: this.pokemon.pokemonId } }">
          <i id="edit" class="fa-solid fa-pen-to-square"></i>
      </router-link>
      <i id="delete" class= "fa-solid fa-trash-can" v-on:click="deleteAlert = true"></i>
      <i id="move" class="fa-solid fa-arrow-right"></i>
      <div id="alert" v-if="deleteAlert">
          <h3>Are you sure you want to delete this pokemon?</h3>
          <button id ="deleteButton" v-on:click="deletePoke" title="Delete">Yes</button>
          <button v-on:click="deleteAlert = false">No</button>
      </div>
  </div>
</template>

<script>
import PokemonService from '../services/PokemonService'
export default {
    data() {
        return{
            deleteAlert: false
        }
 
    },
    name: "pokemon-preview-buttons",
    props: ["pokemon"],
    methods: {
        deletePoke() {
            PokemonService.deletePokemon(this.pokemon.pokemonId);
            window.location.reload()
        }
    }    

}
</script>

<style scoped>

#alert {
    background-color: rgb(250, 110, 110);
    border-radius: 5px;
    position: absolute;
    width: 150px;
}
#alert button {
    background-color: rgb(250, 110, 110);
}

#alert button:hover {
    
    background-color: rgb(255, 152, 152);
}

#deleteButton:hover {
    background-color: red !important;
}

#deleteButton.tooltip {
    background-color: grey;
}

#modify {
    display: flex;
    justify-content: space-evenly;
    margin-top: 10px;
    
}

#modify i {
    cursor: pointer;
    color: rgba(0,0,0,0.6);
    
}

#modify i:hover {
    transform: scale(1.2);
}

#edit:hover{
    color: green;
}

#delete:hover {
    color: red
}

#move:hover {
    color: blue
}



</style>