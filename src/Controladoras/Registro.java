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
import org.apache.commons.codec.digest.DigestUtils;

import java.io.IOException;
import java.sql.*;


public class Registro {
    public static Connection conexion;
    PreparedStatement stmt = null;
    PreparedStatement id_user = null;
    @FXML
    TextField usernameBox, nombreBox, apellidoBox;
    @FXML
    Label consola;
    @FXML
    PasswordField passwordBox;
    @FXML
    CheckBox soyProfeCheckbox;
    @FXML
    Button registrarButton;
    @FXML
    TextField careerBox, aulaBox;

    public int registrar() throws SQLException, IOException {
        String password= DigestUtils.sha1Hex(passwordBox.getText());
        if (check()){
            if (!soyProfeCheckbox.isSelected()){

                String expediente = Usuario.generarExpediente();
                OracleBD bd = new OracleBD();
                String insertUsuario = "INSERT INTO usuario (username, password, profesor) VALUES ('" + usernameBox.getText().trim() + "','" + password.trim() + "', '0')";
                bd.setConnection();
                bd.ejecutarQuery(insertUsuario);
                String id = "SELECT id_user FROM usuario WHERE username='" + usernameBox.getText().trim() + "'";
                int id_user = bd.selectQuery(id);
                String insertarAlumno = "insert into alumno (id_user, nombre, apellido, expediente, carrera, clase) values (" + id_user + ",'" + nombreBox.getText().trim() + "','" + apellidoBox.getText() +"','" + expediente +"','" + careerBox.getText() +"','" + aulaBox.getText() +"')";
                bd.ejecutarQuery(insertarAlumno);
                System.out.println("Alumno insertado");
                bd.closeConnection();
                goLogin();
            }
            else{
                String expediente = Usuario.generarExpediente();
                OracleBD bd = new OracleBD();
                String insertUsuario = "INSERT INTO usuario (username, password, profesor) VALUES ('" + usernameBox.getText().trim() + "','" + password.trim() + "', '1')";
                bd.setConnection();
                bd.ejecutarQuery(insertUsuario);
                String id = "SELECT id_user FROM usuario WHERE username='" + usernameBox.getText().trim() + "'";
                int id_user = bd.selectQuery(id);
                String insertarProfesor = "insert into profesor (id_user, nombre, apellido, expediente, carrera, clase) values (" + id_user + ",'" + nombreBox.getText().trim() + "','" + apellidoBox.getText() +"','" + expediente +"','" + careerBox.getText() +"','" + aulaBox.getText() +"')";
                bd.ejecutarQuery(insertarProfesor);
                System.out.println("Profesor insertado");
                bd.closeConnection();
                goLogin();
            }
        }else{
            System.out.println("El usuario existe en la base de datos");

        }
        return 0;
    }


    public  boolean comprobarUsuario(String usuario, String contrasenia) throws SQLException {
        boolean existeUsuario;
        String query = "SELECT * FROM usuario WHERE username='" + usernameBox.getText() + "'";
        OracleBD bd = new OracleBD();
        bd.setConnection();
        int id_usuario = bd.selectQuery(query);
        bd.closeConnection();
        if (id_usuario==0) {
            existeUsuario = false;
        }
        else{
            existeUsuario = true;
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
        }else if (comprobarUsuario(usernameBox.getText(), passwordBox.getText())){
            consola.setText("El usuario " + usernameBox.getText() + " ya existe");
            return false;
        }
        return true;
    }

    public void goLogin() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../Vistas/Login.fxml"));
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