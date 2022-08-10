<template>
  <div id="add-pokemon-form" >
    <img src="..\images\Pokecenter-logo.png" class="top-img"/>
    <div id="register">
    <form class="form-add-pokemon" >
      <h1 class="h3 mb-3 font-weight-normal">Add Pokemon</h1>
      <div class="alert alert-danger" role="alert" v-if="registrationErrors">
        {{ registrationErrorMsg }}
      </div>
     
     
      <label for="species" >Species</label>
      <input
        type="text"
        id="species"
        class="form-control"
        placeholder="Species"
        v-model="newPokemon.species"
        required
        autofocus
      />
      
      <label for="level" >Level</label>
      <input
        type="range"
        id="level"
        min="1"
        max="100"
        class="form-control"
        v-model="newPokemon.level"
        required
      />

        <label for="isShiny" >Shiny</label>
      <input
        type="checkbox"
        id="isShiny"
        class="form-control"
        v-model="newPokemon.isShiny"
        required
      />

      <label for="notes">Notes</label>
      <textarea
        
        id="notes"
        class="form-control"
        placeholder="Notes"
        v-model="newPokemon.notes"
        required
      />

      <div class="buttons">
      <button
        v-on:click="addPokemon()"
        class="btn btn-lg btn-primary btn-block"
        type="submit"
      >CREATE ACCOUNT</button>
      <button v-on:click="goToCollection()" class="btn">CANCEL</button>
      </div>

    </form>
    </div>
  </div>
</template>

<script>


export default {
  name: "add-pokemon-form",
  data() {
    return {
      newPokemon: {
        species: "",
        level: "",
        isShiny: "",
        notes: "user",
        
      },
      registrationErrors: false,
      registrationErrorMsg: "There were problems adding the pokemon.",
    };
  },
  methods: {

      addPokemon() {
          
      }




    // register() {
    //   if (this.user.password != this.user.confirmPassword) {
    //     this.registrationErrors = true;
    //     this.registrationErrorMsg = "Password & Confirm Password do not match.";
    //   } else {
    //     authService
    //       .register(this.user)
    //       .then((response) => {
    //         if (response.status == 201) {
    //           this.$router.push({
    //             path: "/login",
    //             query: { registration: "success" },
    //           });
    //         }
    //       })
    //       .catch((error) => {
    //         const response = error.response;
    //         this.registrationErrors = true;
    //         if (response.status === 400) {
    //           this.registrationErrorMsg = "Bad Request: Validation Errors";
    //         }
    //       });
    //   }
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
