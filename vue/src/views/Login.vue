<template>
  <div id="login" class="text-center">
    <img src="..\images\Pokecenter-logo.png" class="top-img"/>
    <p id="top-blurb">Welcome to PokecenTEr! 
      <br><br>This is a place for you to keep track of all of your Pokemon, and trade Pokemon with your friends. 
      <br><br><em>Gotta catch 'em all!</em>
    </p>
    
    <form class="form-signin" @submit.prevent="login">
      <h1 class="h3 mb-3 font-weight-normal">Please Sign In</h1>
      <div
        class="alert alert-danger"
        role="alert"
        v-if="invalidCredentials"
      >Invalid username and password!</div>
      <div
        class="alert alert-success"
        role="alert"
        v-if="this.$route.query.registration"
      >Thank you for registering, please sign in.</div>
      <label for="username" class="sr-only">Username</label>
      <input
        type="text"
        id="username"
        class="form-control"
        placeholder="Username"
        v-model="user.username"
        required
        autofocus
      />
      <label for="password" class="sr-only">Password</label>
      <input
        type="password"
        id="password"
        class="form-control"
        placeholder="Password"
        v-model="user.password"
        required
      />
      <!-- <router-link :to="{ name: 'register' }">Need an account?</router-link> -->
      <div id="button-row">
        <button type="submit">LOG IN</button>
        <button v-on:click="goToRegister()">REGISTER</button>
      </div>
    </form>
  </div>
</template>

<script>
import authService from "../services/AuthService";

export default {
  name: "login",
  components: {},
  data() {
    return {
      user: {
        username: "",
        password: ""
      },
      invalidCredentials: false
    };
  },
  methods: {
    login() {
      authService
        .login(this.user)
        .then(response => {
          if (response.status == 200) {
            this.$store.commit("SET_AUTH_TOKEN", response.data.token);
            this.$store.commit("SET_USER", response.data.user);
            this.$router.push("/");
          }
        })
        .catch(error => {
          const response = error.response;

          if (response.status === 401) {
            this.invalidCredentials = true;
          }
        });
    },

    goToRegister() {
      this.$router.push({ name: 'register' });
    }
  }
};
</script>

<style scoped>
  div#login {
    height: 90vh;
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
  }

  form.form-signin {
    display: flex;
    flex-direction: column;
    background-color:rgb(4, 4, 48);
    color: yellow;
    padding: 20px 40px 20px 40px;
    border-radius: 20px;
    gap: 10px;
  }

  #button-row{
    display: flex;
    justify-content: space-around;
  }

  router-link {
    color: yellow;
  }

  .top-img {
    max-height: 15%;
    position: fixed;
    top:40px;
  }

  #top-blurb {
    text-align: center;
    color: white;
    font-weight: bold;
    
  }

</style>
