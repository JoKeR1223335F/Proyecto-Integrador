document.addEventListener("DOMContentLoaded", cargarUsuario);

const API_USUARIOS = "/api/usuarios";

let usuarioActual = null;

const usuarioId = document.body.dataset.usuarioId;

async function cargarUsuario() {
    try {
        if (!usuarioId) {
            throw new Error("No se encontró el ID del usuario.");
        }

        const response = await fetch(`${API_USUARIOS}/${usuarioId}`);

        if (!response.ok) {
            throw new Error("No fue posible consultar el usuario.");
        }

        usuarioActual = await response.json();

        document.getElementById("nombres").value =
            usuarioActual.nombres || "";

        document.getElementById("apellidos").value =
            usuarioActual.apellidos || "";

        document.getElementById("correo").value =
            usuarioActual.correo || "";

        document.getElementById("usuario").value =
            usuarioActual.usuario || "";

        document.getElementById("estado").value =
            usuarioActual.estado || "A";

    } catch (error) {
        console.error(error);
        mostrarMensaje(error.message, "danger");
    }
}

document.getElementById("editarPerfilForm")
    .addEventListener("submit", async function(event) {

        event.preventDefault();

        if (!usuarioActual) {
            mostrarMensaje("No se pudo recuperar el usuario original.", "danger");
            return;
        }

        const payload = {
            idUsuario: Number(usuarioId),

            rol: {
                idRol: usuarioActual.rol.idRol
            },

            nombres: document.getElementById("nombres").value.trim(),
            apellidos: document.getElementById("apellidos").value.trim(),
            correo: document.getElementById("correo").value.trim(),
            usuario: document.getElementById("usuario").value.trim(),

            // Se conserva el valor existente.
            // No se muestra ni se modifica desde este formulario.
            contrasenaHash: usuarioActual.contrasenaHash,

            estado: document.getElementById("estado").value
        };

        if (!payload.nombres || !payload.apellidos ||
            !payload.correo || !payload.usuario) {
            mostrarMensaje("Completa todos los campos.", "warning");
            return;
        }

        const boton = document.getElementById("btnGuardar");
        boton.disabled = true;
        boton.textContent = "Guardando...";

        try {
            const response = await fetch(
                `${API_USUARIOS}/${usuarioId}`,
                {
                    method: "PUT",
                    headers: {
                        "Content-Type": "application/json"
                    },
                    body: JSON.stringify(payload)
                }
            );

            if (!response.ok) {
                throw new Error("No fue posible actualizar el perfil.");
            }

            mostrarMensaje("Perfil actualizado correctamente.", "success");

            usuarioActual = {
                ...usuarioActual,
                ...payload
            };

        } catch (error) {
            console.error(error);
            mostrarMensaje(error.message, "danger");
        } finally {
            boton.disabled = false;
            boton.textContent = "Guardar cambios";
        }
    });

function mostrarMensaje(mensaje, tipo) {
    const elemento = document.getElementById("mensaje");
    elemento.textContent = mensaje;
    elemento.className = `alert alert-${tipo}`;
}
