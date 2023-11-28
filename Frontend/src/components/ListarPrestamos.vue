<template>
    <div class="lista-prestamos">
        <h1>Lista de Préstamos</h1>
        <table class="prestamos-table">
            <thead>
                <tr>
                    <th>Id</th>
                    <th>Fecha de Préstamo</th>
                    <th>Fecha de Devolución</th>
                    <th>Ids Libros</th>
                    <th>Titulos</th>
                    <th>Acciones</th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="prestamo in prestamos" :key="prestamo.idPrestamo">
                    <td>{{ prestamo.idPrestamo }}</td>
                    <td>{{ prestamo.fechaPrestamo }}</td>
                    <td>{{ prestamo.fechaDevolucion }}</td>
                    <td>
                        <ul>
                            <li v-for="libro in prestamo.libros" :key="libro.id">
                                {{ libro.id }}
                            </li>
                        </ul>
                    </td>
                    <td>
                        <ul>
                            <li v-for="libro in prestamo.libros" :key="libro.id">
                                {{ libro.titulo }}
                            </li>
                        </ul>
                    </td>
                    <td>
                        <button class="editar" @click="editarPrestamo(prestamo)">Editar</button>
                        <button class="eliminar" @click="eliminarPrestamo(prestamo.idPrestamo)">Eliminar</button>
                    </td>
                </tr>
            </tbody>
        </table>
    </div>
    <!-- Formulario para editar un préstamo (se muestra solo cuando se selecciona "Editar") -->
    <div v-if="prestamoAEditar" class="editar-prestamo">
        <div class="formulario-container">
            <h3>Editar Préstamo</h3>
            <!-- Campos de edición del préstamo -->
            <div class="form-group">
                <label for="idPrestamo">ID:</label>
                <input v-model="prestamoAEditar.idPrestamo" id="idPrestamo" type="text" readonly />
            </div>
            <div class="form-group">
                <label for="fechaPrestamo">Fecha de Préstamo:</label>
                <input v-model="prestamoAEditar.fechaPrestamo" id="fechaPrestamo" type="date" />
            </div>
            <div class="form-group">
                <label for="fechaDevolucion">Fecha de Devolución:</label>
                <input v-model="prestamoAEditar.fechaDevolucion" id="fechaDevolucion" type="date" />
            </div>
            <!-- Campos para editar libros asociados al préstamo -->
            <div v-for="(libro, index) in prestamoAEditar.libros" :key="index">
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
                    <input v-model="libro.titulo" :id="'tituloLibro' + index" readonly />
                </div>
            </div>
            <div class="form-group">
                <button @click="actualizarPrestamo">Actualizar</button>
                <button @click="cancelarEdicion">Cancelar</button>
            </div>
            <div v-if="mensajeExito" class="exito-mensaje">{{ mensajeExito }}</div>
        </div>
    </div>
</template>
    
<script>
export default {
    data() {
        return {
            prestamos: [],
            prestamoAEditar: null,
            libros: [], // Agregué la lista de libros para la edición
            mensajeExito: null,
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

        obtenerDetallesLibro(index) {
            if (index >= 0 && index < this.libros.length) {
                const idLibro = this.librosId[index];
                if (idLibro) {
                    this.$axios.get(`http://localhost:8080/libros/traer/${idLibro}`)
                        .then(response => {
                            const libro = response.data;
                            this.$set(this.librosTitulo, index, libro.titulo);
                        })
                        .catch(error => {
                            if (error.response && error.response.status === 404) {
                                console.error('Libro no encontrado');
                            } else {
                                console.error('Error al obtener detalles del libro:', error);
                            }
                        });
                }
            }
        },

        cargarListaLibros() {
            this.$axios.get('http://localhost:8080/libros/traer')
                .then(response => {
                    this.libros = response.data;
                })
                .catch(error => {
                    console.error('Error al obtener la lista de libros:', error);
                });
        },

        editarPrestamo(prestamo) {
            // Copia profunda para evitar modificar directamente el objeto en la lista
            this.prestamoAEditar = JSON.parse(JSON.stringify(prestamo));
        },

        actualizarPrestamo() {
            console.log('Datos a actualizar:', this.prestamoAEditar);

            // Realiza la llamada PUT para actualizar el préstamo
            this.$axios.put(`http://localhost:8080/prestamos/editar/${this.prestamoAEditar.idPrestamo}`, this.prestamoAEditar)
                .then(() => {
                    this.obtenerPrestamos();
                    this.prestamoAEditar = null;
                    this.mensajeExito = '¡Préstamo editado correctamente!';
                    setTimeout(() => {
                        this.mensajeExito = null;
                    }, 3000);
                })
                .catch(error => {
                    console.error('Error al actualizar el préstamo:', error);
                    console.log('Respuesta del servidor:', error.response);
                });
        },

        cancelarEdicion() {
            this.prestamoAEditar = null;
        },

        eliminarPrestamo(idPrestamo) {
            if (window.confirm('¿Estás seguro de que deseas eliminar este préstamo?')) {
                this.$axios.delete(`http://localhost:8080/prestamos/borrar/${idPrestamo}`)
                    .then(response => {
                        console.log('Préstamo eliminado correctamente:', response);
                        this.obtenerPrestamos();
                    })
                    .catch(error => {
                        console.error('Error al eliminar el préstamo:', error);
                    });
            }
        },
    },

    created() {
        this.obtenerPrestamos();
        this.cargarListaLibros();
    },
};
</script>

  
<style scoped>
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

.lista-prestamos {
    margin-top: 20px;
}

/* Estilos para los botones editar y eliminar */
.editar,
.eliminar {
    padding: 8px 16px;
    /* Aumenté el padding para hacer los botones más grandes */
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

form-group {
    margin-bottom: 15px;
}

.form-group label {
    display: block;
    margin-bottom: 5px;
}

.form-group input {
    width: 70%;
    padding: 8px;
    box-sizing: border-box;
    margin-bottom: 6px;
}

.lista-prestamos {
    margin-top: 10px;
}

/* Estilos para los botones editar y eliminar */
.editar,
.eliminar {
    padding: 5px 10px;
    font-size: 0.9em;
    border: none;
    cursor: pointer;
    border-radius: 3px;
    transition: background-color 0.2s;
    margin-right: 5px;
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

/* Estilos para la tabla */
.prestamos-table {
    width: 100%;
    /* Reduje el ancho de la tabla */
    margin-top: 20px;
    border-collapse: collapse;
}

th,
td {
    border: 1px solid #ddd;
    padding: 8px;
    text-align: left;
}

.editar-prestamo {
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
}

.exito-mensaje {
    margin-top: 10px;
    color: green;
}

.editar-prestamo {
    /* Centrar el formulario en la pantalla */
    display: flex;
    justify-content: flex-end;
    /* Alinea el contenido a la derecha */
    padding: 20px;
    /* Añade un poco de espacio alrededor del formulario */
}

.formulario-container {
    width: 100%;
    /* Puedes ajustar el ancho según tus necesidades */
    background-color: #fff;
    /* Fondo blanco para el formulario */
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    /* Sombra para resaltar el formulario */
    padding: 20px;
    /* Añade espacio alrededor del contenido del formulario */
}

.back-link {
    text-decoration: none;
    background-color: #4caf50;
    color: white;
    padding: 8px 16px;
    border-radius: 5px;
    transition: background-color 0.3s;
    margin-top: 10px;
}

.back-link:hover {
    background-color: #45a049;
}

.btn-back {
    margin-top: 40px;
}
</style>
  
  
  