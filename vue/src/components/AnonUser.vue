<template>
  <div id="header">
    <img src="..\images\PokecenterUpdated.png" class="header-img" />
    <div id="anon-user">
      <router-link id="login" v-bind:to="{ name: 'login' }">
        <div id="login">
            <i class="fa-solid fa-door-open"></i>
            <br>
            <h4>Go to Login</h4>

        </div>
      </router-link>
    </div>
  </div>
</template>

<script>
import "@fortawesome/fontawesome-free/css/all.css";

export default {
  name: "anon-user",
  props: ["user"],
};
</script>

<style scoped>
#login {
  background-color: rgb(4, 4, 48, 0.5);
  color: white;
  border-radius: 0px 0px 0px 20px;
  display: flex;
  flex-direction: row;
  padding: 15px;
  align-items: center;
  justify-content: center;
  position: relative;
  top: -8px;
  right: -8px;
  width: 200px;
  height: 50px;
  gap: 10px;
}

#login div {
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

.fa-door-open {
  font-size: 1.5em;
}

</style>