
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import jfxtras.styles.jmetro.JMetro;
import jfxtras.styles.jmetro.Style;

import java.sql.SQLException;


public class Main extends Application {

    public static void main(String[] args) throws SQLException {

        // TODO estan jodidos los selecUser de los listview por culpa del blob, el tamaño de la fuente no se conserva en el tema negro, no distingue profe de alumno, no muestra foto


        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("Vistas/Login.fxml"));
            primaryStage.setTitle("Login");
            JMetro Jmetro = new JMetro(root, Style.DARK);
            Jmetro.automaticallyColorPanesProperty();
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
        } catch (Exception e ){
            System.out.println(e.getMessage());
            System.out.println(e.getCause());
        }
    }
}