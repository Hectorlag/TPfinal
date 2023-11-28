import { createApp } from 'vue';
import App from './App.vue';
import axios from 'axios';
import { createRouter, createWebHistory } from 'vue-router';
import BuscarLibro from './components/BuscarLibro.vue';
import Inicio from './components/Inicio.vue';
import QuienesSomos from './components/QuienesSomos.vue';
import RegistrarUsuario from './components/RegistrarUsuario.vue';
import ListarUsuarios from './components/ListarUsuarios.vue';
import RegistrarLibro from './components/RegistrarLibro.vue';
import ListarLibros from './components/ListarLibros.vue';
import RegistrarPrestamo from './components/RegistrarPrestamo.vue';
import ListarPrestamos from './components/ListarPrestamos.vue';
import Login from './components/Login.vue';


// Configura Axios para usar la URL base de tu API Spring Boot
axios.defaults.baseURL = 'http://localhost:8080'; 
axios.defaults.headers.common['Access-Control-Allow-Origin'] = '*';

const app = createApp(App);

// Agrega Axios a las propiedades globales de la aplicación Vue
app.config.globalProperties.$axios = axios;

// Configura las rutas de Vue Router
const routes = [
  { path: '/buscarlibro', component: BuscarLibro },
  { path: '/inicio', component: Inicio },
  { path: '/quienessomos', component: QuienesSomos },
  { path: '/registrarusuario', component: RegistrarUsuario },
  { path: '/listarusuarios', component: ListarUsuarios },
  { path: '/registrarlibro', component: RegistrarLibro },
  { path: '/listarlibros', component: ListarLibros },
  { path: '/registrarprestamo', component: RegistrarPrestamo },
  { path: '/listarprestamos', component: ListarPrestamos },
  { path: '/login', component: Login },

 
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

app.use(router);

app.mount('#app');




/*
import { createApp } from 'vue';
import App from './App.vue';
import axios from 'axios';

// Configura Axios para usar la URL base de tu API Spring Boot
axios.defaults.baseURL = 'http://localhost:8080'; // Ajusta la URL según tu configuración

const app = createApp(App);

// Agrega Axios a las propiedades globales de la aplicación Vue
app.config.globalProperties.$axios = axios;

app.mount('#app');
-----------------------------------------------------------------------------
*/




/*

import Vue from 'vue';
import App from './App.vue';
import router from '@/router';

Vue.config.productionTip = false;

new Vue({
  render: (h) => h(App),
  router, // Registra el enrutador en la instancia de Vue
}).$mount('#app');
*/




