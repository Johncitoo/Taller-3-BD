package Controlador;

import modelo.Cliente;
import servicio.CuentaServicio;
import servicio.TransaccionServicio;

import java.util.Scanner;

public class clienteControlador {

    private final CuentaServicio cuentaServicio;
    private final TransaccionServicio transaccionServicio;

    public clienteControlador() {
        this.cuentaServicio = new CuentaServicio();
        this.transaccionServicio = new TransaccionServicio();
    }

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
