<template>
  <div id="app">
    <router-link v-bind:to="{ name: 'home' }">
    <current-user v-if="$store.state.token != ''"/>
    </router-link>
    <div id="nav">
   
    </div>
    <router-view />
    
  </div>
</template>

<script>
import CurrentUser from '@/components/CurrentUser.vue'

export default {
  components: {
    CurrentUser
  }
}
</script>


<style>
html {
  background-image: radial-gradient(#0f26b8, #030b42);
  height: 100%;
  font-family: Arial, Helvetica, sans-serif
}

current-user {
  position: fixed;
  top: 0px;
}
</style>