<template>
  <div id="main-container">
    <h3> Collection Statistics </h3>
    <div v-if="isLoaded" id="statistics-container">
      <div
        id="individual-statistic"
        v-for="(n, index) in typeList.length"
        v-bind:key="index"
      >
        <img class="stat-image" :src="images[index]" width="50px" />
        <h5>: {{ stats[typeList[index]] }}</h5>
      </div>
    </div>
  </div>
</template>

<script>
import PokemonService from "../services/PokemonService.js";

export default {
  data() {
    return {
      typeList: [
        "bug",
        "dark",
        "dragon",
        "electric",
        "fairy",
        "fighting",
        "fire",
        "flying",
        "ghost",
        "grass",
        "ground",
        "ice",
        "normal",
        "poison",
        "psychic",
        "rock",
        "steel",
        "water",
      ],
      stats: {},
      images: [],
      isLoaded: false,
    };
  },
  created() {
    PokemonService.getCollectionTypeStatistics(this.collectionId).then(
      (response) => {
        this.stats = response.data;
      }
    );
    for (let i = 0; i < this.typeList.length; i++) {
      this.images.push(this.$store.state.images[this.typeList[i]]);
    }
    this.isLoaded = true;
  },
  props: ["collectionId"],
};
</script>

<style scoped>
#main-container{
  display:flex;
  flex-direction: column;
  color: yellow;
  justify-content: center;
  align-items: center;
}
#statistics-container {
  display: flex;
  flex-direction: column;
  height: 400px;
  width: 400px;
  flex-wrap: wrap;
  background-color:rgba(0,0,0,0.5);
  align-items: center;
  justify-content: center;
  border-radius: 10px;
  color: yellow;
}
#individual-statistic {
  display: flex;
  align-items: center;
}
.stat-image {
  padding: 5px;
}
</style>