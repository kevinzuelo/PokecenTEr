<template>
    <div id="browse-page">
        <h1 id="title">{{ $store.state.token ? "All Collections" : "All Public Collections" }}</h1>
        <div id="browse-filter-container">
            <h2>Search By</h2>
            <table id="browse-filter">
                <thead>
                    <tr>
                        <th>Username</th>
                        <th>Collection</th>
                        <th>Pokemon</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>
                            <input type="text" v-model="filter.username" id="usernameFilter"  />
                        </td>
                        <td>
                            <input type="text" v-model="filter.collectionName" id="collectionFilter" />
                        </td>
                        <td>
                            <input type="text" v-model="filter.pokemon" id="pokemonFilter" />
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>
        
        <div id="browse-collections">
            <public-collection-preview v-for="collection in filteredCollections" v-bind:key="collection.collectionId" v-bind:collection="collection" />
        </div>

    </div>
</template>

<script>
import CollectionService from '@/services/CollectionService.js'
import PublicCollectionPreview from '@/components/PublicCollectionPreview.vue'
import UserService from '../services/UserService'
import PokemonService from '@/services/PokemonService.js'

export default {
    name: 'browse',
    components: {
        PublicCollectionPreview
    },
    data() {

        return {
            filter: {
            username: "",
            collectionName: "",
            pokemon: ""
            },

            visibleCollections: []



        }
        },

    computed: {
        filteredCollections(){

            let filtered = this.visibleCollections;

                if(this.filter.username != ""){
                    filtered = filtered.filter( (currentCollection) => {
                        return currentCollection.owner.startsWith(this.filter.username);
                    });                    
                }
                
                if(this.filter.collection != ""){
                    filtered = filtered.filter( (currentCollection) => {
                        return currentCollection.name.startsWith(this.filter.collectionName)
                    });
                }
                
                if(this.filter.pokemon != ""){
                    filtered = filtered.filter( (currentCollection) => {
                        for(let pokemon of currentCollection.pokemon){
                            if (pokemon.species.startsWith(this.filter.pokemon.toLowerCase())){
                                return true;
                            }
                        }
                    });
                }

            return filtered;
           
        }
    },

    created() {

        let collections = [];

        CollectionService.getAllCollections()
                        .then( (response) => {
                            if(response.status === 200){
                                collections = response.data;


                                if(this.$store.state.token === ""){
                                    this.visibleCollections = collections.filter( (collection) => {
                                        return !collection.isPrivate;
                                    });
                                }
                                else{
                                    this.visibleCollections = collections;
                                }
                            }

                            this.visibleCollections.forEach( (collection) => {
                                UserService.getUserByUserId(collection.userId)
                                    .then( (response) => {
                                        if(response.status === 200){
                                            collection.owner = response.data.username;
                                        }

                                        PokemonService.getPokemonByCollectionId(collection.collectionId)
                                                    .then( (response) => {
                                                        if(response.status === 200){
                                                            collection.pokemon = response.data;
                                                        }
                                                    })
                                    });
                            }
                            )

                            
                        });
                                




    }

        
   
    
}
</script>

<style scoped>

h1 {
    font-size: 3em;
}
#browse-page {
    
    display: grid;
    grid-template-areas: 
    "title filter"
    "collections collections";

}

#title {
    grid-area: title;
}

#browse-filter-container {
    grid-area: filter;
    color: yellow;
    background-color: rgba(0,0,0,0.5);
    padding: 20px;
}

#browse-collections {
    grid-area: collections;
    display: flex;
    flex-wrap: wrap;
    margin: 50px;
    gap: 20px;

}
</style>