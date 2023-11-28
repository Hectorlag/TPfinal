# TPfinal
# Configuración entorno java backend con Spring Boot

# Este proyecto consiste en una crud y login para un sistema de una biblioteca

## Tabla de Contenidos

1 [Requisitos Previos](#requisitos-previos)
2 [Configuración del Entorno](#configuración-del-entorno)
3 [Cómo Ejecutar la Aplicación](#cómo-ejecutar-la-aplicación)
4 [Cómo Detener la Aplicación](#cómo-detener-la-aplicación)
--------------------------------------

## Servicio Java (Backend)

Este servicio utiliza Spring Boot y se ejecuta en un contenedor Docker.

### Construir la Imagen
#Crea una archivo dockefile en tu aplicación
# Selecciona la imagen base
  -FROM openjdk:17-jdk-alpine

# Copia el archivo JAR a la imagen
  -COPY target/serviciobiblioteca-0.0.1-SNAPSHOT.jar practico.jar

# Comando para ejecutar tu aplicación
  -ENTRYPOINT ["java", "-jar", "practico.jar"]


## Requisitos Previos

1) Antes de comenzar, asegúrate de tener instalado Docker en tu máquina.
--------------------------------------
 2)Configuración del Entorno

Este proyecto utiliza Docker para gestionar los servicios.

```yaml
version: '3.9'

services:
  # Nuevo servicio (java_app)
  java_app:
    container_name: java_app
    image: hector37/java_app:1.0.0
    build: .
    ports:
      - 8080:8080
    environment:
      - DATABASE_URL=jdbc:postgresql://java_db:5432/postgres
      - DATABASE_USERNAME=postgres
      - DATABASE_PASSWORD=postgres
    depends_on:
      - java_db

  # Antiguo servicio (postgres)
  java_db:
    container_name: java_db
    image: postgres:12
    ports:
      - 5432:5432
    environment:
      POSTGRES_USER: postgres
      POSTGRES_PASSWORD: postgres
      POSTGRES_DB: postgres
    volumes:
      - pgdata:/var/lib/postgresql/data

volumes:
  pgdata: {}
  --------------------------------------
  3)como ejecutar la aplicación
  -Clona este repositorio
  -Abre una terminal y navega hasta el directorio del proyecto
  -Ejecuta el siguiente comando: docker compose up -d java_app
4) Ejecuta el siguiente comando: docker compose down

## Configuración del Entorno Vue

Este proyecto utiliza Vue.js para el frontend. Asegúrate de tener Node.js y npm instalados en tu máquina.

## Configuración del Entorno

El servicio de Vue se comunica con el servicio Java a través del puerto 8080. Asegúrate de que este puerto esté disponible y no esté siendo utilizado por otros servicios.

```dockerfile
# Dockerfile para Vue

# Selecciona la imagen base
FROM node:lts-alpine

# Establece un directorio de trabajo
WORKDIR /app

# Copia el archivo package.json y package-lock.json (si está disponible)
COPY package*.json ./

# Instala las dependencias del proyecto
RUN npm install

# Copia el resto de la aplicación
COPY . .

# Expone el puerto que usará tu aplicación
EXPOSE 5173

# Comando para ejecutar tu aplicación
CMD ["npm", "run", "dev"]
---------------------------------------
Cómo Ejecutar la Aplicación Vue

    -Clona este repositorio.
    -Abre una terminal y navega al directorio del proyecto.
    -Navega al directorio de Vue: cd path/a/tu/proyecto/vue
    -Ejecuta el siguiente comando para instalar las dependencias: npm install
    -Ejecuta el siguiente comando para iniciar la aplicación Vue: npm run dev
    -Comando para detener la aplicación vue: control+c
    
#Nota: Asegúrate de que el servicio Java (backend) esté en ejecución y configurado correctamente antes de iniciar la aplicación Vue
    
    
    
    
    
    
    
    
    
