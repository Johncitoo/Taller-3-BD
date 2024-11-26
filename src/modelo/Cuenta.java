package modelo;

public class Cuenta {
    private int idCuenta;
    private int idCliente;
    private double saldo;
    private String fechaCreacion;

    /**
     * Constructor de la clase Cuenta.
     * Inicializa una nueva instancia de Cuenta con los valores proporcionados para sus atributos.
     *
     * @param idCuenta      identificador único de la cuenta.
     * @param idCliente     identificador del cliente asociado a la cuenta.
     * @param saldo         saldo actual de la cuenta.
     * @param fechaCreacion fecha de creación de la cuenta.
     */
    public Cuenta(int idCuenta, int idCliente, double saldo, String fechaCreacion) {
        this.idCuenta = idCuenta;
        this.idCliente = idCliente;
        this.saldo = saldo;
        this.fechaCreacion = fechaCreacion;
    }

    /**
     * Constructor vacío de la clase Cuenta.
     * Permite crear una instancia de Cuenta sin inicializar sus atributos.
     */
    public Cuenta() {
    }

    // Getters y Setters

    /**
     * Método para obtener el ID de la cuenta.
     *
     * @return el identificador único de la cuenta.
     */
    public int getIdCuenta() {
        return idCuenta;
    }

    /**
     * Método para establecer el ID de la cuenta.
     *
     * @param idCuenta el identificador único de la cuenta.
     */
    public void setIdCuenta(int idCuenta) {
        this.idCuenta = idCuenta;
    }

    /**
     * Método para obtener el ID del cliente asociado a la cuenta.
     *
     * @return el identificador del cliente asociado.
     */
    public int getIdCliente() {
        return idCliente;
    }

    /**
     * Método para establecer el ID del cliente asociado a la cuenta.
     *
     * @param idCliente el identificador del cliente asociado.
     */
    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    /**
     * Método para obtener el saldo actual de la cuenta.
     *
     * @return el saldo actual de la cuenta.
     */
    public double getSaldo() {
        return saldo;
    }

    /**
     * Método para establecer el saldo de la cuenta.
     *
     * @param saldo el saldo actual de la cuenta.
     */
    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    /**
     * Método para obtener la fecha de creación de la cuenta.
     *
     * @return la fecha de creación de la cuenta.
     */
    public String getFechaCreacion() {
        return fechaCreacion;
    }

    /**
     * Método para establecer la fecha de creación de la cuenta.
     *
     * @param fechaCreacion la fecha de creación de la cuenta.
     */
    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
}
