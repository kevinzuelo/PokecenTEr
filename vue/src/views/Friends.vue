<template>
  <div id="friends-page">
      <h1 id="friends-header">Friends</h1>
      <div id="friend-list">
        <friend-preview v-for="user in friendslist" v-bind:key="user.id" v-bind:user="user"/>
      </div>
  </div>
</template>

<script>
import FriendPreview from '@/components/FriendPreview.vue'
import FriendshipService from '@/services/FriendshipService.js'

export default {
    name: 'friends',
    components: {
        FriendPreview
  },
  data(){
      return {
          friendslist: []
      }
  },
  created() {
      FriendshipService.getFriendslist(this.$store.state.user.id)
                        .then( (response) => {
                            console.log(response.status);
                            if(response.status === 200){
                                this.friendslist = response.data;
                            }
                        });
  }
}
</script>

<style>
   
    #friends-page {
        padding: 50px;
    }

    #friend-list {
        display: flex;
        flex-direction: column;
        gap: 30px;
    }
</style>