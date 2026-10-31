
const API_DESTINOS = "/api/destinos";
const API_CATEGORIAS = "/api/categorias-destino";

let destinos = [];
let categoriaSeleccionada = null;

document.addEventListener("DOMContentLoaded", () => {
    cargarCategorias();
    cargarDestinos();

    document.getElementById("buscador")
        .addEventListener("input", filtrarDestinos);
});

async function cargarDestinos() {
    try {
        const respuesta = await fetch(API_DESTINOS);

        if (!respuesta.ok) {
            throw new Error("Error al consultar destinos");
        }

        destinos = await respuesta.json();

        document.getElementById("cargando").remove();

        mostrarDestinos(destinos);

    } catch (error) {
        console.error(error);

        document.getElementById("contenedorDestinos").innerHTML = `
            <div class="col-12 text-center text-danger">
                No fue posible cargar los destinos.
            </div>
        `;
    }
}

async function cargarCategorias() {
    try {
        const respuesta = await fetch(API_CATEGORIAS);

        if (!respuesta.ok) {
            throw new Error("Error al consultar categorías");
        }

        const categorias = await respuesta.json();

        const contenedor =
            document.getElementById("filtrosCategorias");

        contenedor.innerHTML = `
            <button class="btn btn-primary btn-filtro"
                    onclick="seleccionarCategoria(null)">
                Todas
            </button>
        `;

        categorias.forEach(categoria => {
            contenedor.innerHTML += `
                <button class="btn btn-outline-primary btn-filtro"
                        onclick="seleccionarCategoria(${categoria.idCategoria})">
                    ${categoria.nombre}
                </button>
            `;
        });

    } catch (error) {
        console.error("Error cargando categorías:", error);
    }
}

function mostrarDestinos(lista) {
    const contenedor =
        document.getElementById("contenedorDestinos");

    const sinResultados =
        document.getElementById("sinResultados");

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
            destino.categoriaDestino?.nombre || "Destino";

        const tarjeta = `
            <div class="col-md-6 col-lg-4">
                <div class="card destino-card shadow-sm">

                    <img src="${imagen}"
                         alt="${destino.nombre}"
                         onerror="this.src='https://placehold.co/600x400?text=Destino'">

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

                        <button class="btn btn-outline-primary w-100"
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

function seleccionarCategoria(id) {
    categoriaSeleccionada = id;
    filtrarDestinos();
}

function filtrarDestinos() {
    const busqueda = document.getElementById("buscador")
        .value.toLowerCase();

    const filtrados = destinos.filter(destino => {

        const coincideNombre =
            destino.nombre?.toLowerCase().includes(busqueda);

        const coincideCategoria =
            categoriaSeleccionada === null ||
            destino.categoriaDestino?.idCategoria === categoriaSeleccionada;

        return coincideNombre && coincideCategoria;
    });

    mostrarDestinos(filtrados);
}

function verDetalles(id) {
    const destino = destinos.find(d => d.idDestino === id);

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
