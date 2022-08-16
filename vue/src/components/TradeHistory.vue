<template>
    <div id="trade-history-component" class="trade-tables">
        <h2>Trade History</h2>
        <table v-if="trades.length > 0">
            <tr>
                <th>Requested Pokemon</th>
                <th>User Requested From</th>
                <th>Offered Pokemon</th>
                <th>Offering User</th>
                <th>Status</th>
            </tr>
            <tr v-for="trade in trades" v-bind:key=trade.tradeId>
                <td><div id="flex-in-table-row"><img v-bind:src="trade.requestedPokemon.imgSprite" />Lvl {{trade.requestedPokemon.level }} {{trade.requestedPokemon.species}} </div></td>
                <td>{{trade.tradeReceiver.username}}</td>
                <td><div id="flex-in-table-row"><img v-bind:src="trade.offeredPokemon.imgSprite" />Lvl {{trade.offeredPokemon.level }} {{trade.offeredPokemon.species}} </div></td>
                <td>{{trade.tradeInitiator.username}}</td>
                <td>{{trade.tradeStatus}}</td>
            </tr>
        </table>
        <h2 v-else class="no-trades">No trades to display...</h2>
    </div>
</template>

<script>
import TradeService from '@/services/TradeService.js'

export default {
    name: 'trade-history',
    data() {
        return {
            trades: []
        }
    },
    created() {
        TradeService.getAllTradesByUserId(this.$store.state.user.id)
                    .then( (response) => {
                        if(response.status === 200){
                        
                        let allTrades = [];
                            allTrades = response.data;

                        allTrades = allTrades.filter( (trade) => {
                            return trade.tradeStatus === "Approved" || trade.tradeStatus === "Rejected"
                        });

                        this.trades = allTrades;
                        }
                    })
    }
}
</script>

<style>
/* #trade-history-component{
    background-color: white;
} */

</style>