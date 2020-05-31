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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import jfxtras.styles.jmetro.JMetro;
import jfxtras.styles.jmetro.Style;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class controladoraPerfil extends controladoraPrincipal{

    @FXML
    Button logOffButton, cargarfoto;
    Usuario currentUser;

    @FXML
    ImageView mostrarFoto;
    @FXML
    TextField nombreTextfield, apellidosTextfield, carreraTextfield, expedienteTextfield, grupoTextfield;

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
        String query;
        if(!currentUser.esProfesor()){
             query = "SELECT id_user, nombre, apellido, expediente, carrera, clase FROM alumno WHERE expediente = " + currentUser.getNumeroExpediente();
        }else{
             query = "SELECT id_user, nombre, apellido, expediente, carrera, clase FROM profesor WHERE expediente = " + currentUser.getNumeroExpediente();
        }
        listaPrincipal = bd.getArrayList(query);
        bd.closeConnection();
    }

    public void logOut(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Vistas/Login.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        JMetro Jmetro = new JMetro(root, Style.DARK);
        Jmetro.automaticallyColorPanesProperty();
        stage.setScene(new Scene(root));
        stage.show();
        Stage newStage = (Stage) logOffButton.getScene().getWindow();
        newStage.close();
    }

    public void pintar(ActionEvent actionEvent) throws SQLException{
        OracleBD bd = new OracleBD();
        bd.setConnection();
        String query;
        if(currentUser.esProfesor()){
            query = "SELECT FOTO FROM profesor WHERE EXPEDIENTE= '"+currentUser.getNumeroExpediente()+"'";
        }else{
            query = "SELECT FOTO FROM ALUMNO WHERE EXPEDIENTE= '"+currentUser.getNumeroExpediente()+"'";
        }
        fotoperfil = bd.getImagen(query);
        mostrarFoto.setImage(fotoperfil);
        bd.closeConnection();

        nombreTextfield.setText((String) listaPrincipal.get(1));
        apellidosTextfield.setText((String) listaPrincipal.get(2));
        carreraTextfield.setText((String) listaPrincipal.get(4));
        expedienteTextfield.setText((String) listaPrincipal.get(3));
        grupoTextfield.setText((String) listaPrincipal.get(5));
    }
}