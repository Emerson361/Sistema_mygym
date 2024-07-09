<%-- 
    Document   : main
    Created on : 08/07/2024, 06:50:10 PM
    Author     : Armando Fiestas
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gimnasio MyGym</title>
    <link rel="stylesheet" href="style.css">
   
</head>
<body>
    <div class="video-background">
        <video autoplay loop muted>
            <source src="https://media.istockphoto.com/id/1227517584/ru/%D0%B2%D0%B8%D0%B4%D0%B5%D0%BE/%D0%BA%D0%BE%D0%BD%D1%86%D0%B5%D0%BF%D1%86%D0%B8%D1%8F-%D0%BC%D0%B0%D0%BB%D0%BE%D0%B3%D0%BE-%D0%B1%D0%B8%D0%B7%D0%BD%D0%B5%D1%81%D0%B0-%D0%BC%D0%BD%D0%BE%D0%B3%D0%BE-%D1%82%D1%80%D0%B5%D0%BD%D0%B0%D0%B6%D0%B5%D1%80%D0%BE%D0%B2-%D0%B2-%D0%BF%D1%83%D1%81%D1%82%D0%BE%D0%BC-%D1%82%D1%80%D0%B5%D0%BD%D0%B0%D0%B6%D0%B5%D1%80%D0%BD%D0%BE%D0%BC-%D0%B7%D0%B0%D0%BB%D0%B5.mp4?s=mp4-640x640-is&k=20&c=BPIzMz1oKW66xquNX8uaW6JtrZlKoNwmZpDymsowQLE=" type="video/mp4">
            Tu navegador no soporta la etiqueta de video.
        </video>
    </div>
    <header>
        <div class="container">
            <h1>Gimnasio  MyGym</h1>
            <nav>
                <div class="menu-icon" id="menu-icon">
                    <div class="bar1"></div>
                    <div class="bar2"></div>
                    <div class="bar3"></div>
                </div>
                <ul id="nav-list">
                    <li><a href="#home">Inicio</a></li>
                    <li><a href="#about">Nosotros</a></li>
                    <li><a href="#services">Servicios</a></li>
                    <li><a href="#contact">Contacto</a></li>
                    <li><a href="index.jsp">Iniciar Sesión</a></li>
                </ul>
            </nav>
        </div>
    </header>
    <section id="home">
        <div class="container">
            <h2>Bienvenido a Gimnasio  MyGym</h2>
            <p>Transforma tu vida hoy</p>
        </div>
    </section>
    
    <section id="about">
    <div class="container">
        <h2>Sobre Nosotros</h2>
        <p>Somos un gimnasio dedicado a mejorar tu bienestar físico y mental.</p>
          <img src="https://eshi.es/wp-content/uploads/2019/12/180516-ejercicio-fisico.jpg" alt="Sobre Nosotros">
        <h3>Visión</h3>
        <p>
            Brindar a sus clientes un espacio comprometido con la salud y el bienestar, ofreciendo programas de entrenamiento personalizados, instalaciones de calidad y un ambiente motivador. Nos esforzamos por inspirar y guiar a nuestros miembros hacia un estilo de vida activo y saludable, promoviendo la felicidad y el equilibrio en sus vidas.
        </p>
        <h3>Misión</h3>
        <p>
            Ser reconocidos como el gimnasio preferido en nuestra comunidad, destacando por la excelencia en servicios de entrenamiento, la atención personalizada y el impacto positivo que generamos en la salud de nuestros miembros. Buscamos expandir nuestra influencia en el ámbito local, siendo un referente en la promoción de un estilo de vida saludable y sostenible.
        </p>
        </div>
    </section>
    <section id="services">
        <div class="container">
            <h2>Servicios</h2>
            <div class="service">
                <h3>Entrenamiento Personalizado</h3>
                <p>Planes de entrenamiento adaptados a tus necesidades.</p>
                <img src="https://encolombia.com/wp-content/uploads/2019/01/Tendencias-Deportivas.jpg" alt="Entrenamiento Personalizado">
            </div>
            <div class="service">
                <h3>Clases Grupales</h3>
                <p>Clases de yoga, spinning, y más.</p>
                <img src="https://www.lifestyleupdated.com/wp-content/uploads/2018/07/daily-burn-video-review-900x360.jpg" alt="Clases Grupales">
            </div>
            <div class="service">
                <h3>Nutrición</h3>
                <p>Asesoría nutricional para complementar tu entrenamiento.</p>
                <img src="https://esculpetufisico.com/cdn/shop/articles/Nutricion_gimnasio_deporte_2000x.jpg?v=1685867112" alt="Nutrición">
            </div>
        </div>
    </section>
    <section id="contact">
    <div class="container">
        <h2>Contacto</h2>
        <div class="contact-info">
            <p>Teléfono: +51 994 567 890</p>
            <!-- Aquí se puede agregar cualquier otra información de contacto -->
        </div>
        <form>
                <label for="name">Nombre:</label>
                <input type="text" id="name" name="name" required>
                <label for="email">Email:</label>
                <input type="email" id="email" name="email" required>
                <label for="message">Mensaje:</label>
                <textarea id="message" name="message" required></textarea>
                <button type="submit">Enviar</button>
            </form>
        <div id="map"></div>
      <div class="map-container">
            <iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3973.459278280177!2d-80.65234056538162!3d-5.190252752707615!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x904a1bf3f113dd7d%3A0xc0cb5547db7cfb87!2sCONEXION%20FIT%20PIURA!5e0!3m2!1sen!2spe!4v1720494963417!5m2!1sen!2spe" width="600" height="450" style="border:0;" allowfullscreen="" loading="lazy" referrerpolicy="no-referrer-when-downgrade"></iframe>
        </div>
    </div>
</section>

   
    <footer>
        <div class="container">
            <p>&copy; 2024 Gimnasio  MyGym. Todos los derechos reservados.</p>
        </div>
    </footer>
    <script>document.getElementById("menu-icon").addEventListener("click", function() {
    var navList = document.getElementById("nav-list");
    navList.classList.toggle("show");
});</script>
</body>
</html>
