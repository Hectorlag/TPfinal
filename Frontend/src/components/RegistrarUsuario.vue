<template>
    <div>
        <h2>Usuarios</h2>
        <!-- Contenido del componente -->
        <router-link to="/inicio" class="back-link">Volver</router-link>
    </div>
    <div class="registrar-usuario-container">
      <div class="nuevo-usuario">
        <h3>Registrar Nuevo Usuario</h3>
        <!-- Agregar campos para ingresar información del nuevo usuario -->
        <div class="form-group">
          <label for="nombre">Nombre de usuario:</label>
          <input v-model="nuevoUsuario.nombre" placeholder="Nombre de usuario" id="nombre" />
        </div>
  
        <div class="form-group">
          <label for="contrasena">Contraseña:</label>
          <input v-model="nuevoUsuario.contrasenia" type="password" placeholder="Contraseña" id="contrasena" />
        </div>
  
        <button class="registrar" @click="registrarUsuario">Registrar</button>
  
        <!-- Mensaje de confirmación -->
        <div v-if="registroExitoso" class="confirmacion">
          ¡Registro exitoso! El usuario {{ nuevoUsuario.nombre }} ha sido registrado.
        </div>
      </div>
  
      <!-- Puedes mantener la estructura de la lista de libros o ajustarla según tus necesidades -->
    </div>
  </template>
  
  <script>
  export default {
    data() {
      return {
        nuevoUsuario: { nombre: '', contrasenia: '' },
        usuarios: [],
        registroExitoso: false,
      };
    },
    methods: {
      obtenerUsuarios() {
        this.$axios.get('http://localhost:8080/usuarios/traer')
          .then(response => {
            console.log('Respuesta de la API:', response.data);
            this.usuarios = response.data;
          });
      },
      registrarUsuario() {
        console.log('Nuevo usuario a registrar:', this.nuevoUsuario);
        // Lógica para registrar un nuevo usuario
        this.$axios.post('http://localhost:8080/usuarios/crear', this.nuevoUsuario)
          .then(() => {
            this.obtenerUsuarios();
            this.nuevoUsuario = { nombre: '', contrasenia: '' };
            // Muestra el mensaje de confirmación
            this.registroExitoso = true;
            // Limpia el mensaje después de unos segundos
            setTimeout(() => {
              this.registroExitoso = false;
            }, 5000);
          })
          .catch(error => {
            console.error('Error al agregar usuario:', error);
            // Puedes manejar errores aquí según tus necesidades
          });
      },
    },
  };
  </script>
  
  <style scoped>
  .registrar-usuario-container {
    font-family: Arial, sans-serif;
    background-color: #f7f7f7;
    min-height: 100vh;
    padding: 50px;
    display: flex;
    flex-direction: column;
    justify-content: flex-start;
    align-items: center;
  }
  
  .nuevo-usuario {
    background-color: #fff;
    padding: 20px;
    border-radius: 5px;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
    margin-bottom: 20px;
    width: 100%;
    max-width: 600px;
  }
  
  /* Estilos para los campos de formulario */
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
  
  .registrar {
    background-color: #4caf50;
    color: white;
    padding: 10px 20px;
    border: none;
    border-radius: 5px;
    cursor: pointer;
    font-size: 1em;
    transition: background-color 0.3s;
  }
  
  .registrar:hover {
    background-color: #45a049;
  }
  
  /* Estilos para el mensaje de confirmación */
  .confirmacion {
    background-color: #d4edda;
    color: #155724;
    padding: 10px;
    border: 1px solid #c3e6cb;
    border-radius: 5px;
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
  </style>
  
