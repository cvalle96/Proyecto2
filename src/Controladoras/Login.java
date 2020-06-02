package Controladoras;

import BBDD.OracleBD;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import jfxtras.styles.jmetro.JMetro;
import jfxtras.styles.jmetro.Style;
import org.apache.commons.codec.digest.DigestUtils;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class Login {
    @FXML
    private Button loginButton;
    @FXML
    private TextField contrasenaField, usuarioField ;
    @FXML
    private Label consola;

    String psw, user;

    public Login(){

    }

    
    public void login(ActionEvent actionEvent) throws Exception {
        user = usuarioField.getText().trim();
        psw = contrasenaField.getText().trim();
        String pswCod = DigestUtils.sha1Hex(psw);

        if(check() && comprobarUsuario(user,pswCod)){

            String query = "SELECT profesor, id_user FROM usuario where username='" + user + "'";
            OracleBD bd = new OracleBD();
            bd.setConnection();
            ArrayList<Double> rs = bd.getDoubleList(query);

            if( rs.get(0) == 0 ){

                query = "SELECT expediente from alumno where id_user = " + rs.get(1).intValue() ;
                ArrayList rs1 = bd.getArrayList(query);
                bd.closeConnection();
                startApp(Integer.parseInt(rs1.get(0).toString()), false);
            }
            else if (rs.get(0) == 1){

                query = "SELECT expediente from profesor where id_user = " + rs.get(1).intValue() ;
                ArrayList rs1 = bd.getArrayList(query);
                bd.closeConnection();
                System.out.println("enviando " + rs1.get(0).toString());
                startApp(Integer.parseInt(rs1.get(0).toString()), true);
            }else{
                System.out.println("bad answer from query, \n" + rs.toString());
            }
        }
    }

    public boolean check() throws IOException, SQLException {
        if (usuarioField.getText().trim().equals("") ){
            consola.setText("falta nombre de usuario!");
            return false;
        }else if (contrasenaField.getText().trim().equals("") ){
            consola.setText("falta contraseña!");
            return false;
        }
        return true;
    }

    private void startApp(int expediente, boolean prof) throws IOException, SQLException {
        FXMLLoader loader;
        controladoraPrincipal controler = new controladoraPrincipal();
        controler.setProfesor(prof);
        controler.setExpediente(expediente);

        if(prof){
            System.out.println("profesor enviado");
            loader = new FXMLLoader(getClass().getResource("/Vistas/tabProfesor.fxml"));
        }else{
            System.out.println("usuario enviado");
            loader = new FXMLLoader(getClass().getResource("/Vistas/tabController.fxml"));
        }

        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setTitle("App");
        JMetro Jmetro = new JMetro(root, Style.DARK);
        Jmetro.automaticallyColorPanesProperty();
        stage.setScene(new Scene(root));
        stage.show();

        Stage newwStage = (Stage) loginButton.getScene().getWindow();
        newwStage.close();
    }


    private boolean comprobarUsuario(String username, String contrasenaString) throws SQLException {
        String query = "SELECT password FROM usuario WHERE username='" + username + "'";
        OracleBD bd = new OracleBD();
        bd.setConnection();
        ArrayList rs = bd.getArrayList(query);
        bd.closeConnection();

        if (rs.get(0).equals(contrasenaString)) {
            return false;
        }
        return true;
    }

    public void registro(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Vistas/Registro.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            JMetro Jmetro = new JMetro(root, Style.DARK);
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
