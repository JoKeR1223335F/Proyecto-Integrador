
const API_DESTINOS = "/api/destinos";
const API_CATEGORIAS = "/api/categorias-destino";
const API_IMAGENES = "/api/destino-imagenes/destino";

let destinos = [];
let categorias = [];
let categoriaSeleccionada = null;

document.addEventListener("DOMContentLoaded", () => {
    cargarCategorias();
    cargarDestinos();

    document.getElementById("buscador")
        .addEventListener("input", filtrarDestinos);
});

// ======================================
// CARGAR DESTINOS E IMÁGENES
// ======================================

async function cargarDestinos() {
    const contenedor = document.getElementById("contenedorDestinos");

    try {
        const respuesta = await fetch(API_DESTINOS);

        if (!respuesta.ok) {
            throw new Error("Error al consultar destinos");
        }

        destinos = await respuesta.json();

        // Consultar imágenes de cada destino
        destinos = await Promise.all(
            destinos.map(async destino => {
                try {
                    const respuestaImagen = await fetch(
                        `${API_IMAGENES}/${destino.idDestino}`
                    );

                    if (!respuestaImagen.ok) {
                        return {
                            ...destino,
                            imagenPrincipal: null
                        };
                    }

                    const imagenes = await respuestaImagen.json();

                    const principal = imagenes.find(
                        imagen => imagen.esPrincipal === true
                    );

                    const imagen = principal ||
                        imagenes.sort((a, b) => a.orden - b.orden)[0];

                    return {
                        ...destino,
                        imagenPrincipal: imagen?.urlImagen || null
                    };

                } catch (error) {
                    console.error(
                        `Error cargando imágenes del destino ${destino.idDestino}`,
                        error
                    );

                    return {
                        ...destino,
                        imagenPrincipal: null
                    };
                }
            })
        );

        const cargando = document.getElementById("cargando");

        if (cargando) {
            cargando.remove();
        }

        mostrarDestinos(destinos);

    } catch (error) {
        console.error(error);

        contenedor.innerHTML = `
            <div class="col-12 text-center text-danger">
                No fue posible cargar los destinos.
            </div>
        `;
    }
}

// ======================================
// CARGAR CATEGORÍAS
// ======================================

async function cargarCategorias() {
    try {
        const respuesta = await fetch(API_CATEGORIAS);

        if (!respuesta.ok) {
            throw new Error("Error al consultar categorías");
        }

        categorias = await respuesta.json();

        const contenedor = document.getElementById("filtrosCategorias");

        contenedor.innerHTML = "";

        // Botón para mostrar todas las categorías
        contenedor.innerHTML += `
            <button
                class="btn btn-primary btn-filtro"
                onclick="seleccionarCategoria(null)"
                id="categoria-todas">
                Todas
            </button>
        `;

        // Crear botones dinámicamente
        categorias.forEach(categoria => {

            contenedor.innerHTML += `
                <button
                    class="btn btn-outline-primary btn-filtro"
                    onclick="seleccionarCategoria('${categoria.codigo}')"
                    id="categoria-${categoria.codigo}">
                    ${categoria.descripcion}
                </button>
            `;

        });

    } catch (error) {
        console.error("Error cargando categorías:", error);
    }
}

// ======================================
// SELECCIONAR CATEGORÍA
// ======================================

function seleccionarCategoria(codigo) {

    categoriaSeleccionada = codigo;

    // Actualizar apariencia de los botones
    document.querySelectorAll(".btn-filtro").forEach(boton => {
        boton.classList.remove("btn-primary");
        boton.classList.add("btn-outline-primary");
    });

    const botonActivo = document.getElementById(
        codigo ? `categoria-${codigo}` : "categoria-todas"
    );

    if (botonActivo) {
        botonActivo.classList.remove("btn-outline-primary");
        botonActivo.classList.add("btn-primary");
    }

    filtrarDestinos();
}

// ======================================
// MOSTRAR DESTINOS
// ======================================

function mostrarDestinos(lista) {

    const contenedor = document.getElementById("contenedorDestinos");
    const sinResultados = document.getElementById("sinResultados");

    contenedor.innerHTML = "";

    if (lista.length === 0) {
        sinResultados.classList.remove("d-none");
        return;
    }

    sinResultados.classList.add("d-none");

    lista.forEach(destino => {

        const imagen = destino.imagenPrincipal ||
            "https://placehold.co/600x400?text=Destino";

        const nombreCategoria =
            destino.categoriaDestino?.descripcion || "Destino";

        const tarjeta = `
            <div class="col-md-6 col-lg-4">
                <div class="card destino-card shadow-sm">

                    <img
                        src="${imagen}"
                        alt="${destino.nombre}"
                        onerror="this.onerror=null;this.src='https://placehold.co/600x400?text=Destino'">

                    <div class="card-body">

                        <span class="badge bg-primary mb-2">
                            ${nombreCategoria}
                        </span>

                        <h5 class="card-title fw-bold">
                            ${destino.nombre}
                        </h5>

                        <p class="destino-descripcion">
                            ${destino.descripcion || "Descubre este increíble destino."}
                        </p>

                        <p class="text-muted small">
                            📍 ${destino.ubicacion || "Ubicación no disponible"}
                        </p>

                        <button
                            class="btn btn-outline-primary w-100"
                            onclick="verDetalles(${destino.idDestino})">
                            Ver detalles
                        </button>

                    </div>
                </div>
            </div>
        `;

        contenedor.innerHTML += tarjeta;
    });
}

// ======================================
// FILTRAR DESTINOS
// ======================================

function filtrarDestinos() {

    const busqueda = document.getElementById("buscador")
        .value.trim().toLowerCase();

    const filtrados = destinos.filter(destino => {

        // Buscar por nombre
        const coincideNombre =
            destino.nombre?.toLowerCase().includes(busqueda);

        // Buscar por código de categoría
        const coincideCategoria =
            categoriaSeleccionada === null ||
            destino.categoriaDestino?.codigo === categoriaSeleccionada;

        return coincideNombre && coincideCategoria;
    });

    mostrarDestinos(filtrados);
}

// ======================================
// VER DETALLES
// ======================================

function verDetalles(id) {

    const destino = destinos.find(
        d => d.idDestino === id
    );

    if (!destino) return;

    document.getElementById("modalTitulo").textContent =
        destino.nombre;

    document.getElementById("modalDescripcion").textContent =
        destino.descripcion || "Sin descripción disponible.";

    document.getElementById("modalUbicacion").textContent =
        destino.ubicacion || "Ubicación no disponible";

    document.getElementById("modalImagen").src =
        destino.imagenPrincipal ||
        "https://placehold.co/600x400?text=Destino";

    const modal = new bootstrap.Modal(
        document.getElementById("modalDestino")
    );

    modal.show();
}
