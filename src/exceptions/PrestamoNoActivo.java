package exceptions;

public class PrestamoNoActivo extends RuntimeException {
    public PrestamoNoActivo(String message) {
        super(message);
    }
}
