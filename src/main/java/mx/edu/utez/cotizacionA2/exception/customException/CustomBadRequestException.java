package mx.edu.utez.cotizacionA2.exception.customException;

public class CustomBadRequestException extends RuntimeException {
    public CustomBadRequestException(String mensaje) {
        super(mensaje);
    }
}