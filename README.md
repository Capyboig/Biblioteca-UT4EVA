# Sistema de Gestión de Biblioteca - Arquitectura MVC

## Descripción del proyecto
Este proyecto consiste en un sistema de gestión de biblioteca diseñado bajo el patrón de arquitectura MVC (Modelo-Vista-Controlador). El sistema ha sido desarrollado con un enfoque empresarial para garantizar que sea serio, seguro y altamente escalable. 

Permite la administración completa del catálogo de libros y de los usuarios registrados, garantizando la integridad de los datos mediante el uso de `Enums` para los géneros y estados de los libros, y aplicando validaciones estrictas de negocio.

**Características principales implementadas:**
* **Gestión de inventario físico:** Control de copias totales y disponibles por cada libro.
* **Control de préstamos:** Un usuario no puede tener más de 3 libros prestados simultáneamente.
* **Control de tiempo y penalizaciones:** Límite de 30 días de préstamo por libro. Si un usuario agota este plazo, el sistema bloquea temporalmente (7 días) la posibilidad de volver a pedir exactamente el mismo título.
* **Manejo de Excepciones:** Uso de excepciones personalizadas (`LibroNoDisponibleException`, `LimitePrestamosExcedidoException`) para evitar fallos silenciosos y garantizar la fiabilidad del sistema.

## Cómo ejecutar el programa
1. Asegúrate de tener instalado el **JDK de Java** en tu equipo.
2. Clona este repositorio o descomprime el archivo `.zip`.
3. Abre el proyecto en tu IDE de preferencia (Eclipse, IntelliJ IDEA, VSCode) o compílalo directamente desde la terminal apuntando a la carpeta `src`.
4. Ejecuta la clase principal `Main.java` ubicada dentro del paquete `Main`.
5. Interactúa con el sistema a través del menú dinámico mostrado en la consola. Puedes comenzar registrando un usuario (Opción 8) y agregando un libro al catálogo (Opción 7).

## Reparto de tareas
* **[Francisco Javier]**: 
  * Diseño e implementación de la arquitectura MVC y capas de Servicio/Repositorio.
  * Desarrollo de la lógica de validaciones complejas (límites de préstamos, fechas y penalizaciones).
  * Implementación de Excepciones personalizadas y seguridad de tipos con Enums.
  * Solución de conflictos de integración y versionado en Git.

* **[Sofia]**: 
  * Diseño inicial de las clases del Modelo (Libro, Usuario).
  * Estructuración base de la Vista (menú de consola estático).
  * Planteamiento inicial de los flujos de interacción con el usuario.
