package dao;

import modelo.Cliente;
import util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClienteDAO {

    /**
     * Método para buscar un cliente en la base de datos utilizando su correo electrónico.
     * Ejecuta una consulta SQL para obtener los datos del cliente asociado al correo proporcionado.
     * Si el cliente es encontrado, retorna una instancia de la clase Cliente con sus datos.
     * En caso contrario, retorna null.
     *
     * @param correo correo electrónico del cliente a buscar.
     * @return instancia de Cliente si el correo existe en la base de datos, null si no se encuentra.
     */
    public Cliente buscarPorCorreo(String correo) {
        String query = "SELECT * FROM PerfilCliente WHERE correo = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, correo);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Cliente(
                        rs.getInt("id_cliente"),
                        rs.getString("nombre"),
                        rs.getString("direccion"),
                        rs.getString("telefono"),
                        rs.getString("correo"),
                        rs.getString("contrasena")
                );
            }
        } catch (SQLException e) {
            System.out.println("Error buscando cliente por correo: " + e.getMessage());
        }
        return null;
    }

    /**
     * Método para insertar un nuevo cliente en la base de datos.
     * Utiliza los datos del cliente proporcionado para ejecutar una consulta SQL de inserción.
     * Retorna true si la inserción fue exitosa, o false si ocurrió un error.
     *
     * @param cliente instancia de Cliente con los datos a insertar en la base de datos.
     * @return true si el cliente fue insertado correctamente, false en caso de error.
     */
    public boolean insertar(Cliente cliente) {
        String query = "INSERT INTO PerfilCliente (nombre, direccion, telefono, correo, contrasena) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, cliente.getNombre());
            stmt.setString(2, cliente.getDireccion());
            stmt.setString(3, cliente.getTelefono());
            stmt.setString(4, cliente.getCorreo());
            stmt.setString(5, cliente.getContrasena());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error insertando cliente: " + e.getMessage());
        }
        return false;
    }

    /**
     * Método para buscar un cliente en la base de datos utilizando su correo electrónico y contraseña.
     * Ejecuta una consulta SQL para validar las credenciales proporcionadas.
     * Si las credenciales son correctas, retorna una instancia de la clase Cliente con los datos correspondientes.
     * En caso contrario, retorna null.
     *
     * @param correo      correo electrónico del cliente.
     * @param contrasena  contraseña del cliente.
     * @return instancia de Cliente si las credenciales son válidas, null si no se encuentra o las credenciales son incorrectas.
     */
    public Cliente buscarPorCredenciales(String correo, String contrasena) {
        String query = "SELECT * FROM PerfilCliente WHERE correo = ? AND contrasena = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, correo);
            stmt.setString(2, contrasena);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Cliente(
                        rs.getInt("id_cliente"),
                        rs.getString("nombre"),
                        rs.getString("direccion"),
                        rs.getString("telefono"),
                        rs.getString("correo"),
                        rs.getString("contrasena")
                );
            }
        } catch (SQLException e) {
            System.out.println("Error buscando cliente: " + e.getMessage());
        }
        return null;
    }  
}
