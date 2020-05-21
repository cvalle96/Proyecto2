package Controladoras;

import BBDD.OracleBD;
import Modelo.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import jfxtras.styles.jmetro.JMetro;
import jfxtras.styles.jmetro.Style;

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
        currentUser = controladoraPrincipal.currentUser;
        getDatos();
    }

    private void getDatos() throws SQLException {
        OracleBD bd = new OracleBD();
        bd.setConnection();
        String query = "SELECT * FROM alumno WHERE expediente = '" + currentUser.getNumeroExpediente() +"'";
        listaPrincipal = bd.getArrayList(query);
        bd.closeConnection();
    }

    public void logOut(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Vistas/Login.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        JMetro Jmetro = new JMetro(root, Style.DARK);
        Scene sc = new Scene(root);
        stage.setScene(sc);
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