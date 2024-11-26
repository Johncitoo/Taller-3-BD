package vista;

import Controlador.clienteControlador;
import modelo.Cliente;

public class MenuCliente {

    public static void mostrar(Cliente cliente) {
        clienteControlador clienteControlador = new clienteControlador();
        clienteControlador.mostrarMenuCliente(cliente);
    }
}
