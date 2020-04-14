package Controladoras;

import Modelo.Usuario;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import java.io.*;
import java.util.ArrayList;

public class controladoraEditor extends controladoraPrincipal {
    @FXML
    ObservableList<String> observableUsuariosString;
    @FXML
    ListView listaUsuarios;
    @FXML
    Label labelAlumnoModificar;
    @FXML
    TextField textFieldExpediente,textFieldNombre, textFieldGrupo;
    @FXML
    Button borrarButton, actualizarButton;

    ArrayList<String> listaNombres;
    Usuario usuarioModificar;
    Usuario currentUser;

    /*
    public controladoraEditor(){
        conectar();
        poblarListView();

    }
*/
    @Override
    public void setCurrentUser(Usuario user) {
        currentUser = user;
    }

    public void selectThisUser(MouseEvent mouseEvent) {

        int indexNombre=listaUsuarios.getSelectionModel().getSelectedIndex();
        String username = listaNombres.get(indexNombre);

        //buscar la coincidencia en la bbdd y obtener el usuario en forma de Usuario()
        //dummy line next
        seleccionarAlumnoModificar(new Usuario(null,null,null,null));

    }

    private void seleccionarAlumnoModificar(Usuario usuario) {
        usuarioModificar = usuario;
        labelAlumnoModificar.setText(usuario.getNombreUser());
        textFieldNombre.setText(usuario.getNombreUser());
        textFieldExpediente.setText(usuario.getNumeroExpediente());
        textFieldGrupo.setText(usuario.getClase());
    }

    private void conectar() {
        //crear conexion con la bbdd
    }

    private void poblarListView() {
        try {
            listaNombres = new ArrayList<String>();

            //introducir en listaNombres los usuarios de la BBDD

            observableUsuariosString = FXCollections.observableArrayList(listaNombres);
            listaUsuarios.setItems(observableUsuariosString);
            listaUsuarios.refresh();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void actualizarValores(ActionEvent actionEvent) {

        //pillar usuarioModificar, obtenerlo de la bbdd y hacer select + insert para actualizar los valores

    }

    private void escribirCambios() throws IOException {
        //guardar cambios en la bbdd si necesario

        poblarListView();
    }

    public void borrarEsteUsuario(ActionEvent actionEvent) {

        //cargarnos usuarioModificar de la bbdd
        textFieldExpediente.clear();
        textFieldNombre.clear();
        textFieldGrupo.clear();
        try{
            escribirCambios();
            poblarListView();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
