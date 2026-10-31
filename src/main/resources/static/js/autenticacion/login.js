
document.addEventListener("DOMContentLoaded", () => {

    const API_AUTENTICAR = "/api/usuarios/autenticar";

    const formulario = document.getElementById("loginForm");
    const correo = document.getElementById("correo");
    const contrasena = document.getElementById("contrasena");
    const boton = document.getElementById("btnLogin");
    const mensaje = document.getElementById("mensajeLogin");

    // Mostrar / ocultar contraseña
    const toggle = document.getElementById("togglePassword");

    toggle.addEventListener("click", () => {

        const mostrar = contrasena.type === "password";

        contrasena.type = mostrar ? "text" : "password";
        toggle.textContent = mostrar ? "Ocultar" : "Ver";

    });

    function mostrarMensaje(texto, tipo) {

        mensaje.className = `alert alert-${tipo}`;
        mensaje.textContent = texto;

    }

    formulario.addEventListener("submit", async (event) => {

        event.preventDefault();

        if (!formulario.reportValidity()) return;

        const datos = {
            correo: correo.value.trim(),
            contrasena: contrasena.value
        };

        try {

            boton.disabled = true;
            boton.textContent = "Iniciando sesión...";

            const respuesta = await fetch(API_AUTENTICAR, {

                method: "POST",

                headers: {
                    "Content-Type": "application/json"
                },

                body: JSON.stringify(datos)

            });

            const resultado = await respuesta.json();

            if (!respuesta.ok) {

                throw new Error(
                    typeof resultado === "string"
                        ? resultado
                        : resultado?.mensaje ||
                          resultado?.message ||
                          "Correo o contraseña incorrectos."
                );

            }

            const idUsuario = resultado.idUsuario;

            if (!idUsuario) {
                throw new Error("No se recibió el identificador del usuario.");
            }

            // Guardar sesión del usuario
            sessionStorage.setItem("idUsuario", idUsuario);

            mostrarMensaje(
                "¡Bienvenido! Redirigiendo a tu perfil...",
                "success"
            );

            // Redirigir compartiendo el ID
            window.location.href = `/usuario?id=${encodeURIComponent(idUsuario)}`;

        } catch (error) {

            console.error("Error al iniciar sesión:", error);

            mostrarMensaje(error.message, "danger");

            boton.disabled = false;
            boton.textContent = "Iniciar sesión";

        }

    });

});
