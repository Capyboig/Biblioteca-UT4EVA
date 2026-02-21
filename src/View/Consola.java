package View;

import Controller.BibliotecaController;
import Model.EstadoLibro;
import Model.Genero;
import Model.Libro;
import Model.Prestamo;
import Model.Usuario;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Consola {
    private final BibliotecaController controller;
    private final Scanner scanner;

    public static final String RESET = "\u001B[0m";
    public static final String ROJO = "\u001B[31m";
    public static final String VERDE = "\u001B[32m";

    public Consola(BibliotecaController controller) {
        this.controller = controller;
        this.scanner = new Scanner(System.in);
    }

    public int mostrarMenu() {
        System.out.println("\n--- BIBLIOTECA ---");
        System.out.println("1. Prestar un libro");
        System.out.println("2. Devolver un libro");
        System.out.println("3. Reservar un libro");
        System.out.println("4. Buscar un libro");
        System.out.println("5. Resumen de Biblioteca");
        System.out.println("6. Identificar usuario con libro prestado");
        System.out.println("7. Agregar libro al catálogo");
        System.out.println("8. Registrar usuario");
        System.out.println("9. Salir");
        System.out.print("Opción: ");
        int op = scanner.nextInt();
        scanner.nextLine();
        return op;
    }

    public void mostrarConsola() {
        try {
            int opcion;
            do {
                opcion = mostrarMenu();
                switch (opcion) {
                    case 1:
                        controller.solicitarPrestamo(pedirCadena("ID del usuario"), pedirCadena("ISBN del libro"));
                        break;
                    case 2:
                        controller.procesarDevolucion(pedirCadena("ID del usuario"), pedirCadena("ISBN del libro"));
                        break;
                    case 3:
                        controller.procesarReserva(pedirCadena("ID del usuario"), pedirCadena("ISBN del libro"));
                        break;
                    case 4:
                        buscarLibroMenu();
                        break;
                    case 5:
                        resumirBiblioteca();
                        break;
                    case 6:
                        identificarUsuarioDeLibro();
                        break;
                    case 7:
                        agregarLibroMenu();
                        break;
                    case 8:
                        controller.registrarNuevoUsuario(pedirCadena("ID de usuario"), pedirCadena("Nombre de usuario"));
                        break;
                }
            } while (opcion != 9);
        } catch (InputMismatchException e) {
            System.out.println("ERROR: Formato inválido.");
        }
    }

    private String pedirCadena(String mensaje) {
        System.out.print(mensaje + ": ");
        return scanner.nextLine();
    }

    private void buscarLibroMenu() {
        System.out.println("1. Por Título | 2. Por ISBN | 3. Por Género");
        System.out.print("Opción: ");
        int op = scanner.nextInt();
        scanner.nextLine();
        if (op == 1) {
            Libro l = controller.buscarLibroPorTitulo(pedirCadena("Título"));
            imprimirInfoLibro(l);
        } else if (op == 2) {
            Libro l = controller.buscarLibroPorIsbn(pedirCadena("ISBN"));
            imprimirInfoLibro(l);
        } else if (op == 3) {
            try {
                Genero g = Genero.valueOf(pedirCadena("Género (ej. NOVELA)").toUpperCase());
                List<Libro> lista = controller.filtrarLibrosPorGenero(g);
                for (Libro lib : lista) imprimirInfoLibro(lib);
            } catch (IllegalArgumentException e) {
                System.out.println("Género no válido.");
            }
        }
    }

    private void imprimirInfoLibro(Libro l) {
        if (l != null) {
            System.out.println("-> " + l.getTitulo() + " | Copias disp: " + l.getCopiasDisponibles() + " | Estado: " + l.getEstado());
        } else {
            System.out.println("Libro no encontrado.");
        }
    }

    private void resumirBiblioteca() {
        System.out.println("\n--- Resumen de Libros ---");
        for (Libro l : controller.obtenerTodosLosLibros()) {
            String color = l.getEstado() == EstadoLibro.DISPONIBLE ? VERDE : ROJO;
            System.out.printf("%s[%s] %s - Disp: %d/%d - Estado: %s%s%n",
                    color, l.getISBN(), l.getTitulo(), l.getCopiasDisponibles(), l.getTotalCopias(), l.getEstado(), RESET);
        }
        System.out.println("\n--- Resumen de Usuarios ---");
        for (Usuario u : controller.obtenerTodosLosUsuarios()) {
            System.out.println("Usuario: " + u.getNombre() + " | Libros prestados: " + u.getLibrosPrestados().size());
            for (Libro lp : u.getLibrosPrestados()) {
                System.out.println("  - " + lp.getTitulo());
            }
        }
    }

    private void identificarUsuarioDeLibro() {
        String isbn = pedirCadena("ISBN del libro");
        boolean encontrado = false;
        for (Prestamo p : controller.obtenerPrestamosActivos()) {
            if (p.getLibro().getISBN().equals(isbn)) {
                System.out.println("El usuario " + p.getUsuario().getNombre() + " tiene una copia prestada.");
                encontrado = true;
            }
        }
        if (!encontrado) System.out.println("No hay préstamos activos para ese ISBN.");
    }

    private void agregarLibroMenu() {
        String isbn = pedirCadena("ISBN");
        String titulo = pedirCadena("Título");
        String autor = pedirCadena("Autor");
        System.out.print("Año: ");
        int anio = scanner.nextInt();
        scanner.nextLine();
        String editorial = pedirCadena("Editorial");
        System.out.print("Género (NOVELA, CIENCIA_FICCION, HISTORIA...): ");
        Genero genero;
        try {
            genero = Genero.valueOf(scanner.nextLine().toUpperCase());
        } catch (IllegalArgumentException e) {
            System.out.println("Género inválido. Se asignará OTRO.");
            genero = Genero.OTRO;
        }
        System.out.print("Total de copias: ");
        int copias = scanner.nextInt();
        scanner.nextLine();

        controller.agregarNuevoLibro(isbn, titulo, autor, anio, editorial, genero, copias);
    }
}