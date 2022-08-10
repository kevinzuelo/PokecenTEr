<template>
  <div id="app">
    <router-link v-bind:to="{ name: 'home' }">
    <current-user v-if="$store.state.token != ''" v-bind:user="this.$store.state.user"/>
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

button:hover {
  cursor: pointer;
  background-color: darkred;
}

button {
  background-color: #f20000;
  color: white;
  font-weight: bold;
  padding: 10px;
  border-radius: 10px;
  font-size: 1.125em;
  text-align: center;
  margin: 5px;
}

current-user {
  position: fixed;
  top: 0px;
}
</style>