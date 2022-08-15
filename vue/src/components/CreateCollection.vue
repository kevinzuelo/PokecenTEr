<template>
    <div class="create-collection-container">
        <h1>Create Collection</h1>
        <div class="alert alert-danger" role="alert" v-if="creationErrors">
            <p>{{ errorMessage }}</p>
        </div>
        <form id="create-collection-form">
            <label for="collection-name" >Collection Name</label>
            <input
                type="text"
                maxlength="30"
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

        <!-- <label for="game">Game</label>
        <input
            type="text"  
            id="game" 
            class="form-control" 
            v-model="newCollection.game"
            required
        /> -->

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
        creationErrors: false,
        newCollection: {
            name: "",
            userId: "",
            isPrivate: ""
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
    h1 {
        color: white;
          letter-spacing: 2px;
        text-align: center;
        align-content: center;
        justify-content: center;
    }

    #create-collection-form {
        display: flex;
        flex-direction: column;
        max-width: 600px;
        background-color:rgba(0,0,0,0.5);
        padding: 20px 40px 50px 40px;
        border-radius: 20px;
        color: #ffe019;
        justify-content: space-evenly;
        gap: 5px;
        min-height: 200px;
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

    .create-collection-container {
        height: 80vh;
        display: flex;
        flex-direction: column;
        align-items: center;
        justify-content: center;
    }
</style>