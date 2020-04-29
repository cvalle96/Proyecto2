
import BBDD.OracleBD;
import Controladoras.controladoraPrincipal;
import Modelo.Usuario;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Main extends Application {

    public static void main(String[] args) throws SQLException {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("Vistas/tabController.fxml"));
            primaryStage.setTitle("Login");
            controladoraPrincipal controladoraPrincipal = new controladoraPrincipal();
            controladoraPrincipal.setCurrentUser(new Usuario("Miguel", "Fernandez", "123", "1","21726301", false));
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
        } catch (Exception e ){
            e.printStackTrace();
        }
    }
}
