package Service;

import Model.EstadoLibro;
import Model.Genero;
import Model.Libro;
import Model.Prestamo;
import Model.Usuario;
import exceptions.LibroNoDisponibleException;
import exceptions.LimiteDePrestamosException;
import repository.BibliotecaRepository;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class BibliotecaService {
    private final BibliotecaRepository repository;

    public BibliotecaService(BibliotecaRepository repository) {
        this.repository = repository;
    }

    public void agregarLibro(Libro libro) {
        if (libro == null || repository.buscarLibroPorIsbn(libro.getISBN()) != null) {
            throw new IllegalArgumentException("Datos de libro inválidos o ISBN duplicado.");
        }
        repository.agregarLibro(libro);
    }

    public void registrarUsuario(Usuario usuario) {
        if (usuario == null || repository.buscarUsuarioPorId(usuario.getIdUsuario()) != null) {
            throw new IllegalArgumentException("Datos de usuario inválidos o ID duplicado.");
        }
        repository.registrarUsuario(usuario);
    }

    public void prestarLibro(String idUsuario, String isbn) {
        Usuario usuario = repository.buscarUsuarioPorId(idUsuario);
        Libro libro = repository.buscarLibroPorIsbn(isbn);

        if (usuario == null) throw new IllegalArgumentException("El usuario no existe.");
        if (libro == null) throw new IllegalArgumentException("El libro no existe.");

        if (libro.getEstado() != EstadoLibro.DISPONIBLE || libro.getCopiasDisponibles() <= 0) {
            throw new LibroNoDisponibleException("El libro no está disponible para préstamo.");
        }

        if (usuario.getLibrosPrestados().size() >= 3) {
            throw new LimiteDePrestamosException("El usuario ya tiene 3 libros prestados.");
        }

        // Regla: Si tuvo este mismo libro por 30 días, esperar 7 días tras devolverlo
        List<Prestamo> historialDelLibro = repository.obtenerHistorialPrestamosPorUsuarioYLibro(idUsuario, isbn);
        for (Prestamo p : historialDelLibro) {
            if (p.getFechaDevolucionReal() != null) {
                long diasPrestado = ChronoUnit.DAYS.between(p.getFechaInicio(), p.getFechaDevolucionReal());
                if (diasPrestado >= 30) {
                    long diasDesdeDevolucion = ChronoUnit.DAYS.between(p.getFechaDevolucionReal(), LocalDate.now());
                    if (diasDesdeDevolucion < 7) {
                        throw new RuntimeException("Penalización: Deben pasar 7 días desde la devolución para volver a pedir este libro. Faltan " + (7 - diasDesdeDevolucion) + " días.");
                    }
                }
            }
        }

        Prestamo nuevoPrestamo = new Prestamo(usuario, libro);

        libro.setCopiasDisponibles(libro.getCopiasDisponibles() - 1);
        usuario.agregarLibroPrestado(libro);

        repository.registrarNuevoPrestamo(nuevoPrestamo);
    }

    public void devolverLibro(String idUsuario, String isbn) {
        Prestamo prestamoActivo = repository.buscarPrestamoActivo(idUsuario, isbn);
        if (prestamoActivo == null) {
            throw new IllegalArgumentException("El usuario no tiene prestado este libro actualmente.");
        }

        prestamoActivo.registrarDevolucion(LocalDate.now());

        Libro libro = prestamoActivo.getLibro();
        Usuario usuario = prestamoActivo.getUsuario();

        libro.setCopiasDisponibles(libro.getCopiasDisponibles() + 1);
        usuario.removerLibroPrestado(libro);
        usuario.agregarAlHistorial(libro);

        repository.moverPrestamoAHistorial(prestamoActivo);
    }

    public void reservarLibro(String idUsuario, String isbn) {
        Libro libro = repository.buscarLibroPorIsbn(isbn);
        if (libro == null) throw new IllegalArgumentException("El libro no existe.");
        if (libro.getEstado() == EstadoLibro.DISPONIBLE && libro.getCopiasDisponibles() > 0) {
            throw new IllegalArgumentException("El libro está disponible, puedes pedirlo directamente.");
        }
        libro.setEstado(EstadoLibro.RESERVADO);
    }

    public Libro buscarLibroPorTitulo(String titulo) { return repository.buscarLibroPorTitulo(titulo); }
    public Libro buscarLibroPorIsbn(String isbn) { return repository.buscarLibroPorIsbn(isbn); }
    public List<Libro> filtrarLibrosPorGenero(Genero genero) { return repository.filtrarLibrosPorGenero(genero); }
    public List<Libro> obtenerTodosLosLibros() { return repository.obtenerTodosLosLibros(); }
    public List<Usuario> obtenerTodosLosUsuarios() { return repository.obtenerTodosLosUsuarios(); }
    public List<Prestamo> obtenerPrestamosActivos() { return repository.obtenerPrestamosActivos(); }
}