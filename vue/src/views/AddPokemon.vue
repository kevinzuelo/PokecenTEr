<template>
  <add-pokemon-form />
</template>

<script>
import AddPokemonForm from '../components/AddPokemonForm.vue'


export default {
  components: { AddPokemonForm },
    name: 'add-pokemon'
}
   
</script>

<style>
@media only screen and (max-width: 600px) {
  #add-pokemon-form {
    flex-direction: column;
    width: 300px;
    
  }

  .form-add-pokemon {
    width: 100% !important;
  }

  #add-pokemon-preview {
    order: 1;
    width: 300px !important;
    padding: 20px 0px 20px 0px !important;
  }

  #addPokemon {
    order: 2;
    width: 100% !important;
    
  }

  .buttons {
    margin: 10px;
    gap: 10px

  }
}

</style>