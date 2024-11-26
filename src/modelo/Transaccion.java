package modelo;

public class Transaccion {
    private int idTransaccion;
    private int idCuentaOrigen;
    private int idCuentaDestino;
    private String tipoTransaccion; // aqui va a variar dependiedndo si es deposito, retiro o transferencia
    private double monto;
    private String fecha;

    /**
     * Constructor completo de la clase Transaccion.
     * Inicializa una nueva instancia de Transaccion con los valores proporcionados para sus atributos.
     *
     * @param idTransaccion   identificador único de la transacción.
     * @param idCuentaOrigen  identificador de la cuenta de origen de la transacción.
     * @param idCuentaDestino identificador de la cuenta de destino de la transacción.
     * @param tipoTransaccion tipo de transacción (por ejemplo, depósito, retiro, transferencia).
     * @param monto           monto de la transacción.
     * @param fecha           fecha en que se realizó la transacción.
     */
    public Transaccion(int idTransaccion, int idCuentaOrigen, int idCuentaDestino, String tipoTransaccion, double monto, String fecha) {
        this.idTransaccion = idTransaccion;
        this.idCuentaOrigen = idCuentaOrigen;
        this.idCuentaDestino = idCuentaDestino;
        this.tipoTransaccion = tipoTransaccion;
        this.monto = monto;
        this.fecha = fecha;
    }

    /**
     * Constructor vacío de la clase Transaccion.
     * Permite crear una instancia de Transaccion sin inicializar sus atributos.
     */
    public Transaccion() {
    }

    // Getters y Setters

    /**
     * Método para obtener el ID de la transacción.
     *
     * @return el identificador único de la transacción.
     */
    public int getIdTransaccion() {
        return idTransaccion;
    }

    /**
     * Método para establecer el ID de la transacción.
     *
     * @param idTransaccion el identificador único de la transacción.
     */
    public void setIdTransaccion(int idTransaccion) {
        this.idTransaccion = idTransaccion;
    }

    /**
     * Método para obtener el ID de la cuenta de origen de la transacción.
     *
     * @return el identificador de la cuenta de origen.
     */
    public int getIdCuentaOrigen() {
        return idCuentaOrigen;
    }

    /**
     * Método para establecer el ID de la cuenta de origen de la transacción.
     *
     * @param idCuentaOrigen el identificador de la cuenta de origen.
     */
    public void setIdCuentaOrigen(int idCuentaOrigen) {
        this.idCuentaOrigen = idCuentaOrigen;
    }

    /**
     * Método para obtener el ID de la cuenta de destino de la transacción.
     *
     * @return el identificador de la cuenta de destino.
     */
    public int getIdCuentaDestino() {
        return idCuentaDestino;
    }

    /**
     * Método para establecer el ID de la cuenta de destino de la transacción.
     *
     * @param idCuentaDestino el identificador de la cuenta de destino.
     */
    public void setIdCuentaDestino(int idCuentaDestino) {
        this.idCuentaDestino = idCuentaDestino;
    }

    /**
     * Método para obtener el tipo de la transacción.
     *
     * @return el tipo de la transacción (por ejemplo, depósito, retiro, transferencia).
     */
    public String getTipoTransaccion() {
        return tipoTransaccion;
    }

    /**
     * Método para establecer el tipo de la transacción.
     *
     * @param tipoTransaccion el tipo de la transacción.
     */
    public void setTipoTransaccion(String tipoTransaccion) {
        this.tipoTransaccion = tipoTransaccion;
    }

    /**
     * Método para obtener el monto de la transacción.
     *
     * @return el monto de la transacción.
     */
    public double getMonto() {
        return monto;
    }

    /**
     * Método para establecer el monto de la transacción.
     *
     * @param monto el monto de la transacción.
     */
    public void setMonto(double monto) {
        this.monto = monto;
    }

    /**
     * Método para obtener la fecha de la transacción.
     *
     * @return la fecha en que se realizó la transacción.
     */
    public String getFecha() {
        return fecha;
    }

    /**
     * Método para establecer la fecha de la transacción.
     *
     * @param fecha la fecha en que se realizó la transacción.
     */
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

}
