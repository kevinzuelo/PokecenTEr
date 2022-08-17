<template>
  <div id="app">
    <router-link v-bind:to="{ name: 'home' }">
    
    <current-user v-if="$store.state.token != ''" v-bind:user="this.$store.state.user"/>
    <anon-user v-if="!isLoggedIn && areWeHome"/>
    </router-link>
    <div id="nav">
   
    </div>
    <router-view />
    
  </div>
</template>

<script>
import CurrentUser from '@/components/CurrentUser.vue'
import AnonUser from '@/components/AnonUser.vue'

export default {
  components: {
    CurrentUser,
    AnonUser
  },
      computed: {
        isLoggedIn() {
        return this.$store.state.token != "";
      },
        areWeHome() {
          return this.$route.name != "login";
        }
      }
}
</script>


<style>
@import url('https://fonts.googleapis.com/css2?family=Silkscreen&display=swap');
@import url('https://fonts.googleapis.com/css2?family=Rubik:ital,wght@0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,300;1,400;1,500;1,600;1,700;1,800;1,900&display=swap');
@import url('https://fonts.googleapis.com/css2?family=Carter+One&display=swap');
@import url('https://fonts.googleapis.com/css2?family=Changa+One&display=swap');
@import url('https://fonts.googleapis.com/css2?family=Lilita+One&display=swap');
@import url('https://fonts.googleapis.com/css2?family=Signika+Negative:wght@500&display=swap');


html {
  background-image: url('../src/images/Background2.png');
  background-repeat: no-repeat;
  background-attachment: fixed;
  height: 100%;
  /* font-family: 'Rubik', sans-serif; */
/* font-family: 'Carter One', cursive; */
font-family: 'Changa One', cursive;
/* font-family: 'Racing Sans One', cursive; */
  /* letter-spacing: 1px; */
  /* font-family: 'Lilita One', cursive; */
font-family: 'Signika Negative', sans-serif;
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
#all-trades {
/* display: flex;
flex-direction: column; */
align-items: left;
margin-left: auto;
margin-right: auto;
width: 100%;
margin: 20px;
position: relative;
bottom: 30px;
gap: 20px;
}

.trade-tables table {
  background-color: rgba(0,0,0,0.5);
  color: white;
  border: 2px solid dimgray;
  border-collapse: collapse;
  width: 90%;
  text-align: left;
  align-items: center;
  text-transform: capitalize;
  table-layout: fixed;
}

.trade-tables th {
  text-align: center;
  vertical-align: center;
  border: 1px solid white;
  height: 30px;
}

.trade-tables td {
  border: 1px solid white;
  border-collapse: collapse;
  text-align: center;
  align-items: center;
  width: 100px;
  vertical-align: center;
}
.trade-tables tr:hover {
   background-color:rgb(53, 53, 53);
   box-shadow: 0px 0px 20px 5px rgba(255, 0, 0, .45);
}

#flex-in-table-row{
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  padding-bottom: 15px;
}

.no-trades {
  color:red;
  font-family: 'Silkscreen', cursive;
}
</style>