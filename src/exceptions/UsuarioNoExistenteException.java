package exceptions;

public class UsuarioNoExistenteException extends RuntimeException {
    public UsuarioNoExistenteException(String message) {
        super(message);
    }
}
