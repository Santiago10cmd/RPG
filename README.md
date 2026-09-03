# RPG - API REST con Javalin (MVC)

Servicio web API REST desarrollado en **Java** utilizando el microframework **Javalin**, estructurado bajo el patrón **Modelo - Vista - Controlador (MVC)** y el patrón **DAO (Data Access Object)**.

---

## 🏛️ Arquitectura del Proyecto

```text
src/main/java/
├── Modelo/
│   ├── Personaje.java          # Entidad de dominio (nombre, clase, nivel, vida)
│   └── PersonajeDAO.java       # Acceso y manipulación de datos en memoria
├── Controlador/
│   └── PersonajeControlador.java # Procesa la petición y genera respuesta JSON
└── Vista/
    └── main.java               # Inicialización del servidor Javalin en puerto 7070
```

---

## 🚀 Endpoints Disponibles

| Método | Endpoint | Descripción |
| :--- | :--- | :--- |
| `GET` | `/personajes` | Retorna el listado completo de personajes en formato JSON |

### Ejemplo de respuesta:
```json
[
  {
    "nombre": "Arturo",
    "clase": "Guerrero",
    "nivel": 5,
    "vida": 120
  },
  {
    "nombre": "Merlín",
    "clase": "Mago",
    "nivel": 8,
    "vida": 80
  }
]
```

---

## ⚙️ Cómo Ejecutar el Proyecto

### Opción 1: Con Maven (Recomendado)
```bash
# Compilar y ejecutar
mvn exec:java
```

El servidor web arrancará en: **`http://localhost:7070`**  
Puedes probar la ruta abriendo en tu navegador: **`http://localhost:7070/personajes`**

### Opción 2: En tu IDE (IntelliJ IDEA, Eclipse o VS Code)
1. Abrir la carpeta `RPG` como proyecto Maven.
2. El IDE descargará automáticamente Javalin y Jackson.
3. Ejecutar la clase `Vista.main`.
