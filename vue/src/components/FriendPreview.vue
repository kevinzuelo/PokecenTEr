<template>
  <div id="main-container">
        <div id="card-container">
            <router-link v-bind:to="{ name: 'users', params: { id: user.id}}">
            <div id="user-card" v-bind:class=" isFriend ? 'friend-card' : 'user-card'  ">
                <img id="user-icon" v-bind:src="require(`../images/Icons/${user.iconUrl}`)" />
                <div id="name-location">
                    <h3>{{ user.username }} </h3>
                    <h3>{{ user.continent }}</h3>
                </div>
            </div>
            </router-link>
            <div>
                <button  v-on:click="toggleFriendship()"  v-bind:class="{ 'add-friend' : !isFriend }">{{ isFriend ? "Unfriend" : "Add friend"}}</button>
            </div>
        </div>
      <div v-if="!collectionsEmpty" id="collection-previews">
          <collection-preview-link v-for="collection in this.collections" v-bind:key="collection.collectionId" v-bind:collection="collection"/>
      </div>
      <div v-else>
        <p id="empty-collection-message">No collections to display</p>
      </div>
  </div>
</template>

<script>
import CollectionService from '../services/CollectionService.js'
import CollectionPreviewLink from '../components/CollectionPreviewLink.vue'
import FriendshipService from '@/services/FriendshipService.js'

export default {
    components: {CollectionPreviewLink},
    props: ["user"],
    data() {
        return {
            collections: [],
            isLoaded: false,
            firstThreeCollections: [],
            friendsList: []
            
        }
    },
    
    computed: {
        isFriend() {
            
            for(let friend of this.friendsList){
                if(friend.id === this.user.id){
                    return true;
                }
            }

            return false;
        },
        collectionsEmpty(){
            return this.collections.length === 0;
        }
    },
    created() {
        CollectionService.getCollectionsByUserId(this.user.id).then(response => {
            console.log(this.user.id);
            let allCollections = response.data;

            for(let i = 0; i < 3 ; i++){
                if(allCollections[i] != null){
                    this.collections.push(allCollections[i]);
                }
            }

            this.isLoaded = true;
        });

        FriendshipService.getFriendslist(this.$store.state.user.id)
                        .then( (response) => {
                            this.friendsList = response.data;
                        });
        
    },

    methods: {
        toggleFriendship(){
            if(this.isFriend){
                FriendshipService.unfriend(this.$store.state.user.id, this.user.id)
                            .then( (response) => {
                                if(response.status === 200){
                            
                                this.friendsList = this.friendsList.filter( (friend) =>{
                                    return friend.id != this.user.id;
                            
                                });
                                }
                            });
                }
                else{
                    FriendshipService.addFriend(this.$store.state.user.id, this.user.id)
                            .then( (response) => {
                                
                                if(response.status === 201){
                                    this.friendsList.push(this.user);
                                }
                            });
                }
            

        }
    }

    
}
</script>

<style scoped>
#user-card {
    display: grid;
    grid-template-columns: 1fr 3fr;
    grid-template-areas:
    "user-icon name-location"
    "user-icon name-location";
    height:115px;
    align-items: center;
    color: white;
    border-radius: 10px;
    gap:10px;
    width: 400px;
    padding: 10px;
}
#user-icon {
    grid-area: "user-icon";
}
#name-location{
    grid-area: "name-location";
}
#collection-previews{
    display:flex;
    width: 100%;
    overflow-x: auto;
    margin: 30px;
    padding: 10px;
    gap:10px;
}
#collection-previews::-webkit-scrollbar {
    display: none;
}
#main-container{
    display:flex;
    align-items: center;
    justify-content: left;
}

#empty-collection-message{
    color: yellow;
    font-size: 2em;
    padding-left: 50px;
}

#card-container{
    display: flex;
    flex-direction: column;
    align-items: center;
    gap: 5px;
}

.add-friend {
    background-color: rgb(21, 123, 206);
}

.add-friend:hover {
    background-color: rgb(13, 83, 141);
}

.friend-card {
    
    background-color: rgb(21, 123, 206);
}

.user-card {
    background-color: rgba(0,0,0,0.5);
}
</style>