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
        currentUser = controladoraPrincipal.currentUser;
        listaUsuarios = new ListView();
        poblarListView();
        seleccionarAlumnoModificar(new Usuario("miguel","Fernandez","1221", "1","35678",false));
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
        seleccionarAlumnoModificar( obtenerUsuariodeBBDD(username, apellidos) );
    }

    private Usuario obtenerUsuariodeBBDD(String username, String apellidos) throws SQLException {

        ArrayList resultados = null;
        try {
            OracleBD bd = new OracleBD();
            String query = "select * from alumno where nombre = '" + username + "' and apellido = '"+ apellidos + "';";
            bd.setConnection();
            resultados = bd.getArrayList(query);
            bd.closeConnection();
        } catch (Exception e){
            System.out.println(e.getMessage());
        }

        String contrasena =(String) resultados.get(4);
        //String grupo = resultados.getString()                       FALTA EL CAMPO DE CLASE
        String expediente =(String) resultados.get(3);

        Usuario user = new Usuario(username, apellidos, contrasena, "0", expediente, false );

        return user;
    }

    private void seleccionarAlumnoModificar(Usuario usuario) {
        textFieldNombre = new TextField();
        textFieldExpediente= new TextField();
        textFieldGrupo= new TextField();
        usuarioModificar = usuario;
        //labelAlumnoModificar.setText(usuario.getNombreUser());
        textFieldNombre.setText(usuario.getNombreUser());
        textFieldExpediente.setText(usuario.getNumeroExpediente());
        textFieldGrupo.setText(usuario.getClase());
    }

    private void poblarListView() {
        listaNombres = new ArrayList<String>();
        try {

            String query = "select nombre, apellido from alumno " ;
            OracleBD bd = new OracleBD();
            bd.setConnection();
            ArrayList resultados =  bd.getArrayList(query);
            bd.closeConnection();

            for(int i =0; i<resultados.size(); i=i+2){
                listaNombres.add(resultados.get(i) + " "+resultados.get(i+1)) ;
            }
            System.out.println(listaNombres.toString());

            observableUsuariosString = FXCollections.observableArrayList(listaNombres);
            listaUsuarios.setItems(observableUsuariosString);
            System.out.println(listaUsuarios.toString());
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
        bd.makeInsert(query);
        bd.closeConnection();

        poblarListView();
    }

    public void borrarEsteUsuario(ActionEvent actionEvent) throws SQLException {
        OracleBD bd = new OracleBD();
        bd.setConnection();
        bd.makeInsert("DELETE FROM alumno WHERE nombre='" + usuarioModificar.getNombreUser() + "' and apellido='" + usuarioModificar.getApellidoUser() + "';" );
        bd.closeConnection();
        textFieldExpediente.clear();
        textFieldNombre.clear();
        textFieldGrupo.clear();
        poblarListView();
    }
}
