package Controladoras;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Login {
    @FXML
    Button loginButton, registroButton;
    @FXML
    TextField contrasena, usuario, consola;

    String contrasenaString, username;

    
    public void login(ActionEvent actionEvent) throws Exception {
        if (check()){
            username = usuario.getText();
            contrasenaString = contrasena.getText();
            if (!comprobarUsuario(username, contrasenaString)){
                System.out.println("nombre no encontrado");
            }else{
                startChat();
                Stage newStage = (Stage) loginButton.getScene().getWindow();
                newStage.close();
            }
        }
    }

    public boolean check(){
        if (usuario.getText().equals("") ){
            consola.setText("falta nombre de usuario!");
            return false;
        }else if (contrasena.getText().equals("") ){
            consola.setText("falta contraseña!");
            return false;
        }
        return true;
    }

    private void startChat() {

        //este metodo lanza la siguiente pagina

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
