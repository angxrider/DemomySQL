package edu.utj.poo.mysql.demomysql.daos;
//Hernandez Virgen Angel 

import edu.utj.poo.mysql.demomysql.Administrador;
import edu.utj.poo.mysql.demomysql.ERoles;
import edu.utj.poo.mysql.demomysql.EEstados;
import edu.utj.poo.mysql.demomysql.Conexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author TUF
 */
public class AdministradorDAO {

    public boolean guardar(Administrador admin) {
        String sql = "INSERT INTO administradores "
                + "(usuario, nombres, apellidos, correo, contraseña, rol, ruta_imagen, estado) "
                + "VALUES(?,?,?,?,?,?,?,?);";
        try (Connection con = Conexion.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, admin.getUsuario());
            ps.setString(2, admin.getNombres());
            ps.setString(3, admin.getApellidos());
            ps.setString(4, admin.getCorreo());
            ps.setString(5, admin.getContraseña());
            ps.setString(6, admin.getRol() != null ? admin.getRol().name() : ERoles.MOSTRADOR.name());
            ps.setString(7, admin.getRutaImagen());
            ps.setString(8, admin.getEstado() != null ? admin.getEstado().name() : EEstados.ACTIVO.name());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error en guardar: " + e.getMessage());
        }
        return false;
    }

    public boolean actualizar(Administrador admin) {
        String sql = "UPDATE administradores "
                + "SET usuario=?, nombres=?, apellidos=?, correo=?, contraseña=?, rol=?, ruta_imagen=?, estado=? "
                + "WHERE id=?;";
        try (Connection con = Conexion.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, admin.getUsuario());
            ps.setString(2, admin.getNombres());
            ps.setString(3, admin.getApellidos());
            ps.setString(4, admin.getCorreo());
            ps.setString(5, admin.getContraseña());
            ps.setString(6, admin.getRol() != null ? admin.getRol().name() : ERoles.MOSTRADOR.name());
            ps.setString(7, admin.getRutaImagen());
            ps.setString(8, admin.getEstado() != null ? admin.getEstado().name() : EEstados.ACTIVO.name());
            ps.setInt(9, admin.getId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error en actualizar: " + e.getMessage());
        }
        return false;
    }

    public boolean borrar(int id) {
        String sql = "DELETE FROM administradores "
                + "WHERE id=?;";
        try (Connection con = Conexion.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error en borrar: " + e.getMessage());
        }
        return false;
    }

    // Alias para compatibilidad con el controlador de la guía
    public boolean eliminar(int id) {
        return borrar(id);
    }

    public List<Administrador> obtenerTodos() {
        List<Administrador> lista = new ArrayList<>();
        String sql = "SELECT * FROM administradores;";
        try (Connection con = Conexion.conectar(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Administrador admin = new Administrador();
                admin.setId(rs.getInt("id"));
                admin.setUsuario(rs.getString("usuario"));
                admin.setNombres(rs.getString("nombres"));
                admin.setApellidos(rs.getString("apellidos"));
                admin.setCorreo(rs.getString("correo"));
                admin.setContraseña(rs.getString("contraseña"));

                try {
                    admin.setRol(ERoles.valueOf(rs.getString("rol")));
                } catch (Exception e) {
                    admin.setRol(ERoles.MOSTRADOR);
                }

                admin.setRutaImagen(rs.getString("ruta_imagen"));

                try {
                    admin.setEstado(EEstados.valueOf(rs.getString("estado")));
                } catch (Exception e) {
                    admin.setEstado(EEstados.ACTIVO);
                }

                lista.add(admin);
            }
        } catch (SQLException e) {
            System.out.println("Error en obtenerTodos: " + e.getMessage());
        }
        return lista;
    }

    // Alias para compatibilidad con el controlador de la guía
    public List<Administrador> listarTodos() {
        return obtenerTodos();
    }

    public List<Administrador> obtenerAlgunos(String campo) {
        List<Administrador> lista = new ArrayList<>();
        String sql = "SELECT * FROM administradores "
                + "WHERE estado=?;";
        try (Connection con = Conexion.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, campo);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Administrador admin = new Administrador();
                    admin.setId(rs.getInt("id"));
                    admin.setUsuario(rs.getString("usuario"));
                    admin.setNombres(rs.getString("nombres"));
                    admin.setApellidos(rs.getString("apellidos"));
                    admin.setCorreo(rs.getString("correo"));
                    admin.setContraseña(rs.getString("contraseña"));

                    try {
                        admin.setRol(ERoles.valueOf(rs.getString("rol")));
                    } catch (Exception e) {
                        admin.setRol(ERoles.MOSTRADOR);
                    }

                    admin.setRutaImagen(rs.getString("ruta_imagen"));

                    try {
                        admin.setEstado(EEstados.valueOf(rs.getString("estado")));
                    } catch (Exception e) {
                        admin.setEstado(EEstados.ACTIVO);
                    }

                    lista.add(admin);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error en obtenerAlgunos: " + e.getMessage());
        }
        return lista;
    }

    public Administrador obtenerXId(int id) {
        Administrador admin = null;
        String sql = "SELECT * FROM administradores "
                + "WHERE id=?;";
        try (Connection con = Conexion.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    admin = new Administrador();
                    admin.setId(rs.getInt("id"));
                    admin.setUsuario(rs.getString("usuario"));
                    admin.setNombres(rs.getString("nombres"));
                    admin.setApellidos(rs.getString("apellidos"));
                    admin.setCorreo(rs.getString("correo"));
                    admin.setContraseña(rs.getString("contraseña"));

                    try {
                        admin.setRol(ERoles.valueOf(rs.getString("rol")));
                    } catch (Exception e) {
                        admin.setRol(ERoles.MOSTRADOR);
                    }

                    admin.setRutaImagen(rs.getString("ruta_imagen"));

                    try {
                        admin.setEstado(EEstados.valueOf(rs.getString("estado")));
                    } catch (Exception e) {
                        admin.setEstado(EEstados.ACTIVO);
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Error en obtenerXId: " + e.getMessage());
        }
        return admin;
    }
}
