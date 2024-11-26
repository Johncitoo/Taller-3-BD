package servicio;

import dao.ClienteDAO;
import modelo.Cliente;

public class ClienteServicio {

    private ClienteDAO clienteDAO;

    /**
     * Constructor de la clase ClienteServicio.
     * Inicializa una nueva instancia del servicio con una instancia de ClienteDAO para interactuar con la base de datos.
     */
    public ClienteServicio() {
        this.clienteDAO = new ClienteDAO();
    }

    /**
     * Método para iniciar sesión.
     * Verifica las credenciales del cliente (correo y contraseña) llamando al método correspondiente en ClienteDAO.
     * 
     * @param correo      correo electrónico del cliente.
     * @param contrasena  contraseña del cliente.
     * @return una instancia de Cliente si las credenciales son válidas, null si no lo son.
     */
    public Cliente iniciarSesion(String correo, String contrasena) {
        return clienteDAO.buscarPorCredenciales(correo, contrasena);
    }

    /**
     * Método para registrar un nuevo cliente.
     * Verifica si el correo proporcionado ya está registrado. Si no, inserta el cliente en la base de datos.
     * 
     * @param cliente instancia de Cliente con los datos a registrar.
     * @return true si el cliente fue registrado exitosamente, false si el correo ya está registrado o si ocurrió un error.
     */
    public boolean registrarCliente(Cliente cliente) {
        if (clienteDAO.buscarPorCorreo(cliente.getCorreo()) == null) {
            return clienteDAO.insertar(cliente);
        } else {
            System.out.println("El correo ya está registrado.");
            return false;
        }
    }
}
