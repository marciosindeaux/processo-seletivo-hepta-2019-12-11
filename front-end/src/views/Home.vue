<template>
    <table-produtos/>
</template>

<script>
import Table from '../components/shared/table/TableProdutos/TableProdutos.vue'
export default {
  name: 'home',
  data() {
    return {
      produtos: [],
    };
  },
  created() {
    
  },
  components: {
    'table-produtos':Table
  }
};
</script>
<style scoped>
</style>
