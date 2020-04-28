package Controladoras;

import BBDD.OracleBD;
import Modelo.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;


public class Registro {
    public static Connection conexion;
    PreparedStatement stmt = null;
    PreparedStatement id_user = null;
    @FXML
    TextField usernameBox, careerBox, nombreBox, apellidoBox, aulaBox;
    @FXML
    Label consola;
    @FXML
    PasswordField passwordBox;
    @FXML
    CheckBox soyProfeCheckbox;
    @FXML
    Button registrarButton;


    public int registrar(ActionEvent actionEvent) throws SQLException, IOException {
        if (check()){
            if (!soyProfeCheckbox.isSelected()){
                    String expediente = Usuario.generarExpediente();
                    OracleBD bd = new OracleBD();
                    String insertUsuario = "INSERT INTO usuario (username, password, profesor) VALUES ('" + usernameBox.getText() + "','" + passwordBox.getText() + "', '0')";
                    bd.setConnection();
                    bd.ejecutarQuery(insertUsuario);
                    String id = "SELECT id_user FROM usuario WHERE username='" + usernameBox.getText() + "'";
                    int id_user = bd.selectQuery(id);
                    String insertarAlumno = "insert into alumno (id_user, nombre, apellido, expediente, carrera, clase) values (" + id_user + ",'" + nombreBox.getText() + "','" + apellidoBox.getText() +"','" + expediente +"','" + careerBox.getText() +"','" + aulaBox.getText() +"')";
                    bd.ejecutarQuery(insertarAlumno);
                    bd.closeConnection();
            }
            }else{
            String expediente = Usuario.generarExpediente();
            OracleBD bd = new OracleBD();
            String insertUsuario = "INSERT INTO usuario (username, password, profesor) VALUES ('" + usernameBox.getText() + "','" + passwordBox.getText() + "', '1')";
            bd.setConnection();
            bd.ejecutarQuery(insertUsuario);
            String id = "SELECT id_user FROM usuario WHERE username='" + usernameBox.getText() + "'";
            int id_user = bd.selectQuery(id);
            String insertarProfesor = "insert into profesor (id_user, nombre, apellido, expediente, carrera, clase) values (" + id_user + ",'" + nombreBox.getText() + "','" + apellidoBox.getText() +"','" + expediente +"','" + careerBox.getText() +"','" + aulaBox.getText() +"')";
            bd.ejecutarQuery(insertarProfesor);
            bd.closeConnection();

            }
        return 0;
    }


    public  boolean comprobarUsuario(String usuario, String contrasenia) throws SQLException {
        boolean existeUsuario = false;

        String query = "SELECT * FROM usuario WHERE usuario='" + usernameBox.getText() + "'";
        OracleBD bd = new OracleBD();
        bd.setConnection();
        bd.selectQuery(query);
        bd.closeConnection();
        if (Sentencia()) {
            if (Sentencia.getRow() > 0) {
                existeUsuario = true;
            }
        }
        return existeUsuario;

    }


    private boolean check() throws SQLException {
        if (usernameBox.getText().equals("")){
            consola.setText("falta nombre!");
            return false;
        }else if (passwordBox.getText().equals("")){
            consola.setText("falta contraseña!");
            return false;
        }else if (careerBox.getText().equals("")) {
            consola.setText("falta carrera!");
            return false;
        }else if (aulaBox.getText().equals("")) {
            consola.setText("falta aula!");
            return false;
        } else if (nombreBox.getText().equals("")) {
            consola.setText("falta nombre!");
            return false;
        } else if (apellidoBox.getText().equals("")) {
            consola.setText("falta apellido!");
            return false;
        } /*else if (comprobarUsuario(usernameBox.getText(),passwordBox.getText())){
            consola.setText("coincidencia encontrada en la bbdd!");
            return false;
        }*/
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
