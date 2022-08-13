<template>
  <div id="collection-flex">
      <collection-preview v-for="collection in collections" v-bind:key="collection.id" 
        v-bind:collection="collection" v-on:click.native="select(collection)" v-bind:class="{selected: selectedCollectionId == collection.collectionId}"/>
        <div id="confirmMove" v-if="this.selectedCollectionId">
            <h2>Confirm Move</h2>
            <h3>Are you sure you want to move your {{pokemon.species}} to your {{selectedCollection.name}} collection?</h3>
            <button v-on:click="movePokemon">Yes</button>
            <button v-on:click="reset">Cancel</button>
        </div>
  </div>
</template>

<script>
import CollectionPreview from '../components/CollectionPreview.vue';
import CollectionService from '../services/CollectionService.js'
import PokemonService from '../services/PokemonService';
export default {
    data() {
        return {
            collections: [],
            currentCollectionId: 0,
            selectedCollectionId: 0,
            selectedCollection: {},
            pokemon: {
            }
        }
    },
    name: 'move-pokemon-form',
    components: {CollectionPreview},
     created() {
    CollectionService.getCollectionsByUserId(this.$store.state.user.id).then((response) => {
      this.collections = response.data;
      console.log(response.data)
    }),
    PokemonService.getPokemonByPokemonId(this.$route.params.id).then((response) => {
      this.pokemon = response.data;
      this.currentCollectionId = response.data.collectionId
    })
  },
  methods: {
      select(collection) {
        if(this.selectedCollection != collection) {
          this.selectedCollectionId = collection.collectionId
          this.selectedCollection = collection;
          this.pokemon.collectionId = collection.collectionId
        }
        else {
          this.reset();
        }
                
      },
      movePokemon() {
        PokemonService.updatePokemon(this.pokemon).then((response) => {
          if (response.status === 200) {
                          this.showForm = false;
                          this.goToCollection();
        }
        })


      },
      goToCollection() {
      this.$router.push({ name: 'collection', params: {id: this.currentCollectionId}});
    },
    reset() {
          this.selectedCollectionId = 0;
          this.selectedCollection = {};
          this.pokemon.collectionId = this.currentCollectionId;
    }
  }
}
</script>

<style>
#collection-flex {
    display: block;
    height: 800px;
    overflow-y: auto;
    margin: 30px;
    padding: 10px;
}

#collection-flex > div {
  margin-bottom: 10px;
}

.collection-container:hover {
  outline: 9px solid rgb(255, 255, 129);
    outline-offset: -9px;
}

#confirmMove {
    background-color: rgba(135, 206, 250, 0.9);
    border-radius: 15px;
    color:black;
    width: 350px;
    height: 200px;
    position: absolute;
    top: 300px;
    text-align: center;
    right: auto;
    padding-bottom: 10px;
}

.selected {
     outline: 9px solid yellow;
    outline-offset: -9px;
}



#collection-flex::-webkit-scrollbar {
    display: none;
}


</style>