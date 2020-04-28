package Controladoras;

import BBDD.OracleBD;
import Modelo.Usuario;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class controladoraEditor extends controladoraPrincipal {
    /*
    @FXML
    ObservableList<String> observableUsuariosString;
    @FXML
    ListView listaUsuarios;
    @FXML
    Label labelAlumnoModificar;
    @FXML
    TextField textFieldExpediente,textFieldNombre, textFieldGrupo, textFieldApellidos;
    @FXML
    Button borrarButton, actualizarButton;

    ArrayList<String> listaNombres;
    Usuario usuarioModificar;
    Usuario currentUser;


    public controladoraEditor(){
        //poblarListView();
        //currentUser = controladoraPrincipal.currentUser;
    }

    public void selectThisUser(MouseEvent mouseEvent) throws SQLException {
        int indexNombre=listaUsuarios.getSelectionModel().getSelectedIndex();
        String username = listaNombres.get(indexNombre);

        String[] lista = username.split(" ");
        username = lista[0];
        String apellidos = "";
        for (int i = 1; i< lista.length; i++){
            apellidos += lista[i];
        }
        seleccionarAlumnoModificar( obtenerUsuariodeBBDD(nombre, apellidos) );
    }

    private Usuario obtenerUsuariodeBBDD(String username, String apellidos, String carrera, int profesor) throws SQLException {
        ResultSet resultados = null;
        try {
            OracleBD bd = new OracleBD();
            String query = "select * from alumno where nombre = '" + username + "' and apellido = '"+ apellidos + "';";
            bd.setConnection();
            resultados = bd.makeQuery(query);
            bd.closeConnection();
        } catch (Exception e){
            System.out.println(e.getMessage());
        }

        String contrasena = resultados.getString(4);
        //String grupo = resultados.getString()                       FALTA EL CAMPO DE CLASE
        //String expediente = resultados.getString(3);

        Usuario user = new Usuario (String nombre, String apellidos, String contrasenia, String carrera, String usuario, boolean profe)

        return user;
    }

    private void seleccionarAlumnoModificar(Usuario usuario) {
        usuarioModificar = usuario;
        labelAlumnoModificar.setText(usuario.getNombreUser());
        textFieldNombre.setText(usuario.getNombreUser());
        //textFieldExpediente.setText(usuario.getNumeroExpediente());
        textFieldGrupo.setText(usuario.getClase());
    }

    private void poblarListView() {
        listaNombres = new ArrayList<String>();
        try {
            String query = "select nombre, apellido from alumno where esProfe=false ;" ;
            OracleBD bd = new OracleBD();
            bd.setConnection();
            ResultSet resultados =  bd.makeQuery(query);
            bd.closeConnection();

            listaNombres.add(resultados.getString(4) + " "+resultados.getString(5)) ;

            observableUsuariosString = FXCollections.observableArrayList(listaNombres);
            listaUsuarios.setItems(observableUsuariosString);
            listaUsuarios.refresh();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void modificarValores(ActionEvent actionEvent) throws SQLException {
        String update = "update alumno";
        String set = " set nombre = " +textFieldNombre.getText()+ ", apellido= " + textFieldApellidos.getText() + ", grupo = "+ textFieldGrupo.getText() + ", expediente ="+ textFieldExpediente.getText();
        String where = " where nombre='" + usuarioModificar.getNombreUser() + "' and apellido='" + usuarioModificar.getApellidoUser() + "';" ;
        String query = update+set+where;

        OracleBD bd = new OracleBD();
        bd.setConnection();
        bd.makeQuery(query);
        bd.closeConnection();

        poblarListView();
    }

    public void borrarEsteUsuario(ActionEvent actionEvent) throws SQLException {
        OracleBD bd = new OracleBD();
        bd.setConnection();
        bd.makeQuery("DELETE FROM alumno WHERE nombre='" + usuarioModificar.getNombreUser() + "' and apellido='" + usuarioModificar.getApellidoUser() + "';" );
        bd.closeConnection();
        textFieldExpediente.clear();
        textFieldNombre.clear();
        textFieldGrupo.clear();
        poblarListView();
    }
*/}