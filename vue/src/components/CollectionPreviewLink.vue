<template>
    <router-link :to="{ name: 'collection', params: { id: collection.collectionId } }">
    <collection-preview v-bind:collection="collection"/>
    </router-link>
</template>


<script>
import PokemonService from "../services/PokemonService.js"
import CollectionPreview from "../components/CollectionPreview.vue"

export default {
name: "collection-preview-link",
props: ["collection"],
components: {CollectionPreview},
data() {
    return {
        pokemonInCollection: [],
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
 

</style>
