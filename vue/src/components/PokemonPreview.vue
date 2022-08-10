<template>
    <div class="pokemon-preview">
        <h3>{{pokemon.name}}</h3>
        <h4>Lvl {{pokemon.level}}</h4>
        <img v-bind:src=pokemon.imgSprite />
        <p>{{pokemon.type}}</p>   
    </div>
</template>
<script>
export default {
  name: "pokemon-preview",
  props: ["pokemon"],
  methods: {
    
    }
  }


</script>

<style>
  .pokemon-preview {
    display: inline-block;
    background-color: lightskyblue;
    padding: 10px;
    text-align: center;
  }
</style>