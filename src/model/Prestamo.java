package Model;


import Model.Usuario;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class Prestamo {
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFin;
    private LocalDate fechaDevolucion;
    private LocalDate fechaDevolucionReal;
    private Model.Libro libro;
    private Usuario usuario;

    public LocalDateTime getFechaInicio() {
        return fechaInicio;
    }

    public Prestamo(Usuario usuario, Libro libro) {
        this.libro = libro;
        this.usuario = usuario;
    }
    public void setFechaInicio(LocalDateTime fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDateTime getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDateTime fechaFin) {
        this.fechaFin = fechaFin;
    }

    public LocalDate getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setFechaDevolucion(LocalDate fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }

    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public void registrarDevolucion(LocalDate fechaReal) {
        this.fechaDevolucionReal = fechaReal;
    }





    public boolean vendido() {
        if (this.fechaDevolucionReal == null) {
            return LocalDate.now().isAfter(ChronoLocalDate.from(this.fechaFin));
        }

        return this.fechaDevolucionReal.isAfter(ChronoLocalDate.from(this.fechaFin));
    }




    public long calcularDiasDeRetraso() {
        if (vendido() && this.fechaDevolucionReal != null) {
            return ChronoUnit.DAYS.between(this.fechaFin, this.fechaDevolucionReal);
        }

        return 0;
    }
}
