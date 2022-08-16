<template>
    <div id="all-trades">
      <h1>My Trades</h1>
        <trade-requests />
        <pending-trades />
        <trade-history />
    </div>
</template>

<script>
import PendingTrades from '../components/PendingTrades.vue'
import TradeRequests from '../components/TradeRequests.vue'
import TradeHistory from '../components/TradeHistory.vue'
export default {
  components: { TradeRequests, PendingTrades, TradeHistory },

}
</script>

<style>

</style>