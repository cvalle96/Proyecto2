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

    ArrayList<String> listaNombres;
    Usuario usuarioModificar;
    Usuario currentUser;

    public controladoraEditor(){
        conectar();
        poblarListView();

    }

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
        int index = getUserindex(usuarioModificar);

        //cargarnos usuarioModificar de la bbdd
        textFieldExpediente.clear();
        textFieldNombre.clear();
        textFieldGrupo.clear();

        try{
            escribirCambios();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public int getUserindex(Usuario user){
        int index=0;
        //obtener el index de alguna forma, nos e si es relevante ahora que trabajamos con bbdd
        return index;
    }
}
