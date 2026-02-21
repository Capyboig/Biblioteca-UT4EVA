package Model;

import java.util.ArrayList;
import java.util.List;

public class Usuario {
    private String idUsuario;
    private String nombre;
    private List<Libro> librosPrestados;
    private List<Libro> historialPrestados;

    public Usuario(String idUsuario, String nombre) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.librosPrestados = new ArrayList<>();
        this.historialPrestados = new ArrayList<>();
    }

    public String getIdUsuario() { return idUsuario; }
    public String getNombre() { return nombre; }

    public List<Libro> getLibrosPrestados() { return librosPrestados; }
    public void agregarLibroPrestado(Libro libro) { this.librosPrestados.add(libro); }
    public void removerLibroPrestado(Libro libro) { this.librosPrestados.remove(libro); }

    public List<Libro> getHistorialPrestados() { return historialPrestados; }
    public void agregarAlHistorial(Libro libro) { this.historialPrestados.add(libro); }
}