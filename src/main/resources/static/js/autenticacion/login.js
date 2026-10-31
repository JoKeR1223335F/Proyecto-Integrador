javascript
document.addEventListener("DOMContentLoaded", () => {

    const API_URL = "/api/auth/login";

    const formulario = document.getElementById("loginForm");
    const correo = document.getElementById("correo");
    const contrasena = document.getElementById("contrasena");

    const mensajeLogin = document.getElementById("mensajeLogin");
    const btnLogin = document.getElementById("btnLogin");
    const togglePassword = document.getElementById("togglePassword");

    // Mostrar / ocultar contraseña
    togglePassword.addEventListener("click", () => {

        const visible = contrasena.type === "password";

        contrasena.type = visible ? "text" : "password";

        togglePassword.textContent = visible ? "Ocultar" : "Ver";

    });

    // Mostrar mensajes
    function mostrarMensaje(mensaje, tipo) {

        mensajeLogin.className = `alert alert-${tipo}`;
        mensajeLogin.textContent = mensaje;

    }

    // Redirección según rol
    function redirigirSegunRol(usuario) {

        const rol = usuario.rol;

        if (!rol) {
            mostrarMensaje("La cuenta no tiene un rol asignado.", "danger");
            btnLogin.disabled = false;
            btnLogin.textContent = "Iniciar sesión";
            return;
        }

        const nombreRol = String(rol.nombre || "").toUpperCase();
        const idRol = Number(rol.idRol);

        if (idRol === 2 || nombreRol === "VISITANTE") {

            window.location.href = "/usuario";

        } else if (nombreRol === "ADMINISTRADOR") {

            window.location.href = "/admin";

        } else {

            mostrarMensaje(
                "No existe una ruta asignada para este tipo de usuario.",
                "warning"
            );

            btnLogin.disabled = false;
            btnLogin.textContent = "Iniciar sesión";

        }

    }

    // LOGIN
    formulario.addEventListener("submit", async (event) => {

        event.preventDefault();

        if (!formulario.reportValidity()) {
            return;
        }

        const datos = {
            correo: correo.value.trim(),
            contrasena: contrasena.value
        };

        try {

            btnLogin.disabled = true;
            btnLogin.textContent = "Verificando...";

            mensajeLogin.classList.add("d-none");

            const respuesta = await fetch(API_URL, {

                method: "POST",

                headers: {
                    "Content-Type": "application/json"
                },

                credentials: "same-origin",

                body: JSON.stringify(datos)

            });

            const resultado = await respuesta.json().catch(() => null);

            if (!respuesta.ok) {

                throw new Error(
                    resultado?.message ||
                    resultado?.mensaje ||
                    "Correo o contraseña incorrectos."
                );

            }

            // El backend debe devolver el usuario autenticado
            if (!resultado || !resultado.rol) {

                throw new Error(
                    "El servidor no devolvió los datos del usuario autenticado."
                );

            }

            mostrarMensaje("¡Bienvenido a Viajo!", "success");

            redirigirSegunRol(resultado);

        } catch (error) {

            mostrarMensaje(error.message, "danger");

            btnLogin.disabled = false;
            btnLogin.textContent = "Iniciar sesión";

        }

    });

});
