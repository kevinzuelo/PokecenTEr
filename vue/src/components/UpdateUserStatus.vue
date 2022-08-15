<template>
<div>
  <h1>Update Account</h1>
  <div v-for="user in users" v-bind:key="user.id">
      <h2>{{ user.username }}</h2>
      <select name='user-status' v-model="user.authorities[0]">
          <option value='ROLE_USER' v-bind:selected="user.authorities[0] === value">Standard User</option>
          <option value='ROLE_PREMIUM_USER' v-bind:selected="user.authorities[0] === value">Premium User</option>
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
            users: [],
        }
    },
    methods: {
        setAuthorities(user, authority) {
            let authorities = [];
            authorities.push(authority);
            user.authorities[0] = authorities;
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
                }
                return true;
            })
        }




        )
    }
}
</script>

<style>
    h2 {
        color: yellow;
    }
</style>