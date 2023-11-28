<template>
  <div id="app">
    <router-view />
  </div>
</template>
<script>

import BuscarLibro from './components/BuscarLibro.vue';
import Inicio from './components/Inicio.vue';
import QuienesSomos from './components/QuienesSomos.vue';
import RegistrarUsuario from './components/RegistrarUsuario.vue';
import ListarUsuarios from './components/ListarUsuarios.vue';
import RegistrarLibro from './components/RegistrarLibro.vue';
import ListarLibros from './components/ListarLibros.vue'
import RegistrarPrestamo from './components/RegistrarPrestamo.vue';
import ListarPrestamos from './components/ListarPrestamos.vue';
import Login from './components/Login.vue';

export default {
  name: 'App',
  components: {
    BuscarLibro,
    Inicio,
    QuienesSomos,
    RegistrarUsuario,
    ListarUsuarios,
    RegistrarLibro,
    ListarLibros,
    RegistrarLibro,
    RegistrarPrestamo,
    ListarPrestamos,
    Login
  }
};
</script>






<!-- App.vue 
<template>
  <div id="app">
    <Navbar :links="navLinks" />
    <router-view />
  </div>
</template>

<script>
import Navbar from "@/components/Navbar.vue";

export default {
  components: {
    Navbar,
  },
  data() {
    return {
      navLinks: [
        { text: "Inicio", path: "/" },
        { text: "Lista de Libros", path: "/books" },
      ],
    };
  },
};
</script>-->






<!--
  <template>
  <div id="app">
    <AgregarLibro/>

  </div>
</template>
<script>

import AgregarLibro from './components/AgregarLibro.vue';
import NuevoLibroForm from './components/NuevoLibroForm.vue';
export default {
  name: 'App',
  components: {
    AgregarLibro
  }
};
</script>-->






<!--<template>
  <div id="app">
    <Navegacion />
    <router-view />
  </div>
</template>

<script>
import Navegacion from '@/components/Navegacion.vue';

export default {
  components: {
    Navegacion,
  },
}
</script>-->






















