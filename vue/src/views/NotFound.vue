<template>
    <div id="not-found-container">
        <h1>Page Not Found</h1>
        <img src="@/images/psyduck.jpg" />
        <router-link v-bind:to="{name: 'home'}">
            <button class="btn">GO TO HOMEPAGE</button>
        </router-link>
    </div>
</template>

<script>
export default {

}
</script>

<style>

#not-found-container {
    display: flex;
    flex-direction: column;
    margin-top: 20px;
    align-items: center;
    background-color: rgb(0,0,0,0,0.5);
    color: #ffe019;
    gap: 20px;
}
</style>