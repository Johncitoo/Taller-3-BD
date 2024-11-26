package vista;

import Controlador.AdministradorControlador;

public class MenuAdministrador {

    public static void mostrar() {
        AdministradorControlador adminControlador = new AdministradorControlador();
        adminControlador.mostrarMenuAdministrador();
    }
}
