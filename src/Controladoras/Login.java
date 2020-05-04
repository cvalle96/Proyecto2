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

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Login {
    @FXML
    Button loginButton, registroButton;
    @FXML
    TextField contrasenaField, usuarioField ;
    @FXML
    Label consola;

    Usuario currentUser;

    String username = usuarioField.getText();
    String contrasena = contrasenaField.getText();




    public  boolean comprobarUsuario(String usuario, String contrasena) throws SQLException {
        boolean existeUsuario;
        String query = "SELECT * FROM usuario WHERE username='" + username + "' and password = '" + contrasena + "'";
        OracleBD bd = new OracleBD();
        bd.setConnection();
        int id_usuario = bd.selectQuery(query);
        bd.closeConnection();
        if (id_usuario==0) {
            existeUsuario = true;
        }
        else{
            existeUsuario = false;
        }
        return existeUsuario;
    }

    public void login(ActionEvent actionEvent) throws Exception {
        if (check()){
            boolean logueo;
            if (comprobarUsuario(username, contrasena)){
                System.out.println("Usuario logueado correctamente");
                startApp();
                }
            }else{
                consola.setText("nombre no encontrado en la bbdd");
            }
        }


    public boolean check() throws SQLException {
        if (usuarioField.getText().equals("") ){
            System.out.println(usuarioField.getText());
            consola.setText("falta nombre de usuario!");
            return false;
        }else if (contrasenaField.getText().equals("") ){
            consola.setText("falta contraseña!");
            return false;
        }
        else if (comprobarUsuario(usuarioField.getText(),contrasenaField.getText())){
            consola.setText("El usuario " + username + " no existe en la base de datos");
            return true;
        }
        return true;
    }

    private void startApp() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Vistas/EditorUsuarios.fxml"));
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
