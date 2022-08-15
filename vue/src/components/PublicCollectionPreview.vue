<template>
<router-link :to="{ name: 'collection', params: { id: collection.collectionId } }">
  <div class="collection-container">
      <h3>{{collection.name}}</h3>
        <div class="img-preview-container">
        <!-- :style="{'grid-template-columns': pokemonInCollection.length <4 ? pokemonInCollection.length ==1 ? '1fr' : '1fr 1fr' : '1fr 1fr 1fr'}" -->
            <img class="pokeIcon" v-for="poke in pokemonInCollection" v-bind:key="poke.pokemonId" v-bind:src="poke.imgSprite" />
        </div>
      <h5>Owner:<br> {{user.username}}</h5>
  </div>
</router-link>
</template>

<script>
import PokemonService from "../services/PokemonService.js"
import UserService from "../services/UserService.js"


export default {
    name: "public-collection-preview",
    props: ["collection"],
data() {
    return {
        pokemonInCollection: [],
        user: {}
    }
},
created() {
    PokemonService.getPokemonByCollectionId(this.collection.collectionId).then(response => {
        let fullCollection = response.data;

        UserService.getUserByUserId(this.collection.userId).then(response => {
            this.user = response.data;
         })
        for(let i=0; i<fullCollection.length; i++){
            if(i > 8){
                break;
            }
            this.pokemonInCollection.push(fullCollection[i]);
        }
    });
}
}
</script>

<style scoped>
  .collection-container{
    background-color:rgba(0,0,0,0.5);
    border-radius: 15px;
    display: flex;
    flex-direction: column;
    width: 200px;
    height: 350px;
    align-items: center;
    color: white;
    position: relative;
    bottom: 10px;
  }

  h1 {
     margin-bottom: -10px;
  }

  h2 {
     margin-bottom: -10px;
  }

  img {
     padding: 20px;
  }
  h3 {
    padding: 15px;
    overflow-wrap:break-word;    
    text-align: center;
    margin-bottom: -10px;
     background-color: rgba(0,0,0,0.3);
     border-radius: 15px 15px 0px 0px;
     width: 170px;
     margin-top: 0;
     padding-top: 20px;
     padding-bottom: 10px;
  }
  
.img-preview-container{
    display:flex;
    flex-wrap: wrap;
    justify-content: center;
    overflow: hidden;
    width: 100%;
    margin-top: auto;
    margin-bottom: auto;
}

.pokeIcon{
  height: 80px;
  margin: -20px;
}
h5 {
  text-align: center;
  color: #ffe019;
}

</style>