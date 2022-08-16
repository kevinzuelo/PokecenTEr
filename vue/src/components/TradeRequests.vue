<template>
    <div id="trade-request-component">
        <h2>Trade Requests</h2>
        <table>
            <tr>
                <th>My Pokemon</th>
                <th>Offered Pokemon</th>
                <th>Requestor</th>
                <th>Approve/Deny</th>
            </tr>
            <tr v-for="trade in trades" v-bind:key=trade.tradeId>
                <td>Lvl {{trade.requestedPokemon.level }} {{trade.requestedPokemon.species}} <img v-bind:src="trade.requestedPokemon.imgSprite" /></td>
                <td>Lvl {{trade.offeredPokemon.level }} {{trade.offeredPokemon.species}} <img v-bind:src="trade.offeredPokemon.imgSprite" /></td>
                <td>{{trade.tradeInitiator.username}}</td>
                <td><button v-on:click="respondToTradeRequest(trade.tradeId, 'Approved')">Approve</button><button v-on:click="respondToTradeRequest(trade.tradeId, 'Rejected')" >Reject</button></td>
            </tr>
        </table>
    </div>
</template>

<script>
import TradeService from '@/services/TradeService.js'

export default {
    name: 'trade-requests',
    data() {
        return {
            trades: []
        }
    },
    methods: {
        respondToTradeRequest(tradeId, approvalStatus){
            
            TradeService.updateTrade(tradeId, approvalStatus)
                .then( (response) => {
                    if(response.status === 200){
                        window.location.reload();
                    }
                })
        }
    },

    created() {
        TradeService.getAllTradesByUserId(this.$store.state.user.id)
                    .then( (response) => {
                        if(response.status === 200){
                            this.trades = response.data;
                        }
                    })
    }
}
</script>

<style>
#trade-request-component{
    background-color: white;
}

</style>