/*
 * Hernandez Virgen Angel Lionel
 */
package edu.utj.poo.mysql.demomysql.controladores;

import edu.utj.poo.mysql.demomysql.Conexion;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

public class PrimaryController {

    @FXML
    private Button primaryButton;

    @FXML
    void conectarBD(ActionEvent event) {
        Connection conn = null;

        try {
            conn = Conexion.conectar();
            if (conn != null && !conn.isClosed()) {
                Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                alerta.setTitle("Conexión Exitosa");
                alerta.setHeaderText(null);
                alerta.setContentText("Conexión establecida correctamente.");
                alerta.showAndWait();
            }
        } catch (SQLException e) {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Error de Conexión");
            alerta.setHeaderText(null);
            alerta.setContentText("NO FUE POSIBLE ESTABLECER LA CONEXION: " + e.getMessage());
            alerta.showAndWait();
        } finally {
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(PrimaryController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}