<template>
  <div>
      <div id="user-card">
          <img :src="user.icon" />
          <h3>{{ user.username }}</h3>
          <h3>{{ user.continent }}</h3>
      </div>
  </div>
</template>

<script>
export default {
    props: ["user"],
    data() {
        return {

        }
    },
    methods: {

    },
    computed: {

    }
}
</script>

<style>
#user-card {
    display: grid
}
</style>