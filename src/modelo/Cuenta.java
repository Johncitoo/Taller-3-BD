package modelo;

public class Cuenta {
    private int idCuenta;
    private int idCliente;
    private double saldo;
    private String fechaCreacion;

    // Constructor completo
    public Cuenta(int idCuenta, int idCliente, double saldo, String fechaCreacion) {
        this.idCuenta = idCuenta;
        this.idCliente = idCliente;
        this.saldo = saldo;
        this.fechaCreacion = fechaCreacion;
    }

    // Constructor vacío
    public Cuenta() {
    }

    // Getters y Setters
    public int getIdCuenta() {
        return idCuenta;
    }

    public void setIdCuenta(int idCuenta) {
        this.idCuenta = idCuenta;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    @Override
    public String toString() {
        return "Cuenta{" +
                "idCuenta=" + idCuenta +
                ", idCliente=" + idCliente +
                ", saldo=" + saldo +
                ", fechaCreacion='" + fechaCreacion + '\'' +
                '}';
    }
}
