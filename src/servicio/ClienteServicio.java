package servicio;

import dao.ClienteDAO;
import modelo.Cliente;

public class ClienteServicio {

    private ClienteDAO clienteDAO;

    public ClienteServicio() {
        this.clienteDAO = new ClienteDAO();
    }

    public Cliente iniciarSesion(String correo, String contrasena) {
        return clienteDAO.buscarPorCredenciales(correo, contrasena);
    }

    public boolean registrarCliente(Cliente cliente) {
        if (clienteDAO.buscarPorCorreo(cliente.getCorreo()) == null) {
            return clienteDAO.insertar(cliente);
        } else {
            System.out.println("El correo ya está registrado.");
            return false;
        }
    }
}
