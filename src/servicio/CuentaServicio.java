package servicio;

import dao.CuentaDAO;
import modelo.Cuenta;

public class CuentaServicio {

    private CuentaDAO cuentaDAO;

    /**
     * Constructor de la clase CuentaServicio.
     * Inicializa una nueva instancia del servicio con una instancia de CuentaDAO para interactuar con la base de datos.
     */
    public CuentaServicio() {
        this.cuentaDAO = new CuentaDAO();
    }

    /**
     * Método para realizar un depósito en una cuenta.
     * Busca la cuenta correspondiente por su ID, actualiza el saldo sumando el monto indicado y guarda los cambios en la base de datos.
     * 
     * @param idCuenta ID de la cuenta en la que se realizará el depósito.
     * @param monto    monto a depositar.
     * @return true si el depósito fue exitoso, false si la cuenta no fue encontrada o si ocurrió un error.
     */
    public boolean realizarDeposito(int idCuenta, double monto) {
        Cuenta cuenta = cuentaDAO.buscarPorId(idCuenta);
        if (cuenta != null) {
            cuenta.setSaldo(cuenta.getSaldo() + monto);
            return cuentaDAO.actualizarSaldo(cuenta);
        }
        System.out.println("Cuenta no encontrada.");
        return false;
    }

    /**
     * Método para realizar un retiro de una cuenta.
     * Busca la cuenta correspondiente por su ID, verifica si el saldo es suficiente y actualiza el saldo restando el monto indicado.
     * 
     * @param idCuenta ID de la cuenta de la que se realizará el retiro.
     * @param monto    monto a retirar.
     * @return true si el retiro fue exitoso, false si la cuenta no fue encontrada, el saldo es insuficiente o si ocurrió un error.
     */
    public boolean realizarRetiro(int idCuenta, double monto) {
        Cuenta cuenta = cuentaDAO.buscarPorId(idCuenta);
        if (cuenta != null) {
            if (cuenta.getSaldo() >= monto) {
                cuenta.setSaldo(cuenta.getSaldo() - monto);
                return cuentaDAO.actualizarSaldo(cuenta);
            } else {
                System.out.println("Saldo insuficiente.");
            }
        } else {
            System.out.println("Cuenta no encontrada.");
        }
        return false;
    }

    /**
     * Método para consultar el saldo de una cuenta.
     * Busca la cuenta correspondiente por su ID y retorna el saldo actual.
     * 
     * @param idCuenta ID de la cuenta cuyo saldo se desea consultar.
     * @return el saldo de la cuenta si fue encontrada, o -1 si no se encontró la cuenta.
     */
    public double consultarSaldo(int idCuenta) {
        Cuenta cuenta = cuentaDAO.buscarPorId(idCuenta);
        return (cuenta != null) ? cuenta.getSaldo() : -1;
    }
}
