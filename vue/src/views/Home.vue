<template>
  <div class="home">
    <h1>{{ $store.state.user.username }}</h1>
    <display-aggregate-statistics />
    <h1>My Collections</h1>
    <div class="myCollections">
    <collection-preview-link v-for="collection in collections" v-bind:key="collection.id" v-bind:collection="collection"/>
    <add-new-collection id="add-new" />
    </div>
    <div id="browse-collections">
      <h1>Recent Collections</h1>
      <div id="recent-collections">
        <public-collection-preview v-for="collection in recentCollections" v-bind:key="collection.id" v-bind:collection="collection" />
      </div>
      <router-link v-bind:to="{ name: 'browse' }">
        <button>Browse All Collections</button>
      </router-link>
    </div>
    <update-user-status/>
  </div>
</template>

<script>
import CollectionPreviewLink from '../components/CollectionPreviewLink.vue';
import AddNewCollection from '../components/AddNewCollection.vue';
import CollectionService from '../services/CollectionService';
import DisplayAggregateStatistics from '../components/DisplayAggregateStatistics.vue';
import PublicCollectionPreview from '@/components/PublicCollectionPreview.vue';
import UpdateUserStatus from '../components/UpdateUserStatus.vue';

export default {
  data() {
    return {
     collections : [],
     recentCollections: []
    }
  },
  components: { CollectionPreviewLink, AddNewCollection, DisplayAggregateStatistics, PublicCollectionPreview, UpdateUserStatus},
  name: "home",
  created() {
    CollectionService.getCollectionsByUserId(this.$store.state.user.id).then((response) => {
      this.collections = response.data;
      console.log(response.data)
    })

    CollectionService.getRecentCollections().then(response =>{
      this.recentCollections = response.data;
    })
  }
};
</script>

<style scoped>

h1 {
  color: white;
  font-size: 2em;
}
.myCollections {
  display: flex;
  flex-wrap: wrap;
  gap: 25px;
  align-items: center;
  
}

#browse-collections {
  display: flex;
  flex-direction: column;
  align-items: center;
}

#recent-collections {
 display: flex;
 gap: 20px;
}

.home {
  margin: 50px;
}

#add-new:hover {
  box-shadow: 0px 0px 20px 5px rgba(255, 0, 0, 0.527);
}
</style>