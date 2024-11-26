package Controlador;

import modelo.Cliente;
import servicio.CuentaServicio;
import servicio.TransaccionServicio;

import java.util.Scanner;

public class clienteControlador {

    private final CuentaServicio cuentaServicio;
    private final TransaccionServicio transaccionServicio;

    /**
     * Constructor de la clase clienteControlador.
     * Inicializa las instancias de los servicios necesarios para la gestión de cuentas y transacciones
     * relacionadas con los clientes.
     */
    public clienteControlador() {
        this.cuentaServicio = new CuentaServicio();
        this.transaccionServicio = new TransaccionServicio();
    }

    /**
     * Método para mostrar el menú del cliente.
     * Proporciona opciones para realizar depósitos, retiros, transferencias y consultar el saldo.
     * Solicita al cliente seleccionar una opción y ejecuta la acción correspondiente.
     *
     * @param cliente instancia del cliente que inició sesión y que interactuará con el sistema.
     */
    public void mostrarMenuCliente(Cliente cliente) {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("=== Menú del Cliente ===");
            System.out.println("1. Realizar Depósito");
            System.out.println("2. Realizar Retiro");
            System.out.println("3. Realizar Transferencia");
            System.out.println("4. Consultar Saldo");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1 -> realizarDeposito(cliente, scanner);
                case 2 -> realizarRetiro(cliente, scanner);
                case 3 -> realizarTransferencia(cliente, scanner);
                case 4 -> consultarSaldo(cliente, scanner);
                case 5 -> System.out.println("Saliendo del menú...");
                default -> System.out.println("Opción no válida.");
            }
        } while (opcion != 5);
    }

    /**
     * Método para realizar un depósito en una cuenta específica.
     * Solicita al cliente el ID de la cuenta y el monto a depositar.
     * Llama al servicio correspondiente para realizar el depósito e informa
     * si la operación fue exitosa o si ocurrió un error.
     *
     * @param cliente instancia del cliente que realiza el depósito.
     * @param scanner objeto Scanner para capturar la entrada del cliente.
     */
    private void realizarDeposito(Cliente cliente, Scanner scanner) {
        System.out.print("Ingrese el ID de la cuenta: ");
        int idCuenta = scanner.nextInt();
        System.out.print("Ingrese el monto a depositar: ");
        double monto = scanner.nextDouble();

        if (cuentaServicio.realizarDeposito(idCuenta, monto)) {
            System.out.println("Depósito realizado con éxito.");
        } else {
            System.out.println("Error al realizar el depósito.");
        }
    }

    /**
     * Método para realizar un retiro de una cuenta específica.
     * Solicita al cliente el ID de la cuenta y el monto a retirar.
     * Llama al servicio correspondiente para realizar el retiro e informa
     * si la operación fue exitosa o si ocurrió un error.
     *
     * @param cliente instancia del cliente que realiza el retiro.
     * @param scanner objeto Scanner para capturar la entrada del cliente.
     */
    private void realizarRetiro(Cliente cliente, Scanner scanner) {
        System.out.print("Ingrese el ID de la cuenta: ");
        int idCuenta = scanner.nextInt();
        System.out.print("Ingrese el monto a retirar: ");
        double monto = scanner.nextDouble();

        if (cuentaServicio.realizarRetiro(idCuenta, monto)) {
            System.out.println("Retiro realizado con éxito.");
        } else {
            System.out.println("Error al realizar el retiro.");
        }
    }

    /**
     * Método para realizar una transferencia entre dos cuentas.
     * Solicita al cliente los IDs de las cuentas de origen y destino, así como el monto a transferir.
     * Llama al servicio correspondiente para realizar la transferencia e informa
     * si la operación fue exitosa o si ocurrió un error.
     *
     * @param cliente instancia del cliente que realiza la transferencia.
     * @param scanner objeto Scanner para capturar la entrada del cliente.
     */
    private void realizarTransferencia(Cliente cliente, Scanner scanner) {
        System.out.print("Ingrese el ID de la cuenta de origen: ");
        int idCuentaOrigen = scanner.nextInt();
        System.out.print("Ingrese el ID de la cuenta de destino: ");
        int idCuentaDestino = scanner.nextInt();
        System.out.print("Ingrese el monto a transferir: ");
        double monto = scanner.nextDouble();

        if (transaccionServicio.realizarTransferencia(idCuentaOrigen, idCuentaDestino, monto)) {
            System.out.println("Transferencia realizada con éxito.");
        } else {
            System.out.println("Error al realizar la transferencia.");
        }
    }

    /**
     * Método para consultar el saldo de una cuenta específica.
     * Solicita al cliente el ID de la cuenta y consulta el saldo utilizando el servicio correspondiente.
     * Muestra el saldo si la operación es exitosa o un mensaje de error en caso contrario.
     *
     * @param cliente instancia del cliente que consulta el saldo.
     * @param scanner objeto Scanner para capturar la entrada del cliente.
     */
    private void consultarSaldo(Cliente cliente, Scanner scanner) {
        System.out.print("Ingrese el ID de la cuenta: ");
        int idCuenta = scanner.nextInt();

        double saldo = cuentaServicio.consultarSaldo(idCuenta);
        if (saldo >= 0) {
            System.out.println("El saldo de la cuenta es: $" + saldo);
        } else {
            System.out.println("Error al consultar el saldo.");
        }
    }
}
