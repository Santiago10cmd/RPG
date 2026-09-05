package Vista;

import Controlador.PersonajeControlador;
import io.javalin.Javalin;

public class main {

    public static void main(String[] args) {

        // Inicia Javalin con soporte para carpeta pública en el puerto 7070
        Javalin app = Javalin.create(config -> {
            config.staticFiles.add("/public");
        }).start(7070);

        // Rutas conectadas a tu controlador
        app.get("/", ctx -> ctx.redirect("/personajes"));
        app.get("/personajes", ctx -> PersonajeControlador.obtenerPersonajes(ctx));
        app.post("/personajes", ctx -> PersonajeControlador.crearPersonaje(ctx));
    }
}
