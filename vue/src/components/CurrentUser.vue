<template>
<div id="header">
  <img src="..\images\PokecenterUpdated.png" class="header-img"/>
  <div id="user">
    <img id="user-logo" v-bind:src="user.iconUrl">
    <h2>Hi, {{user.username}}</h2>
    <router-link id="logout" v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">
      <div id="logout">
        <div>
          <i class="fa-solid fa-arrow-right-from-bracket fa-xl " />
        </div>
      <h4>Logout</h4>
      </div>
      </router-link>
     </div> 
  </div>
</template>

<script>
import '@fortawesome/fontawesome-free/css/all.css'

export default {
    name: 'user-info',
    props: ["user"]

}
</script>

<style>

a {
  text-decoration: none;
  color: white;
}

#user-logo {
  width: 100px;
  
}

/* h2 {
  margin-left: -30px
} */
 
div#user {
  width: 35%;
   background-color: rgb(4, 4, 48, 0.5);
   border-radius: 0px 0px 0px 20px;
   display: flex;
   color: white;
   padding: .6em;
   align-items: center;
   justify-content: space-around;
   position:relative;
   top:-7px;
   right:-7px;
   max-height: 60px;
   max-width: 350px
 }

 #logout {
   align-items: center;
   justify-items: center;
   height: 50px
 }

 #logout div {
   text-align: center;
   }

.header-img {
  position: relative;
top: 0px;
left: 0px;
width: 35%;
height: 35%;
max-width: 400px;
}

#header {
  display: flex;
  flex-direction: row;
  justify-content: space-between;
}

</style>