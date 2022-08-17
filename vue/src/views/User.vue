<template>
    <div id="user-page">
        <h1 id="user-page-header">{{ user.username }}'s Collections</h1>
        <div id="friend-button-container">
            <button id="friendship-button" v-on:click="toggleFriendship()"  v-bind:class="{ 'add-friend' : !isFriend }">{{ isFriend ? "Unfriend" : "Add friend"}}</button>
        </div>
        <div id="nav-buttons">
            <router-link v-bind:to="{ name: 'browse-users'}">
                <button>Browse Users</button>
            </router-link>
            <router-link v-bind:to="{ name: 'friends'}">
                <button>Go to Friends</button>
            </router-link>
        </div>
        <div id="collection-container">
            <collection-preview-link v-for="collection in collections" v-bind:key="collection.id" v-bind:collection="collection"/>
    
        </div>
    </div>
</template>

<script>
import CollectionPreviewLink from '@/components/CollectionPreviewLink.vue'
import UserService from '@/services/UserService.js'
import CollectionService from '@/services/CollectionService.js'
import FriendshipService from '@/services/FriendshipService.js'

export default {
    name: 'user',
    components: {
        CollectionPreviewLink
        
    },
    data() {
        return {
            user: {},
            collections: {},
            friendslist: []
        }
    },

    created() {
        UserService.getUserByUserId(this.$route.params.id)
                        .then( (response) => {
                            if(response.status === 200){
                                this.user = response.data;

                                CollectionService.getCollectionsByUserId(this.user.id)
                                                .then( (response) => {
                                                    if(response.status === 200){
                                                        this.collections = response.data;
                                                    }
                                                });
                                
                            }
                        });

        FriendshipService.getFriendslist(this.$store.state.user.id)
                        .then( (response) => {
                            console.log(response.status);
                            if(response.status === 200){
                                this.friendslist = response.data;
                            }
                        });
    },
    computed: {
        isFriend() {
            
            for(let friend of this.friendslist){
                if(friend.id === this.user.id){
                    return true;
                }
            }

            return false;
        }
    },
    methods: {
        toggleFriendship(){
            if(this.isFriend){
                FriendshipService.unfriend(this.$store.state.user.id, this.$route.params.id)
                            .then( (response) => {
                                if(response.status === 200){
                            
                                this.friendslist = this.friendslist.filter( (friend) =>{
                                    return friend.id != this.user.id;
                            
                                });
                                }
                            });
                }
                else{
                    FriendshipService.addFriend(this.$store.state.user.id, this.$route.params.id)
                            .then( (response) => {
                                
                                if(response.status === 201){
                                    this.friendslist.push(this.user);
                                }
                            });
                }
            

        }
    }
    
}
</script>

<style>
    #collection-container {
        display: flex;
        flex-wrap: wrap;
        padding: 50px;
        gap: 20px;
    }

    #user-page {
        display: flex;
        flex-direction: column;
        justify-content: center;
    }

    #nav-buttons {
        width: 100%;
        display: flex;
        justify-content: left;
        gap: 20px;
        padding-left: 30px;
    }

    #user-page-header {
        font-size: 3em;
    }

    #friendship-button {
        width: 200px;
    }

    .add-friend {
        background-color: rgb(21, 123, 206);
    }

    .add-friend:hover {
        background-color: rgb(13, 83, 141);
    }

    #friend-button-container {
        display: flex;
        justify-content: center;
        margin-bottom: 50px;
    }

</style>