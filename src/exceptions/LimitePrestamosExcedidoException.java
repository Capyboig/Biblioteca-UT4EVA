package exceptions;

public class LimitePrestamosExcedidoException extends RuntimeException {
    public LimitePrestamosExcedidoException(String message) {
        super(message);
    }
}
