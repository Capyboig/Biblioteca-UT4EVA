package Controller;

import Model.Genero;
import Model.Libro;
import Model.Prestamo;
import Model.Usuario;
import Service.BibliotecaService;

import java.util.List;

public class BibliotecaController {
    private final BibliotecaService service;

    public BibliotecaController(BibliotecaService service) {
        this.service = service;
    }

    public void agregarNuevoLibro(String isbn, String titulo, String autor, int anio, String editorial, Genero genero, int totalCopias) {
        try {
            Libro nuevoLibro = new Libro(isbn, titulo, autor, anio, editorial, genero, totalCopias);
            service.agregarLibro(nuevoLibro);
            System.out.println("[ÉXITO] Libro '" + titulo + "' añadido.");
        } catch (RuntimeException e) {
            System.out.println("[ERROR] " + e.getMessage());
        }
    }

    public void registrarNuevoUsuario(String idUsuario, String nombre) {
        try {
            Usuario nuevoUsuario = new Usuario(idUsuario, nombre);
            service.registrarUsuario(nuevoUsuario);
            System.out.println("[ÉXITO] Usuario registrado.");
        } catch (RuntimeException e) {
            System.out.println("[ERROR] " + e.getMessage());
        }
    }

    public void solicitarPrestamo(String idUsuario, String isbn) {
        try {
            service.prestarLibro(idUsuario, isbn);
            System.out.println("[ÉXITO] Préstamo registrado.");
        } catch (RuntimeException e) {
            System.out.println("[ERROR] " + e.getMessage());
        }
    }

    public void procesarDevolucion(String idUsuario, String isbn) {
        try {
            service.devolverLibro(idUsuario, isbn);
            System.out.println("[ÉXITO] Devolución procesada.");
        } catch (RuntimeException e) {
            System.out.println("[ERROR] " + e.getMessage());
        }
    }

    public void procesarReserva(String idUsuario, String isbn) {
        try {
            service.reservarLibro(idUsuario, isbn);
            System.out.println("[ÉXITO] Reserva realizada.");
        } catch (RuntimeException e) {
            System.out.println("[ERROR] " + e.getMessage());
        }
    }

    public Libro buscarLibroPorTitulo(String titulo) { return service.buscarLibroPorTitulo(titulo); }
    public Libro buscarLibroPorIsbn(String isbn) { return service.buscarLibroPorIsbn(isbn); }
    public List<Libro> filtrarLibrosPorGenero(Genero genero) { return service.filtrarLibrosPorGenero(genero); }
    public List<Libro> obtenerTodosLosLibros() { return service.obtenerTodosLosLibros(); }
    public List<Usuario> obtenerTodosLosUsuarios() { return service.obtenerTodosLosUsuarios(); }
    public List<Prestamo> obtenerPrestamosActivos() { return service.obtenerPrestamosActivos(); }
}