<template>
    <div class="collection-container">
        <h1>{{collection.name}}</h1>
        <h2>{{collection.game}}</h2>
        <img src="https://assets1.ignimgs.com/2019/05/17/pokemon-yellow---button-1558057648010.jpg"
        width="300px" />

    </div>
</template>



<script>
export default {
name: "collection-preview",
props: ["collection"]


}
</script>


<style scoped>

.collection-container{
    background-color:rgba(0,0,0,0.5);
    border-radius: 30px;
    display: flex;
    
    flex-direction: column;
    max-width: 400px;
    align-items: center;
    color: white;
}

 h1 {
     margin-bottom: -10px;
 }

 h2 {
     margin-bottom: -10px;
 }

 img {
     padding: 20px;
 }
 

</style>
