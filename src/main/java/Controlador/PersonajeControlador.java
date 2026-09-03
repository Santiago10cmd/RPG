package Controlador;

import Modelo.Personaje;
import Modelo.PersonajeDAO;
import io.javalin.http.Context;

import java.util.ArrayList;

/**
 * Controlador - Maneja las peticiones HTTP (GET /personajes y POST /personajes).
 * Es el intermediario entre la Vista (HTML) y el Modelo (PersonajeDAO).
 */
public class PersonajeControlador {

    private static PersonajeDAO pdao = new PersonajeDAO();

    /**
     * RUTA GET /personajes
     * Solicita la lista de personajes al Modelo y la retorna como JSON a la Vista.
     */
    public static void obtenerPersonajes(Context ctx) {
        ArrayList<Personaje> personajes = pdao.listaDePersonajes();
        ctx.status(200).json(personajes);
    }

    /**
     * RUTA POST /personajes
     * Recibe los datos del nuevo personaje enviados por la Vista en el cuerpo JSON,
     * los delega al Modelo para su validación y almacenamiento, y responde con
     * el código de estado correspondiente.
     */
    public static void crearPersonaje(Context ctx) {
        try {
            // El Controlador extrae los datos del cuerpo de la petición HTTP
            Personaje nuevoPersonaje = ctx.bodyAsClass(Personaje.class);

            // Delega al Modelo la aplicación de reglas de negocio y guardado
            boolean guardado = pdao.agregarPersonaje(nuevoPersonaje);

            if (guardado) {
                // 201 Created: El personaje fue creado con éxito según las reglas
                ctx.status(201).json(nuevoPersonaje);
            } else {
                // 400 Bad Request: Las reglas de negocio del Modelo rechazaron los datos
                ctx.status(400).result("Error: No cumple las reglas de negocio del juego (Nombre no vacío, Nivel >= 1, Vida > 0, Clase: Guerrero, Mago o Arquero).");
            }
        } catch (Exception e) {
            ctx.status(400).result("Error al procesar los datos recibidos: " + e.getMessage());
        }
    }
}
