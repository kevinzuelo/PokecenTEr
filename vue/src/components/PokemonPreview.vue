<template>
    <div class="pokemon-preview">
        <h3>{{pokemon.name}}</h3>
        <h4>Lvl {{pokemon.level}}</h4>
        <img v-bind:src=pokemon.imgSprite />
        <img v-for="type in typeArray" v-bind:key="type" v-bind:src=type />
    </div>
</template>
<script>
export default {
    data() {
    return {
        typeArray: []
    }
    },
  name: "pokemon-preview",
  props: ["pokemon"],
  created() {
      this.typeArray = this.pokemon.type.split(" ")
      for (let i = 0; i < this.typeArray.length - 1; i++) {
        this.typeArray[i] = `..\\images\\types\\${this.typeArray[i]}.png`
      }
      this.typeArray.pop();
      console.log(this.typeArray)
    },
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