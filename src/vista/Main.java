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
            scanner.nextLine();

            switch (opcion) {
                case 1 -> iniciarSesion(clienteServicio, scanner);
                case 2 -> registrarse(clienteServicio, scanner);
                case 3 -> {
                    System.out.println("Gracias por usar Banca Segura. ¡Hasta pronto!");
                    return;
                }
                default -> System.out.println("Opción no válida. Intente nuevamente.");
            }
        }
    }

    /**
     * Método estático para manejar el inicio de sesión en el sistema.
     * Permite a los usuarios autenticarse como cliente o administrador.
     * Si las credenciales son válidas, redirige al menú correspondiente según el rol.
     * 
     * @param clienteServicio instancia del servicio de cliente que gestiona las operaciones relacionadas con clientes.
     * @param scanner         objeto Scanner para leer la entrada del usuario desde la consola.
     */
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

    /**
     * Método estático para registrar un nuevo cliente en el sistema.
     * Solicita los datos del cliente al usuario y los envía al servicio de cliente
     * para que se inserten en la base de datos.
     * Si el registro falla, permite intentarlo nuevamente.
     *
     * @param clienteServicio instancia del servicio de cliente que gestiona las operaciones relacionadas con clientes.
     * @param scanner         objeto Scanner para leer la entrada del usuario desde la consola.
     */
    private static void registrarse(ClienteServicio clienteServicio, Scanner scanner) {
        while (true) {
            System.out.println("=== Registro de Cliente ===");
            System.out.print("Nombre: ");
            String nombre = scanner.nextLine();
            System.out.print("Direccion: ");
            String direccion = scanner.nextLine();
            System.out.print("Telefono: ");
            String telefono = scanner.nextLine();
            System.out.print("Correo: ");
            String correo = scanner.nextLine();
            System.out.print("Contraseña: ");
            String contrasena = scanner.nextLine();

            Cliente cliente = new Cliente(0, nombre, direccion, telefono, correo, contrasena);
            if (clienteServicio.registrarCliente(cliente)) {
                System.out.println("Registro exitoso. Ahora puede iniciar sesion.");
                break; // Salir del bucle al completar el registro
            } else {
                System.out.println("Error en el registro. Intente nuevamente.");
            }
        }
    }
}
