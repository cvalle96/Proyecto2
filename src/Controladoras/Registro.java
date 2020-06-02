package Controladoras;

import BBDD.OracleBD;
import Modelo.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import jfxtras.styles.jmetro.JMetro;
import jfxtras.styles.jmetro.Style;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class Registro {

    @FXML
    TextField usernameBox, nombreBox, apellidoBox, careerBox, aulaBox;
    @FXML
    Label consola;
    @FXML
    PasswordField passwordBox;
    @FXML
    CheckBox soyProfeCheckbox;
    @FXML
    Button registrarButton;

    InputStream imagen;
    String imagenAlumno ;

    public Registro(){
        imagenAlumno = new String();
        inicializar();
    }

    public void registrar(ActionEvent actionEvent) throws IOException, SQLException {

        if (check()){
            Boolean prof = false;
            String answer = Math.random() +"";
            String expediente = answer.substring(2,9);
            OracleBD bd = new OracleBD();
            bd.setConnection();
            PreparedStatement stmt ;

            if (! soyProfeCheckbox.isSelected()){

                String query = "INSERT INTO usuario (username, password, profesor) VALUES ('" + usernameBox.getText().trim() + "','" + passwordBox.getText().trim() + "', '0')";
                bd.makeInsert(query);

                query = "SELECT id_user FROM usuario WHERE username='" + usernameBox.getText().trim() + "'";
                ArrayList<Double> rs = bd.getDoubleList(query);
                int id_user = rs.get(0).intValue();

                stmt = bd.prepareStatement("insert into alumno (id_user, nombre, apellido, expediente, carrera, clase, foto) values (?,?,?,?,?,?,?)");
                stmt.setInt(1,id_user);

            }
            else{
                prof=true;
                String query = "INSERT INTO usuario (username, password, profesor) VALUES ('" + usernameBox.getText().trim() + "','" + passwordBox.getText().trim() + "', '1')";
                bd.makeInsert(query);

                query = "SELECT id_user FROM usuario WHERE username='" + usernameBox.getText().trim() + "'";
                ArrayList<Double> rs = bd.getDoubleList(query);
                int id_user = rs.get(0).intValue();

                stmt = bd.prepareStatement("insert into profesor (id_user, nombre, apellido, expediente, carrera, clase, foto) values (?,?,?,?,?,?,?)");
                stmt.setInt(1,id_user);
            }

            stmt.setString(2, nombreBox.getText().trim());
            stmt.setString(3, apellidoBox.getText());
            stmt.setString(4, expediente);
            stmt.setString(5, careerBox.getText());
            stmt.setString(6, aulaBox.getText());
            stmt.setBinaryStream(7, imagen);
            bd.ejecutarst(stmt);
            bd.closeConnection();
            letslogin(Integer.parseInt(expediente), prof);
        }
    }

    public void imagen() throws IOException, SQLException {
        FileChooser fileChooser = new FileChooser();
        File selectedFile = fileChooser.showOpenDialog(null);

        if (selectedFile == null) {
            consola.setText("Imagen no seleccionada");
            return;
        }
        System.out.println(selectedFile);
        imagen = new FileInputStream(selectedFile);

        File fi = new File(selectedFile.toString());
        byte[] imagenBinario = Files.readAllBytes(fi.toPath());
        String imagenBinarioString = new String(imagenBinario);
        imagenAlumno = imagenBinarioString;
    }

    private boolean comprobarUsuario(String username) throws SQLException {

        String query = "SELECT * FROM usuario WHERE username='" + username + "'";
        OracleBD bd = new OracleBD();
        bd.setConnection();
        ArrayList rs = bd.getArrayList(query);
        bd.closeConnection();

        return !rs.isEmpty();
    }

    private boolean check() throws SQLException {
        if (usernameBox.getText().equals("")) {
            consola.setText("falta nombre!");
            return false;
        } else if (passwordBox.getText().equals("")) {
            consola.setText("falta contraseña!");
            return false;
        } else if (careerBox.getText().equals("")) {
            consola.setText("falta carrera!");
            return false;
        }else if (aulaBox.getText().equals("")) {
            consola.setText("falta grupo!");
            return false;
        }else if (apellidoBox.getText().equals("")) {
            consola.setText("faltan apellidos!");
            return false;
        }else if ( comprobarUsuario(usernameBox.getText())){
            consola.setText("El usuario " + usernameBox.getText() + " ya existe");
            return false;

        }else {
            return true;
        }
    }

    private void inicializar() {
        usernameBox = new TextField();
        nombreBox = new TextField();
        apellidoBox = new TextField();
        careerBox = new TextField();
        aulaBox= new TextField();
    }


    public void goLogin(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Vistas/Login.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        JMetro Jmetro = new JMetro(root, Style.DARK);

        stage.setScene(new Scene(root));
        stage.setTitle("Login");
        stage.show();

        Stage newStage = (Stage) usernameBox.getScene().getWindow();
        newStage.close();
    }

    public void letslogin(int expediente, boolean prof) throws IOException, SQLException {
        controladoraPrincipal controler = new controladoraPrincipal();
        controler.setProfesor(prof);
        controler.setExpediente(expediente);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Vistas/tabController.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        JMetro Jmetro = new JMetro(root, Style.DARK);
        Scene sc = new Scene(root);
        stage.setScene(sc);
        stage.setTitle("App");
        stage.show();

        Stage newStage = (Stage) nombreBox.getScene().getWindow();
        newStage.close();
    }
}
