<template>
    <router-link v-bind:to="{ name: 'AddCollection' }">
        <button>
      Add New Collection
  </button>
  </router-link>

  
</template>

<script>
export default {
    name: 'add-New-Collection'
}
</script>

<style>
    button {
        max-height: 50px;
        margin: 25px;
        
    }
</style>