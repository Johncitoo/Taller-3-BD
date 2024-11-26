package util;

public class ExcepcionPersonalizada extends Exception {

    /**
     * Clase personalizada para manejar excepciones específicas del sistema.
     * Extiende la clase Exception para permitir el uso de mensajes personalizados.
     */
    public ExcepcionPersonalizada(String mensaje) {
        super(mensaje);
    }
}
