package Vista;

import Controlador.PersonajeControlador;
import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;

/**
 * Punto de entrada del servidor web Javalin.
 * Configura el servicio de archivos estáticos para la Vista HTML y mapea las rutas del Controlador.
 */
public class main {

    public static void main(String[] arg) {
        // Inicializa Javalin sirviendo la carpeta 'public' del classpath donde está index.html
        Javalin app = Javalin.create(config -> {
            config.staticFiles.add("/public", Location.CLASSPATH);
        }).start(7070);

        // Rutas del Controlador
        app.get("/personajes", ctx -> PersonajeControlador.obtenerPersonajes(ctx));
        app.post("/personajes", ctx -> PersonajeControlador.crearPersonaje(ctx));

        System.out.println("==================================================");
        System.out.println(" ⚔️ Servidor RPG en ejecución en http://localhost:7070");
        System.out.println(" 🌐 Vista HTML disponible en: http://localhost:7070/");
        System.out.println(" 📋 Endpoint GET:  http://localhost:7070/personajes");
        System.out.println(" ➕ Endpoint POST: http://localhost:7070/personajes");
        System.out.println("==================================================");
    }
}
