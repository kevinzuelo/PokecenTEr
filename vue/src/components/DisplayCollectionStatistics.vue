<template>
  <div v-if="isLoaded" id="statistics-container">
    <div id="individual-statistic" v-for="(n,index) in typeList.length" v-bind:key="index">
      <h5>{{ stats[typeList[index]] }}</h5>
      <img :src="images[index]" width="50px" />
    </div>
  </div>
</template>

<script>
import PokemonService from "../services/PokemonService.js"

export default {
  data(){
    return{
      typeList: ["bug","dark","dragon","electric","fairy","fighting","fire","flying","ghost","grass","ground","ice","normal","poison","psychic","rock","steel","water"],
      stats: {},
      images: [],
      isLoaded: false
    }
  },
  created(){
    PokemonService.getCollectionTypeStatistics(this.collectionId).then(response => {
      this.stats = response.data;
    });
    for(let i = 0; i<this.typeList.length; i++){
      this.images.push(this.$store.state.images[this.typeList[i]])
    }
    this.isLoaded = true;
  },
  props: ["collectionId"]
}
</script>

<style>
#statistics-container{
  display: flex;
  flex-direction: column;
}
</style>