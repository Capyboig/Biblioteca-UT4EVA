package Service;

import Model.Libro;
import Model.Prestamo;
import Model.Usuario;
import repository.BibliotecaRepository;
import exceptions.*;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;


public class BibliotecaService {

    private final BibliotecaRepository repository;

    public BibliotecaService(BibliotecaRepository repository) {
    this.repository = repository;
    }





    public void prestarLibro(String idUsuario, String isbn) {
    Usuario usuario = repository.buscarUsuarioPorId(idUsuario);
    Libro libro = repository.buscarLibroPorIsbn(isbn);


        if (usuario == null) {
            throw new UsuarioNoExistenteException("[ERROR] El usuario no es existente");

        }

        if (libro == null) {
            throw new LibroNoDisponibleException("[ERROR] El libro no es existente");

        }



        if (!libro.getEstado().equals("DISPONIBLE")) {
            throw new LibroNoDisponibleException("[ERROR] El libro " + libro + " no esta disponible");
        }

        if (usuario.getCantidadDeLibros() >= 3) {
            throw new LimiteDePrestamosException("[ERROR] El usuario " + idUsuario + " ha alcanzado el limite de libros");
        }


        if (usuario.isPenalizacion()) {
            throw new UsuarioPenalizadoException("[ERROR] El usuario " + idUsuario + " esta penalizado");
        }


        List<Prestamo> historial = repository.obtenerHistorialDeUsuario(idUsuario);

        for (Prestamo prestamoAntiguo : historial) {
            if (prestamoAntiguo.getLibro().getISBN().equals(isbn)) {

                LocalDate fechaDevolucion = prestamoAntiguo.getFechaDevolucion();
                LocalDate hoy = LocalDate.now();

                long diasPasados = ChronoUnit.DAYS.between(fechaDevolucion, hoy);


                if (diasPasados < 7) {
                    throw new RestriccionSieteDiasException("Faltan " + (7 - diasPasados) + " para poder volver a prestar un libro");
                }
            }
        }



        Prestamo nuevoPrestamo = new Prestamo(usuario, libro);


        libro.setEstado("PRESTADO");
        usuario.setCantidadDeLibros(usuario.getCantidadDeLibros() + 1);

        repository.registrarNuevoPrestamo(nuevoPrestamo);
    }



    public void devolverLibro(String idUsuario, String ISBN) {

        Prestamo prestamoActivo = repository.buscarPrestamo(idUsuario, ISBN);

        if (prestamoActivo == null) {
            throw new PrestamoNoActivo("[ERROR] No existe el prestamo activo para ese usuario & libro");
        }


        prestamoActivo.registrarDevolucion(LocalDate.now());

        if (prestamoActivo.vendido()) {
            Usuario usuario = prestamoActivo.getUsuario();
            usuario.setPenalizacion(true);
        }



        Libro libro = prestamoActivo.getLibro();
        Usuario usuario = prestamoActivo.getUsuario();

        libro.setEstado("DISPONIBLE");
        usuario.setCantidadDeLibros(usuario.getCantidadDeLibros() - 1);

        repository.moverPrestamoAHistorial(prestamoActivo);

    }



}
