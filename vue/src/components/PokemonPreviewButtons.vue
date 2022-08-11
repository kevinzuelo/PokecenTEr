<template>
  <div id="modify">
      <i id="edit" class="fa-solid fa-pen-to-square"></i>
      <i id="delete" class= "fa-solid fa-trash-can" v-on:click="deletePoke()"></i>
      <i id="move" class="fa-solid fa-arrow-right"></i>
  </div>
</template>

<script>
import PokemonService from '../services/PokemonService'
export default {
    name: "pokemon-preview-buttons",
    props: ["pokemon"],
    methods: {
        deletePoke() {
            PokemonService.deletePokemon(this.pokemon.pokemonId);
        }
    }    

}
</script>

<style>

#modify {
    display: flex;
    justify-content: space-evenly;
    margin-top: 10px;
    
}

#modify i {
    cursor: pointer;
    
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