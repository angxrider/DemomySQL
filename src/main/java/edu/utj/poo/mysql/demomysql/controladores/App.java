package edu.utj.poo.mysql.demomysql.controladores;

import java.io.IOException;
import java.net.URL;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    // Ruta del FXML dentro de src/main/resources
    private static final String RUTA_FXML = "/com/mycompany/demomysql/";

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("administrador"), 1000, 650);
        stage.setTitle("Gestión de Administradores");
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        URL url = App.class.getResource(RUTA_FXML + fxml + ".fxml");
        if (url == null) {
            throw new IOException("No se encontró el FXML en: " + RUTA_FXML + fxml + ".fxml");
        }
        FXMLLoader fxmlLoader = new FXMLLoader(url);
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}
