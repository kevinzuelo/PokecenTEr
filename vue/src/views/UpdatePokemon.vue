<template>
  <update-pokemon-form/>
</template>

<script>
import UpdatePokemonForm from '../components/UpdatePokemonForm.vue'


export default {
  components: { UpdatePokemonForm },
    name: 'update-pokemon'
}
   
</script>

<style scoped>

 @media (max-width: 600px) {

   #add-pokemon-page {
     justify-content: center ;
     align-items: center;

   }

   #add-pokemon-form{
     margin: auto;
     
   }
 }



</style>