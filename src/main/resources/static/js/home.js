document.addEventListener("DOMContentLoaded", () => {

    cargarDestinos();

});

async function cargarDestinos() {

    const container = document.getElementById("destinos-container");

    try {

        const response = await fetch("/api/destinos");

        if (!response.ok) {
            throw new Error("No se pudieron cargar los destinos");
        }

        const destinos = await response.json();

        container.innerHTML = "";

        destinos.slice(0, 8).forEach(destino => {

            const card = document.createElement("article");
            card.className = "destination-card";

            const titulo = document.createElement("h3");
            titulo.textContent = destino.nombre || "Destino";

            const descripcion = document.createElement("p");
            descripcion.textContent =
                destino.descripcion || "Descubre este destino.";

            const info = document.createElement("div");
            info.className = "destination-info";

            info.append(titulo, descripcion);
            card.appendChild(info);

            container.appendChild(card);

        });

        if (destinos.length === 0) {
            container.textContent = "Todavía no hay destinos disponibles.";
        }

    } catch (error) {

        console.error(error);

        container.textContent =
            "No fue posible cargar los destinos.";

    }
}
