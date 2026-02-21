package Model;

public class Libro {
    private String ISBN;
    private String titulo;
    private String autor;
    private int anioPublicacion;
    private String editorial;
    private Genero genero;
    private int totalCopias;
    private int copiasDisponibles;
    private EstadoLibro estado;

    public Libro(String ISBN, String titulo, String autor, int anioPublicacion, String editorial, Genero genero, int totalCopias) {
        this.ISBN = ISBN;
        this.titulo = titulo;
        this.autor = autor;
        this.anioPublicacion = anioPublicacion;
        this.editorial = editorial;
        this.genero = genero;
        this.totalCopias = totalCopias;
        this.copiasDisponibles = totalCopias;
        this.estado = EstadoLibro.DISPONIBLE;
    }

    public String getISBN() { return ISBN; }
    public String getTitulo() { return titulo; }
    public String getAutor() { return autor; }
    public int getAnioPublicacion() { return anioPublicacion; }
    public String getEditorial() { return editorial; }
    public Genero getGenero() { return genero; }
    public int getTotalCopias() { return totalCopias; }

    public int getCopiasDisponibles() { return copiasDisponibles; }
    public void setCopiasDisponibles(int copiasDisponibles) {
        this.copiasDisponibles = copiasDisponibles;
        if (this.copiasDisponibles == 0 && this.estado == EstadoLibro.DISPONIBLE) {
            this.estado = EstadoLibro.PRESTADO;
        } else if (this.copiasDisponibles > 0 && this.estado == EstadoLibro.PRESTADO) {
            this.estado = EstadoLibro.DISPONIBLE;
        }
    }

    public EstadoLibro getEstado() { return estado; }
    public void setEstado(EstadoLibro estado) { this.estado = estado; }
}