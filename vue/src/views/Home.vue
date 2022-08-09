<template>
  <div class="home">
    <h1>My Collections</h1>
    <div class="myCollections">
    <collection-preview v-for="collection in collections" v-bind:key="collection.id" v-bind:collection="collection"/>
    <add-new-collection />
    </div>
    <h1>Browse All Collections</h1>
  </div>
</template>

<script>
import CollectionPreview from '../components/CollectionPreview.vue';
import AddNewCollection from '../components/AddNewCollection.vue';
import CollectionService from '../services/CollectionService'

export default {
  data() {
    return {
     collections : []
    }
  },
  components: { CollectionPreview, AddNewCollection},
  name: "home",
  created() {
    CollectionService.getCollectionsByUserId(this.$store.state.user.id).then((response) => {
      this.collections = response.data;
      console.log(response.data)
    })
  }
};
</script>

<style scoped>

h1 {
  color: white;
}
.myCollections {
  display: flex;
  flex-wrap: wrap;
  gap: 25px;
  align-items: center;
}


</style>