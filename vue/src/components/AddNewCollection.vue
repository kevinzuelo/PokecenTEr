<template>
    <router-link v-bind:to="{ name: 'AddCollection' }">
        <h3>Add New Collection</h3>
    </router-link>

  
</template>

<script>
export default {
    name: 'add-New-Collection'
}
</script>

<style scoped>
    h3 {
        max-height: 50px;
        margin: 25px;
        background-color: rgba(0,0,0,0.5);
        color: yellow;
        padding: 10px;
    }
</style>