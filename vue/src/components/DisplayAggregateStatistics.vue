<template>
  <div class="statistics-container">
    <h1>Aggregate Statistics</h1>
    <div id="all-type-statistic" v-for="(n,index) in this.typeList.length" v-bind:key="index">
      <h5>{{ typeList[index] }} : {{ userStats[typeList[index]] }}</h5>
    </div>
  </div>
</template>

<script>
import CollectionService from "../services/CollectionService.js"

export default {
  data() {
    return {
      typeList: ["bug","dark","dragon","electric","fairy","fighting","fire","flying","ghost","grass","ground","ice","normal","poison","psychic","rock","steel","water"],
      userStats: {},
    }
  },
  props: ["user"],
  name: "aggregate-statistics",
  created() {
    CollectionService.getPokemonTypesByUserId(this.$store.state.user.id).then(response => {
      this.userStats = response.data;
      console.log(this.userStats);
    });
  }
}
</script>

<style scoped>
  .statistics-container {
  background-color:rgba(0,0,0,0.5);
  padding: 30px;
  display: flex;
  flex-wrap: wrap;
  gap: 20px;
  width: 50%;
  color: white;
  }
</style>