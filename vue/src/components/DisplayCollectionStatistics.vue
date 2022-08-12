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
      images: {
        "bug": "https://static.wikia.nocookie.net/pokemon/images/6/64/Type_Bug.gif",
        "dark": "https://static.wikia.nocookie.net/pokemon/images/0/0d/Type_Dark.gif",
        "dragon": "https://static.wikia.nocookie.net/pokemon/images/2/26/Type_Dragon.gif",
        "electric": "https://static.wikia.nocookie.net/pokemon/images/a/aa/Type_Electric.gif",
        "fairy": "https://static.wikia.nocookie.net/pokemon/images/7/74/Type_Fairy.gif",
        "fighting": "https://static.wikia.nocookie.net/pokemon/images/6/6b/Type_Fighting.gif",
        "fire": "https://static.wikia.nocookie.net/pokemon/images/4/4d/Type_Fire.gif",
        "flying": "https://static.wikia.nocookie.net/pokemon/images/4/4b/Type_Flying.gif",
        "ghost": "https://static.wikia.nocookie.net/pokemon/images/f/f2/Type_Ghost.gif",
        "grass": "https://static.wikia.nocookie.net/pokemon/images/4/46/Type_Grass.gif",
        "ground": "https://static.wikia.nocookie.net/pokemon/images/1/1d/Type_Ground.gif",
        "ice": "https://static.wikia.nocookie.net/pokemon/images/8/84/Type_Ice.gif",
        "normal": "https://static.wikia.nocookie.net/pokemon/images/6/61/Type_Normal.gif",
        "poison": "https://static.wikia.nocookie.net/pokemon/images/8/82/Type_Poison.gif",
        "psychic" : "https://static.wikia.nocookie.net/pokemon/images/6/65/Type_Psychic.gif",
        "rock": "https://static.wikia.nocookie.net/pokemon/images/b/b3/Type_Rock.gif",
        "steel": "https://static.wikia.nocookie.net/pokemon/images/a/ab/Type_Steel.gif",
        "water": "https://static.wikia.nocookie.net/pokemon/images/e/ed/Type_Water.gif",
      },
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