package Main;

import Controller.BibliotecaController;
import Service.BibliotecaService;
import repository.BibliotecaRepository;
import View.Consola;

public class Main {
    public static void main(String[] args) {
        BibliotecaRepository repository = new BibliotecaRepository();
        BibliotecaService service = new BibliotecaService(repository);
        BibliotecaController controller = new BibliotecaController(service);
        Consola consola = new Consola(controller);

        consola.mostrarConsola();
    }
}