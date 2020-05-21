
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import jfxtras.styles.jmetro.JMetro;
import jfxtras.styles.jmetro.JMetroStyleClass;
import jfxtras.styles.jmetro.Style;
import Controladoras.Registro;
import java.awt.*;

public class Main extends Application{
    @FXML
    AnchorPane panelPrueba;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("Vistas/Registro.fxml"));
            primaryStage.setTitle("Login");
            //panelPrueba.getStyleClass().add(JMetroStyleClass.BACKGROUND);
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
