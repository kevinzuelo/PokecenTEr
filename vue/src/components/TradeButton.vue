<template>
    <div>
            <router-link :to="{ name: 'trade', params: { id: this.pokemon.pokemonId } }" v-if="this.$route.name != 'trade'">
          <i id="trade-button" class="fa-solid fa-arrow-right-arrow-left"></i>
      </router-link>
    </div>

</template>

<script>
export default {
    name: "trade-button",
    props: ["pokemon"],

}
</script>

<style scoped>
div {
    width: 50px;
    margin: auto;
}

i {
    cursor: pointer;
    color: rgba(0,0,0,0.6);

    
    
}

i:hover {
    transform: scale(1.2);
    color: rgb(0, 148, 0)
}


</style>