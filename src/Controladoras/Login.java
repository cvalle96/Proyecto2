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
    TextField contrasenaField, usuario ;
    @FXML
    Label consola;

    String contrasena, username;
    Usuario currentUser;
    boolean logueo;



    
    public void login(ActionEvent actionEvent) throws Exception {
        if (check()){
            boolean logueo;
            username = usuario.getText();
            contrasena = contrasenaField.getText();
            if (comprobarUsuario(username, contrasena)){

                    try {
                        OracleBD bd = new OracleBD();
                        bd.setConnection();
                        String login = "select username, password from usuario where username='"+username+"' and password='"+contrasena+"'";
                        bd.selectQuery(login);
                        System.out.println(bd.selectQuery(login));
                        logueo = true;
                        currentUser = new Usuario(username, contrasena);

                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                }
                startApp();
            }else{
                consola.setText("nombre no encontrado en al bbdd");
            }
        }


    public boolean check() throws IOException {
        if (usuario.equals("") ){
            consola.setText("falta nombre de usuario!");
            startApp();
            return true;
        }else if (contrasena.equals("") ){
            consola.setText("falta contraseña!");
            return true;
        }
        return true;
    }

    private void startApp() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Vistas/EditorUsuarios.fxml"));
        Parent root = loader.load();

        //controladoraPrincipal controler = new controladoraPrincipal();
        //controler.setCurrentUser(currentUser);

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("App");
        stage.show();

        Stage newStage = (Stage) loginButton.getScene().getWindow();
        newStage.close();
    }

    private boolean comprobarUsuario(String username, String contrasenaString) {

        //este metodo comprueba coincidencias en la bbdd

        return true;
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
