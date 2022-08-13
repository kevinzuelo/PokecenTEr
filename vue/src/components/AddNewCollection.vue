<template>
    <router-link v-bind:to="{ name: 'AddCollection' }">
        <div>
            <i class="fa-solid fa-plus"></i>
            <h3>Add New Collection</h3>
        </div>

    </router-link>

  
</template>

<script>
export default {
    name: 'add-New-Collection'
}
</script>

<style scoped>
    h3 {          
        color: #ffe019;
    }

    a {
        background-color: rgba(0,0,0,0.5);
        color: #ffe019;
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