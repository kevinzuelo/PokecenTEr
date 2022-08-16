<template>
    <div id="trade-request-component">
        <h2>Trade Requests</h2>
        <table>
            <tr>
                <th>Requested Pokemon</th>
                <th>From</th>
                <th>Offered Pokemon</th>
                <th>Trade Status</th>
            </tr>
            <tr v-for="trade in trades" v-bind:key=trade.tradeId>
                <td>Lvl {{trade.requestedPokemon.level }} {{trade.requestedPokemon.species}} <img v-bind:src="trade.requestedPokemon.imgSprite" /></td>
                <td>{{trade.tradeReceiver.username}}</td>
                <td>Lvl {{trade.offeredPokemon.level }} {{trade.offeredPokemon.species}} <img v-bind:src="trade.offeredPokemon.imgSprite" /></td>
                <td>Pending</td>
            </tr>
        </table>
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
#trade-request-component{
    background-color: white;
}

</style>