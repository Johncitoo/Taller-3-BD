package modelo;

public class Cliente {
    private int idCliente;
    private String nombre;
    private String direccion;
    private String telefono;
    private String correo;
    private String contrasena;

    /**
     * Constructor de la clase Cliente.
     * Inicializa una nueva instancia de Cliente con los valores proporcionados para sus atributos.
     *
     * @param idCliente   identificador único del cliente.
     * @param nombre      nombre del cliente.
     * @param direccion   dirección del cliente.
     * @param telefono    número de teléfono del cliente.
     * @param correo      correo electrónico del cliente.
     * @param contrasena  contraseña del cliente.
     */
    public Cliente(int idCliente, String nombre, String direccion, String telefono, String correo, String contrasena) {
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.correo = correo;
        this.contrasena = contrasena;
    }

    /**
     * Constructor vacío de la clase Cliente.
     * Permite crear una instancia de Cliente sin inicializar sus atributos.
     */
    public Cliente() {
    }

    // Getters y Setters

    /**
     * Método para obtener el ID del cliente.
     *
     * @return el identificador único del cliente.
     */
    public int getIdCliente() {
        return idCliente;
    }

    /**
     * Método para establecer el ID del cliente.
     *
     * @param idCliente el identificador único del cliente.
     */
    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    /**
     * Método para obtener el nombre del cliente.
     *
     * @return el nombre del cliente.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Método para establecer el nombre del cliente.
     *
     * @param nombre el nombre del cliente.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Método para obtener la dirección del cliente.
     *
     * @return la dirección del cliente.
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * Método para establecer la dirección del cliente.
     *
     * @param direccion la dirección del cliente.
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * Método para obtener el teléfono del cliente.
     *
     * @return el número de teléfono del cliente.
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * Método para establecer el teléfono del cliente.
     *
     * @param telefono el número de teléfono del cliente.
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * Método para obtener el correo electrónico del cliente.
     *
     * @return el correo electrónico del cliente.
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * Método para establecer el correo electrónico del cliente.
     *
     * @param correo el correo electrónico del cliente.
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    /**
     * Método para obtener la contraseña del cliente.
     *
     * @return la contraseña del cliente.
     */
    public String getContrasena() {
        return contrasena;
    }

    /**
     * Método para establecer la contraseña del cliente.
     *
     * @param contrasena la contraseña del cliente.
     */
    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
}
