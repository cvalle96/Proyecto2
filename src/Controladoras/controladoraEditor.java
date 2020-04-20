package Controladoras;

import BBDD.OracleBD;
import Modelo.Usuario;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.sql.SQLException;
import java.util.ArrayList;

public class controladoraEditor extends controladoraPrincipal {
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
    }

    /*
    @Override
    public void setCurrentUser(Usuario user) {
        currentUser = user;
    }
    */

    public void selectThisUser(MouseEvent mouseEvent) {
        int indexNombre=listaUsuarios.getSelectionModel().getSelectedIndex();
        String username = listaNombres.get(indexNombre);

        String[] lista = username.split(" ");
        username = lista[0];
        String apellidos = "";
        for (int i = 1; i< lista.length; i++){
            apellidos += lista[i];
        }
        seleccionarAlumnoModificar( obtenerUsuariodeBBDD(username, apellidos) );
    }

    private Usuario obtenerUsuariodeBBDD(String username, String apellidos){
        ArrayList<ArrayList> resultados = null;
        try {
            OracleBD bd = new OracleBD();
            String query = "select * from alumno where nombre = '" + username + "' and apellido = '"+ apellidos + "';";
            resultados = bd.newQueryBD(query);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
//estos index habrá que ajustarlos
        resultados = resultados.get(0);
        String contrasena = resultados.get(2).toString();
        String grupo = resultados.get(3).toString();
        String expediente = resultados.get(4).toString();

        Usuario user = new Usuario(username, apellidos, contrasena, grupo, expediente, false );
        return user;
    }

    private void seleccionarAlumnoModificar(Usuario usuario) {
        usuarioModificar = usuario;
        labelAlumnoModificar.setText(usuario.getNombreUser());
        textFieldNombre.setText(usuario.getNombreUser());
        textFieldExpediente.setText(usuario.getNumeroExpediente());
        textFieldGrupo.setText(usuario.getGrupo());
    }

    private void poblarListView() {
        /*
        try {
            String query = "select nombre, apellido from alumno where esProfe=false ;" ;
            OracleBD nueva = new OracleBD();
            ArrayList<ArrayList> p =  nueva.newQueryBD(query);
            listaNombres = p.get(0);

            observableUsuariosString = FXCollections.observableArrayList(listaNombres);
            listaUsuarios.setItems(observableUsuariosString);
            listaUsuarios.refresh();

        } catch (Exception e) {
            e.printStackTrace();
        }

         */
    }

    public void modificarValores(ActionEvent actionEvent) throws SQLException {
        String update = "update alumno";
        String set = " set nombre = " +textFieldNombre.getText()+ ", apellido= " + textFieldApellidos.getText() + ", grupo = "+ textFieldGrupo.getText() + ", expediente ="+ textFieldExpediente.getText();
        String where = " where nombre='" + usuarioModificar.getNombreUser() + "' and apellido='" + usuarioModificar.getApellidoUser() + "';" ;
        String query = update+set+where;
        OracleBD nueva = new OracleBD();
        nueva.newQueryBD(query);

        poblarListView();
    }

    public void borrarEsteUsuario(ActionEvent actionEvent) throws SQLException {
        OracleBD nueva = new OracleBD();
        nueva.newQueryBD("DELETE FROM alumno WHERE nombre='" + usuarioModificar.getNombreUser() + "' and apellido='" + usuarioModificar.getApellidoUser() + "';" );

        textFieldExpediente.clear();
        textFieldNombre.clear();
        textFieldGrupo.clear();
        poblarListView();
    }
}
