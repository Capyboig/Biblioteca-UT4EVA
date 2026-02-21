package repository;

import Model.Libro;
import Model.Prestamo;
import Model.Usuario;
import Model.Genero;

import java.util.ArrayList;
import java.util.List;

public class BibliotecaRepository {

    private List<Libro> catalogoLibros = new ArrayList<>();
    private List<Usuario> usuariosRegistrados = new ArrayList<>();
    private List<Prestamo> prestamosActivos = new ArrayList<>();
    private List<Prestamo> historialPrestamos = new ArrayList<>();

    public void agregarLibro(Libro libro) { catalogoLibros.add(libro); }
    public void registrarUsuario(Usuario usuario) { usuariosRegistrados.add(usuario); }

    public Libro buscarLibroPorIsbn(String isbn) {
        for (Libro libro : catalogoLibros) {
            if (libro.getISBN().equals(isbn)) return libro;
        }
        return null;
    }

    public Libro buscarLibroPorTitulo(String titulo) {
        for (Libro libro : catalogoLibros) {
            if (libro.getTitulo().equalsIgnoreCase(titulo)) return libro;
        }
        return null;
    }

    public Usuario buscarUsuarioPorId(String idUsuario) {
        for (Usuario usuario : usuariosRegistrados) {
            if (usuario.getIdUsuario().equals(idUsuario)) return usuario;
        }
        return null;
    }

    public List<Libro> filtrarLibrosPorGenero(Genero genero) {
        List<Libro> encontrados = new ArrayList<>();
        for (Libro libro : catalogoLibros) {
            if (libro.getGenero() == genero) encontrados.add(libro);
        }
        return encontrados;
    }

    public void registrarNuevoPrestamo(Prestamo prestamo) { prestamosActivos.add(prestamo); }

    public void moverPrestamoAHistorial(Prestamo prestamo) {
        prestamosActivos.remove(prestamo);
        historialPrestamos.add(prestamo);
    }

    public Prestamo buscarPrestamoActivo(String idUsuario, String ISBN) {
        for (Prestamo prestamo : prestamosActivos) {
            if (prestamo.getUsuario().getIdUsuario().equals(idUsuario) && prestamo.getLibro().getISBN().equals(ISBN)) {
                return prestamo;
            }
        }
        return null;
    }

    public List<Prestamo> obtenerHistorialPrestamosPorUsuarioYLibro(String idUsuario, String isbn) {
        List<Prestamo> filtrados = new ArrayList<>();
        for (Prestamo p : historialPrestamos) {
            if (p.getUsuario().getIdUsuario().equals(idUsuario) && p.getLibro().getISBN().equals(isbn)) {
                filtrados.add(p);
            }
        }
        return filtrados;
    }

    public List<Libro> obtenerTodosLosLibros() { return catalogoLibros; }
    public List<Usuario> obtenerTodosLosUsuarios() { return usuariosRegistrados; }
    public List<Prestamo> obtenerPrestamosActivos() { return prestamosActivos; }
}