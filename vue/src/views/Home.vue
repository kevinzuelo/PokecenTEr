<template>
  <div class="home">
    <div id ="page-header">
      <h1>{{ $store.state.user.username }}</h1>
      <div id="nav-buttons">
        <router-link v-bind:to="{ name: 'friends' }">
          <h1 class="trades-button">Friends 
            <i class="fa-solid fa-user-group"></i></h1>
        </router-link>
        <router-link  v-bind:to="{ name: 'my-trades', params: { id: this.$store.state.user.id }  }">
          <h1 id="myTrades" class="trades-button">View my trades
          <i class="fa-solid fa-bell" v-if="havePending.length" id="trades-alert-bell"></i></h1>
        </router-link>
      </div>
    </div>
    <display-aggregate-statistics id="display-aggregate" />
    <h1>My Collections</h1>
    <div class="myCollections">
    <collection-preview-link v-for="collection in collections" v-bind:key="collection.id" v-bind:collection="collection"/>
    <add-new-collection id="add-new" />
    </div>
    <div id="browse-collections">
      <h1>Recent Collections</h1>
      <div id="recent-collections">
        <public-collection-preview v-for="collection in recentCollections" v-bind:key="collection.id" v-bind:collection="collection" />
      </div>
      <router-link v-bind:to="{ name: 'browse' }">
        <button>Browse All Collections</button>
      </router-link>
      <router-link v-bind:to="{ name: 'browse-users'}">
        <button>Browse Other Users</button>
      </router-link>
    </div>
    <update-user-status v-if="isAdmin" />
  </div>
</template>

<script>
import CollectionPreviewLink from '../components/CollectionPreviewLink.vue';
import AddNewCollection from '../components/AddNewCollection.vue';
import CollectionService from '../services/CollectionService';
import DisplayAggregateStatistics from '../components/DisplayAggregateStatistics.vue';
import PublicCollectionPreview from '@/components/PublicCollectionPreview.vue';
import UpdateUserStatus from '../components/UpdateUserStatus.vue';
import TradeService from '@/services/TradeService.js'

export default {
  data() {
    return {
     collections : [],
     recentCollections: [],
     trades: [],
     myPendingTrades: []
    }
  },
  components: { CollectionPreviewLink, AddNewCollection, DisplayAggregateStatistics, PublicCollectionPreview, UpdateUserStatus},
  name: "home",
  created() {
    CollectionService.getCollectionsByUserId(this.$store.state.user.id).then((response) => {
      this.collections = response.data;
      console.log(response.data)
    });

    CollectionService.getRecentCollections().then(response =>{
      this.recentCollections = response.data;
    });

    TradeService.getAllTradesByUserId(this.$store.state.user.id).then( (response) => {
      console.log(response)
      if(response.status === 200){
        this.trades = response.data;
      }
    });
  },
  computed: {
    isAdmin() {
      return this.$store.state.user.authorities[0].name === "ROLE_ADMIN";
    },
    havePending() {
      let myPendingTrades = [];
      
      myPendingTrades =  this.trades.filter((trade) => {
          return trade.tradeReceiver.id === this.$store.state.user.id && trade.tradeStatus === 'Pending';
      });
      return myPendingTrades;
    }
  }
};
</script>

<style scoped>

h1 {
  color: white;
  font-size: 2em;
  text-align: center;

}
.myCollections {
  display: flex;
  flex-wrap: wrap;
  gap: 25px;
  align-items: center;
  
}

#browse-collections {
  display: flex;
  flex-direction: column;
  align-items: center;
  padding-bottom: 50px;
}

#recent-collections {
 display: flex;
 gap: 5px
}

#myTrades {
  display: flex;
  flex-direction: column;
  text-align: center;
}

.home {
  margin: 0px 50px 50px 50px;
}

#add-new:hover {
  box-shadow: 0px 0px 20px 5px rgba(255, 0, 0, 0.527);
}

#page-header {
  display: flex;
  justify-content: space-between;
  padding: 0 90px;
  flex-direction: column;
}

.trades-button {
  font-size: 25px;
  background-color: rgb(21, 123, 206);
  border-radius: 4px;
  font-family: 'Silkscreen', cursive;
  padding: 10px 20px;
  width: 100%;
  height: 90px;
  align-items: center;
  display: flex;
  flex-direction: column;
  min-width: 175px;
}


.trades-button:hover {
  background-color: rgb(13, 83, 141);
}


#trades-alert-bell{
  margin-top: 5px;
 font-size: 1.25em;
 animation: vertical-shaking 0.8s infinite;
 color: white;
}
@keyframes vertical-shaking {
  0% { transform: rotate(0deg); }
  25% { transform: rotate(10deg); }
  50% { transform: rotate(0eg); }
  75% { transform: rotate(-10deg); }
  100% { transform: rotate(0deg); }
}

@media only screen and (max-width: 600px){
  #page-header{
    flex-direction: column;
    padding:0 0px;
  }

  #nav-buttons{
  display: flex;
  flex-direction: column;
  gap:0px !important
}
  #recent-collections{
    flex-direction: column;
  }
  #display-aggregate{
    display: none;
  }
  .myCollections{
    justify-content: center;
  }
}
#nav-buttons{
  display: flex;
  justify-content: center;
  padding: 0px;
  gap:50px
}

#nav-buttons > a {
  justify-content: center;
  display: flex;
}
</style>