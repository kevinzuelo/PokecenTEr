<template>
    <div id="redirect-container">
        <div id="redirect-box">
            <h1>Access Denied</h1>
            <h2>You must be logged in to view this content</h2>
            <img src="@/images/Snorlax.jpg"/>
            <div id = "links">
                <button v-on:click="goToLogin()" class="btn">LOGIN</button>
                <button v-on:click="goToRegister()" class="btn">REGISTER</button>
            </div>
        </div>
    </div>
</template>

<script>
export default {
    name: 'redirect',

    methods: {
        goToLogin() {
            this.$router.push({name: "home"});
        },

        goToRegister() {
             this.$router.push({name: "register"});
        }
    }

}
</script>

<style scoped>

    #links {
        display: flex;
        gap: 20px;
    }

    #redirect-container {
        display: flex;
        justify-content: center;
        align-items: center;
        margin-top: 50px;
   
    }

    #redirect-box {
        display: flex;
        flex-direction: column;
        color: red;
        background-color: rgba(0,0,0,0.5);
        align-items: center;
        width: 500px;
        padding: 20px;
        border-radius: 20px;
        gap: 10px;
        text-align: center;
    }

    img {
        width: 80%;
    }

</style>