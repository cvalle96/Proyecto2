
import BBDD.OracleBD;
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
        OracleBD nueva = new OracleBD();
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("Vistas/Login.fxml"));
            primaryStage.setTitle("Login");
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
        } catch (Exception e ){
            System.out.println(e.getMessage());
            System.out.println(e.getCause());
        }
    }


}
