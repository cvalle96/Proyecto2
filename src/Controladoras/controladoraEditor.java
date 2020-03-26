package Controladoras;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class controladoraEditor {

    public void returnToLogin(ActionEvent actionEvent) {
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
       // Stage newStage = (Stage) usernameBox.getScene().getWindow();
       // newStage.close();
    }

    public void selectThisUser(MouseEvent mouseEvent) {
    }

    public void actualizarValores(ActionEvent actionEvent) {
    }

    public void borrarEsteUsuario(ActionEvent actionEvent) {
        
    }
}
