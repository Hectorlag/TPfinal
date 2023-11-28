<template>
  <div>
    <h2>Libros</h2>
    <!-- Contenido del componente -->
    <div class="libros-container">
    </div>
    <div class="container">
      <h2 class="mb-4">Buscar Libro</h2>
      <form @submit.prevent="buscarLibro" class="mb-4">
        <div class="search-container">
          <div class="mb-3">
            <label for="titulo" class="form-label">Título del Libro:</label>
            <input v-model="titulo" id="titulo" class="form-control input-lg" placeholder="Ingrese el título del libro" />
          </div>
          <div class="button-container">
            <button type="submit" class="btn btn-primary btn-lg">Buscar</button>
            <button type="button" @click="limpiarCampos" class="btn btn-secondary btn-lg">Limpiar</button>
          </div>
        </div>
      </form>
      <div v-if="libroEncontrado" class="result-container">
        <h3 class="mb-3">Resultado de la Búsqueda</h3>
        <table class="table">
          <tbody>
            <tr>
              <th scope="row">Id</th>
              <td>{{ libroEncontrado.idLibro }}</td>
            </tr>
            <tr>
              <th scope="row">Título</th>
              <td>{{ libroEncontrado.titulo }}</td>
            </tr>
            <tr>
              <th scope="row">Autor</th>
              <td>{{ libroEncontrado.autor }}</td>
            </tr>
            <tr>
              <th scope="row">Género</th>
              <td>{{ libroEncontrado.genero }}</td>
            </tr>
            <tr>
              <th scope="row">Páginas</th>
              <td>{{ libroEncontrado.cantPaginas }}</td>
            </tr>
            <tr>
              <th scope="row">Sinopsis</th>
              <td>{{ libroEncontrado.sinopsis }}</td>
            </tr>
          </tbody>
        </table>
      </div>
      <div v-if="error" class="alert alert-danger mt-4">
        <p>Error al buscar libros: {{ error }}</p>
      </div>

      <!-- Mostrar el mensaje solo cuando no hay libro y no hay error -->
      <div v-if="!libroEncontrado && !error" class="no-result-message mt-4">
        <p>No se encontró ningún libro con ese título.</p>
      </div>
    </div>
  </div>
  <div class="btn-back">
    <router-link to="/inicio" class="back-link">Volver</router-link>
  </div>
</template>

<script>
export default {
  data() {
    return {
      titulo: '',
      libroEncontrado: null,
      error: null,
    };
  },

  methods: {
    limpiarCampos() {
      this.titulo = '';
      this.libroEncontrado = null;
      this.error = null;
    },

    buscarLibro() {
      if (this.titulo.trim() === '') {
        this.error = 'Por favor, ingrese un título de libro.';
        return;
      }

      // Hacer la solicitud a la API para buscar libros por título
      this.$axios.get(`http://localhost:8080/libros/traerXtitulo/${this.titulo}`)
        .then(response => {
          this.libroEncontrado = response.data;
          this.error = null; // Reiniciar el error si la búsqueda es exitosa
        })
        .catch(error => {
          console.error('Error al buscar libros:', error);
          this.libroEncontrado = null;
          this.error = 'Error al buscar libros. Inténtelo de nuevo.';
        });
    },
  },
};
</script>

<style scoped>
.libros-container {
  display: flex;
  flex-direction: column;
  align-items: center;
  padding: 20px;
}

.container {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  height: 70vh;
}

.search-container {
  display: flex;
  flex-direction: column;
  align-items: center;
}

.result-container {
  background-color: #f0f0f0;
  padding: 20px;
  border-radius: 8px;
  margin-top: 20px;
}

.input-lg {
  padding: 12px;
  font-size: 16px;
  margin: 10px;
}

.btn-lg {
  padding: 10px 30px;
  font-size: 16px;
  margin-left: 80px;
}

.btn-primary {
  background-color: #4caf50;
  border-color: #4caf50;
}

.btn-primary:hover {
  background-color: #45a049;
}

.alert-danger {
  background-color: #f8d7da;
  border-color: #f5c6cb;
  color: #721c24;
}

/* Estilos para mostrar clave y valor en una fila */
.table tbody tr {
  display: flex;
  flex-direction: row;
  align-items: center;
  margin-top: 10px;
}

.table th,
.table td {
  margin: 5px;
  text-align: left !important;
  /* Asegura que la alineación a la izquierda tenga prioridad */
}

/* Alinea el texto de las celdas del encabezado a la izquierda */
.table th {
  text-align: left;
}

.no-result-message {
  text-align: center;
  color: #777;
}

.button-container {
  display: flex;
  gap: 10px;
  margin-top: 10px;
}

.back-link {
  text-decoration: none;
  background-color: #4caf50;
  color: white;
  padding: 8px 16px;
  border-radius: 5px;
  transition: background-color 0.3s;
}

.back-link:hover {
  background-color: #45a049;
}

.btn-back {
  margin-bottom: 0;
}
</style>
