package Controlador;

import servicio.CuentaServicio;
import servicio.TransaccionServicio;

import java.util.Scanner;

public class AdministradorControlador {

    private final CuentaServicio cuentaServicio;
    private final TransaccionServicio transaccionServicio;

    public AdministradorControlador() {
        this.cuentaServicio = new CuentaServicio();
        this.transaccionServicio = new TransaccionServicio();
    }

    public void mostrarMenuAdministrador() {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("=== Menú del Administrador ===");
            System.out.println("1. Consultar Historial de Transacciones");
            System.out.println("2. Generar Reportes Financieros");
            System.out.println("3. Revisar Cuentas Inactivas");
            System.out.println("4. Configurar Usuarios");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1 -> consultarHistorialTransacciones(scanner);
                case 2 -> generarReportesFinancieros();
                case 3 -> revisarCuentasInactivas(scanner);
                case 4 -> configurarUsuarios();
                case 5 -> System.out.println("Saliendo del menú...");
                default -> System.out.println("Opción no válida.");
            }
        } while (opcion != 5);
    }

    private void consultarHistorialTransacciones(Scanner scanner) {
        System.out.println("Funcionalidad pendiente: Consultar historial de transacciones.");
    }

    private void generarReportesFinancieros() {
        System.out.println("Funcionalidad pendiente: Generar reportes financieros.");
    }

    private void revisarCuentasInactivas(Scanner scanner) {
        System.out.println("Funcionalidad pendiente: Revisar cuentas inactivas.");
    }

    private void configurarUsuarios() {
        System.out.println("Funcionalidad pendiente: Configurar usuarios.");
    }
}
