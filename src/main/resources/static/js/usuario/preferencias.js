document.addEventListener("DOMContentLoaded", inicializar);

const API = {
    preferencias: "/api/preferencias-visitante",
    tipos: "/api/tipos-turismo"
};

let preferenciaActual = null;
let tiposDisponibles = [];

const pagina = document.body;

const visitanteId = Number(pagina.dataset.visitanteId);
const preferenciaId = Number(pagina.dataset.preferenciaId);

async function obtener(url) {
    const response = await fetch(url);

    if (!response.ok) {
        throw new Error(`Error consultando ${url}`);
    }

    return response.json();
}

async function inicializar() {

    try {
        if (!visitanteId || !preferenciaId) {
            throw new Error("No se encontraron los identificadores necesarios.");
        }

        // Consultar ambos recursos.
        const [preferencia, tipos] = await Promise.all([
            obtener(`${API.preferencias}/${preferenciaId}`),
            obtener(API.tipos)
        ]);

        preferenciaActual = preferencia;

        // Solo se permiten tipos de turismo activos.
        tiposDisponibles = tipos.filter(tipo => tipo.activo === true);

        if (tiposDisponibles.length === 0) {
            throw new Error("No existen tipos de turismo activos.");
        }

        cargarOpciones(preferencia);
        cargarPreferencia(preferencia);

    } catch (error) {
        console.error(error);
        mostrarMensaje(error.message, "danger");
    }
}

function cargarOpciones(preferencia) {

    const select = document.getElementById("tipoTurismo");

    select.innerHTML = '<option value="">Selecciona una preferencia</option>';

    tiposDisponibles.forEach(tipo => {

        const option = document.createElement("option");

        option.value = tipo.codigo;
        option.textContent = tipo.descripcion;

        select.appendChild(option);
    });

    const codigoActual = preferencia.tipoTurismo?.codigo;

    if (codigoActual) {
        select.value = codigoActual;
    }
}

function cargarPreferencia(preferencia) {

    document.getElementById("activa").checked =
        preferencia.activa === true;

}

document.getElementById("preferenciasForm")
    .addEventListener("submit", async function(event) {

        event.preventDefault();

        const codigo = document.getElementById("tipoTurismo").value;

        const tipoSeleccionado = tiposDisponibles.find(
            tipo => tipo.codigo === codigo
        );

        // Validación contra los tipos obtenidos de la API.
        if (!tipoSeleccionado) {
            mostrarMensaje("Selecciona un tipo de turismo válido y activo.", "warning");
            return;
        }

        if (!preferenciaActual) {
            mostrarMensaje("No se pudo recuperar la preferencia actual.", "danger");
            return;
        }

        const payload = {
            idPreferencia: preferenciaId,

            visitante: {
                idVisitante: visitanteId
            },

            tipoTurismo: {
                codigo: tipoSeleccionado.codigo
            },

            activa: document.getElementById("activa").checked
        };

        const boton = document.getElementById("btnGuardar");

        boton.disabled = true;
        boton.textContent = "Guardando...";

        try {

            const response = await fetch(
                `${API.preferencias}/${preferenciaId}`,
                {
                    method: "PUT",
                    headers: {
                        "Content-Type": "application/json"
                    },
                    body: JSON.stringify(payload)
                }
            );

            if (!response.ok) {
                throw new Error("No fue posible actualizar la preferencia.");
            }

            mostrarMensaje(
                "Tu preferencia turística se actualizó correctamente.",
                "success"
            );

            preferenciaActual = {
                ...preferenciaActual,
                ...payload
            };

        } catch (error) {

            console.error(error);
            mostrarMensaje(error.message, "danger");

        } finally {

            boton.disabled = false;
            boton.textContent = "Guardar preferencias";

        }
    });

function mostrarMensaje(mensaje, tipo) {

    const elemento = document.getElementById("mensaje");

    elemento.textContent = mensaje;
    elemento.className = `alert alert-${tipo}`;

}
