package vista;

import Controlador.clienteControlador;
import modelo.Cliente;

public class MenuCliente {

    /**
     * Método estático para mostrar el menú del cliente.
     * Crea una instancia de clienteControlador y llama al método
     * mostrarMenuCliente, pasando como parámetro el cliente que inició sesión.
     *
     * @param cliente instancia de Cliente que interactuará con el menú.
     */
    public static void mostrar(Cliente cliente) {
        clienteControlador clienteControlador = new clienteControlador();
        clienteControlador.mostrarMenuCliente(cliente);
    }
}
