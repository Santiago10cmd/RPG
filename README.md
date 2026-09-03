# RPG - Arquitectura Modelo - Vista - Controlador (MVC) con Javalin y HTML

Proyecto académico desarrollado en **Java** con el framework **Javalin** e interfaz en **HTML/CSS/JS**, estructurado bajo el patrón **Modelo - Vista - Controlador (MVC)**.

---

## 🏛️ Separación de Responsabilidades por Capa

### 1. Modelo (`Modelo/` - Semanas 1 y 2)
* [`Personaje.java`](file:///src/main/java/Modelo/Personaje.java): Entidad de dominio con los atributos requeridos (`nombre`, `clase` —Guerrero, Mago, Arquero—, `nivel` y `vida`).
* [`PersonajeDAO.java`](file:///src/main/java/Modelo/PersonajeDAO.java):
  * **Estructura en memoria:** `ArrayList<Personaje>` donde se almacenan y persisten en tiempo de ejecución los héroes.
  * **Reglas de negocio:** Valida que el nivel sea mayor o igual a 1 (`nivel >= 1`), la vida sea positiva (`vida > 0`) y la clase pertenezca a las permitidas antes de registrar.

### 2. Controlador (`Controlador/` - Semanas 1 a 3)
* [`PersonajeControlador.java`](file:///src/main/java/Controlador/PersonajeControlador.java):
  * **Ruta GET `/personajes`:** Solicita la lista de personajes al Modelo y la retorna como JSON.
  * **Ruta POST `/personajes`:** Recibe los datos enviados por la Vista, delega al Modelo la aplicación de reglas y responde con código HTTP `201 Created` o `400 Bad Request`.

### 3. Vista (`Vista/` y `resources/public/` - Semana 3)
* [`index.html`](file:///src/main/resources/public/index.html):
  * **Principio fundamental:** La Vista **solo muestra la interfaz y captura datos, sin tomar decisiones ni ejecutar reglas de negocio**.
  * Formulario de captura de datos (Nombre, selector de Clase, Nivel y Vida).
  * Renderizado dinámico de la lista de personajes obtenida del servidor.
  * Captura el envío y lo transmite vía `fetch` al Controlador (`POST /personajes`).
* [`main.java`](file:///src/main/java/Vista/main.java):
  * Inicializa el servidor Javalin en el puerto `7070` sirviendo archivos estáticos (`/public`) y vinculando las rutas del Controlador.

---

## 🚀 Endpoints de la API

| Método | Ruta | Descripción | Código Éxito |
| :--- | :--- | :--- | :--- |
| `GET` | `/personajes` | Retorna el listado completo de personajes en JSON | `200 OK` |
| `POST` | `/personajes` | Recibe un nuevo personaje y lo delega al Modelo | `201 Created` |

### Ejemplo de JSON para POST:
```json
{
  "nombre": "Conan",
  "clase": "Guerrero",
  "nivel": 3,
  "vida": 150
}
```

---

## ⚙️ Cómo Ejecutar el Proyecto

Desde la raíz del proyecto en tu terminal:

```bash
# Ejecutar con Maven
mvn exec:java
```

Una vez que el servidor inicie:
* Abre en tu navegador la **Vista HTML**: 👉 **`http://localhost:7070`**
* O consulta directamente el endpoint **GET**: **`http://localhost:7070/personajes`**
