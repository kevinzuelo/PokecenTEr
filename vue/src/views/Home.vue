<template>
  <div class="home">
    <h1>My Collections</h1>
    <div class="myCollections">
    <collection-preview />
    <add-new-collection />
    </div>
    <h1>Browse All Collections</h1>
  </div>
</template>

<script>
import CollectionPreview from '../components/CollectionPreview.vue';
import AddNewCollection from '../components/AddNewCollection.vue';

export default {
  components: { CollectionPreview, AddNewCollection },
  name: "home"
};
</script>

<style scoped>

h1 {
  color: white;
}
.myCollections {
  display: flex;
  gap: 25px;
  align-items: center;
}


</style>