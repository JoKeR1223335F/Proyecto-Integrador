
document.addEventListener("DOMContentLoaded", cargarPerfil);

const API = {
    usuarios: "http://localhost:8080/api/usuarios",
    visitantes: "/api/visitantes",
    preferencias: "/api/preferencias-visitante",
    tipos: "/api/tipos-turismo"
};

// Usuario fijo
const USUARIO_ID = 3;

async function obtener(url) {
    const response = await fetch(url);

    if (!response.ok) {
        throw new Error(`Error ${response.status}: ${url}`);
    }

    return response.json();
}

async function cargarPerfil() {
    const pagina = document.body;

    const visitanteId = pagina.dataset.visitanteId;
    const preferenciaId = pagina.dataset.preferenciaId;

    if (!visitanteId || !preferenciaId) {
        mostrarMensaje(
            "No se encontraron los identificadores del perfil.",
            "danger"
        );
        return;
    }

    try {
        const [usuario, visitante, preferencia] = await Promise.all([
            obtener(`${API.usuarios}/${USUARIO_ID}`),
            obtener(`${API.visitantes}/${visitanteId}`),
            obtener(`${API.preferencias}/${preferenciaId}`)
        ]);

        mostrarUsuario(usuario);
        mostrarVisitante(visitante);
        mostrarPreferencia(preferencia);

    } catch (error) {
        console.error("Error al cargar el perfil:", error);

        mostrarMensaje(
            "No fue posible cargar la información del perfil.",
            "danger"
        );
    }
}

function mostrarUsuario(usuario) {
    document.getElementById("nombreCompleto").textContent =
        `${usuario.nombres || ""} ${usuario.apellidos || ""}`.trim();

    document.getElementById("nombreUsuario").textContent =
        `@${usuario.usuario || "-"}`;

    document.getElementById("nombres").textContent =
        usuario.nombres || "-";

    document.getElementById("apellidos").textContent =
        usuario.apellidos || "-";

    document.getElementById("correo").textContent =
        usuario.correo || "-";

    document.getElementById("usuario").textContent =
        usuario.usuario || "-";

    document.getElementById("estado").textContent =
        usuario.estado === "A" ? "Activo" : "Inactivo";

    document.getElementById("rol").textContent =
        usuario.rol?.nombreRol || "-";
}

function mostrarVisitante(visitante) {
    document.getElementById("fechaNacimiento").textContent =
        visitante.fechaNacimiento || "-";

    document.getElementById("paisResidencia").textContent =
        visitante.paisResidencia || "-";

    document.getElementById("telefono").textContent =
        visitante.telefono || "-";

    document.getElementById("tipoTurismo").textContent =
        visitante.tipoTurismo?.descripcion || "-";
}

function mostrarPreferencia(preferencia) {
    document.getElementById("preferenciaNombre").textContent =
        preferencia.tipoTurismo?.descripcion || "Sin preferencia";

    document.getElementById("preferenciaDescripcion").textContent =
        `Código: ${preferencia.tipoTurismo?.codigo || "-"}`;

    document.getElementById("preferenciaEstado").textContent =
        preferencia.activa
            ? "Preferencia activa"
            : "Preferencia inactiva";
}

function mostrarMensaje(mensaje, tipo) {
    const elemento = document.getElementById("mensaje");

    if (!elemento) return;

    elemento.textContent = mensaje;
    elemento.className = `alert alert-${tipo}`;
}
