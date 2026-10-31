
document.addEventListener("DOMContentLoaded", () => {

    const API_REGISTRO = "http://localhost:8080/api/usuarios/registro";
    const API_AUTENTICAR = "http://localhost:8080/api/usuarios/autenticar";

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

            input.type = input.type === "password" ? "text" : "password";

            boton.textContent =
                input.type === "password" ? "Ver" : "Ocultar";

        });

    });

    // Validar contraseñas
    function validarPassword() {

        if (!confirmarContrasena.value) {
            mensajePassword.textContent = "";
            return false;
        }

        const coinciden = contrasena.value === confirmarContrasena.value;

        mensajePassword.textContent = coinciden
            ? "Las contraseñas coinciden."
            : "Las contraseñas no coinciden.";

        mensajePassword.style.color = coinciden ? "green" : "red";

        return coinciden;
    }

    confirmarContrasena.addEventListener("input", validarPassword);
    contrasena.addEventListener("input", validarPassword);

    // Mostrar mensajes
    function mostrarMensaje(texto, tipo) {

        mensajeRegistro.className = `alert alert-${tipo}`;
        mensajeRegistro.textContent = texto;

    }

    // Convertir respuesta a JSON de forma segura
    async function leerRespuesta(respuesta) {

        const texto = await respuesta.text();

        if (!texto) return null;

        try {
            return JSON.parse(texto);
        } catch {
            return texto;
        }
    }

    // REGISTRAR Y AUTENTICAR
    formulario.addEventListener("submit", async (event) => {

        event.preventDefault();

        if (!formulario.reportValidity()) return;

        if (!validarPassword()) {
            mostrarMensaje("Las contraseñas no coinciden.", "danger");
            return;
        }

        const datosRegistro = {

            idRol: 2,

            nombres: document.getElementById("nombres").value.trim(),

            apellidos: document.getElementById("apellidos").value.trim(),

            correo: document.getElementById("correo").value.trim(),

            usuario: document.getElementById("usuario").value.trim(),

            contrasena: contrasena.value

        };

        try {

            btnRegistro.disabled = true;
            btnRegistro.textContent = "Creando cuenta...";

            mensajeRegistro.classList.add("d-none");

            // PASO 1: REGISTRAR USUARIO
            const respuestaRegistro = await fetch(API_REGISTRO, {

                method: "POST",

                headers: {
                    "Content-Type": "application/json"
                },

                body: JSON.stringify(datosRegistro)

            });

            const resultadoRegistro = await leerRespuesta(respuestaRegistro);

            if (!respuestaRegistro.ok) {

                throw new Error(
                    resultadoRegistro?.message ||
                    resultadoRegistro?.mensaje ||
                    resultadoRegistro ||
                    "No fue posible registrar el usuario."
                );

            }

            mostrarMensaje(
                "Cuenta creada correctamente. Iniciando sesión...",
                "success"
            );

            // PASO 2: AUTENTICAR USUARIO
            const respuestaLogin = await fetch(API_AUTENTICAR, {

                method: "POST",

                headers: {
                    "Content-Type": "application/json"
                },

                body: JSON.stringify({

                    correo: datosRegistro.correo,

                    contrasena: datosRegistro.contrasena

                })

            });

            const resultadoLogin = await leerRespuesta(respuestaLogin);

            if (!respuestaLogin.ok) {

                throw new Error(
                    resultadoLogin?.message ||
                    resultadoLogin?.mensaje ||
                    resultadoLogin ||
                    "La cuenta se creó, pero no fue posible iniciar sesión."
                );

            }

            // PASO 3: OBTENER ID DEL USUARIO
            const idUsuario = resultadoLogin?.idUsuario;

            if (!idUsuario) {
                throw new Error(
                    "No se recibió el identificador del usuario."
                );
            }

            // PASO 4: GUARDAR ID Y REDIRIGIR
            sessionStorage.setItem("idUsuario", idUsuario);

            mostrarMensaje(
                "¡Bienvenido a Viajo! Redirigiendo...",
                "success"
            );

            setTimeout(() => {

                window.location.href = "/usuario";

            }, 1200);

        } catch (error) {

            console.error("Error en el registro:", error);

            mostrarMensaje(
                error.message || "Error al conectar con el servidor.",
                "danger"
            );

        } finally {

            btnRegistro.disabled = false;
            btnRegistro.textContent = "Crear mi cuenta";

        }

    });

});
