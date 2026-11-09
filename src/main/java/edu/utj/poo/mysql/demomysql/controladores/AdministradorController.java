package edu.utj.poo.mysql.demomysql.controladores;

//Hernandez Virgen Angel Lionel
import edu.utj.poo.mysql.demomysql.Administrador;
import edu.utj.poo.mysql.demomysql.EEstados;
import edu.utj.poo.mysql.demomysql.ERoles;
import edu.utj.poo.mysql.demomysql.daos.AdministradorDAO;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

/**
 * Controlador JavaFX con comunicacion directa al DAO para la gestion de
 * Administradores.
 *
 * @author DocentesUTJCCD
 */
public class AdministradorController implements Initializable {

    @FXML
    private TextField txtId;
    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtApellidos;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtUsuario;
    @FXML
    private TextField txtClaveAcceso;
    @FXML
    private ComboBox<ERoles> cmbRol;
    @FXML
    private ComboBox<EEstados> cmbEstado;
    @FXML
    private TextField txtRutaImagen;
    @FXML
    private ImageView imgVistaPrevia;
    @FXML
    private Button btnGuardar;
    @FXML
    private Button btnActualizar;
    @FXML
    private Button btnEliminar;
    @FXML
    private Button btnLimpiar;
    @FXML
    private TableView<Administrador> tblAdministradores;
    @FXML
    private TableColumn<Administrador, Integer> colId;
    @FXML
    private TableColumn<Administrador, String> colNombre;
    @FXML
    private TableColumn<Administrador, String> colApellidos;
    @FXML
    private TableColumn<Administrador, String> colEmail;
    @FXML
    private TableColumn<Administrador, String> colUsuario;
    @FXML
    private TableColumn<Administrador, ERoles> colRol;
    @FXML
    private TableColumn<Administrador, EEstados> colEstado;
    @FXML
    private TableColumn<Administrador, String> colRutaImagen;

    // Instanciacion directa de la capa DAO
    private final AdministradorDAO administradorDAO = new AdministradorDAO();
    private final ObservableList<Administrador> listaObservable = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Los nombres deben coincidir con los getters de Administrador
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombres"));
        colApellidos.setCellValueFactory(new PropertyValueFactory<>("apellidos"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("correo"));
        colUsuario.setCellValueFactory(new PropertyValueFactory<>("usuario"));
        colRol.setCellValueFactory(new PropertyValueFactory<>("rol"));
        colEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));
        colRutaImagen.setCellValueFactory(new PropertyValueFactory<>("rutaImagen"));

        cmbRol.setItems(FXCollections.observableArrayList(ERoles.values()));
        cmbRol.setValue(ERoles.MOSTRADOR);
        cmbEstado.setItems(FXCollections.observableArrayList(EEstados.values()));
        cmbEstado.setValue(EEstados.ACTIVO);

        tblAdministradores.setItems(listaObservable);

        tblAdministradores.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> mostrarDetalleAdministrador(newValue)
        );

        actualizarTabla();
    }

    @FXML
    private void btnSeleccionarImagenOnAction(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Seleccionar Foto de Perfil");
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Archivos de Imagen", "*.png", "*.jpg", "*.jpeg", "*.gif")
        );
        File archivoSeleccionado = fileChooser.showOpenDialog(txtId.getScene().getWindow());
        if (archivoSeleccionado != null) {
            txtRutaImagen.setText(archivoSeleccionado.getAbsolutePath());
            cargarImagenVistaPrevia(archivoSeleccionado.getAbsolutePath());
        }
    }

    @FXML
    private void btnGuardarOnAction(ActionEvent event) {
        String nombres = txtNombre.getText().trim();
        String apellidos = txtApellidos.getText().trim();
        String correo = txtEmail.getText().trim();
        String usuario = txtUsuario.getText().trim();
        String clave = txtClaveAcceso.getText().trim();
        ERoles rol = cmbRol.getValue();
        EEstados estado = cmbEstado.getValue();
        String rutaImagen = txtRutaImagen.getText().trim();

        if (nombres.isEmpty() || correo.isEmpty() || usuario.isEmpty() || clave.isEmpty()) {
            mostrarAlerta(Alert.AlertType.WARNING, "Campos Requeridos",
                    "Nombre, Email, Usuario y Contraseña son obligatorios.");
            return;
        }

        Administrador nuevoAdmin = new Administrador(0, usuario, nombres, apellidos, correo, clave,
                rol, rutaImagen, estado);

        // Registro directo mediante el DAO
        if (administradorDAO.guardar(nuevoAdmin)) {
            mostrarAlerta(Alert.AlertType.INFORMATION, "Éxito",
                    "Administrador registrado correctamente en la base de datos.");
            actualizarTabla();
            limpiarCampos();
        } else {
            mostrarAlerta(Alert.AlertType.ERROR, "Error",
                    "No se pudo guardar el registro. Verifique que usuario o email no estén duplicados.");
        }
    }

    @FXML
    private void btnActualizarOnAction(ActionEvent event) {
        if (txtId.getText().trim().isEmpty()) {
            mostrarAlerta(Alert.AlertType.WARNING, "Selección Requerida",
                    "Seleccione un administrador de la tabla para actualizar.");
            return;
        }
        try {
            int id = Integer.parseInt(txtId.getText().trim());
            String nombres = txtNombre.getText().trim();
            String apellidos = txtApellidos.getText().trim();
            String correo = txtEmail.getText().trim();
            String usuario = txtUsuario.getText().trim();
            String clave = txtClaveAcceso.getText().trim();
            ERoles rol = cmbRol.getValue();
            EEstados estado = cmbEstado.getValue();
            String rutaImagen = txtRutaImagen.getText().trim();

            Administrador adminActualizar = new Administrador(id, usuario, nombres, apellidos, correo,
                    clave, rol, rutaImagen, estado);

            // Actualización directa mediante el DAO
            if (administradorDAO.actualizar(adminActualizar)) {
                mostrarAlerta(Alert.AlertType.INFORMATION, "Éxito", "Registro actualizado correctamente.");
                actualizarTabla();
                limpiarCampos();
            } else {
                mostrarAlerta(Alert.AlertType.ERROR, "Error",
                        "No se pudo actualizar la información en la base de datos.");
            }
        } catch (NumberFormatException e) {
            mostrarAlerta(Alert.AlertType.ERROR, "Error", "Identificador no válido.");
        }
    }

    @FXML
    private void btnEliminarOnAction(ActionEvent event) {
        if (txtId.getText().trim().isEmpty()) {
            mostrarAlerta(Alert.AlertType.WARNING, "Selección Requerida",
                    "Seleccione un registro de la tabla para eliminar.");
            return;
        }
        try {
            int id = Integer.parseInt(txtId.getText().trim());

            // Eliminación directa mediante el DAO
            if (administradorDAO.eliminar(id)) {
                mostrarAlerta(Alert.AlertType.INFORMATION, "Éxito",
                        "Administrador desactivado/eliminado correctamente.");
                actualizarTabla();
                limpiarCampos();
            } else {
                mostrarAlerta(Alert.AlertType.ERROR, "Error",
                        "No se pudo eliminar el registro de la base de datos.");
            }
        } catch (NumberFormatException e) {
            mostrarAlerta(Alert.AlertType.ERROR, "Error", "Identificador no válido.");
        }
    }

    @FXML
    private void btnLimpiarOnAction(ActionEvent event) {
        limpiarCampos();
    }

    private void mostrarDetalleAdministrador(Administrador admin) {
        if (admin != null) {
            txtId.setText(String.valueOf(admin.getId()));
            txtNombre.setText(admin.getNombres());
            txtApellidos.setText(admin.getApellidos());
            txtEmail.setText(admin.getCorreo());
            txtUsuario.setText(admin.getUsuario());
            txtClaveAcceso.setText(admin.getContraseña());
            cmbRol.setValue(admin.getRol());
            cmbEstado.setValue(admin.getEstado());
            txtRutaImagen.setText(admin.getRutaImagen());
            cargarImagenVistaPrevia(admin.getRutaImagen());
        }
    }

    private void cargarImagenVistaPrevia(String ruta) {
        if (ruta != null && !ruta.trim().isEmpty()) {
            File archivo = new File(ruta);
            if (archivo.exists()) {
                Image imagen = new Image(archivo.toURI().toString());
                imgVistaPrevia.setImage(imagen);
                return;
            }
        }
        imgVistaPrevia.setImage(null);
    }

    private void actualizarTabla() {
        // Consulta directa de datos desde el DAO
        listaObservable.setAll(administradorDAO.listarTodos());
    }

    private void limpiarCampos() {
        txtId.setText("");
        txtNombre.setText("");
        txtApellidos.setText("");
        txtEmail.setText("");
        txtUsuario.setText("");
        txtClaveAcceso.setText("");
        cmbRol.setValue(ERoles.MOSTRADOR);
        cmbEstado.setValue(EEstados.ACTIVO);
        txtRutaImagen.setText("");
        imgVistaPrevia.setImage(null);
        tblAdministradores.getSelectionModel().clearSelection();
    }

    private void mostrarAlerta(Alert.AlertType tipo, String titulo, String mensaje) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}
