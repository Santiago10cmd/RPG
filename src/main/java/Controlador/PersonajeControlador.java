package Controlador;

import Modelo.Personaje;
import Modelo.Personaje_AGR;
import io.javalin.http.Context;

public class PersonajeControlador {

    private static Personaje_AGR pdao = new Personaje_AGR();

    // 1. Devuelve la lista en JSON
    public static void obtenerPersonajes(Context ctx) {
        ctx.json(pdao.listaDePersonajes());
    }

    // 2. Guarda un nuevo personaje con los 4 datos de tu modelo
    public static void crearPersonaje(Context ctx) {
        String nombre = ctx.formParam("nombre");
        String clase = ctx.formParam("clase");
        int nivel = Integer.parseInt(ctx.formParam("nivel"));
        int vida = Integer.parseInt(ctx.formParam("vida"));

        Personaje nuevo = new Personaje(nombre, clase, nivel, vida);
        pdao.agregarPersonaje(nuevo);

        ctx.result("¡Personaje creado con éxito!");
    }
}
