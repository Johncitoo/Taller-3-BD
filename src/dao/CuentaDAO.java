package dao;

import modelo.Cuenta;
import util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CuentaDAO {

    /**
     * Método para buscar una cuenta en la base de datos utilizando su ID.
     * Ejecuta una consulta SQL para recuperar los datos de la cuenta correspondiente.
     * Si la cuenta es encontrada, retorna una instancia de la clase Cuenta con los datos.
     * En caso contrario, retorna null.
     *
     * @param idCuenta ID de la cuenta a buscar.
     * @return instancia de Cuenta si la cuenta es encontrada, null si no se encuentra.
     */
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

    /**
     * Método para actualizar el saldo de una cuenta en la base de datos.
     * Recibe una instancia de Cuenta con el nuevo saldo y el ID de la cuenta a actualizar.
     * Retorna true si la operación de actualización fue exitosa, o false si ocurrió un error.
     *
     * @param cuenta instancia de Cuenta con el nuevo saldo y el ID de la cuenta.
     * @return true si el saldo fue actualizado correctamente, false en caso de error.
     */
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
