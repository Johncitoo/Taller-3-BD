package dao;

import modelo.Cliente;
import util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClienteDAO {

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
