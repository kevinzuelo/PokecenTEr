<template>
    <div class="collection-container">
        <h1>{{collection.name}}</h1>
        <div class="img-preview-container" :style="{'grid-template-columns': pokemonInCollection.length <4 ? pokemonInCollection.length ==1 ? '1fr' : '1fr 1fr' : '1fr 1fr 1fr'}">
            <img class="pokeIcon" v-for="poke in pokemonInCollection" v-bind:key="poke.pokemonId" v-bind:src="poke.imgSprite" />
            <div id="empty-collection" v-if="!pokemonInCollection.length">
                <img id="" src="../images/pokeballs.png" alt="">
                <h2>Empty Collection</h2>
                <h2>Start Catching!</h2>
            </div>
        </div>
    </div>
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
    border-radius: 30px;
    display: flex;
    overflow: hidden;
    flex-direction: column;
    width: 350px;
    height: 350px;
    align-items: center;
    color: white;
}



.img-preview-container{
    display:grid;
    justify-content: center;
    align-content: center;
    overflow: hidden;
    width: 100%;
    margin-top: auto;
    margin-bottom: auto;
    
    
    
}

.pokeIcon {
    padding: 0px;
    width: 80%;
    justify-self: center;


}

 h1 {
     margin-bottom: -10px;
     background-color: rgba(0,0,0,0.3);
     width: 100%;
     margin-top: 0;
     padding-top: 20px;
     padding-bottom: 10px;

     
 }

 h2 {
     margin-bottom: -10px;
 }

 img {
     padding: 20px;
 }

 #empty-collection {
     display: flex;
     flex-direction: column;
 }
 

</style>
