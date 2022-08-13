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
@import url('https://fonts.googleapis.com/css2?family=Silkscreen&display=swap');
@import url('https://fonts.googleapis.com/css2?family=Rubik:ital,wght@0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,300;1,400;1,500;1,600;1,700;1,800;1,900&display=swap');


html {
  background-image: radial-gradient(#0b1c8a, #030b42);
  height: 100%;
  font-family: 'Rubik', sans-serif;
  
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
  font-family: 'Silkscreen', cursive;
}

current-user {
  position: fixed;
  top: 0px;
}
</style>