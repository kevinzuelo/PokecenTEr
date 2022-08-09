<template>
  <div id="app">
    <div id="nav">
      <router-link v-bind:to="{ name: 'home' }">Home</router-link>&nbsp;|&nbsp;    
    </div>
    <router-view />
    <current-user v-if="$store.state.token != ''"/>
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
</style>