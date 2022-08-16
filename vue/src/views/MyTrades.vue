<template>
    <div>
        <trade-requests />
        <pending-trades />
    </div>
</template>

<script>
import PendingTrades from '../components/PendingTrades.vue'
import TradeRequests from '../components/TradeRequests.vue'
export default {
  components: { TradeRequests, PendingTrades },

}
</script>

<style>

</style>