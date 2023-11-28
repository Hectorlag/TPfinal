<template>
  <div class="listado-usuarios">
    <h1>Lista de Usuarios</h1>
    <table class="table">
      <thead>
        <tr>
          <th>ID</th>
          <th>Nombre</th>
          <th>Contraseña</th>
          <th>Acciones</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="usuario in usuarios" :key="usuario.id">
          <td>{{ usuario.id }}</td>
          <td>{{ usuario.nombre }}</td>
          <td>{{ usuario.contrasenia }}</td>
          <td class="acciones">
            <button class="editar" @click="editarUsuario(usuario)">Editar</button>
            <button class="eliminar" @click="eliminarUsuario(usuario.id)">Eliminar</button>
          </td>
        </tr>
      </tbody>
    </table>

    <!-- Formulario para editar un usuario (se muestra solo cuando se selecciona "Editar") -->
    <div v-if="usuarioAEditar" class="editar-usuario">
      <h3>Editar Usuario</h3>
      <div class="form-group">
        <label>ID:</label>
        <input v-model="usuarioAEditar.id" disabled />
      </div>
      <div class="form-group">
        <label>Nombre:</label>
        <input v-model="usuarioAEditar.nombre" />
      </div>
      <div class="form-group">
        <label>Contraseña:</label>
        <input v-model="usuarioAEditar.contrasenia" />
      </div>
      <button @click="actualizarUsuario">Actualizar</button>
      <button @click="cancelarEdicion">Cancelar</button>
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
      usuarios: [],
      usuarioAEditar: null,
    };
  },
  methods: {
    obtenerUsuarios() {
      this.$axios.get('http://localhost:8080/usuarios/traer')
        .then(response => {
          console.log('Respuesta de la API:', response.data);
          this.usuarios = response.data;
        })
        .catch(error => {
          console.error('Error al obtener usuarios:', error);
        });
    },
    editarUsuario(usuario) {
      this.usuarioAEditar= Object.assign({}, usuario);
    },
    actualizarUsuario() {
      // Lógica para actualizar el usuario
      this.$axios.put(`http://localhost:8080/usuarios/editar/${this.usuarioAEditar.id}`, this.usuarioAEditar)
        .then(() => {
          this.obtenerUsuarios();
          this.usuarioAEditar = null; // Limpiamos el libroAEditar después de la actualización
        });
      console.log('Usuario actualizado:', this.usuarioAEditar);
      // Reinicia la variable de usuarioAEditar después de la actualización
      this.usuarioAEditar = null;
    },
    cancelarEdicion() {
      // Cancela la edición y reinicia la variable de usuarioAEditar
      this.usuarioAEditar = null;
    },
    eliminarUsuario(id) {
     // Pregunta al usuario si está seguro antes de eliminar el usuario
     if (window.confirm('¿Estás seguro de que deseas eliminar al usuario?')) {
        // Lógica para eliminar el usuario
        this.$axios.delete(`http://localhost:8080/usuarios/borrar/${id}`)
          .then(response => {
            console.log('Libro eliminado correctamente:', response);
            // Actualizar la lista de usuarios después de la eliminación
            this.usuarios = this.usuarios.filter(usuario => usuario.id !== id);
          })
          .catch(error => {
            console.error('Error al eliminar el usuario:', error);
          });
      }
    },
  },
  mounted() {
    this.obtenerUsuarios();
  },
};
</script>

<style scoped>
/* Puedes agregar estilos adicionales según tus preferencias */
.listado-usuarios {
  margin-top: 20px;
}

/* Estilos para la tabla */
.table {
  width: 100%;
  border-collapse: collapse;
  margin-top: 10px;
}

.table th, .table td {
  border: 1px solid #ddd;
  padding: 18px;
  text-align: left;
}

.table th {
  background-color: #f2f2f2;
}

/* Estilos para los botones editar y eliminar */
.editar,
.eliminar {
  margin-right: 5px;
  padding: 3px 7px;
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

/* Estilos para el formulario de edición */
.editar-usuario {
  background-color: #fff;
  padding: 20px;
  border-radius: 5px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  margin-top: 20px;
  width: 100%;
  max-width: 600px;
}

.form-group {
  margin-bottom: 15px;
}

.form-group label {
  display: block;
  margin-bottom: 5px;
}

.form-group input {
  width: 100%;
  padding: 8px;
  box-sizing: border-box;
  margin-bottom: 10px;
}

.editar-usuario button {
  background-color: #4caf50;
  color: white;
  padding: 10px 20px;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  font-size: 1em;
  transition: background-color 0.3s;
  margin-right: 15px;
}

.editar-usuario button:hover {
  background-color: #45a049;
}

.back-link {
    text-decoration: none;
    background-color: #4caf50;
    color: white;
    padding: 8px 16px;
    border-radius: 5px;
    transition: background-color 0.3s;
    margin-bottom: 10px;
}

.back-link:hover {
    background-color: #45a049;
}

.btn-back {
  margin-top: 90px;
}
</style>
