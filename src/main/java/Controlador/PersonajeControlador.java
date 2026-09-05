package Controlador;

import Modelo.Personaje;
import Modelo.PersonajeDAO;
import io.javalin.http.Context;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

/**
 * Controlador - Maneja las peticiones HTTP (GET /personajes y POST /personajes).
 * Es el intermediario entre la Vista (HTML) y el Modelo (PersonajeDAO).
 */
public class PersonajeControlador {

    private static PersonajeDAO pdao = new PersonajeDAO();

    /**
     * RUTA GET /personajes (Server-Side Rendering)
     * Lee el único archivo HTML del proyecto (/public/index.html), itera sobre el
     * ArrayList<Personaje> del Modelo para generar las filas de la tabla, inyecta
     * los datos en la plantilla y entrega el HTML completo al navegador con ctx.html().
     */
    public static void obtenerPersonajes(Context ctx) {
        try {
            // 1. Obtiene los datos del Modelo
            ArrayList<Personaje> personajes = pdao.listaDePersonajes();

            // 2. Lee el único archivo HTML ubicado en src/main/resources/public/index.html
            InputStream inputStream = PersonajeControlador.class.getResourceAsStream("/public/index.html");
            if (inputStream == null) {
                ctx.status(500).result("Error: No se encontró la plantilla /public/index.html");
                return;
            }
            String plantillaHtml = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);

            // 3. Itera en bucle sobre la lista del Modelo para generar las filas <tr> dinámicamente
            StringBuilder filasTabla = new StringBuilder();
            for (Personaje p : personajes) {
                filasTabla.append("            <tr>\n")
                          .append("                <td>").append(p.getNombre()).append("</td>\n")
                          .append("                <td>").append(p.getClase()).append("</td>\n")
                          .append("                <td>").append(p.getNivel()).append("</td>\n")
                          .append("            </tr>\n");
            }

            // 4. Inyecta las filas generadas en la marca <!--FILAS_PERSONAJES--> de la tabla
            String htmlFinal = plantillaHtml.replace("<!--FILAS_PERSONAJES-->", filasTabla.toString().trim());

            // 5. Envía el HTML listo al navegador
            ctx.html(htmlFinal);

        } catch (Exception e) {
            ctx.status(500).result("Error al renderizar la vista: " + e.getMessage());
        }
    }

    /**
     * RUTA POST /personajes
     * Procesa el envío tradicional de un formulario HTML (application/x-www-form-urlencoded).
     * Lee campo por campo con ctx.formParam, crea el objeto del Modelo y aplica Post/Redirect/Get.
     */
    public static void crearPersonaje(Context ctx) {
        try {
            // 1. Lectura de campos individuales del formulario HTML (según el atributo 'name')
            String nombre = ctx.formParam("nombre");
            String clase = ctx.formParam("clase");
            int nivel = Integer.parseInt(ctx.formParam("nivel"));

            String vidaParam = ctx.formParam("vida");
            int vida = (vidaParam != null && !vidaParam.isEmpty()) ? Integer.parseInt(vidaParam) : 100;

            // 2. Creación de la instancia del Modelo
            Personaje nuevoPersonaje = new Personaje(nombre, clase, nivel, vida);

            // 3. Delegación al Modelo para validar reglas de negocio y almacenar
            boolean guardado = pdao.agregarPersonaje(nuevoPersonaje);

            if (guardado) {
                // 4. Flujo web tradicional (Post/Redirect/Get): redirige a la vista
                ctx.redirect("/personajes");
            } else {
                // Si el Modelo rechaza por reglas de negocio (ej. nivel < 1)
                ctx.status(400).result("Error: No cumple las reglas de negocio del juego (Nivel >= 1, Vida > 0, Clase permitida).");
            }
        } catch (NumberFormatException e) {
            ctx.status(400).result("Error: El nivel y la vida deben ser números válidos.");
        } catch (Exception e) {
            ctx.status(400).result("Error al procesar el formulario: " + e.getMessage());
        }
    }
}
