<template>
  <div id="register" class="text-center">
    <form class="form-register" @submit.prevent="register">
      <h1 class="h3 mb-3 font-weight-normal">Create Account</h1>
      <div class="alert alert-danger" role="alert" v-if="registrationErrors">
        {{ registrationErrorMsg }}
      </div>
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

      <label for="icon">Icon</label>
      <select name="icon" id="icon" class="form-control" v-model="user.iconUrl">
        <option
          value="https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/25.png"
        >
          Pikachu
        </option>
      </select>
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
  max-width: 600px;
  background-color: rgb(4, 4, 48);
  padding: 20px 40px 30px 40px;
  border-radius: 20px;
  height: 50%;
  color: yellow;
  justify-content: space-evenly;
  gap: 5px;
}

#register {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  height: 90vh;
}

.btn {
  background-color: gray;
  color: #424347;
  font-weight: bold;
  padding: 10px;
  border-radius: 10px;
  font-size: 1.125em;
  text-align: center;
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

</style>
