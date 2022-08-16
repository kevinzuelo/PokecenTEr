<template>
    <div id="trade-history-component" class="trade-tables">
        <h2>Trade History</h2>
        <table>
            <tr>
                <th>Requested Pokemon</th>
                <th>Requesting User</th>
                <th>Offered Pokemon</th>
                <th>Offering User</th>
                <th>Status</th>
            </tr>
            <tr v-for="trade in trades" v-bind:key=trade.tradeId>
                <td>Lvl {{trade.requestedPokemon.level }} {{trade.requestedPokemon.species}} <img v-bind:src="trade.requestedPokemon.imgSprite" /></td>
                <td>{{trade.tradeReceiver.username}}</td>
                <td>Lvl {{trade.offeredPokemon.level }} {{trade.offeredPokemon.species}} <img v-bind:src="trade.offeredPokemon.imgSprite" /></td>
                <td>{{trade.tradeInitiator.username}}</td>
                <td>{{trade.tradeStatus}}</td>
            </tr>
        </table>
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

                        // allTrades = allTrades.filter( (trade) => {
                        //     return trade.tradeReceiver.id === this.$store.state.user.id && trade.tradeInitiator.id === this.$store.state.user.id;
                        // });

                        this.trades = allTrades;
                        }
                    })
    }
}
</script>

<style>
#trade-history-component{
    background-color: white;
}

</style>