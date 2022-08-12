<template>
  <div id="collection-flex">
      <collection-preview v-for="collection in collections" v-bind:key="collection.id" 
        v-bind:collection="collection" v-on:click.native="select(collection)" v-bind:class="{selected: selectedCollectionId == collection.collectionId}"/>
        <div id="confirmMove" v-if="this.selectedCollectionId">
            <h2>Confirm Move</h2>
            <h3>Are you sure you want to move your Pokemon to {{selectedCollection.name}} ?</h3>
            <button>Yes</button>
            <button>Cancel</button>
        </div>
  </div>
</template>

<script>
import CollectionPreview from '../components/CollectionPreview.vue';
import CollectionService from '../services/CollectionService.js'
export default {
    data() {
        return {
            collections: [],
            selectedCollectionId: 0,
            selectedCollection: {}
        }
    },
    name: 'move-pokemon-form',
    components: {CollectionPreview},
     created() {
    CollectionService.getCollectionsByUserId(this.$store.state.user.id).then((response) => {
      this.collections = response.data;
      console.log(response.data)
    })
  },
  methods: {
      select(collection) {
          this.selectedCollectionId = collection.collectionId
          this.selectedCollection = collection

      }
  }
}
</script>

<style>
#collection-flex {
    display: block;
    height: 800px;
    overflow-y: auto;
}

#collection-flex > div {
  margin: 10px;
}

#collection-flex > div:hover {
  outline: 9px solid rgb(255, 255, 129);
    outline-offset: -9px;
}

#confirmMove {
    background-color: rgba(135, 206, 250, 0.76);
    border-radius: 15px;
    color:black;
    width: 400px;
    height: 200px;
    position: absolute;
    top: 150px;
    left: 45%;
    text-align: center;
}

.selected {
     outline: 9px solid yellow;
    outline-offset: -9px;
}



#collection-flex::-webkit-scrollbar {
    display: none;
}


</style>