package Modelo;

import java.util.*;

public class Personaje_AGR {

    private static ArrayList<Personaje> listaPersonajes = new ArrayList<>();

    static {
        // Datos de prueba iniciales para consultar vía API
        listaPersonajes.add(new Personaje("Arturo", "Guerrero", 5, 120));
        listaPersonajes.add(new Personaje("Merlín", "Mago", 8, 80));
    }

    public void agregarPersonaje(Personaje p) {
        if (p.getVida() >= 0 && p.getNivel() >= 0) {
            listaPersonajes.add(p);
        } else {
            System.out.println("Datos de vida o nivel no validos");
        }
    }

    public ArrayList<Personaje> listaDePersonajes() {
        return listaPersonajes;
    }
}
