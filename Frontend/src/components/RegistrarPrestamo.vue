<template>
    <div>
        <h2>Prestamos</h2>
        <!-- Contenido del componente -->
        <router-link to="/inicio" class="back-link">Volver</router-link>
        <div class="prestamo-container">
            <div class="nuevo-prestamo">
                <h3>Registrar Nuevo Préstamo</h3>
                <!-- Agregar campos para ingresar información del nuevo préstamo -->
                <div class="form-group">
                    <label for="fechaPrestamo">Fecha de Préstamo:</label>
                    <input type="date" v-model="nuevoPrestamo.fechaPrestamo" id="fechaPrestamo" />
                </div>

                <div class="form-group">
                    <label for="fechaDevolucion">Fecha de Devolución:</label>
                    <input type="date" v-model="nuevoPrestamo.fechaDevolucion" id="fechaDevolucion" />
                </div>

                <div v-for="(libro, index) in nuevoPrestamo.libros" :key="index">
                    <div class="form-group">
                        <label :for="'idLibro' + index">ID del Libro {{ index + 1 }}:</label>
                        <select v-model="libro.idLibro" @change="cargarTituloLibro(index)" :id="'idLibro' + index">
                            <option v-for="libroOption in libros" :key="libroOption.idLibro" :value="libroOption.idLibro">
                                {{ libroOption.idLibro }}
                            </option>
                        </select>
                    </div>

                    <div class="form-group">
                        <label :for="'tituloLibro' + index">Título del Libro {{ index + 1 }}:</label>
                        <input type="text" v-model="libro.titulo" :id="'tituloLibro' + index" readonly />
                    </div>
                </div>

                <button class="agregar" @click="agregarLibro">Agregar Libro</button>
                <button class="agregar" @click="registrarPrestamo">Registrar prestamo</button>

                <!-- Mensaje de confirmación -->
                <div v-if="registroExitoso" class="confirmacion">
                    ¡Registro exitoso! El préstamo del libro ha sido registrado.
                </div>
            </div>
        </div>
    </div>
</template>
  
<script>
export default {
    data() {
        return {
            prestamos: [],
            libros: [],
            nuevoPrestamo: {
                idPrestamo: null,
                fechaPrestamo: '',
                fechaDevolucion: '',
                libros: [{ idLibro: '', titulo: '' }],
            },
            registroExitoso: false, // Propiedad para el mensaje de éxito
        };
    },
    methods: {
        obtenerPrestamos() {
            this.$axios.get('http://localhost:8080/prestamos/traer')
                .then(response => {
                    console.log('Respuesta de la API:', response.data);
                    this.prestamos = response.data;
                });
        },

        cargarTituloLibro(index) {
            const libroSeleccionado = this.libros.find(libro => libro.idLibro === this.nuevoPrestamo.libros[index].idLibro);
            if (libroSeleccionado) {
                this.nuevoPrestamo.libros[index].titulo = libroSeleccionado.titulo;
            } else {
                this.nuevoPrestamo.libros[index].titulo = '';
            }
        },
        obtenerLibros() {
            this.$axios.get('http://localhost:8080/libros/traer')
                .then(response => {
                    console.log('Respuesta de la API (Libros):', response.data);
                    this.libros = response.data;
                });
        },
        registrarPrestamo() {
            console.log('Nuevo préstamo a registrar:', this.nuevoPrestamo);

            // Enviar la solicitud POST para crear un nuevo préstamo
            this.$axios.post('http://localhost:8080/prestamos/crear', this.nuevoPrestamo)
                .then(response => {
                    console.log('Respuesta del servidor:', response.data);

                    // Actualizar la lista de préstamos con el nuevo préstamo registrado
                    this.prestamos.push(response.data);
                    this.registroExitoso = true;

                    // Limpia el mensaje después de unos segundos
                    setTimeout(() => {
                        this.registroExitoso = false;
                    }, 3000);

                    // Limpia el formulario después del registro
                    this.nuevoPrestamo = {
                        idPrestamo: null,
                        fechaPrestamo: '',
                        fechaDevolucion: '',
                        libro: { idLibro: '', titulo: '' },
                    };
                })
                .catch(error => {
                    console.error('Error al registrar préstamo:', error);
                    console.log('Error completo:', error.response);
                });

        },
        agregarLibro() {
            if (this.nuevoPrestamo.libros.length < 3) {
                this.nuevoPrestamo.libros.push({ idLibro: '', titulo: '' });
            }
        },
    },

    created() {
        // Carga la lista de préstamos y la lista de libros al crear el componente
        this.obtenerPrestamos();
        this.obtenerLibros();
    },

};
</script>
  
<style scoped>
/* Estilos para el componente RegistrarPrestamo */
.prestamo-container {
    font-family: Arial, sans-serif;
    background-color: #f7f7f7;
    min-height: 100vh;
    padding: 50px;
    display: flex;
    flex-direction: column;
    justify-content: flex-start;
    align-items: center;
}

.nuevo-prestamo {
    background-color: #fff;
    padding: 20px;
    border-radius: 5px;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
    margin-bottom: 20px;
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

.form-group input,
.form-group select {
    width: 100%;
    padding: 8px;
    box-sizing: border-box;
    margin-bottom: 10px;
}

.agregar {
    background-color: #4caf50;
    color: white;
    padding: 10px 20px;
    border: none;
    border-radius: 5px;
    cursor: pointer;
    font-size: 1em;
    transition: background-color 0.3s;
    margin-bottom: 10px;
    /* Agregando margen inferior */
    margin-right: 30px;
}

.agregar:hover {
    background-color: #45a049;
}

/* Verde más oscuro al pasar el mouse */

.confirmacion {
    background-color: #d4edda;
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
  