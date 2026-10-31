document.addEventListener("DOMContentLoaded", () => {

    const API_URL = "http://localhost:8080/api/usuarios";

    const formulario = document.getElementById("registroForm");
    const mensajeRegistro = document.getElementById("mensajeRegistro");
    const mensajePassword = document.getElementById("mensajePassword");
    const btnRegistro = document.getElementById("btnRegistro");

    const contrasena = document.getElementById("contrasena");
    const confirmarContrasena = document.getElementById("confirmarContrasena");

    // Mostrar / ocultar contraseñas
    document.querySelectorAll(".registro-toggle").forEach(boton => {

        boton.addEventListener("click", () => {

            const input = document.getElementById(boton.dataset.target);

            if (input.type === "password") {
                input.type = "text";
                boton.textContent = "Ocultar";
            } else {
                input.type = "password";
                boton.textContent = "Ver";
            }

        });

    });

    // Validar coincidencia de contraseñas
    confirmarContrasena.addEventListener("input", validarPassword);
    contrasena.addEventListener("input", validarPassword);

    function validarPassword() {

        if (!confirmarContrasena.value) {
            mensajePassword.textContent = "";
            return false;
        }

        if (contrasena.value !== confirmarContrasena.value) {

            mensajePassword.textContent = "Las contraseñas no coinciden.";
            mensajePassword.style.color = "red";

            return false;
        }

        mensajePassword.textContent = "Las contraseñas coinciden.";
        mensajePassword.style.color = "green";

        return true;
    }

    // Mostrar mensajes
    function mostrarMensaje(texto, tipo) {

        mensajeRegistro.className = `alert alert-${tipo}`;
        mensajeRegistro.textContent = texto;

    }

    // REGISTRO
    formulario.addEventListener("submit", async (event) => {

        event.preventDefault();

        if (!formulario.reportValidity()) {
            return;
        }

        if (!validarPassword()) {

            mostrarMensaje(
                "Las contraseñas no coinciden.",
                "danger"
            );

            return;
        }

        const datos = {

            rol: {
                idRol: 2
            },

            nombres: document.getElementById("nombres").value.trim(),

            apellidos: document.getElementById("apellidos").value.trim(),

            correo: document.getElementById("correo").value.trim(),

            usuario: document.getElementById("usuario").value.trim(),

            contrasenaHash: contrasena.value,

            estado: "A"

        };

        try {

            btnRegistro.disabled = true;
            btnRegistro.textContent = "Creando cuenta...";

            mensajeRegistro.classList.add("d-none");

            const respuesta = await fetch(API_URL, {

                method: "POST",

                headers: {
                    "Content-Type": "application/json"
                },

                body: JSON.stringify(datos)

            });

            const resultado = await respuesta.json().catch(() => null);

            if (!respuesta.ok) {

                throw new Error(
                    resultado?.message ||
                    resultado?.mensaje ||
                    "No fue posible crear la cuenta."
                );

            }

            mostrarMensaje(
                "¡Cuenta creada correctamente! Redirigiendo al inicio de sesión...",
                "success"
            );

            formulario.reset();

            setTimeout(() => {
                window.location.href = "/login";
            }, 1800);

        } catch (error) {

            mostrarMensaje(
                error.message || "Error al conectar con el servidor.",
                "danger"
            );

            btnRegistro.disabled = false;
            btnRegistro.textContent = "Crear mi cuenta";

        }

    });

});
