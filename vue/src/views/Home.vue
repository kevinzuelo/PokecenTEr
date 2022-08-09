<template>
  <div class="home">
    <h1>My Collections</h1>
  
    <collection-preview />
    <h1>Browse All Collections</h1>
  </div>
</template>

<script>
import CollectionPreview from '../components/CollectionPreview.vue';
export default {
  components: { CollectionPreview },
  name: "home"
};
</script>

<style scoped>

h1 {
  color: white;
}

</style>