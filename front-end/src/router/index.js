import Vue from 'vue';
import VueRouter from 'vue-router';
import Home from '../views/Home.vue';
import Form from '../components/shared/form/Form.vue'

Vue.use(VueRouter);

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home,
    id: 1,
    isItem:true
  },
  {
    path:'/cadastro',
    name:'Cadastro',
    component: Form,
    id: 2,
    isItem:true
  },
  {
    path: '/cadastro/:id', 
    name:'Editar', 
    component: Form,
    id: 3,
    isItem: false
  },
  {
    path:'*',
    component:Home,
    isItem:false
  }
];

const router = new VueRouter({
  routes,
});

export default router;
