# TPfinal - Backend Java con Spring Boot y Frontend Vue.js

## Configuración del Entorno

Este proyecto utiliza Spring Boot para el backend y Vue.js para el frontend. Ambos se ejecutan en contenedores Docker para el backend y de forma local para el frontend.

### Construir la Imagen y Ejecutar con Docker Compose

El archivo `Dockerfile` y `docker-compose.yml` ya están creados en la raíz del proyecto para el backend. Aquí tienes las instrucciones para el frontend:

### Backend (Java con Spring Boot)

1. **Clonar este repositorio:**

    ```bash
    git clone <URL_DEL_REPOSITORIO>
    ```

2. **Navegar al directorio del proyecto:**

    ```bash
    cd NOMBRE_DE_TU_PROYECTO
    ```

3. **Construir y levantar los contenedores con Docker Compose:**

    ```bash
    docker-compose up -d --build
    ```

    Esto construirá la imagen del backend y levantará tanto el servicio Java (backend) como la base de datos PostgreSQL.

### Frontend (Vue.js)

El frontend se ejecuta localmente sin Docker Compose. Aquí tienes los pasos:

1. **Navegar al directorio del proyecto Vue:**

    ```bash
    cd path/a/tu/proyecto/vue
    ```

2. **Construir la imagen del frontend:**

    ```bash
    docker build -t "nombre_de_la_imagen" .
    ```

3. **Ejecutar la aplicación Vue.js:**

    ```bash
    docker run -p 5173:5173 "nombre_de_la_imagen"
    ```

Esto construirá la imagen del frontend y ejecutará la aplicación Vue.js. Asegúrate de que el servicio Java (backend) esté en ejecución y configurado correctamente antes de iniciar la aplicación Vue.

### Cómo Detener la Aplicación Completa

Para detener la aplicación y los contenedores de Docker, ejecuta el siguiente comando desde el directorio principal del proyecto:

```bash
docker-compose down -v


    
    
    
    
    
    
    
    
    
