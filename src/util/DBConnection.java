package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static Connection connection;
    private static final String URL = "jdbc:postgresql://localhost:5432/Taller3"; // Aqui reemplaza por la ubicacion de la base de datos, yo utilice pg4admin
    private static final String USER = "postgres";
    private static final String PASSWORD = "112002";

    /**
     * Constructor privado de la clase DBConnection.
     * Previene la instanciación directa de la clase, garantizando el uso del patrón Singleton.
     */
    private DBConnection() {
    }

    /**
     * Método estático para obtener una conexión a la base de datos.
     * Crea una nueva conexión si no existe o si la conexión actual está cerrada.
     * Retorna la conexión establecida.
     * 
     * @return instancia de Connection para interactuar con la base de datos.
     */
    public static Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("Conexión exitosa a la base de datos.");
            }
        } catch (SQLException e) {
            System.out.println("Error al conectar a la base de datos: " + e.getMessage());
        }
        return connection;
    }

    /**
     * Método estático para cerrar la conexión a la base de datos.
     * Verifica si existe una conexión activa, la cierra y establece el objeto de conexión a null.
     * Maneja posibles errores al cerrar la conexión.
     */
    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                connection = null;
                System.out.println("Conexión cerrada.");
            } catch (SQLException e) {
                System.out.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }
    }
}
