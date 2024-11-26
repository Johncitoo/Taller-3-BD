package vista;

import Controlador.clienteControlador;
import Controlador.AdministradorControlador;
import servicio.ClienteServicio;
import modelo.Cliente;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        ClienteServicio clienteServicio = new ClienteServicio();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("=== Bienvenido a Banca Segura ===");
            System.out.println("1. Iniciar Sesión");
            System.out.println("2. Registrarse");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer

            switch (opcion) {
                case 1 -> iniciarSesion(clienteServicio, scanner);
                case 2 -> registrarse(clienteServicio, scanner);
                case 3 -> {
                    System.out.println("Gracias por usar Banca Segura. ¡Hasta pronto!");
                    return; // Salir del programa
                }
                default -> System.out.println("Opción no válida. Intente nuevamente.");
            }
        }
    }

    private static void iniciarSesion(ClienteServicio clienteServicio, Scanner scanner) {
        while (true) {
            System.out.print("Correo: ");
            String correo = scanner.nextLine();
            System.out.print("Contraseña: ");
            String contrasena = scanner.nextLine();

            if ("skibidi".equals(correo) && "toilet".equals(contrasena)) {
                AdministradorControlador adminControlador = new AdministradorControlador();
                adminControlador.mostrarMenuAdministrador();
                break; // Salir del bucle al mostrar el menú de administrador
            } else {
                Cliente cliente = clienteServicio.iniciarSesion(correo, contrasena);
                if (cliente != null) {
                    clienteControlador clienteControlador = new clienteControlador();
                    clienteControlador.mostrarMenuCliente(cliente);
                    break; // Salir del bucle al mostrar el menú de cliente
                } else {
                    System.out.println("Credenciales incorrectas. Intente nuevamente.");
                }
            }
        }
    }

    private static void registrarse(ClienteServicio clienteServicio, Scanner scanner) {
        while (true) {
            System.out.println("=== Registro de Cliente ===");
            System.out.print("Nombre: ");
            String nombre = scanner.nextLine();
            System.out.print("Dirección: ");
            String direccion = scanner.nextLine();
            System.out.print("Teléfono: ");
            String telefono = scanner.nextLine();
            System.out.print("Correo: ");
            String correo = scanner.nextLine();
            System.out.print("Contraseña: ");
            String contrasena = scanner.nextLine();

            Cliente cliente = new Cliente(0, nombre, direccion, telefono, correo, contrasena);
            if (clienteServicio.registrarCliente(cliente)) {
                System.out.println("Registro exitoso. Ahora puede iniciar sesión.");
                break; // Salir del bucle al completar el registro
            } else {
                System.out.println("Error en el registro. Intente nuevamente.");
            }
        }
    }
}
