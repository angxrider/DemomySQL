package edu.utj.poo.mysql.demomysql;

//Hernandez Virgen Angel Lionel
public class Administrador {

    private int id;
    private String usuario;
    private String nombres;
    private String apellidos;
    private String correo;
    private String contraseña;
    private ERoles rol;
    private String rutaImagen;
    private EEstados estado;

    public Administrador() {
    }

    public Administrador(int id, String usuario, String nombres, String apellidos, String correo,
            String contraseña, ERoles rol, String rutaImagen, EEstados estado) {
        this.id = id;
        this.usuario = usuario;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.correo = correo;
        this.contraseña = contraseña;
        this.rol = rol;
        this.rutaImagen = rutaImagen;
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getNombres() {
        return nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public String getCorreo() {
        return correo;
    }

    public String getContraseña() {
        return contraseña;
    }

    public ERoles getRol() {
        return rol;
    }

    public String getRutaImagen() {
        return rutaImagen;
    }

    public EEstados getEstado() {
        return estado;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public void setRol(ERoles rol) {
        this.rol = rol;
    }

    public void setRutaImagen(String rutaImagen) {
        this.rutaImagen = rutaImagen;
    }

    public void setEstado(EEstados estado) {
        this.estado = estado;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Administrador other = (Administrador) obj;
        return this.id == other.id;
    }

    @Override
    public String toString() {
        return "Administrador{" + "id=" + id + ", usuario=" + usuario + ", nombre=" + nombres + ", apellidos=" + apellidos + ", correo=" + correo + ", contrase\u00f1a=" + contraseña + ", rol=" + rol + ", rutaImagen=" + rutaImagen + ", estado=" + estado + '}';
    }
}
