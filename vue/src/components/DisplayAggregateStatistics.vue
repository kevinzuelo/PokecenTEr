<template> 
  <div class="statistics-container">
    <h1>Overall Statistics</h1> 
      <div id="all-type-statistics" v-for="(n, index) in this.typeList.length" v-bind:key="index">
        <h5><img :src="images[index]" width="50px"  /> : {{ userStats[typeList[index]] }}</h5>
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
      images: [],
      isLoaded: false
    }
  },
  props: ["user"],
  name: "aggregate-statistics",
   created(){
    CollectionService.getPokemonTypesByUserId(this.$store.state.user.id).then(response => {
      this.userStats = response.data;
    });
    for(let i = 0; i<this.typeList.length; i++){
      this.images.push(this.$store.state.images[this.typeList[i]])
    }
    this.isLoaded = true;
  }
}
</script>

<style scoped>
  .statistics-container {
  margin-top: 60px;
  background-color:rgba(0,0,0,0.5);
  padding: 30px;
  color: white;
  border-radius: 30px;
  display: flex;
  flex-wrap: wrap;
  flex-direction: row;
  gap: 15px;
  width: 80%;
  justify-content: center;
  }
  
  #all-type-statistics {
  display: flex;
  flex-wrap: wrap;
  flex-direction: row;
  gap: 3px;

  }

</style>