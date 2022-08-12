<template>
  <div id="statistics-container">
    <div id="individual-statistic" v-for="(n,index) in this.typeList.length" v-bind:key="index">
      <h5>{{ typeList[index] }} : {{ stats[typeList[index]] }}</h5>
    </div>
  </div>
</template>

<script>
import PokemonService from "../services/PokemonService.js"

export default {
  data(){
    return{
      typeList: ["bug","dark","dragon","electric","fairy","fighting","fire","flying","ghost","grass","ground","ice","normal","poison","psychic","rock","steel","water"],
      stats: {}
    }
  },
  created(){
    PokemonService.getCollectionTypeStatistics(this.collectionId).then(response => {
      this.stats = response.data;
    })
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