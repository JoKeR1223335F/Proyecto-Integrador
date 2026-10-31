
document.addEventListener("DOMContentLoaded", () => {
    cargarDestinos();
});

const API_DESTINOS = "/api/destinos";
const API_IMAGENES = "/api/destino-imagenes/destino";

const IMAGEN_DEFAULT =
    "https://placehold.co/600x400?text=Destino";

// ======================================
// CARGAR DESTINOS
// ======================================

async function cargarDestinos() {

    const container = document.getElementById("destinos-container");

    if (!container) return;

    try {

        container.innerHTML = "<p>Cargando destinos...</p>";

        const response = await fetch(API_DESTINOS);

        if (!response.ok) {
            throw new Error("No se pudieron cargar los destinos");
        }

        const destinos = await response.json();

        if (destinos.length === 0) {
            container.textContent =
                "Todavía no hay destinos disponibles.";
            return;
        }

        // Obtener imágenes de los primeros 8 destinos
        const destinosConImagen = await Promise.all(
            destinos.slice(0, 8).map(async destino => {

                const imagen = await obtenerImagenPrincipal(
                    destino.idDestino
                );

                return {
                    ...destino,
                    imagenPrincipal: imagen
                };

            })
        );

        container.innerHTML = "";

        destinosConImagen.forEach(destino => {

            const card = document.createElement("article");
            card.className = "destination-card";

            // IMAGEN
            const imagen = document.createElement("img");

            imagen.src = destino.imagenPrincipal || IMAGEN_DEFAULT;
            imagen.alt = destino.nombre || "Destino turístico";
            imagen.loading = "lazy";

            imagen.onerror = () => {
                imagen.onerror = null;
                imagen.src = IMAGEN_DEFAULT;
            };

            // INFORMACIÓN
            const info = document.createElement("div");
            info.className = "destination-info";

            const titulo = document.createElement("h3");
            titulo.textContent = destino.nombre || "Destino";

            const descripcion = document.createElement("p");
            descripcion.textContent =
                destino.descripcion || "Descubre este destino.";

            // UBICACIÓN
            const ubicacion = document.createElement("span");
            ubicacion.className = "destination-location";

            ubicacion.textContent =
                "📍 " + (destino.ubicacion || "Ubicación no disponible");

            // CATEGORÍA
            const categoria = document.createElement("span");
            categoria.className = "destination-category";

            categoria.textContent =
                destino.categoriaDestino?.descripcion || "Destino";

            // BOTÓN
            const boton = document.createElement("a");
            boton.href = "/destinos";
            boton.className = "destination-link";
            boton.textContent = "Explorar destino";

            info.append(
                categoria,
                titulo,
                descripcion,
                ubicacion,
                boton
            );

            card.append(imagen, info);

            container.appendChild(card);

        });

    } catch (error) {

        console.error("Error cargando destinos:", error);

        container.textContent =
            "No fue posible cargar los destinos.";

    }
}

// ======================================
// OBTENER IMAGEN PRINCIPAL
// ======================================

async function obtenerImagenPrincipal(idDestino) {

    try {

        const response = await fetch(
            `${API_IMAGENES}/${idDestino}`
        );

        if (!response.ok) {
            return IMAGEN_DEFAULT;
        }

        const imagenes = await response.json();

        if (!Array.isArray(imagenes) || imagenes.length === 0) {
            return IMAGEN_DEFAULT;
        }

        // Buscar imagen principal
        const principal = imagenes.find(
            imagen => imagen.esPrincipal === true
        );

        // Si no hay principal, seleccionar la primera por orden
        const imagenSeleccionada = principal ||
            [...imagenes].sort(
                (a, b) => a.orden - b.orden
            )[0];

        return imagenSeleccionada?.urlImagen || IMAGEN_DEFAULT;

    } catch (error) {

        console.error(
            `Error obteniendo imagen del destino ${idDestino}:`,
            error
        );

        return IMAGEN_DEFAULT;
    }
}
