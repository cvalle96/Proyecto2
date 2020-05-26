package Controladoras;

import BBDD.OracleBD;
import Modelo.Usuario;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.sql.SQLException;
import java.util.ArrayList;

public class controladoraEditor extends controladoraPrincipal {
    @FXML
    ObservableList<String> observableUsuariosString;
    @FXML
    ListView listaUsuarios;
    @FXML
    TextField textFieldNombre, textFieldGrupo, textFieldExpediente, textFieldCarrera;
    @FXML
    Button borrarButton, actualizarButton;


    ArrayList<String> listaNombres;
    Usuario usuarioModificar;
    Usuario currentUser;

    public controladoraEditor(){
        currentUser = controladoraPrincipal.currentUser;
        listaUsuarios = new ListView();

        getAlumnos();
    }

    public void selectThisUser(MouseEvent mouseEvent) throws SQLException {
        int indexNombre=listaUsuarios.getSelectionModel().getSelectedIndex();
        String nombre = listaNombres.get(indexNombre);

        String[] lista = nombre.split(" ");
        nombre = lista[0];
        String apellidos = "";
        for (int i = 1; i< lista.length; i++){
            apellidos += lista[i];
        }
        seleccionarAlumnoModificar( obtenerUsuariodeBBDD(nombre, apellidos) );
    }

    private Usuario obtenerUsuariodeBBDD(String nombre, String apellidos) throws SQLException {

        ArrayList resultados = null;
        try {
            OracleBD bd = new OracleBD();
            String query = "select * from alumno where nombre = '" + nombre + "' and apellido = '"+ apellidos + "'";
            bd.setConnection();
            resultados = bd.getArrayList(query);
            bd.closeConnection();
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        String carrera = (String) resultados.get(5);
        String grupo = (String) resultados.get(6);
        String expediente =(String) resultados.get(4);

        //le estoy pasando la carrera como si fuera la contraseña porque no tengo el campo en el constructor y contraseña no se utilizaba
        Usuario user = new Usuario(nombre, apellidos, carrera, grupo, expediente, false );
        return user;
    }

    private void seleccionarAlumnoModificar(Usuario usuario) {
        usuarioModificar = usuario;
        textFieldNombre.setText(usuario.getNombreUser());
        textFieldCarrera.setText(usuario.getContrasenia());
        textFieldExpediente.setText(usuario.getNumeroExpediente());
        textFieldGrupo.setText(usuario.getClase());
    }

    private void getAlumnos() {
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void modificarValores(ActionEvent actionEvent) throws SQLException {

        String update = "update alumno";
        String set = " set nombre = '" +textFieldNombre.getText()+ "', carrera= '" + textFieldCarrera.getText() + "', clase = '"+ textFieldGrupo.getText() + "', expediente ='"+ textFieldExpediente.getText() + "'";
        String where = " where expediente='" + usuarioModificar.getNumeroExpediente() +"'";
        String query = update+set+where;

        OracleBD bd = new OracleBD();
        bd.setConnection();
        bd.makeInsert(query);
        bd.closeConnection();

        getAlumnos();
        listaUsuarios.refresh();
    }

    public void borrarEsteUsuario(ActionEvent actionEvent) throws SQLException {
        OracleBD bd = new OracleBD();
        bd.setConnection();
        bd.makeInsert("DELETE FROM alumno WHERE expediente='" + usuarioModificar.getNumeroExpediente() + "'");
        bd.closeConnection();
        textFieldExpediente.clear();
        textFieldNombre.clear();
        textFieldGrupo.clear();
        getAlumnos();
        listaUsuarios.refresh();
    }

    public void pintarAlumnos(ActionEvent actionEvent) {
        observableUsuariosString = FXCollections.observableArrayList(listaNombres);
        listaUsuarios.setItems(observableUsuariosString);
        listaUsuarios.refresh();
    }
}
