package Controlador;

import servicio.CuentaServicio;
import servicio.TransaccionServicio;
import util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class AdministradorControlador {

    private final CuentaServicio cuentaServicio;
    private final TransaccionServicio transaccionServicio;

    /**
     * Constructor de la clase AdministradorControlador.
     * Inicializa las instancias de los servicios necesarios para la gestión de cuentas y transacciones.
     */
    public AdministradorControlador() {
        this.cuentaServicio = new CuentaServicio();
        this.transaccionServicio = new TransaccionServicio();
    }

    /**
     * Método para mostrar el menú del administrador.
     * Proporciona opciones para consultar el historial de transacciones,
     * generar reportes financieros, revisar cuentas inactivas y configurar usuarios.
     * Permite al administrador interactuar con el sistema mediante un menú interactivo.
     */
    public void mostrarMenuAdministrador() {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("=== Menu del Administrador ===");
            System.out.println("1. Consultar Historial de Transacciones");
            System.out.println("2. Generar Reportes Financieros");
            System.out.println("3. Revisar Cuentas Inactivas");
            System.out.println("4. Configurar Usuarios");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opcion: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1 -> consultarHistorialTransacciones(scanner);
                case 2 -> generarReportesFinancieros();
                case 3 -> revisarCuentasInactivas(scanner);
                case 4 -> configurarUsuarios(scanner);
                case 5 -> System.out.println("Saliendo del menu...");
                default -> System.out.println("Opcion no valida.");
            }
        } while (opcion != 5);
    }

    /**
     * Método para consultar el historial de transacciones.
     * Permite al administrador ver todas las transacciones o filtrarlas por un período de tiempo.
     * Solicita al usuario que elija una opción y ejecuta la consulta correspondiente.
     *
     * @param scanner objeto Scanner para capturar la entrada del usuario.
     */
    private void consultarHistorialTransacciones(Scanner scanner) {
        System.out.println("=== Consultar Historial de Transacciones ===");
        System.out.println("1. Ver todas las transacciones");
        System.out.println("2. Filtrar por período de tiempo");
        System.out.print("Seleccione una opcion: ");
        int opcion = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer
    
        String query;
        switch (opcion) {
            case 1:
                query = "SELECT * FROM Transaccion";
                mostrarHistorialTransacciones(query, null, null);
                break;
            case 2:
                System.out.print("Ingrese la fecha de inicio (YYYY-MM-DD): ");
                String fechaInicio = scanner.nextLine();
                System.out.print("Ingrese la fecha de fin (YYYY-MM-DD): ");
                String fechaFin = scanner.nextLine();
                query = "SELECT * FROM Transaccion WHERE fecha BETWEEN ? AND ?";
                mostrarHistorialTransacciones(query, fechaInicio, fechaFin);
                break;
            default:
                System.out.println("Opcion no valida.");
                break;
        }
    }
    
    /**
     * Método para mostrar el historial de transacciones según la consulta especificada.
     * Puede mostrar todas las transacciones o filtrarlas por un rango de fechas.
     * Realiza la consulta SQL y muestra los resultados en la consola.
     *
     * @param query       consulta SQL que se ejecutará para recuperar las transacciones.
     * @param fechaInicio fecha de inicio del rango de filtro (puede ser null si no se aplica filtro).
     * @param fechaFin    fecha de fin del rango de filtro (puede ser null si no se aplica filtro).
     */
    private void mostrarHistorialTransacciones(String query, String fechaInicio, String fechaFin) {
        try (Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query)) {

            if (fechaInicio != null && fechaFin != null) {
                stmt.setString(1, fechaInicio);
                stmt.setString(2, fechaFin);
            }

            ResultSet rs = stmt.executeQuery();

            System.out.println("=== Historial de Transacciones ===");
            while (rs.next()) {
                System.out.println("ID Transaccion: " + rs.getInt("id_transaccion"));
                System.out.println("Cuenta Origen: " + rs.getInt("id_cuenta_origen"));
                System.out.println("Cuenta Destino: " + rs.getInt("id_cuenta_destino"));
                System.out.println("Tipo de Transaccion: " + rs.getString("tipo_transaccion"));
                System.out.println("Monto: " + rs.getDouble("monto"));
                System.out.println("Fecha: " + rs.getDate("fecha"));
                System.out.println("-------------------------------");
            }
        } catch (SQLException e) {
            System.out.println("Error consultando el historial de transacciones: " + e.getMessage());
        }
    }

    /**
     * Método para generar reportes financieros.
     * Proporciona opciones para calcular el saldo promedio de las cuentas,
     * identificar las cuentas con mayor número de transacciones,
     * y generar una vista general de los ingresos netos.
     * Solicita al usuario seleccionar una opción y ejecuta la acción correspondiente.
     */
    private void generarReportesFinancieros() {
        System.out.println("=== Generar Reportes Financieros ===");
        System.out.println("1. Saldo promedio de las cuentas");
        System.out.println("2. Cuentas con mayor número de transacciones");
        System.out.println("3. Vista general de ingresos netos");
        System.out.print("Seleccione una opción: ");
        Scanner scanner = new Scanner(System.in);
        int opcion = scanner.nextInt();
        scanner.nextLine();
    
        switch (opcion) {
            case 1:
                generarSaldoPromedio();
                break;
            case 2:
                generarCuentasMayorTransacciones();
                break;
            case 3:
                generarIngresosNetos();
                break;
            default:
                System.out.println("Opción no válida.");
                break;
        }
    }

    /**
 * Método para generar un reporte de las cuentas con mayor número de transacciones.
 * Consulta la base de datos para identificar las cuentas más activas basándose
 * en la cantidad de transacciones realizadas como origen o destino.
 * Muestra las cinco cuentas con mayor actividad junto con el total de transacciones.
 */
    private void generarCuentasMayorTransacciones() {
        String query = """
            SELECT c.id_cuenta, COUNT(t.id_transaccion) AS total_transacciones
            FROM cuenta c
            LEFT JOIN transaccion t ON c.id_cuenta = t.id_cuenta_origen OR c.id_cuenta = t.id_cuenta_destino
            GROUP BY c.id_cuenta
            ORDER BY total_transacciones DESC
            LIMIT 5
        """;
    
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
    
            System.out.println("=== Cuentas con Mayor Número de Transacciones ===");
            while (rs.next()) {
                System.out.println("ID Cuenta: " + rs.getInt("id_cuenta"));
                System.out.println("Total de Transacciones: " + rs.getInt("total_transacciones"));
                System.out.println("-------------------------------");
            }
        } catch (SQLException e) {
            System.out.println("Error generando el reporte de cuentas con más transacciones: " + e.getMessage());
        }
    }
    
    /**
     * Método para generar un reporte del saldo promedio de las cuentas.
     * Consulta la base de datos para calcular el promedio de los saldos
     * de todas las cuentas registradas y muestra el resultado.
     */
    private void generarSaldoPromedio() {
        String query = "SELECT AVG(saldo) AS saldo_promedio FROM cuenta";
    
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
    
            if (rs.next()) {
                System.out.println("Saldo promedio de las cuentas: $" + rs.getDouble("saldo_promedio"));
            }
        } catch (SQLException e) {
            System.out.println("Error generando el reporte de saldo promedio: " + e.getMessage());
        }
    }
    
    /**
     * Método para generar un reporte de ingresos netos.
     * Calcula y muestra:
     * - El total de depósitos realizados.
     * - El total de retiros realizados.
     * - El balance neto sumando depósitos y restando retiros.
     * Realiza una consulta SQL sobre la tabla de transacciones para obtener los datos.
     */
    private void generarIngresosNetos() {
        String query = """
            SELECT 
                SUM(CASE WHEN tipo_transaccion = 'deposito' THEN monto ELSE 0 END) AS total_depositos,
                SUM(CASE WHEN tipo_transaccion = 'retiro' THEN monto ELSE 0 END) AS total_retiros,
                SUM(CASE WHEN tipo_transaccion = 'deposito' THEN monto ELSE -monto END) AS ingresos_netos
            FROM transaccion
        """;
    
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
    
            if (rs.next()) {
                System.out.println("=== Vista General de Ingresos Netos ===");
                System.out.println("Total Depósitos: $" + rs.getDouble("total_depositos"));
                System.out.println("Total Retiros: $" + rs.getDouble("total_retiros"));
                System.out.println("Ingresos Netos: $" + rs.getDouble("ingresos_netos"));
            }
        } catch (SQLException e) {
            System.out.println("Error generando el reporte de ingresos netos: " + e.getMessage());
        }
    }
    
    /**
     * Método para revisar cuentas inactivas.
     * Permite al administrador consultar cuentas que no han registrado movimientos
     * durante un período especificado por el usuario.
     * Solicita el número de días de inactividad y realiza una consulta SQL para identificar dichas cuentas.
     *
     * @param scanner objeto Scanner para capturar la entrada del usuario.
     */
    private void revisarCuentasInactivas(Scanner scanner) {
        System.out.println("=== Revisar Cuentas Inactivas ===");
        System.out.print("Ingrese el número de días de inactividad: ");
        int diasInactividad = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer
    
        String query = """
            SELECT c.id_cuenta, c.saldo, c.fecha_creacion, p.nombre
            FROM cuenta c
            JOIN perfilcliente p ON c.id_cliente = p.id_cliente
            WHERE NOT EXISTS (
                SELECT 1
                FROM transaccion t
                WHERE t.id_cuenta_origen = c.id_cuenta OR t.id_cuenta_destino = c.id_cuenta
                AND t.fecha > (CURRENT_DATE - INTERVAL '?' DAY)
            )
        """;
    
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
    
            stmt.setInt(1, diasInactividad);
            ResultSet rs = stmt.executeQuery();
    
            System.out.println("=== Cuentas Inactivas ===");
            while (rs.next()) {
                System.out.println("ID Cuenta: " + rs.getInt("id_cuenta"));
                System.out.println("Nombre del Cliente: " + rs.getString("nombre"));
                System.out.println("Saldo: $" + rs.getDouble("saldo"));
                System.out.println("Fecha de Creación: " + rs.getDate("fecha_creacion"));
                System.out.println("-------------------------------");
            }
        } catch (SQLException e) {
            System.out.println("Error consultando cuentas inactivas: " + e.getMessage());
        }
    }
    
    /**
     * Método para configurar usuarios en el sistema.
     * Permite al administrador realizar las siguientes operaciones:
     * - Agregar un nuevo usuario.
     * - Editar la información de un usuario existente.
     * - Eliminar un usuario.
     * Solicita al usuario seleccionar una opción y ejecuta la acción correspondiente.
     *
     * @param scanner objeto Scanner para capturar la entrada del usuario.
     */
    private void configurarUsuarios(Scanner scanner) {
        System.out.println("=== Configuración de Usuarios ===");
        System.out.println("1. Agregar Usuario");
        System.out.println("2. Editar Usuario");
        System.out.println("3. Eliminar Usuario");
        System.out.print("Seleccione una opción: ");
        int opcion = scanner.nextInt();
        scanner.nextLine();
    
        switch (opcion) {
            case 1:
                agregarUsuario(scanner);
                break;
            case 2:
                editarUsuario(scanner);
                break;
            case 3:
                eliminarUsuario(scanner);
                break;
            default:
                System.out.println("Opción no válida.");
                break;
        }
    }

    /**
     * Método para agregar un nuevo usuario al sistema.
     * Solicita al administrador ingresar la información del usuario, como nombre, dirección,
     * teléfono, correo y contraseña. Luego, inserta los datos en la base de datos.
     *
     * @param scanner objeto Scanner para capturar la entrada del administrador.
     */
    private void agregarUsuario(Scanner scanner) {
        System.out.println("=== Agregar Usuario ===");
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
    
        String query = "INSERT INTO perfilcliente (nombre, direccion, telefono, correo, contrasena) VALUES (?, ?, ?, ?, ?)";
    
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
    
            stmt.setString(1, nombre);
            stmt.setString(2, direccion);
            stmt.setString(3, telefono);
            stmt.setString(4, correo);
            stmt.setString(5, contrasena);
    
            stmt.executeUpdate();
            System.out.println("Usuario agregado exitosamente.");
        } catch (SQLException e) {
            System.out.println("Error agregando usuario: " + e.getMessage());
        }
    }
    
    /**
     * Método para editar la información de un usuario existente.
     * Solicita al administrador el ID del usuario a editar y los nuevos valores
     * para los campos nombre, dirección, teléfono, correo y contraseña.
     * Actualiza los datos en la base de datos.
     *
     * @param scanner objeto Scanner para capturar la entrada del administrador.
     */
    private void editarUsuario(Scanner scanner) {
        System.out.println("=== Editar Usuario ===");
        System.out.print("Ingrese el ID del usuario a editar: ");
        int idCliente = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Nuevo Nombre: ");
        String nuevoNombre = scanner.nextLine();
        System.out.print("Nueva Dirección: ");
        String nuevaDireccion = scanner.nextLine();
        System.out.print("Nuevo Teléfono: ");
        String nuevoTelefono = scanner.nextLine();
        System.out.print("Nuevo Correo: ");
        String nuevoCorreo = scanner.nextLine();
        System.out.print("Nueva Contraseña: ");
        String nuevaContrasena = scanner.nextLine();
    
        String query = "UPDATE perfilcliente SET nombre = ?, direccion = ?, telefono = ?, correo = ?, contrasena = ? WHERE id_cliente = ?";
    
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
    
            stmt.setString(1, nuevoNombre);
            stmt.setString(2, nuevaDireccion);
            stmt.setString(3, nuevoTelefono);
            stmt.setString(4, nuevoCorreo);
            stmt.setString(5, nuevaContrasena);
            stmt.setInt(6, idCliente);
    
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Usuario editado exitosamente.");
            } else {
                System.out.println("No se encontró un usuario con el ID especificado.");
            }
        } catch (SQLException e) {
            System.out.println("Error editando usuario: " + e.getMessage());
        }
    }

    /**
     * Método para eliminar un usuario del sistema.
     * Solicita al administrador el ID del usuario a eliminar y elimina el registro correspondiente
     * en la base de datos. Informa si el usuario fue eliminado exitosamente o si no se encontró el ID.
     *
     * @param scanner objeto Scanner para capturar la entrada del administrador.
     */
    private void eliminarUsuario(Scanner scanner) {
        System.out.println("=== Eliminar Usuario ===");
        System.out.print("Ingrese el ID del usuario a eliminar: ");
        int idCliente = scanner.nextInt();
    
        String query = "DELETE FROM perfilcliente WHERE id_cliente = ?";
    
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
    
            stmt.setInt(1, idCliente);
    
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Usuario eliminado exitosamente.");
            } else {
                System.out.println("No se encontró un usuario con el ID especificado.");
            }
        } catch (SQLException e) {
            System.out.println("Error eliminando usuario: " + e.getMessage());
        }
    }
    
    
    
}
