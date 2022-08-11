<template>
    <router-link :to="{ name: 'collection', params: { id: collection.collectionId } }">
    <div class="collection-container">
        <h1>{{collection.name}}</h1>
        <div class="img-preview-container">
            <img v-for="poke in pokemonInCollection" v-bind:key="poke.pokemonId" v-bind:src="poke.imgSprite" width="100px" />
        </div>
    </div>
    </router-link>
</template>


<script>
import PokemonService from "../services/PokemonService.js"

export default {
name: "collection-preview",
props: ["collection"],
data() {
    return {
        pokemonInCollection: []
    }
},
created() {
    PokemonService.getPokemonByCollectionId(this.collection.collectionId).then(response => {
        let fullCollection = response.data;
        for(let i=0; i<fullCollection.length; i++){
            if(i > 3){
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
    border-radius: 30px;
    display: flex;
    
    flex-direction: column;
    width: 350px;
    height: 350px;
    align-items: center;
    color: white;
}

.img-preview-container{
    display:flex;
    max-width:400px;
    flex-wrap:wrap;
    justify-content: center;
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
 

</style>
