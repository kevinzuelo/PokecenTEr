<template>
    <div id="delete-collection" >

        <div>
            <i class="fa-solid fa-trash-can"></i>
            <h3>Delete Collection</h3>
        </div>

    </div>
</template>

<script>
export default {
    name: 'delete-collection-button'
}
</script>

<style scoped>
    h3 {          
        color: white;
    }

    #delete-collection {
        display: flex;
        width: 150px;
        height: 150px;
        margin: 10px;
       
        
    }

    #delete-collection div {
        display: block;
        background-color: red;
        color: white;
        border-radius: 15px;
        text-align: center;
        width: 150px;
        height: 150px;
        align-items: center;
        justify-content: center;
        flex-direction: column;   
    }

    #delete-collection div:hover {
        cursor: pointer;
        background-color: darkred;
    }

    #delete-collection div i {
        font-size: 35px
    }
</style>