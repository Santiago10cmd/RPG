package Modelo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Modelo - Capa de Acceso a Datos y Reglas de Negocio en Memoria.
 * Administra el ArrayList de personajes y aplica las reglas del dominio.
 */
public class PersonajeDAO {

    // Estructura en memoria solicitada (ArrayList)
    private static ArrayList<Personaje> listaPersonajes = new ArrayList<>();

    // Clases válidas para el dominio RPG (Guerrero, Mago, Arquero)
    private static final List<String> CLASES_VALIDAS = Arrays.asList("guerrero", "mago", "arquero");

    static {
        // Datos de ejemplo iniciales en memoria
        listaPersonajes.add(new Personaje("Arturo", "Guerrero", 5, 120));
        listaPersonajes.add(new Personaje("Merlín", "Mago", 8, 80));
        listaPersonajes.add(new Personaje("Robin", "Arquero", 4, 95));
    }

    /**
     * REGLAS DE NEGOCIO (Modelo):
     * Valida que el personaje cumpla las restricciones del juego antes de agregarlo:
     * 1. El nombre no debe estar vacío.
     * 2. El nivel debe ser mayor o igual a 1 (no se permiten niveles <= 0).
     * 3. La vida debe ser mayor a 0.
     * 4. La clase debe pertenecer a las clases permitidas (Guerrero, Mago, Arquero).
     *
     * @param p Personaje a agregar.
     * @return true si cumple las reglas de negocio y fue agregado, false en caso contrario.
     */
    public boolean agregarPersonaje(Personaje p) {
        if (p == null) {
            return false;
        }

        if (p.getNombre() == null || p.getNombre().trim().isEmpty()) {
            System.out.println("[Modelo] Regla violada: El nombre no puede estar vacío.");
            return false;
        }

        // Regla de negocio: El nivel debe ser >= 1
        if (p.getNivel() < 1) {
            System.out.println("[Modelo] Regla violada: El nivel debe ser mayor o igual a 1.");
            return false;
        }

        // Regla de negocio: La vida debe ser > 0
        if (p.getVida() <= 0) {
            System.out.println("[Modelo] Regla violada: La vida debe ser mayor a 0.");
            return false;
        }

        // Regla de negocio: Clase válida
        if (p.getClase() == null || !CLASES_VALIDAS.contains(p.getClase().trim().toLowerCase())) {
            System.out.println("[Modelo] Regla violada: La clase debe ser Guerrero, Mago o Arquero.");
            return false;
        }

        listaPersonajes.add(p);
        return true;
    }

    public ArrayList<Personaje> listaDePersonajes() {
        return listaPersonajes;
    }
}
