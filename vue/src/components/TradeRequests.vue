<template>
    <div id="trade-request-component" class="trade-tables">
        <h2>Trade Requests</h2>
        <table v-if="trades.length > 0">
            <tr>
                <th>Requested Pokemon</th>
                <th>Offered Pokemon</th>
                <th>Requestor</th>
                <th>Approve/Deny</th>
            </tr>
            <tr v-for="trade in trades" v-bind:key=trade.tradeId>
                <td><div id="flex-in-table-row"><img v-bind:src="trade.requestedPokemon.imgSprite" /> Lvl {{trade.requestedPokemon.level }} {{trade.requestedPokemon.species}} </div></td>
                <td><div id="flex-in-table-row"> <img v-bind:src="trade.offeredPokemon.imgSprite" /> Lvl {{trade.offeredPokemon.level }} {{trade.offeredPokemon.species}}</div></td>
                <td>{{trade.tradeInitiator.username}}</td>
                <td><button v-on:click="respondToTradeRequest(trade.tradeId, 'Approved')" id="approve-trade-button">Approve</button><button v-on:click="respondToTradeRequest(trade.tradeId, 'Rejected')" >Reject</button></td>
            </tr>
        </table>
        <h2 v-else class="no-trades">No trade requests yet...</h2>
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
                        
                        let requestedTrades = [];
                            requestedTrades = response.data;
                        

                        requestedTrades = requestedTrades.filter( (trade) => {
                            return trade.tradeReceiver.id === this.$store.state.user.id && trade.tradeStatus === 'Pending';
                        });

                        this.trades = requestedTrades;
                        }

                        
                    })
    }
}
</script>

<style>
/* #trade-request-component{
    background-color: white;
} */
#approve-trade-button{
    background-color: rgb(0, 180, 0);
}
#approve-trade-button:hover {
    background-color: rgb(0, 90, 0);
}

</style>