package Controladoras;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class Registro {

    @FXML
    TextField usernameBox, groupBox;
    @FXML
    Label consola;
    @FXML
    PasswordField passwordBox;
    @FXML
    CheckBox soyProfeCheckbox;
    @FXML
    Button registrarButton;


    public void registrar(ActionEvent actionEvent) throws IOException {
        if (check()){
            if (! soyProfeCheckbox.isSelected()){
                //introducir en la bbdd como alumno

                //cargar vista de perfil personal

            }else{

                //introducir en la bbdd como profesor

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Vistas/Profesor.fxml"));
                Parent root = loader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.setTitle("Profesor");
                stage.show();
                Stage newStage = (Stage) usernameBox.getScene().getWindow();
                newStage.close();
            }
        }
    }

    private boolean comprobarUsuario(String nombre, String contrasenia, String grupo) {

        //este metodo comprueba coincidencias en la bbdd

        return true;
    }

    private boolean check() {
        if (usernameBox.getText().equals("")){
            consola.setText("falta nombre!");
            return false;
        }else if (passwordBox.getText().equals("")){
            consola.setText("falta contraseña!");
            return false;
        }else if (groupBox.getText().equals("")) {
            consola.setText("falta grupo!");
            return false;
        } else if (comprobarUsuario(usernameBox.getText(),passwordBox.getText(), groupBox.getText())){
            consola.setText("coincidencia encontrada en la bbdd!");
            return false;
        }
        return true;
    }

    public void goLogin(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Vistas/Login.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Login");
            stage.show();
        } catch (Exception e ){
            System.out.println(e.getMessage());
            System.out.println(e.getCause());
        }
        Stage newStage = (Stage) usernameBox.getScene().getWindow();
        newStage.close();
    }
}
