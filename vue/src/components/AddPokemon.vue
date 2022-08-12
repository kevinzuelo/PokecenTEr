
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
        color: white;
    }

    #addPokemon {
        display: flex;
        width: 150px;
        height: 150px;
        margin: 10px;
    }

    #addPokemon div {
        background-color: rgba(135,206,250,0.3);
        color: white;
        border-radius: 15px;
        text-align: center;
        width: 150px;
    }

    #addPokemon div:hover {
        background-color: rgba(135,206,250, 0.8);
    }

    a div {
        padding-top: 2em;
        padding-bottom: .5em;
    }

    a div i {
        font-size: 35px
    }


</style>