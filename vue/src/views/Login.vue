<template>
  <div id="login" class="text-center">
    <img src="..\images\PokecenterUpdated.png" class="top-img"/>
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
      <div id="button-row">
        <button type="submit">LOG IN</button>
        <button v-on:click="goToRegister()">REGISTER</button>
      </div>
    </form>
    <br>
    <h3><em>Not ready to make an account?</em> Browse some recent public collections:</h3>
    <div id="recent-collections">
      <public-collection-preview class="col-prevs" v-for="collection in recentCollections" v-bind:key="collection.id" v-bind:collection="collection" />
    </div>
    <router-link v-bind:to="{ name: 'browse' }">
      <button class="browse-all-collections-button">Browse All Collections</button>
    </router-link>
  </div>
</template>

<script>
import authService from "../services/AuthService";
import collectionService from "../services/CollectionService";
import PublicCollectionPreview from "@/components/PublicCollectionPreview.vue"

export default {
  name: "login",
  components: { PublicCollectionPreview },
  data() {
    return {
      user: {
        username: "",
        password: ""
      },
      invalidCredentials: false,
      recentCollections: []
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
  },
  created() {
    collectionService.getRecentCollections().then(response =>{
      this.recentCollections = response.data;
    })
  }
};
</script>

<style scoped>
  div#recent-collections{
    display:flex;
    gap:20px;
  }

  div#login {
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;

    
  }

  form.form-signin {
    display: flex;
    flex-direction: column;
    background-color:rgb(4, 4, 48);
    color: #ffe019;
    padding: 10px 40px 20px 40px;
    border-radius: 10px;
    gap: 10px;
    width: 300px;
  }

  #button-row{
    display: flex;
    justify-content: space-evenly;
  }

  router-link {
    color: #ffe019;
  }

  .top-img {
    top: 0;
    max-width: 40%;
    min-width: 400px
  }

  #top-blurb {
    text-align: center;
    color: white;
    font-weight: bold;
    font-size: 1.25em;
  }
  h3 {
    text-align: center;
    color: white;
    font-weight: bold;
    margin: 30px;
    top: 580px;
  }

  #recent-collections {
    margin: 30px;
  }
  .browse-all-collections-button {
    text-align: center;
    margin-bottom: 40px;
    right: 0;
    left: 0;
    margin-left: auto;
    margin-right: auto;
    width: 400px;
    
  }

  @media only screen and (max-width: 600px){
    #recent-collections{
      flex-direction:column;
      gap:10px;
    }
  }
</style>
