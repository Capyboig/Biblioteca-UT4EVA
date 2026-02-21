package Model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Prestamo {
    private LocalDate fechaInicio;
    private LocalDate fechaFin; // Límite de 30 días
    private LocalDate fechaDevolucionReal;
    private Libro libro;
    private Usuario usuario;

    public Prestamo(Usuario usuario, Libro libro) {
        this.usuario = usuario;
        this.libro = libro;
        this.fechaInicio = LocalDate.now();
        this.fechaFin = this.fechaInicio.plusDays(30);
    }

    public LocalDate getFechaInicio() { return fechaInicio; }
    public LocalDate getFechaFin() { return fechaFin; }
    public LocalDate getFechaDevolucionReal() { return fechaDevolucionReal; }
    public Libro getLibro() { return libro; }
    public Usuario getUsuario() { return usuario; }

    public void registrarDevolucion(LocalDate fechaReal) {
        this.fechaDevolucionReal = fechaReal;
    }

    public boolean estaVencido() {
        if (this.fechaDevolucionReal == null) {
            return LocalDate.now().isAfter(this.fechaFin);
        }
        return this.fechaDevolucionReal.isAfter(this.fechaFin);
    }
}