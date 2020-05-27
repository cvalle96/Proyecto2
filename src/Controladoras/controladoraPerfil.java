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
import javafx.scene.image.*;
import javafx.stage.Stage;
import jdk.internal.util.xml.impl.Input;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;


public class controladoraPerfil extends controladoraPrincipal{

    @FXML
    Button logOffButton, cargarfoto;
    Usuario currentUser;

    @FXML
    TextField nombreTextfield, apellidosTextfield, carreraTextfield, expedienteTextfield, grupoTextfield;
    @FXML
    ImageView mostrarFoto;

    Image fotoperfil;
    ArrayList listaPrincipal;


    public controladoraPerfil() throws SQLException {
        currentUser = controladoraPrincipal.currentUser;
        mostrarFoto = new ImageView();
        getDatos();

    }

    private void getDatos() throws SQLException {
        OracleBD bd = new OracleBD();
        bd.setConnection();
        String query = "SELECT nombre, apellido, carrera, expediente, clase FROM alumno WHERE expediente = '" + currentUser.getNumeroExpediente() +"'";
        listaPrincipal = bd.getArrayList(query);
        bd.closeConnection();
    }

    @FXML
    private void getFoto(ActionEvent actionEvent) throws SQLException{
        OracleBD bd = new OracleBD();
        bd.setConnection();
        String query = "SELECT FOTO FROM ALUMNO WHERE EXPEDIENTE= '"+currentUser.getNumeroExpediente()+"'";
        fotoperfil = bd.getImagen(query);
        mostrarFoto.setImage(fotoperfil);
        System.out.println(fotoperfil);
        bd.closeConnection();
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

    public void pintar(ActionEvent actionEvent) throws SQLException {
        nombreTextfield.setText((String) listaPrincipal.get(0));
        apellidosTextfield.setText((String) listaPrincipal.get(1));
        carreraTextfield.setText((String) listaPrincipal.get(2));
        expedienteTextfield.setText((String) listaPrincipal.get(3));
        grupoTextfield.setText((String) listaPrincipal.get(4));


    }

}