package Model;

public class Usuario {
            /*Estos datos son solo temporales, mas tardes cambialos por los datos completos
            a traer del view
            */
    private String usuario;
    private String idUsuario;
    private int CantidadDeLibros;
    private boolean penalizacion;


    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getCantidadDeLibros() {
        return CantidadDeLibros;
    }

    public void setCantidadDeLibros(int cantidadDeLibros) {
        CantidadDeLibros = cantidadDeLibros;
    }

    public boolean isPenalizacion() {
        return penalizacion;
    }

    public void setPenalizacion(boolean penalizacion) {
        this.penalizacion = penalizacion;
    }

    public String getUsuario() {return usuario;}
}
