package dao;

import modelo.Transaccion;
import util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TransaccionDAO {

    /**
     * Método para insertar una nueva transacción en la base de datos.
     * Utiliza los datos de una instancia de Transaccion para ejecutar una consulta SQL de inserción.
     * Retorna true si la transacción fue insertada correctamente, o false si ocurrió un error.
     *
     * @param transaccion instancia de Transaccion con los datos a insertar en la base de datos.
     * @return true si la transacción fue insertada exitosamente, false en caso de error.
     */
    public boolean insertar(Transaccion transaccion) {
        String query = "INSERT INTO Transaccion (id_cuenta_origen, id_cuenta_destino, tipo_transaccion, monto, fecha) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, transaccion.getIdCuentaOrigen());
            stmt.setInt(2, transaccion.getIdCuentaDestino());
            stmt.setString(3, transaccion.getTipoTransaccion());
            stmt.setDouble(4, transaccion.getMonto());
            stmt.setString(5, transaccion.getFecha());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error insertando transacción: " + e.getMessage());
        }
        return false;
    }
}
