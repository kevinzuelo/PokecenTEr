<template>
  <div id="main-container">
      <div id="user-card">
          <img id="user-icon" v-bind:src="require(`../images/Icons/${user.iconUrl}`)" />
          <div id="name-location">
            <h3>{{ user.username }} </h3>
            <h3>{{ user.continent }}</h3>
          </div>
      </div>
      <div v-if="isLoaded" id="collection-previews">
          <collection-preview-link v-for="collection in this.collections" v-bind:key="collection.collectionId" v-bind:collection="collection"/>
      </div>
  </div>
</template>

<script>
import CollectionService from '../services/CollectionService.js'
import CollectionPreviewLink from '../components/CollectionPreviewLink.vue'

export default {
    components: {CollectionPreviewLink},
    props: ["user"],
    data() {
        return {
            collections: [],
            isLoaded: false
        }
    },
    methods: {

    },
    computed: {

    },
    created() {
        CollectionService.getCollectionsByUserId(this.user.id).then(response => {
            console.log(this.user.id);
            this.collections = response.data;
            this.isLoaded = true;
        });
    }
}
</script>

<style scoped>
#user-card {
    display: grid;
    grid-template-columns: 1fr 3fr;
    grid-template-areas:
    "user-icon name-location"
    "user-icon name-location";
    background-color:rgba(0,0,0,0.5);
    height:115px;
    align-items: center;
    color: white;
    border-radius: 10px;
    gap:10px;
}
#user-icon {
    grid-area: "user-icon";
}
#name-location{
    grid-area: "name-location";
}
#collection-previews{
    display:flex;
    width: 100%;
    overflow-x: auto;
    margin: 30px;
    padding: 10px;
    gap:10px;
}
#collection-previews::-webkit-scrollbar {
    display: none;
}
#main-container{
    display:flex;
    align-items: center;
    justify-content: center;
}
</style>