package vista;

import Controlador.AdministradorControlador;

public class MenuAdministrador {

    /**
     * Método estático para mostrar el menú del administrador.
     * Crea una instancia de AdministradorControlador y llama al método
     * mostrarMenuAdministrador para iniciar la interacción con el administrador.
     */
    public static void mostrar() {
        AdministradorControlador adminControlador = new AdministradorControlador();
        adminControlador.mostrarMenuAdministrador();
    }
}
