<template>
  <div id="user">
    <img id="user-logo">
    <h2>Hi, User</h2>
      <div id="logout">
        <div>
          <i class="fa-solid fa-arrow-right-from-bracket fa-xl " />
        </div>
        <h4>Logout</h4>
      </div>
  </div>
</template>

<script>
import '@fortawesome/fontawesome-free/css/all.css'

export default {
    name: 'user-info',

}
</script>

<style>

#user-logo {
  background-color: grey;
  width: 50px;
  height:50px;
  border-radius: 50%;

}

h2 {
  margin-left: -30px
}
 
div#user {
  width: 350px;
   background-color: rgb(4, 4, 48);
   border-radius: 20px;
   display: flex;
   color: white;
   padding: .6em;
   align-items: center;
   justify-content: space-around;


 }

 #logout {
   align-items: center;
   justify-items: center;
   height: 50px
 }

 #logout div {
   text-align: center;
  
 }



</style>