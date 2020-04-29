package Controladoras;

import BBDD.OracleBD;
import Modelo.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class controladoraPerfil extends controladoraPrincipal{

    @FXML
    Button logOffButton;
    Usuario currentUser;

    @FXML
    TextField nombreTextfield, apellidosTextfield, carreraTextfield, expedienteTextfield, grupoTextfield;

    ArrayList listaPrincipal;

    public controladoraPerfil() throws SQLException {
        currentUser = new Usuario("Miguel", "Fernandez", "123", "1","21726301", false);
        getDatos();
    }

    private void getDatos() throws SQLException {
        OracleBD bd = new OracleBD();
        bd.setConnection();
        String query1 = "SELECT id_user FROM usuario WHERE username = '" + "admin" + "'";
        ArrayList resultados = bd.getDoubleList(query1);
        int idUsuario = (int) resultados.get(0);
        String query = "SELECT * FROM alumno WHERE id_user = " + idUsuario;
        ArrayList <String> resultaditos = bd.getList(query);
        bd.closeConnection();
        listaPrincipal = resultaditos;
    }

    public void logOut(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Vistas/Login.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Login");
        stage.show();
        Stage newStage = (Stage) logOffButton.getScene().getWindow();
        newStage.close();
    }

    public void pintar(ActionEvent actionEvent) {
        nombreTextfield.setText((String) listaPrincipal.get(2));
        apellidosTextfield.setText((String) listaPrincipal.get(3));
        carreraTextfield.setText((String) listaPrincipal.get(5));
        expedienteTextfield.setText((String) listaPrincipal.get(4));
        grupoTextfield.setText((String) listaPrincipal.get(6));
    }

}