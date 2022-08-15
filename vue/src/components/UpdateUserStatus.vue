<template>
<div id="update-status">
  <h1>Update Account</h1>
  <div v-for="(user, index) in users" v-bind:key="user.id" class="user-status">
      <h2>{{ user.username }}:</h2>
      <select name='user-status'  v-model="users[index].role" v-on:change="updateUser(user)">
          <option value='ROLE_USER' v-bind:selected="user.role === 'ROLE_USER'">Standard User</option>
          <option value='ROLE_PREMIUM_USER' v-bind:selected="user.role === 'ROLE_PREMIUM_USER'">Premium User</option>
      </select>
  </div>
</div>
</template>

<script>
import UserService from '@/services/UserService.js';
export default {
    
    name: 'update-user-status',
    data() {
        return {
            users: []
        }
    },
    methods: {
       
    updateUser(user){
        UserService.updateUser(user)
                    .then( (response) => {
                        console.log(response);
                    })
    }


    },
    created() {
        let allUsers = [];
        UserService.getAllUsers().then((response) => {
            allUsers = response.data;
            this.users = allUsers.filter(user => {
                let authorities = user.authorities;
                for (let authority of authorities) {
                    if (authority.name === 'ROLE_ADMIN') {
                        return false;
                    }
                    user.role = user.authorities[0].name;
                }
                return true;
            });

           
        }




        )
    }
}
</script>

<style>
    h2 {
        color: yellow;
    }

    .user-status {
        display: flex;
        align-items: center;
        gap: 5px;
        justify-content: space-between;
    }

    #update-status {
        background-color: rgba(0,0,0,0.5);
        max-width: 250px;
        padding: 20px;
        border-radius: 20px;
    }
</style>