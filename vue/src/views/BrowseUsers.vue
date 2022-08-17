<template>
  <div>
        <div id="user-search">
            <h2>Search: </h2>
            <input type="text" placeholder="Search username" v-model="filter" />
        </div>
        <div id="users-list" v-if="isLoaded">
            <friend-preview v-for="user in filteredUsers" v-bind:key="user.id" v-bind:user="user"/>
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
            isLoaded: false,
            filter: ""
        }
    },
    components: {FriendPreview},
    created() {
        UserService.getAllUsers().then(response => {
            for(let i = 0; i<response.data.length; i++){
                if(response.data[i].id == 1 || response.data[i].id == 2 || response.data[i].id == this.$store.state.user.id){
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
    },
    computed: {
        filteredUsers(){
                let filtered = this.users;

                if(this.filter != ""){
                    filtered = this.users.filter( (user) => {
                        return user.username.toLowerCase().startsWith(this.filter.toLowerCase());
                    });
                }
            
            return filtered;
        }
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
#user-search{
    display: flex;
    background-color: rgba(0,0,0,0.5);
    border-radius: 10px;
    width: 200px;
    padding: 20px;
    margin: 30px;
    align-items: center;
    gap: 10px;
    width: 400px;
}
</style>