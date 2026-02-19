package repository;

import Model.Libro;
import Model.Prestamo;
import Model.Usuario;

import java.util.ArrayList;
import java.util.List;

public class BibliotecaRepository {

    private List<Libro> catalogoLibros;
    private List<Usuario> usuariosRegistrados;
    private List<Prestamo> prestamosActivos;
    private List<Prestamo> historialPrestamos;

    public BibliotecaRepository() {
        this.catalogoLibros = new ArrayList<>();
        this.usuariosRegistrados = new ArrayList<>();
        this.prestamosActivos = new ArrayList<>();
        this.historialPrestamos = new ArrayList<>();
    }

    public Libro buscarLibroPorIsbn(String isbn) {
        for (Libro libro : catalogoLibros) {
            if (libro.getIsbn().equals(isbn)) {
                return libro;
            }
        }
        return null;
    }

    public Usuario buscarUsuarioPorId(String idUsuario) {
        for (Usuario usuario : usuariosRegistrados) {
            if (usuario.getUsuario().equals(idUsuario)) {
                return usuario;
            }
        }

        return null;
    }

    public List<Libro> filtrarLibrosPorGenero(String genero) {
        List<Libro> encontrados = new ArrayList<>();
        for (Libro libro : catalogoLibros) {
            if (libro.getGenero().equalsIgnoreCase(genero)) {
                encontrados.add(libro);
            }
        }
        return encontrados;
    }

    public void registrarNuevoPrestamo(Prestamo prestamo) {
        prestamosActivos.add(prestamo);
    }

    public void moverPrestamoAHistorial(Prestamo prestamo) {
        prestamosActivos.remove(prestamo);
        historialPrestamos.add(prestamo);
    }

    public List<Prestamo> obtenerHistorialDeUsuario(String idUsuario) {
        List<Prestamo> historialDelUsuario = new ArrayList<>();
        for (Prestamo prestamo : historialPrestamos) {
            if (prestamo.getUsuario().getIdUsuario().equals(idUsuario)) {
                historialDelUsuario.add(prestamo);
            }
        }
        return historialDelUsuario;
    }


    public Prestamo buscarPrestamo(String idUsuarios, String ISBN) {
        for (Prestamo prestamo : prestamosActivos) {
            if (prestamo.getUsuario().equals(idUsuarios) && prestamo.getLibro().getISBN().equals(ISBN)) {
                return prestamo;
            }
        }

        return null;
    }
}