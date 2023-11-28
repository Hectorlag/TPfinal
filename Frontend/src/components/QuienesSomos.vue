<template>
  <div>
    <h1>Nosotros</h1>
    <!-- Contenido del componente -->
    <router-link to="/inicio" class="back-link">Volver</router-link>
  </div>
  <div class="quienes-somos-container">
    <h2>Quiénes Somos</h2>
    <p>Bienvenido a nuestra biblioteca, un lugar dedicado a la promoción de la lectura y el conocimiento. En nuestra
      biblioteca, nos esforzamos por ofrecer un ambiente acogedor donde los amantes de los libros pueden explorar,
      aprender y disfrutar de la vasta riqueza de la literatura.</p>

    <h2>Nuestra Misión</h2>
    <p>Nuestra misión es fomentar el amor por la lectura, proporcionar acceso a una amplia variedad de recursos y crear un
      espacio comunitario donde las ideas puedan florecer. Creemos en el poder transformador de los libros y su capacidad
      para enriquecer nuestras vidas.</p>

    <h2>Nuestra Colección</h2>
    <p>Contamos con una extensa colección de libros que abarca diversos géneros y temas. Desde clásicos atemporales hasta
      las últimas novedades, nuestra biblioteca está diseñada para satisfacer los intereses y necesidades de lectores de
      todas las edades.</p>

    <h2>Eventos y Actividades</h2>
    <p>Además de ofrecer una amplia gama de libros, organizamos eventos y actividades que fomentan la participación de la
      comunidad. Desde clubes de lectura hasta charlas con autores, nuestro objetivo es crear una experiencia
      enriquecedora para todos nuestros visitantes.</p>

    <h2>Únete a Nosotros</h2>
    <p>¡Te invitamos a ser parte de nuestra comunidad! Ya seas un ávido lector, un entusiasta de la literatura o
      simplemente alguien que busca un lugar tranquilo para estudiar, estamos aquí para ti. Únete a nosotros en nuestra
      misión de hacer que la lectura sea accesible y emocionante para todos.</p>
  </div>
</template>
  
<style scoped>
.quienes-somos-container {
  max-width: 800px;
  margin: 0 auto;
  padding: 20px;
  background-color: #f9f9f9;
  border-radius: 8px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

h1,
h2 {
  color: #333;
}

p {
  margin-bottom: 20px;
  line-height: 1.6;
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
  