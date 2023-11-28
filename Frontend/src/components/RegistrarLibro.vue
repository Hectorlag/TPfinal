<template>
    <div>
        <h2>Libros</h2>
        <!-- Contenido del componente -->
    </div>
    <div class="libros-container">
        <div class="nuevo-libro">
            <h3>Agregar Nuevo Libro</h3>
            <!-- Agregar campos para ingresar información del nuevo libro -->
            <div class="form-group">
                <label for="titulo">Título del libro:</label>
                <input v-model="nuevoLibro.titulo" placeholder="Título del libro" id="titulo" />
            </div>

            <div class="form-group">
                <label for="autor">Autor del libro:</label>
                <input v-model="nuevoLibro.autor" placeholder="Autor del libro" id="autor" />
            </div>

            <div class="form-group">
                <label for="genero">Género del libro:</label>
                <input v-model="nuevoLibro.genero" placeholder="Género del libro" id="genero" />
            </div>

            <div class="form-group">
                <label for="cantPaginas">Cantidad de páginas:</label>
                <input v-model="nuevoLibro.cantPaginas" placeholder="Cantidad de páginas" id="cantPaginas" />
            </div>

            <div class="form-group">
                <label for="sinopsis">Sinopsis:</label>
                <input v-model="nuevoLibro.sinopsis" placeholder="Sinopsis" id="sinopsis" />
            </div>

            <button class="agregar" @click="crearLibro">Registrar</button>
            <!-- Mensaje de confirmación -->
            <div v-if="registroExitoso" class="confirmacion">
                ¡Registro exitoso! El libro {{ nuevoLibro.titulo }} ha sido registrado.
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
            libros: [],
            nuevoLibro: { idLibro: null, titulo: '', autor: '', genero: '', cantPaginas: null, sinopsis: '' },
            registroExitoso: false, // Propiedad para el mensaje de éxito
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

        crearLibro() {
            console.log('Nuevo libro a agregar:', this.nuevoLibro);
            this.$axios.post('http://localhost:8080/libros/crear', this.nuevoLibro)

                .then(() => {
                    this.obtenerLibros();
                    this.nuevoLibro = { titulo: '', autor: '', genero: '', cantPaginas: null, sinopsis: '' };
                    this.registroExitoso = true;
                    // Limpia el mensaje después de unos segundos
                    setTimeout(() => {
                        this.registroExitoso = false;
                    }, 5000);
                })

                .catch(error => {
                    console.error('Error al agregar libro:', error);
                    console.log('Respuesta del servidor:', error.response); // Agregado para imprimir la respuesta del servidor
                    console.log('Data del error:', error.data);
                    console.log('Datos enviados desde Vue.js:', this.nuevoLibro);
                    console.log('Configuración de la solicitud:', error.config);

                });
        },

        created() {
            this.obtenerLibros();
        },
    }
}

</script>
<style scoped>
.libros-container {
    font-family: Arial, sans-serif;
    background-color: #f7f7f7;
    min-height: 40vh;
    padding: 10px;
    display: flex;
    flex-direction: column;
    justify-content: flex-start;
    align-items: center;
}

.nuevo-libro {
    background-color: #fff;
    padding: 20px;
    border-radius: 5px;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
    margin-bottom: 0px;
    width: 100%;
    /* Ocupa todo el ancho disponible */
    max-width: 600px;
    /* Limita el ancho para una mejor legibilidad */
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

.agregar {
    background-color: #4caf50;
    /* Verde */
    color: white;
    padding: 10px 20px;
    border: none;
    border-radius: 5px;
    cursor: pointer;
    font-size: 1em;
    transition: background-color 0.3s;
}

.agregar:hover {
    background-color: #45a049;
    /* Verde más oscuro al pasar el mouse */
}

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

.btn-back {
    margin-bottom: 0;
}
</style>
  
  