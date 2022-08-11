<template>
    <router-link :to="{ name: 'collection', params: { id: pokemon.collectionId } }">
        <div>
            <i class="fa-solid fa-arrow-left"></i>
            <h3>Return to Collection</h3>
        </div>
    </router-link> 
</template>

<script>
export default {
    name: 'back-to-collection-button',
    props: ["pokemon"]
}
</script>

<style scoped>
    h3 {          
        color: yellow;
    }

    a {
        background-color: rgba(0,0,0,0.5);
        color: yellow;
        border-radius: 15px;
        text-align: center;
        width: 10em;
        
    }

    a div {
        padding-top: 2em;
        
    }

    a div i {
        font-size: 35px
    }
</style>