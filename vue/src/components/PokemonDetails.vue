<template>
  <div class="pokemon-details">
    <h3 class="species-name">{{ pokemon.species }}</h3>
    <h4 class="level">Lvl {{ pokemon.level }}</h4>
    <div id="imgContainer">
      <img id="main" v-bind:src="pokemon.imgMain" />
      <img id="glitter" src="../images/shiny.gif" v-if="pokemon.isShiny" />
    </div>
    <img
      class="type-image"
      v-for="type in typeArray2"
      v-bind:key="type"
      v-bind:src="type"
    />
    <p class="notes" v-if="this.pokemon.notes != ''">{{ pokemon.notes }}</p>

    <div id="pokemon-detail-buttons">
      <pokemon-preview-buttons v-bind:pokemon="this.pokemon" v-if="isMine" />
    </div>
  </div>
</template>

<script>
import PokemonPreviewButtons from './PokemonPreviewButtons.vue';
import CollectionService from '@/services/CollectionService.js'

export default {
  components: {  PokemonPreviewButtons },
  data() {
    return {
      typeArray: [],
      ownerId: ""
    };
  },
  name: "pokemon-details",
  props: ["pokemon"],
  computed: {
    typeArray2() {
      if (this.pokemon.type) {
        let types = this.pokemon.type.split(" ");
        types.pop();
        for (let i = 0; i < types.length; i++) {
          if (types[i] == "bug") {
            types[i] =
              "https://static.wikia.nocookie.net/pokemon/images/6/64/Type_Bug.gif";
          } else if (types[i] == "dark") {
            types[i] =
              "https://static.wikia.nocookie.net/pokemon/images/0/0d/Type_Dark.gif";
          } else if (types[i] == "dragon") {
            types[i] =
              "https://static.wikia.nocookie.net/pokemon/images/2/26/Type_Dragon.gif";
          } else if (types[i] == "electric") {
            types[i] =
              "https://static.wikia.nocookie.net/pokemon/images/a/aa/Type_Electric.gif";
          } else if (types[i] == "fairy") {
            types[i] =
              "https://static.wikia.nocookie.net/pokemon/images/7/74/Type_Fairy.gif";
          } else if (types[i] == "fighting") {
            types[i] =
              "https://static.wikia.nocookie.net/pokemon/images/6/6b/Type_Fighting.gif";
          } else if (types[i] == "fire") {
            types[i] =
              "https://static.wikia.nocookie.net/pokemon/images/4/4d/Type_Fire.gif";
          } else if (types[i] == "flying") {
            types[i] =
              "https://static.wikia.nocookie.net/pokemon/images/4/4b/Type_Flying.gif";
          } else if (types[i] == "ghost") {
            types[i] =
              "https://static.wikia.nocookie.net/pokemon/images/f/f2/Type_Ghost.gif";
          } else if (types[i] == "grass") {
            types[i] =
              "https://static.wikia.nocookie.net/pokemon/images/4/46/Type_Grass.gif";
          } else if (types[i] == "ground") {
            types[i] =
              "https://static.wikia.nocookie.net/pokemon/images/1/1d/Type_Ground.gif";
          } else if (types[i] == "ice") {
            types[i] =
              "https://static.wikia.nocookie.net/pokemon/images/8/84/Type_Ice.gif";
          } else if (types[i] == "normal") {
            types[i] =
              "https://static.wikia.nocookie.net/pokemon/images/6/61/Type_Normal.gif";
          } else if (types[i] == "poison") {
            types[i] =
              "https://static.wikia.nocookie.net/pokemon/images/8/82/Type_Poison.gif";
          } else if (types[i] == "psychic") {
            types[i] =
              "https://static.wikia.nocookie.net/pokemon/images/6/65/Type_Psychic.gif";
          } else if (types[i] == "rock") {
            types[i] =
              "https://static.wikia.nocookie.net/pokemon/images/b/b3/Type_Rock.gif";
          } else if (types[i] == "steel") {
            types[i] =
              "https://static.wikia.nocookie.net/pokemon/images/a/ab/Type_Steel.gif";
          } else if (types[i] == "water") {
            types[i] =
              "https://static.wikia.nocookie.net/pokemon/images/e/ed/Type_Water.gif";
          }
        }
        if (types.length > 2) {
          types.shift();
        }
        return types;
      } else return "";
    },

     isMine() {
        
        return this.$store.state.token != "" && (this.ownerId === this.$store.state.user.id);
      }
  },

  created() {
     let collectionId = this.pokemon.collectionId;

    CollectionService.getCollectionByCollectionId(collectionId)
                      .then( (response) => {
                        
                        if(response.status === 200) {
                          console.log(response.data.userId)
                          this.ownerId = response.data.userId;
                        }
                      });
  }
};
</script>

<style>
.pokemon-details {
  display: inline-block;
  background-color: lightskyblue;
  padding: 10px;
  text-align: center;
  border-radius: 7px;
  margin: 30px;
}
.type-image {
  width: 75px;
}

#type-img-div {
  text-align: center;
  display: flex;
  justify-content: center;
  gap: 30px;
}

.species-name {
  font-size: 1.75em;
  text-transform: capitalize;
  background-color: #030b42;
  color: white;
  padding: 2px;
  border-radius: 30px;
  box-shadow: 0px 0px 7px red;
}

.level {
  font-size: 1.875em;
}
.notes {
  text-align: center;
  font-size: 1em;
  max-width: 325px;
  background-color: white;
  color: black;
  padding: 15px;
  border-radius: 5px;
  overflow-wrap:break-word;
  margin: 15px auto 25px;
}

#imgContainer {
  position: relative;
}

#main {
  position: relative;
  z-index: 1;
}

#glitter {
  position: absolute;
  z-index: 2;
  left: -20px;
}
#pokemon-detail-buttons {
  font-size: 1.5em;
  margin-bottom: 15px;
}

.main-image {
  max-width: 350px;
}
</style>