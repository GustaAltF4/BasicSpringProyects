document.addEventListener('DOMContentLoaded', (event) => {
    const links = document.querySelectorAll('nav ul li a');

    links.forEach(link => {
        link.addEventListener('click', function (e) {
            // Evita la acción por defecto del enlace
            e.preventDefault();

            // Remueve la clase 'seleccionado' de todos los enlaces
            links.forEach(link => link.classList.remove('seleccionado'));

            // Agrega la clase 'seleccionado' al enlace clicado
            this.classList.add('seleccionado');

            // Redirecciona después de un corto retraso para permitir la animación
            const href = this.getAttribute('href');
            setTimeout(() => {
                window.location.href = href;
            }, 500); // Tiempo debe coincidir con la duración de la transición en el CSS
        });
    });
});

let nitch = "Nitch Rosenrrot";

document.getElementById('Titulo').textContent= nitch


