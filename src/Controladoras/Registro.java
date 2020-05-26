package Controladoras;

import BBDD.OracleBD;
import Modelo.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import jfxtras.styles.jmetro.JMetro;
import jfxtras.styles.jmetro.Style;
import oracle.sql.BLOB;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.*;
import java.sql.*;

import java.nio.file.Files;
import java.io.File;

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
    Button registrarButton, botonImagen;
    @FXML
    TextField careerBox, aulaBox;

    InputStream imagen;
    private Registro ImageUtils;
    String imagenAlumno = new String();




    public  boolean comprobarUsuario(String usuario, String contrasenia) throws SQLException {
        boolean existeUsuario;
        String query = "SELECT * FROM usuario WHERE username='" + usernameBox.getText() + "'";
        OracleBD bd = new OracleBD();
        bd.setConnection();
        int id_usuario = bd.idAlumno(query);
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
    public void imagen() throws IOException, SQLException {
        FileChooser imgPerfil = new FileChooser();
        FileChooser fileChooser = new FileChooser();
        File selectedFile = fileChooser.showOpenDialog(null);

        if (selectedFile == null) {
            consola.setText("Imagen no seleccionada");
        }
        System.out.println(selectedFile);
        imagen = new FileInputStream(selectedFile);

        File fi = new File(selectedFile.toString());
        byte[] imagenBinario = Files.readAllBytes(fi.toPath());
        String imagenBinarioString = new String(imagenBinario);
        imagenAlumno = imagenBinarioString;
        System.out.println(imagenAlumno);
    }

    public void goLogin() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../Vistas/Login.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            JMetro Jmetro = new JMetro(root, Style.DARK);
            Scene sc = new Scene(root);
            stage.setScene(sc);
            stage.show();
        } catch (Exception e ){
            System.out.println(e.getMessage());
            System.out.println(e.getCause());
        }
        Stage newStage = (Stage) usernameBox.getScene().getWindow();
        newStage.close();
    }

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
                int id_user = bd.idAlumno(id);
                //id_alumno id_user nombre apellido expediente carrera clase foto
                PreparedStatement stmt = bd.prepareStatement("insert into alumno (id_user, nombre, apellido, expediente, carrera, clase, foto) values (?,?,?,?,?,?,?)");
                stmt.setInt(1,id_user);
                stmt.setString(2, nombreBox.getText().trim());
                stmt.setString(3, apellidoBox.getText());
                stmt.setString(4, expediente);
                stmt.setString(5, careerBox.getText());
                stmt.setString(6, aulaBox.getText());
                stmt.setBinaryStream(7, imagen);
                bd.ejecutarst(stmt);
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
                int id_user = bd.idAlumno(id);
                //id_alumno id_user nombre apellido expediente carrera clase foto
                PreparedStatement stmt = bd.prepareStatement("insert into profesor (id_user, nombre, apellido, expediente, carrera, clase, foto) values (?,?,?,?,?,?,?)");
                stmt.setInt(1,id_user);
                stmt.setString(2, nombreBox.getText().trim());
                stmt.setString(3, apellidoBox.getText());
                stmt.setString(4, expediente);
                stmt.setString(5, careerBox.getText());
                stmt.setString(6, aulaBox.getText());
                stmt.setBinaryStream(7, imagen);
                bd.ejecutarst(stmt);
                bd.closeConnection();
                goLogin();
            }
        }else{
            System.out.println("El usuario existe en la base de datos");

        }
        return 0;
    }
}