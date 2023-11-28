<template>
  <div class="lista-libros">
    <h1>Listado de Libros</h1>
    <table>
      <thead>
        <tr>
          <th>Id</th>
          <th>Título</th>
          <th>Autor</th>
          <th>Género</th>
          <th>Páginas</th>
          <th>Sinopsis</th>
          <th>Acciones</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="libro in libros" :key="libro.idLibro">
          <td>{{ libro.idLibro }}</td>
          <td>{{ libro.titulo }}</td>
          <td>{{ libro.autor }}</td>
          <td>{{ libro.genero }}</td>
          <td>{{ libro.cantPaginas }}</td>
          <td>{{ libro.sinopsis }}</td>
          <td class="acciones">
            <button class="editar" @click="editarLibro(libro)">Editar</button>
            <button class="eliminar" @click="eliminarLibro(libro.idLibro)">Eliminar</button>
          </td>
        </tr>
      </tbody>
    </table>
    <!-- Formulario para editar un libro (se muestra solo cuando se selecciona "Editar") -->
    <div v-if="libroAEditar" class="editar-libro">
      <h3>Editar Libro</h3>
      <form @submit.prevent="actualizarLibro">
        <div class="form-group">
          <label for="titulo">Título:</label>
          <input v-model="libroAEditar.titulo" id="titulo" />
        </div>
        <div class="form-group">
          <label for="autor">Autor:</label>
          <input v-model="libroAEditar.autor" id="autor" />
        </div>
        <div class="form-group">
          <label for="genero">Género:</label>
          <input v-model="libroAEditar.genero" id="genero" />
        </div>
        <div class="form-group">
          <label for="cantPaginas">Páginas:</label>
          <input v-model="libroAEditar.cantPaginas" id="cantPaginas" />
        </div>
        <div class="form-group">
          <label for="sinopsis">Sinopsis:</label>
          <textarea v-model="libroAEditar.sinopsis" id="sinopsis"></textarea>
        </div>
        <div class="form-actions">
          <button type="submit">Actualizar</button>
          <button type="button" @click="cancelarEdicion">Cancelar</button>
        </div>
      </form>
    </div>
  </div>
  <div v-if="mensajeExito" class="mensaje-exito">
    Modificación exitosa
  </div>
  <div class="btn-back">
    <router-link to="/inicio" class="back-link">Volver</router-link>
  </div>
</template>

<script>
export default {
  data() {
    return {
      libros: [],
      libroAEditar: null,
      mensajeExito: false
    };
  },
  methods: {
    obtenerLibros() {
      this.$axios.get('http://localhost:8080/libros/traer')
        .then(response => {
          console.log('Respuesta de la API:', response.data);
          this.libros = response.data;
        });
    },

    editarLibro(libro) {
      this.libroAEditar = Object.assign({}, libro);
    },

    actualizarLibro() {
      // Al hacer clic en el botón de actualizar, enviamos una solicitud PUT para actualizar el libro
      this.$axios.put(`http://localhost:8080/libros/editar/${this.libroAEditar.idLibro}`, this.libroAEditar)
        .then(() => {
          this.obtenerLibros();
          this.libroAEditar = null; // Limpiamos el libroAEditar después de la actualización
          this.mensajeExito = true;

          // Ocultar el mensaje después de 3 segundos
          setTimeout(() => {
            this.mensajeExito = false;
          }, 3000);
        });
    },

    cancelarEdicion() {
      this.libroAEditar = null;
    },

    eliminarLibro(idLibro) {
      // Pregunta al usuario si está seguro antes de eliminar el libro
      if (window.confirm('¿Estás seguro de que deseas eliminar este libro?')) {
        // Lógica para eliminar el libro
        this.$axios.delete(`http://localhost:8080/libros/borrar/${idLibro}`)
          .then(response => {
            console.log('Libro eliminado correctamente:', response);
            // Actualizar la lista de libros después de la eliminación
            this.libros = this.libros.filter(libro => libro.idLibro !== idLibro);
          })
          .catch(error => {
            console.error('Error al eliminar el libro:', error);
          });
      }
    },
  },
  created() {
    this.obtenerLibros();
  },

}
</script>

<style scoped>
/* Estilos para la lista de libros*/
.lista-libros {
  max-width: 100%;
  margin: 20px auto;
}

table {
  width: 100%;
  border-collapse: collapse;
  margin-top: 10px;
}

th,
td {
  border: 1px solid #ddd;
  padding: 6px;
  text-align: left;
}

th {
  background-color: #f2f2f2;
}

button {
  margin-right: 5px;
  padding: 5px 10px;
  font-size: 0.9em;
  border: none;
  cursor: pointer;
  border-radius: 3px;
  transition: background-color 0.2s;
}

.editar {
  background-color: #fdd835;
}

.eliminar {
  background-color: #ff5252;
  color: white;
}

.editar:hover {
  background-color: #fbc02d;
}

.eliminar:hover {
  background-color: #e53935;
}

r {
  display: flex;
  justify-content: space-between;
}

th,
td {
  flex: 1;
  padding: 8px;
  text-align: left;
}

.acciones {
  display: flex;
  gap: 15px;
  flex: 1;
  /* Ajusta la distribución de espacio para las acciones */
  justify-content: flex-end;
  /* Coloca las acciones al final de la fila */
}

/* Estilos para el formulario de edición */
.form-group {
  margin-bottom: 15px;
}

label {
  display: block;
  margin-bottom: 5px;
}

input,
textarea {
  width: 100%;
  padding: 8px;
  box-sizing: border-box;
  margin-bottom: 10px;
}

.form-actions {
  display: flex;
  justify-content: space-between;
  margin-top: 15px;
}

.mensaje-exito {
  background-color: #4caf50;
  color: white;
  padding: 10px;
  border-radius: 5px;
  margin-top: 10px;
  text-align: center;
  display: inline-block;
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
  margin-top: 40px;
}
</style>

