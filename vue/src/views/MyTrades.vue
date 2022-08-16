<template>
<div>
  <trade-requests />
  <trade-history /> 
  </div>
</template>

<script>
import TradeHistory from '../components/TradeHistory.vue'
import TradeRequests from '../components/TradeRequests.vue'
export default {
  components: { TradeRequests, TradeHistory }

}
</script>

<style>

</style>