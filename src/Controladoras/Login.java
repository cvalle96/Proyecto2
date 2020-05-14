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
import javafx.stage.Stage;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class Login {
    @FXML
    private Button loginButton, registroButton;
    @FXML
    private TextField contrasenaField, usuarioField ;
    @FXML
    private Label consola;

    private Usuario currentUser;

    private String user, psw;


    public  boolean comprobarUsuario(String usuario, String contrasenia) throws SQLException {
        boolean existeUsuario;
        String query = "SELECT * FROM usuario WHERE username='" + usuario + "' and password='" + contrasenia + "'";
        OracleBD bd = new OracleBD();
        bd.setConnection();

        int id_usuario = bd.selectQuery(query);
        bd.closeConnection();
        if (id_usuario==0) {
            existeUsuario = false;
        }
        else{
            existeUsuario = true;
        }
        return existeUsuario;
    }

    public void log_in() throws SQLException, IOException {
        user = usuarioField.getText().trim();
        psw = contrasenaField.getText().trim();
        String pswCod = DigestUtils.sha1Hex(psw);

        if (usuarioField.getText().isEmpty() || contrasenaField.getText().isEmpty()){
            System.out.println(usuarioField.getText());
            consola.setText("Debes rellenar los campos");
        } else if(comprobarUsuario(user,pswCod)){
            System.out.println("Usuario Logueado");
            startApp();

        }else{
            consola.setText("Usuario/Contraseña incorrectos");
        }
    }

    private void startApp() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Vistas/tabController.fxml"));
        Parent root = loader.load();

        controladoraPrincipal controler = new controladoraPrincipal();
        controler.setCurrentUser(currentUser);

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("App");
        stage.show();

        Stage newStage = (Stage) loginButton.getScene().getWindow();
        newStage.close();
    }


    public void registro(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Vistas/Registro.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Registro");
            stage.show();
        } catch (Exception e ){
            System.out.println(e.getMessage());
            System.out.println(e.getCause());
        }
        Stage newStage = (Stage) loginButton.getScene().getWindow();
        newStage.close();
    }
}
