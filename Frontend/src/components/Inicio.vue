<template>
  <div>
    <h1 class="titulo">Bienvenido a la Biblioteca</h1>
    <div class="user-info">
      <p>{{ usuario }}</p>
    </div>
    <div class="navbar">
      <div class="nav-section">
        <a href="#" @click="toggleSection('Inicio')" class="nav-link">Inicio</a>
        <ul v-show="secciones['Inicio']">
          <li><router-link to="quienessomos">Quienes somos</router-link></li>
        </ul>
      </div>

      <div class="nav-section">
        <a href="#" @click="toggleSection('Libro')" class="nav-link">Libro</a>
        <ul v-show="secciones['Libro']">
          <li><router-link to="registrarlibro">Registrar</router-link></li>
          <li><router-link to="listarlibros">Listado</router-link></li>
          <li><router-link to="buscarlibro">Buscar</router-link></li>
        </ul>
      </div>

      <div class="nav-section">
        <a href="#" @click="toggleSection('Prestamo')" class="nav-link">Prestamo</a>
        <ul v-show="secciones['Prestamo']">
          <li><router-link to="registrarprestamo">Registrar</router-link></li>
          <li><router-link to="listarprestamos">Listado</router-link></li>
          <li><router-link to="editarprestamo"></router-link></li>
        </ul>
      </div>

      <div class="nav-section">
        <a href="#" @click="toggleSection('Usuario')" class="nav-link">Usuario</a>
        <ul v-show="secciones['Usuario']">
          <li><router-link to="registrarusuario">Registrar</router-link></li>
          <li><router-link to="listarusuarios">Listado</router-link></li>
        </ul>
      </div>
    </div>

    <div class="contenido">
      <!-- Sección 1 -->
      <div class="seccion-imagen">
        <img src="@/assets/biblioteca.jpeg" alt="biblioteca" />
      </div>
      <!-- Sección 2 -->
      <div class="seccion-imagen">
        <img src="@/assets/biblioteca2.jpeg" alt="imagen2" />
      </div>
      <!-- Sección 3 -->
      <div class="seccion-imagen">
        <img src="@/assets/biblioteca3.jpeg" alt="imagen3" />
      </div>
      <!-- Sección 4 -->
      <div class="seccion-imagen">
        <img src="@/assets/biblioteca4.jpeg" alt="imagen4" />
      </div>
      <!-- Sección 5 -->
      <div class="seccion-imagen">
        <img src="@/assets/biblioteca5.jpeg" alt="imagen5" />
      </div>
      <!-- Sección 6 -->
      <div class="seccion-imagen">
        <img src="@/assets/libroypc.jpeg" alt="imagen6" />
      </div>
    </div>
  </div>
  <!-- Agrega esto al final de tu componente -->
<div class="cerrar-sesion" @click="cerrarSesion">Cerrar Sesión</div>

</template>

<script>
export default {
  data() {
    return {
      usuario: '',
      secciones: {
        'Inicio': false,
        'Libro': false,
        'Prestamo': false,
        'Usuario': false,
      },
    };
  },
  mounted() {
    this.usuario = localStorage.getItem('usuario');
  },
  methods: {
    toggleSection(section) {
      this.secciones[section] = !this.secciones[section];
    },
  },
};
</script>

<style scoped>
  .titulo {
    text-align: center;
    margin-top: 20px;
  }

  .navbar {
    display: flex;
    justify-content: space-around;
    align-items: center;
    background-color: #333;
    color: white;
    padding: 10px;
  }

  .nav-section {
    text-align: center;
    margin-top: 50px;
    flex: 1; /* Ocupa todo el espacio disponible */
  }

  .titulo {
    margin: 0; /* Eliminar el margen predeterminado del h1 */
  }

  ul {
    list-style-type: none;
    padding: 0;
  }

  li {
    display: inline-block;
    margin-right: 10px;
  }

  a {
    text-decoration: none;
    color: white;
    font-weight: bold;
  }

  a:hover {
    color: #ffc107;
  }

  .contenido {
    display: flex;
    flex-wrap: wrap;
    justify-content: space-around;
    margin-top: 20px; /* Ajusta según tus necesidades */
  }

  .seccion-imagen {
    width: calc(33.33% - 20px); /* Ancho de cada sección, con espacio entre ellas */
    margin-bottom: 20px;
  }

  .seccion-imagen img {
    width: 100%;
    height: 100%;
    object-fit: cover;
  }

  /* Ajustes para bajar los h2 */
  .nav-section h2 {
    margin-bottom: 5px;
  }

  .user-info {
  position: absolute;
  top: 10px;
  right: 10px;
}

.user-info p {
  color: #4caf50; /* Verde */
  font-weight: bold;
  font-size: 16px;
  margin: 0; /* Elimina el margen predeterminado del párrafo */
  text-decoration: underline; /* Subrayado */
  display: inline-block;
  padding: 2px 5px; /* Añade espacio alrededor del texto */
  border: 2px solid #4caf50; /* Contorno verde */
  border-radius: 5px; /* Bordes redondeados */
}

ul {
    list-style-type: none;
    padding: 0;
  }

  li {
    display: block; /* Cambiar a bloque para que cada elemento se muestre en una línea nueva */
    margin-bottom: 10px; /* Espacio entre cada elemento de la lista */
  }

  .nav-link {
    font-size: 24px; /* Tamaño de fuente más grande para los enlaces principales */
  }
</style>
 

   

  