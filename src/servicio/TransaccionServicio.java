package servicio;

import dao.CuentaDAO;
import dao.TransaccionDAO;
import modelo.Cuenta;
import modelo.Transaccion;

public class TransaccionServicio {

    private TransaccionDAO transaccionDAO;
    private CuentaDAO cuentaDAO;

    public TransaccionServicio() {
        this.transaccionDAO = new TransaccionDAO();
        this.cuentaDAO = new CuentaDAO();
    }

    public boolean realizarTransferencia(int idCuentaOrigen, int idCuentaDestino, double monto) {
        Cuenta cuentaOrigen = cuentaDAO.buscarPorId(idCuentaOrigen);
        Cuenta cuentaDestino = cuentaDAO.buscarPorId(idCuentaDestino);

        if (cuentaOrigen != null && cuentaDestino != null) {
            if (cuentaOrigen.getSaldo() >= monto) {
                cuentaOrigen.setSaldo(cuentaOrigen.getSaldo() - monto);
                cuentaDestino.setSaldo(cuentaDestino.getSaldo() + monto);

                if (cuentaDAO.actualizarSaldo(cuentaOrigen) && cuentaDAO.actualizarSaldo(cuentaDestino)) {
                    Transaccion transaccion = new Transaccion(0, idCuentaOrigen, idCuentaDestino, "Transferencia", monto, java.time.LocalDate.now().toString());
                    return transaccionDAO.insertar(transaccion);
                }
            } else {
                System.out.println("Saldo insuficiente en la cuenta de origen.");
            }
        } else {
            System.out.println("Cuentas no encontradas.");
        }
        return false;
    }
}
