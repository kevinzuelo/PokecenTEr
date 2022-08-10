<template>
  <div id="register-page" class="text-center">
    <img src="..\images\Pokecenter-logo.png" class="top-img"/>
    <div id="register">
    <form class="form-register" @submit.prevent="register">
      <h1 class="h3 mb-3 font-weight-normal">Create Account</h1>
      <div class="alert alert-danger" role="alert" v-if="registrationErrors">
        {{ registrationErrorMsg }}
      </div>
     
      <!-- <label for="username" class="sr-only">Username</label> -->
      <label for="username" >Username</label>
      <input
        type="text"
        id="username"
        class="form-control"
        placeholder="Username"
        v-model="user.username"
        required
        autofocus
      />
      <!-- <label for="password" class="sr-only">Password</label> -->
      <label for="password" >Password</label>
      <input
        type="password"
        id="password"
        class="form-control"
        placeholder="Password"
        v-model="user.password"
        required
      />
      <input
        type="password"
        id="confirmPassword"
        class="form-control"
        placeholder="Confirm Password"
        v-model="user.confirmPassword"
        required
      />
      <label for="email">Email</label>
      <input
        type="email"
        id="email"
        class="form-control"
        placeholder="Email"
        v-model="user.email"
        required
      />
      <label for="continent">Continent</label>
      <select
        name="continent"
        id="continent"
        class="form-control"
        v-model="user.continent"
      >
        <option value="North America">North America</option>
        <option value="South America">South America</option>
        <option value="Europe">Europe</option>
        <option value="Asia">Asia</option>
        <option value="Africa">Africa</option>
        <option value="Australia/Oceania">Australia/Oceania</option>
        <option value="Antarctica">Antarctica</option>
      </select>
      

      <h3>Choose your icon:</h3>
      <div class="icon-menu">
        <div class="icon-choice">
          <label for="pikachu"><img src="https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/25.png"></label>
          <input type="radio" id="pikachu" name="icon" v-model="user.iconUrl" value="https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/25.png">
        </div>

        <div class="icon-choice">
          <label for="bulbasaur"><img src="https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png"></label>
          <input type="radio" id="bulbasaur" name="icon" v-model="user.iconUrl" value="https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png">
        </div>

        <div class="icon-choice">
          <label for="charmander"><img src="https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/4.png"></label>
          <input type="radio" id="charmander" name="icon" v-model="user.iconUrl" value="https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/4.png">
        </div>

        <div class="icon-choice">
          <label for="squirtle"><img src="https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/7.png"></label>
          <input type="radio" id="squirtle" name="icon" v-model="user.iconUrl" value="https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/7.png">
        </div>

      </div >
      <div class="buttons">
      <button
        v-on:click="register()"
        class="btn btn-lg btn-primary btn-block"
        type="submit"
      >CREATE ACCOUNT</button>
      <button v-on:click="goToLogin()" class="btn">RETURN TO LOGIN</button>
      </div>
    </form>
    </div>
  </div>
</template>

<script>
import authService from "../services/AuthService";

export default {
  name: "register",
  data() {
    return {
      user: {
        username: "",
        password: "",
        confirmPassword: "",
        role: "user",
        email: "",
        continent: "",
        iconUrl: "",
      },
      registrationErrors: false,
      registrationErrorMsg: "There were problems registering this user.",
    };
  },
  methods: {
    register() {
      if (this.user.password != this.user.confirmPassword) {
        this.registrationErrors = true;
        this.registrationErrorMsg = "Password & Confirm Password do not match.";
      } else {
        authService
          .register(this.user)
          .then((response) => {
            if (response.status == 201) {
              this.$router.push({
                path: "/login",
                query: { registration: "success" },
              });
            }
          })
          .catch((error) => {
            const response = error.response;
            this.registrationErrors = true;
            if (response.status === 400) {
              this.registrationErrorMsg = "Bad Request: Validation Errors";
            }
          });
      }
    },
    goToLogin() {
      this.$router.push({name: "home"});
    },
    clearErrors() {
      this.registrationErrors = false;
      this.registrationErrorMsg = "There were problems registering this user.";
    },
  },
};
</script>

<style scoped>
.form-register {
  display: flex;
  flex-direction: column;
  min-height: 500px;

  background-color:rgba(0,0,0,0.5);
  padding: 20px 40px 50px 40px;
  border-radius: 20px;
  height: 50%;
  color: yellow;
  justify-content: space-evenly;
  gap: 5px;
}



#register {
  display: flex;
  justify-content: center;
  align-items: center;
  }

.btn {
  background-color: gray;
  color: #424347;
  font-weight: bold;
  padding: 10px;
  border-radius: 10px;
  font-size: 1.125em;
  text-align: center;
  margin: 5px;
}

input, select {
  height: 1.5em;
}

h1{
  text-align: center;
  font-size: 1.75em;
}

label {
  font-weight: bold;
}

.icon-menu {
  display: flex;
}
.icon-choice{
  display: flex;
  flex-direction: column;
  align-items: center;
}
  .top-img {
    text-align: center;
    height: 100px;
    width: 500px;
    position: static;
    top: 30px;
    margin: 30px;
  }

#register-page {
  display: flex;
  flex-direction: column;
  align-items: center;
}

</style>
