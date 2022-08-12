
<template>
    <router-link id="addPokemon" :to="{ name: 'add-pokemon', params: { id: this.$route.params.id } }">
        <div>
            <i class="fa-solid fa-plus"></i>
            <h3>Add Pokemon</h3>
        </div>

    </router-link>

  
</template>

<script>
export default {
    name: 'add-pokemon-button'
}
</script>

<style scoped>
    h3 {          
        color: yellow;
    }

    #addPokemon {
        display: flex;
        width: 150px;
        margin: 10px;
    }

    #addPokemon div {
        background-color: rgba(0,0,0,0.5);
        color: yellow;
        border-radius: 15px;
        text-align: center;
        width: 150px;

        
    }

    a div {
        padding-top: 2em;
        padding-bottom: .5em;
    }

    a div i {
        font-size: 35px
    }
</style>