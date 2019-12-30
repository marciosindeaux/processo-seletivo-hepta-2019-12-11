import Vue from 'vue';
import App from './App.vue';
import router from './router';
import 'bootstrap';
import 'bootstrap/dist/css/bootstrap.min.css';
import AxiosConfig from './config/Axios'

Vue.config.productionTip = false;

Vue.use(AxiosConfig)

Vue.prototype.$axios = AxiosConfig;

new Vue({
  router,
  render: h => h(App),
}).$mount('#app');
