<template>
  <div class="outer-statistics-container">
    <h1>Statistics for all your collections</h1>
    <div class="inner-statistics-container">
      <div
        id="all-type-statistics"
        v-for="(n, index) in this.typeList.length"
        v-bind:key="index"
      >
        <h5>
          <img :src="images[index]" width="50px" /> :
          {{ userStats[typeList[index]] }}
        </h5>
      </div>
      <br />
      <h5><i class="fa-solid fa-star"></i> shiny : {{ shinyStats }}</h5>
      <h5>
        <img src="..\images\pokeball.png" id="poke-ball-icon" /> total :
        {{ totalCount }}
      </h5>
    </div>
  </div>
</template>

<script>
import CollectionService from "../services/CollectionService.js";
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
      userStats: {},
      shinyStats: 0,
      totalCount: 0,
      images: [],
      isLoaded: false,
    };
  },
  props: ["user"],
  name: "aggregate-statistics",
  created() {
    CollectionService.getPokemonTypesByUserId(this.$store.state.user.id).then(
      (response) => {
        this.userStats = response.data;
      }
    );
    for (let i = 0; i < this.typeList.length; i++) {
      this.images.push(this.$store.state.images[this.typeList[i]]);
    }
    this.isLoaded = true;

    CollectionService.getShinyPokemonByUserId(this.$store.state.user.id).then(
      (response) => {
        this.shinyStats = response.data;
      }
    );

    PokemonService.getAllPokemonByUserId(this.$store.state.user.id).then(
      (response) => {
        this.totalCount = response.data;
      }
    );
  },
};
</script>

<style scoped>
.outer-statistics-container {
  text-align: center;
  margin-top: 40px;
  background-color: rgba(0, 0, 0, 0.5);
  padding: 10px;
  color: white;
  border-radius: 30px;
  display: flex;
  flex-wrap: wrap;
  flex-direction: row;
  gap: 5px;
  width: 90%;
  justify-content: center;
  align-items: center;
}

.inner-statistics-container {
  text-align: center;
  background-color: rgba(0, 0, 0, 0.375);
  padding: 20px;
  color: white;
  border-radius: 30px;
  display: flex;
  flex-wrap: wrap;
  flex-direction: row;
  gap: 0px 10px;
  justify-content: center;
  align-items: center;
  
}
h1 {
  font-size: 1.5em;
  font-family: 'Silkscreen', cursive;
}
.fa-star {
  font-size: 1.5em;
}

#poke-ball-icon {
  height: 1.5em;
}
</style>