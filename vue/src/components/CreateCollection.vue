<template>
    <div>
        <h2>Create Collection</h2>
        <div class="alert alert-danger" role="alert" v-if="creationErrors">
        {{ errorMessage }}
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
        <select name="is-private" id="is-private" class="form-control" v-model="collection.isPrivate">
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

        <button v-on:click="addCollection()">ADD COLLECTION</button>

      



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
            isPrivate: "",
            game: ""
        },
    };
    },

    methods: {
        addCollection() {
            this.newCollection.userId = this.$store.state.currentUser.id;
            
            collectionService.addCollection(this.newCollection)
          .then(response => {
            this.creationErrors = false;
            if (response.status === 201) {
              this.$router.push('home');
            }
          })
          .catch(error => {
            this.handleErrorResponse(error);
          });
        },

        handleErrorResponse(error) {
            if(error){
                this.creationErrors=true;
            }
        }

    }

}
</script>

<style>

</style>