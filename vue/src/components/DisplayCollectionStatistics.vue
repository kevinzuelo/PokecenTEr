<template>
  <div id="main-container">
    <h3>Collection Statistics</h3>
    <div v-if="isLoaded" id="statistics-container">
      <div
        id="individual-statistic"
        v-for="(n, index) in typeList.length"
        v-bind:key="index"
      >
        <img class="stat-image" :src="images[index]" width="50px" />
        <h5>: {{ stats[typeList[index]] }}</h5>
      </div>
      <h5 class="totals"><i class="fa-solid fa-star"></i> : {{ shinyStats }}  </h5>
      <h5 class="totals">
        <img src="..\images\pokeball.png" id="poke-ball-icon" /> :
        {{ totalCount }}
      </h5>
    </div>
  </div>
</template>

<script>
import PokemonService from "../services/PokemonService.js";
import CollectionService from "../services/CollectionService.js";

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
      shinyStats: 0,
      totalCount: 0,
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

    PokemonService.getNumberOfPokemonByCollectionId(this.collectionId).then(
      (response) => {
        this.totalCount = response.data;
      }
    );
    CollectionService.getShinyPokemonByCollectionId(this.collectionId).then(
      (response) => {
        this.shinyStats = response.data;
      }
    );
  },
  props: ["collectionId"],
};
</script>

<style scoped>
#main-container {
  display: flex;
  flex-direction: column;
  color: yellow;
  justify-content: center;
  align-items: center;
}
#statistics-container {
  max-height: 700px;
  max-width: 200px;
  display: flex;
  flex-direction: row;
  flex-wrap: wrap;
  background-color: rgba(0, 0, 0, 0.5);
  align-items: center;
  justify-content: center;
  border-radius: 10px;
  color: white;
}
#individual-statistic {
  display: flex;
  align-items: center;
}
.stat-image {
  padding: 10px;
}
#poke-ball-icon {
  height: 1.5em;
  
}
.fa-star {
  color: white;
  font-size: 1.5em;
}
.totals {
  padding: 10px;
}
</style>