<template>
    <div id="pending-trades-component" class="trade-tables">
        <h2>Pending Requests</h2>
        <table v-if="trades.length > 0">
            <tr>
                <th>Requested Pokemon</th>
                <th>From</th>
                <th>Offered Pokemon</th>
                <th>Trade Status</th>
            </tr>
            <tr v-for="trade in trades" v-bind:key=trade.tradeId>
                <td><div id="flex-in-table-row"><img v-bind:src="trade.requestedPokemon.imgSprite" />Lvl {{trade.requestedPokemon.level }} {{trade.requestedPokemon.species}} </div></td>
                <td>{{trade.tradeReceiver.username}}</td>
                <td><div id="flex-in-table-row"><img v-bind:src="trade.offeredPokemon.imgSprite" />Lvl {{trade.offeredPokemon.level }} {{trade.offeredPokemon.species}} </div></td>
                <td>Pending</td>
            </tr>
        </table>
        <h2 v-else class="no-trades">No pending trades...</h2>
    </div>
</template>

<script>
import TradeService from '@/services/TradeService.js'

export default {
    name: 'pending-trades',
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
                            return trade.tradeInitiator.id === this.$store.state.user.id && trade.tradeStatus === 'Pending';
                        });

                        this.trades = requestedTrades;
                        }

                        
                    })
    }
}
</script>

<style>
/* #pending-trades-component{
    background-color: white;
} */

</style>