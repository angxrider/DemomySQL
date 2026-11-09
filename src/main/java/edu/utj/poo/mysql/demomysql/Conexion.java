package edu.utj.poo.mysql.demomysql;
//Hernandez Virgen Angel Lionel

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;

/**
 *
 * @author TUF
 */
public class Conexion {

    private final static String HOST = "localhost";//127.0.0.1
    private final static String PUERTO = "3306";
    private final static String BD = "poo3bdsm";
    private final static String USUARIO = "root";
       private final static String CONTRASEÑA = "";
    private final static String URL = "jdbc:mysql://" + HOST + ":" + PUERTO + "/" + BD;//"jdbc:mysql://localhost:3306/poo";

    public static Connection conectar() throws SQLException {
        Connection con = DriverManager.getConnection(URL, USUARIO, CONTRASEÑA);
        System.out.println("CONEXION ESTABLECIDA");
        return con;
    }
}
