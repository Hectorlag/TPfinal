<template>
  <div class="login-container">
    <h2>Login</h2>
    <form @submit.prevent="login" class="login-form">
      <label for="username">Nombre de Usuario:</label>
      <input type="text" v-model="nombre" required />
      <label for="password">Contraseña:</label>
      <input type="password" v-model="contrasenia" required />
      <button type="submit">Ingresar</button>
    </form>
    <div v-if="error" class="error-message">{{ error }}</div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      nombre: '',
      contrasenia: '',
      error: '',
    };
  },

  methods: {
    login() {
      const credentials = {
        nombre: this.nombre,
        contrasenia: this.contrasenia,
      };

      this.$axios.post('http://localhost:8080/usuarios/login', credentials)
        .then(response => {
          // Verificación exitosa, redirigir a la página principal
          // Almacena el nombre de usuario en localStorage
          localStorage.setItem('usuario', this.nombre);

          // Redirige a la página principal
          this.$router.push('/inicio');
        })
        .catch(error => {
          // Verificación fallida, mostrar mensaje de error
          this.error = 'Usuario y/o contraseña incorrecta';
        });
    },
  },
};
</script>

<style scoped>
.login-container {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  height: 70vh; /* Ajusta la altura según tus necesidades */
}

.login-form {
  max-width: 300px;
  width: 100%;
  padding: 20px;
  border: 1px solid #ccc;
  border-radius: 5px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

label {
  display: block;
  margin-bottom: 8px;
}

input {
  width: 100%;
  padding: 8px;
  margin-bottom: 16px;
  box-sizing: border-box;
}

button {
  background-color: #4caf50;
  color: white;
  padding: 10px;
  border: none;
  border-radius: 5px;
  cursor: pointer;
}

.error-message {
  color: #ff0000;
  margin-top: 10px;
  text-align: center;
}
</style>

  