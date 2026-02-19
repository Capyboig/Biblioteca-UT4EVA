package Controller;

import Service.BibliotecaService;

public class BibliotecaController {

    private final BibliotecaService service;

    public BibliotecaController(BibliotecaService service) {
        this.service = service;
    }




    public void solicitarPrestamo(String idUsuario, String isbn) {
        System.out.println("Procesando solicitud para usuario " + idUsuario + " libro"  + isbn);


        try {
            service.prestarLibro(idUsuario, isbn);
            System.out.println("[EXITO] El prestamo se registro correctamente");
        } catch (RuntimeException e) {
            System.out.println("[ERROR] " + e.getMessage());
        }


        System.out.println("-----------------------------------------------");

    }


    public void procesarDevolucion(String idUsuario, String isbn) {

        try {
            service.devolverLibro(idUsuario, isbn);
        } catch (RuntimeException e) {
            System.out.println("[ERROR] " + e.getMessage());
        }


        System.out.println("--------------------------------------------------");
    }
}
