package servicio;

import dao.CuentaDAO;
import dao.TransaccionDAO;
import modelo.Cuenta;
import modelo.Transaccion;

public class TransaccionServicio {

    private TransaccionDAO transaccionDAO;
    private CuentaDAO cuentaDAO;

    /**
     * Constructor de la clase TransaccionServicio.
     * Inicializa una nueva instancia del servicio con instancias de TransaccionDAO y CuentaDAO para interactuar con la base de datos.
     */
    public TransaccionServicio() {
        this.transaccionDAO = new TransaccionDAO();
        this.cuentaDAO = new CuentaDAO();
    }

    /**
     * Método para realizar una transferencia entre dos cuentas.
     * Verifica que ambas cuentas existan y que la cuenta de origen tenga saldo suficiente.
     * Si la verificación es exitosa, actualiza los saldos de ambas cuentas y registra la transacción en la base de datos.
     * 
     * @param idCuentaOrigen ID de la cuenta de origen de la transferencia.
     * @param idCuentaDestino ID de la cuenta de destino de la transferencia.
     * @param monto monto a transferir.
     * @return true si la transferencia y el registro de la transacción fueron exitosos, false en caso de error o si las condiciones no se cumplen.
     */
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
