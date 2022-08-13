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
                            <input type="text" v-model="filter.collection" id="collectionFilter" />
                        </td>
                        <td>
                            <input type="text" v-model="filter.pokemon" id="pokemonFilter" />
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>
        
        <div id="browse-collections">
            <public-collection-preview v-for="collection in filteredCollections" v-bind:key="collection.id" v-bind:collection="collection" />
        </div>

    </div>
</template>

<script>
import CollectionService from '@/services/CollectionService.js'
import PublicCollectionPreview from '@/components/PublicCollectionPreview.vue'

export default {
    name: 'browse',
    components: {
        PublicCollectionPreview
    },
    data() {

        return {
            filter: {
            username: "",
            collection: "",
            pokemon: ""
            },

            visibleCollections: []



        }
        },

    computed: {
        filteredCollections(){

            





            return this.visibleCollections;
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