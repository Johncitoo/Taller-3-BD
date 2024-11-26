package dao;

import modelo.Cuenta;
import util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CuentaDAO {

    public Cuenta buscarPorId(int idCuenta) {
        String query = "SELECT * FROM Cuenta WHERE id_cuenta = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, idCuenta);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Cuenta(
                        rs.getInt("id_cuenta"),
                        rs.getInt("id_cliente"),
                        rs.getDouble("saldo"),
                        rs.getString("fecha_creacion")
                );
            }
        } catch (SQLException e) {
            System.out.println("Error buscando cuenta: " + e.getMessage());
        }
        return null;
    }

    public boolean actualizarSaldo(Cuenta cuenta) {
        String query = "UPDATE Cuenta SET saldo = ? WHERE id_cuenta = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setDouble(1, cuenta.getSaldo());
            stmt.setInt(2, cuenta.getIdCuenta());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error actualizando saldo: " + e.getMessage());
        }
        return false;
    }
}
