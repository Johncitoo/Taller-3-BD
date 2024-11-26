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

        System.out.println("=== Bienvenido a Banca Segura ===");
        System.out.println("1. Iniciar Sesión");
        System.out.println("2. Registrarse");
        System.out.print("Seleccione una opción: ");
        int opcion = scanner.nextInt();
        scanner.nextLine();

        switch (opcion) {
            case 1 -> iniciarSesion(clienteServicio, scanner);
            case 2 -> registrarse(clienteServicio, scanner);
            default -> System.out.println("Opción no válida.");
        }
    }

    private static void iniciarSesion(ClienteServicio clienteServicio, Scanner scanner) {
        System.out.print("Correo: ");
        String correo = scanner.nextLine();
        System.out.print("Contraseña: ");
        String contrasena = scanner.nextLine();

        if ("skibidi".equals(correo) && "toilet".equals(contrasena)) {
            AdministradorControlador adminControlador = new AdministradorControlador();
            adminControlador.mostrarMenuAdministrador();
        } else {
            Cliente cliente = clienteServicio.iniciarSesion(correo, contrasena);
            if (cliente != null) {
                clienteControlador clienteControlador = new clienteControlador();
                clienteControlador.mostrarMenuCliente(cliente);
            } else {
                System.out.println("Credenciales incorrectas.");
            }
        }
    }

    private static void registrarse(ClienteServicio clienteServicio, Scanner scanner) {
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
        } else {
            System.out.println("Error en el registro. Intente nuevamente.");
        }
    }
}
