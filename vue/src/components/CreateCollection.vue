<template>
    <div>
        <h2>Create Collection</h2>
        <div class="alert alert-danger" role="alert" v-if="creationErrors">
            <p>{{ errorMessage }}</p>
        </div>
        <form id="create-collection-form">
            <label for="collection-name" >Collection Name</label>
            <input
                type="text"
                id="collection-name"
                placeholder="Collection Name"
                v-model="newCollection.name"
                required
            />

        <label for="is-private">Privacy Setting</label>
        <select name="is-private" id="is-private" class="form-control" v-model="newCollection.isPrivate">
            <option value="true">Private</option>
            <option value="false">Public</option>
        </select>

        <label for="game">Game</label>
        <input
            type="text"  
            id="game" 
            class="form-control" 
            v-model="newCollection.game"
            required
        />

        <button v-on:click.prevent="addNewCollection()">ADD COLLECTION</button>

      



        </form>
    </div>
    
</template>

<script>
import collectionService from "@/services/CollectionService"

export default {
    name: "create-collection",
    data() {
    return {
        errorMessage: "An error occurred",
        creationErrors: true,
        newCollection: {
            name: "",
            userId: "",
            isPrivate: "",
            game: ""
        },
    };
    },

    methods: {
        addNewCollection() {
            this.newCollection.userId = this.$store.state.user.id;
            
            collectionService.createCollection(this.newCollection)
          .then(response => {
            this.creationErrors = false;
            if (response.status === 201) {
              this.$router.push({name: "home"});
            }
          })
          .catch(error => {
            this.handleErrorResponse(error);
          });
        },

        handleErrorResponse(error) {
            alert(error.errorMessage)
                this.creationErrors=true;
            
        }

       

    }

}
</script>

<style>
    .form-register {
        display: flex;
        flex-direction: column;
        max-width: 600px;
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