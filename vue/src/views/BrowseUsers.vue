<template>
  <div>
      <div id="users-list" v-if="isLoaded">
        <friend-preview v-for="user in users" v-bind:key="user.id" v-bind:user="user"/>
      </div>
  </div>
</template>

<script>
import FriendPreview from '../components/FriendPreview.vue'
import UserService from '../services/UserService.js'

export default {
    name: "test",
    data() {
        return{
            users: [],
            isLoaded: false
        }
    },
    components: {FriendPreview},
    created() {
        UserService.getAllUsers().then(response => {
            for(let i = 0; i<response.data.length; i++){
                if(response.data[i].id == 1 || response.data[i].id == 2){
                    continue;
                }
                this.users.push(response.data[i]);
            }
            this.isLoaded = true;
            // this.users.push(response.data);
            // console.log(this.users[0].id);
            // this.isLoaded = true;
        }).catch(error => {
            console.log(error);
        });
    }
}
</script>

<style>
#users-list{
    display:flex;
    flex-direction: column;
    height: 800px;
    overflow-x: auto;
    margin: 30px;
    padding: 10px;
    gap:30px;
}
#users-list::-webkit-scrollbar {
    display: none;
}
</style>