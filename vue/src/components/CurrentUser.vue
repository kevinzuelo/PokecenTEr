<template>
<div id="header">
  <img src="..\images\PokecenterUpdated.png" class="header-img"/>
  <div id="user">
          <img id="icon" v-bind:src="require(`../images/Icons/${image}`)" v-if="isLoaded" >
          <div id="user-id">
    <h2>Hi, {{user.username}}</h2>
    <h3 v-if="user.authorities[0].name == 'ROLE_PREMIUM_USER'">PREMIUM</h3>
          </div>

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

// import image from "../images/Icons-04.png";

export default {
    name: 'user-info',
    props: ["user"],
    data() {
      return {
        image: '',
        isLoaded: false
      }
    },
    created () {
      this.image = this.user.iconUrl
      this.isLoaded = true;
    }

}
</script>

<style>

a {
  text-decoration: none;
  color: white;
}
#user-id > h2{
margin: 0px;
text-align: center;
color: white;
}

#user-id > h3{
  letter-spacing: 4px;
  font-size: 12px;
  background-color: rgb(21, 123, 206);
  padding: 3px 5px 3px 8px;
  border-radius: 4px;
  margin: 0px;
  bottom: 5px;
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
 #icon {
   width: 55px;
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
min-width: 250px;
}

#header {
  display: flex;
  flex-direction: row;
  justify-content: space-between;
}
h4 {
  margin-top: -1px;
}

@media (max-width: 600px) {
  div#user{
    flex-direction: column;
    max-height: 150px;
    justify-content: space-between;
  }
  #user-id {
    padding: 10px;
  }

}

</style>