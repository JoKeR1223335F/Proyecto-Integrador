document.addEventListener("DOMContentLoaded", () => {

    const API_URL = "/api/publicaciones";

    const contenedor = document.getElementById("contenedorPublicaciones");
    const buscador = document.getElementById("buscarPublicacion");
    const btnBuscar = document.getElementById("btnBuscarPublicacion");
    const sinResultados = document.getElementById("sinPublicaciones");

    let publicaciones = [];
    let filtroActual = "TODAS";

    // CARGAR PUBLICACIONES
    async function cargarPublicaciones() {

        try {

            const respuesta = await fetch(API_URL);

            if (!respuesta.ok) {
                throw new Error("Error al obtener publicaciones");
            }

            publicaciones = await respuesta.json();

            renderizarPublicaciones(publicaciones);

        } catch (error) {

            console.error(error);

            contenedor.innerHTML = `
                <div class="alert alert-danger">
                    No fue posible cargar las publicaciones.
                </div>
            `;
        }
    }

    // RENDERIZAR
    function renderizarPublicaciones(lista) {

        contenedor.innerHTML = "";

        if (!lista || lista.length === 0) {

            sinResultados.classList.remove("d-none");
            return;
        }

        sinResultados.classList.add("d-none");

        lista.forEach(publicacion => {

            const autor = publicacion.visitante?.usuario?.nombreUsuario
                || publicacion.visitante?.nombre
                || "Viajero";

            const fecha = publicacion.publicadaEn
                ? new Date(publicacion.publicadaEn).toLocaleDateString("es-PE")
                : "Fecha no disponible";

            const tarjeta = document.createElement("article");

            tarjeta.className = "publicacion-card";

            const cuerpo = document.createElement("div");
            cuerpo.className = "publicacion-card-body";

            const titulo = document.createElement("h3");
            titulo.textContent = publicacion.titulo || "Sin título";

            const contenido = document.createElement("p");
            contenido.className = "publicacion-contenido";
            contenido.textContent = publicacion.contenido || "";

            const meta = document.createElement("div");
            meta.className = "publicacion-meta";

            const autorElemento = document.createElement("span");
            autorElemento.className = "publicacion-autor";
            autorElemento.textContent = autor;

            const fechaElemento = document.createElement("span");
            fechaElemento.textContent = fecha;

            meta.append(autorElemento, fechaElemento);

            const boton = document.createElement("a");
            boton.className = "btn-ver-publicacion";
            boton.textContent = "Leer publicación →";

            boton.addEventListener("click", () => {
                mostrarDetalle(publicacion);
            });

            cuerpo.append(titulo, contenido, meta, boton);
            tarjeta.appendChild(cuerpo);

            contenedor.appendChild(tarjeta);

        });
    }

    // BUSCAR
    function buscarPublicaciones() {

        const texto = buscador.value.toLowerCase().trim();

        let resultado = publicaciones.filter(publicacion => {

            const titulo = (publicacion.titulo || "").toLowerCase();
            const contenido = (publicacion.contenido || "").toLowerCase();

            return titulo.includes(texto) || contenido.includes(texto);
        });

        aplicarFiltro(resultado);
    }

    // FILTROS
    function aplicarFiltro(lista) {

        let resultado = [...lista];

        if (filtroActual === "RECIENTES") {

            resultado.sort((a, b) =>
                new Date(b.publicadaEn || b.creadoEn)
                - new Date(a.publicadaEn || a.creadoEn)
            );

        }

        renderizarPublicaciones(resultado);
    }

    document.querySelectorAll(".filtro-publicacion").forEach(boton => {

        boton.addEventListener("click", () => {

            document.querySelectorAll(".filtro-publicacion")
                .forEach(item => item.classList.remove("active"));

            boton.classList.add("active");

            filtroActual = boton.dataset.filtro;

            buscarPublicaciones();
        });
    });

    btnBuscar.addEventListener("click", buscarPublicaciones);

    buscador.addEventListener("input", buscarPublicaciones);

    // DETALLE
    function mostrarDetalle(publicacion) {

        document.getElementById("modalTitulo").textContent =
            publicacion.titulo || "Sin título";

        document.getElementById("modalContenido").textContent =
            publicacion.contenido || "";

        document.getElementById("modalAutor").textContent =
            "Publicado por: " +
            (publicacion.visitante?.usuario?.nombreUsuario
                || publicacion.visitante?.nombre
                || "Viajero");

        document.getElementById("modalFecha").textContent =
            publicacion.publicadaEn
                ? new Date(publicacion.publicadaEn).toLocaleString("es-PE")
                : "Fecha no disponible";

        const modal = bootstrap.Modal.getOrCreateInstance(
            document.getElementById("modalPublicacion")
        );

        modal.show();
    }

    // INICIO
    cargarPublicaciones();

});
