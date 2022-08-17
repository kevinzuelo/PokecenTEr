<template>
    <div>
        <h1>{{ user.username }}</h1>
        <div>
            <collection-preview-link v-for="collection in collections" v-bind:key="collection.id" v-bind:collection="collection"/>
    
        </div>
    </div>
</template>

<script>
import CollectionPreviewLink from '@/components/CollectionPreviewLink.vue'
import UserService from '@/services/UserService.js'
import CollectionService from '@/services/CollectionService.js'

export default {
    name: 'user',
    components: {
        CollectionPreviewLink
        
    },
    data() {
        return {
            user: {},
            collections: {}
        }
    },

    created() {
        UserService.getUserByUserId(this.$route.params.id)
                        .then( (response) => {
                            if(response.status === 200){
                                this.user = response.data;

                                CollectionService.getCollectionsByUserId(this.user.id)
                                                .then( (response) => {
                                                    if(response.status === 200){
                                                        this.collections = response.data;
                                                    }
                                                });
                                
                            }
                        });
    }
    
}
</script>

<style>

</style>