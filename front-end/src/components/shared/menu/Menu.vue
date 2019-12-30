<template>
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <router-link class="navbar-brand" to="/" style="font-size: 20px;">Mercadinho</router-link>
        <div class="collapse navbar-collapse" id="textoNavbar">
            <ul class="navbar-nav mr-auto">
                <li v-for="route in this.routes" :key="route.id">
                    <a><router-link :to="route.path">{{ route.name }}</router-link></a>
                </li>
            </ul>
	    </div>
    </nav>
</template>

<script>
import router from '../../../router/index';

export default {
  data() {
    return {
      routes: router.options.routes.filter((route) => route.isItem),
      current: router.currentRoute.path,
    };
  },
  mounted() {
      console.log(this.current)
  }
};
</script>
<style scoped>
    a {
        color: white;
        margin: 7px;
        font-size: 12pt
    }
</style>