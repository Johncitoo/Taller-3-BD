package servicio;

import dao.CuentaDAO;
import modelo.Cuenta;

public class CuentaServicio {

    private CuentaDAO cuentaDAO;

    public CuentaServicio() {
        this.cuentaDAO = new CuentaDAO();
    }

    public boolean realizarDeposito(int idCuenta, double monto) {
        Cuenta cuenta = cuentaDAO.buscarPorId(idCuenta);
        if (cuenta != null) {
            cuenta.setSaldo(cuenta.getSaldo() + monto);
            return cuentaDAO.actualizarSaldo(cuenta);
        }
        System.out.println("Cuenta no encontrada.");
        return false;
    }

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

    public double consultarSaldo(int idCuenta) {
        Cuenta cuenta = cuentaDAO.buscarPorId(idCuenta);
        return (cuenta != null) ? cuenta.getSaldo() : -1;
    }
}
