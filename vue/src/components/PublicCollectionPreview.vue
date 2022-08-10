<template>
  <div class="collection-container">
      <h3>{{collection.name}}</h3>
      <img src="https://assets1.ignimgs.com/2019/05/17/pokemon-yellow---button-1558057648010.jpg"
        width="150px" />
      <h5>{{collection.userId}}</h5>
  </div>
</template>

<script>
export default {
    name: "public-collection-preview",
    props: ["collection"]
}
</script>

<style>
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