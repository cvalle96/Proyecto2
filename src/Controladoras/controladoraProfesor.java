package Controladoras;

import BBDD.OracleBD;
import Modelo.Usuario;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.sql.SQLException;
import java.util.ArrayList;

public class controladoraProfesor extends controladoraPrincipal {
    @FXML
    ObservableList<String> observableUsuariosString;
    @FXML
    ListView listaUsuarios, listaAsignatura;
    @FXML
    TextField textFieldNombre, textFieldAula, textFieldExpediente, textFieldCarrera, texFieldComentario, textFieldNota;
    @FXML
    Button actualizarButton, buttonEnviarNota;


    ArrayList<String> listaNombres, listaAsignaturas;
    Usuario usuarioModificar;
    Usuario currentUser;


    public controladoraProfesor(){
        currentUser = controladoraPrincipal.currentUser;
        listaUsuarios = new ListView();
        listaAsignatura = new ListView();
        textFieldNombre=new TextField();
        textFieldAula = new TextField();
        textFieldExpediente = new TextField();
        textFieldNota = new TextField();
        texFieldComentario = new TextField();
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
        System.out.println(nombre + apellidos);
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
        System.out.println(resultados.toString());
        String carrera = (String) resultados.get(5);
        String grupo = (String) resultados.get(6);
        String expediente =(String) resultados.get(4);
        System.out.println(resultados.toString());

        //le estoy pasando la carrera como si fuera la contraseña porque no tengo el campo en el constructor y contraseña no se utilizaba
        Usuario user = new Usuario(nombre, apellidos, carrera, grupo, expediente, false );

        return user;
    }

    private void seleccionarAlumnoModificar(Usuario usuario) {
        usuarioModificar = usuario;
        getAsignaturas(usuario);

    }



    private void seleccionarAsignaturaModificar(Usuario usuario) {
        usuarioModificar = usuario;
        textFieldNombre.setText(usuario.getNombreUser());
        textFieldCarrera.setText(usuario.getContrasenia());
        textFieldExpediente.setText(usuario.getNumeroExpediente());
        textFieldAula.setText(usuario.getClase());
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
    private void getAsignaturas(Usuario usuario) {
        listaAsignaturas = new ArrayList<String>();

        try {
            String query = "SELECT A.ASIGNATURA FROM ASIGNATURAS A, CARRERA C, ALUMNO AL WHERE A.ID_CARRERA = C.ID_CARRERA AND C.CARRERA = AL.CARRERA AND AL.EXPEDIENTE = " + usuario.getNumeroExpediente();
            System.out.println(query);
            OracleBD bd = new OracleBD();
            bd.setConnection();
            ArrayList resultados =  bd.getArrayList(query);
            bd.closeConnection();

            for(int i =0; i<resultados.size(); i++){
                listaAsignaturas.add((String)resultados.get(i));
            }
            observableUsuariosString = FXCollections.observableArrayList(listaAsignaturas);
            listaAsignatura.setItems(observableUsuariosString);
            listaAsignatura.refresh();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public void modificarValores(ActionEvent actionEvent) throws SQLException {

        String update = "update alumno";
        String set = " set nombre = '" +textFieldNombre.getText()+ "', carrera= '" + textFieldCarrera.getText() + "', clase = '"+ textFieldAula.getText() + "', expediente ='"+ textFieldExpediente.getText() + "'";
        String where = " where expediente='" + usuarioModificar.getNumeroExpediente() +"'";
        String query = update+set+where;

        OracleBD bd = new OracleBD();
        bd.setConnection();
        bd.makeInsert(query);
        bd.closeConnection();

        getAlumnos();

    }


    public void selectThisAsignatura(MouseEvent mouseEvent) throws SQLException {
        int indexNombre=listaAsignatura.getSelectionModel().getSelectedIndex();
        String nombre = listaAsignaturas.get(indexNombre);
        System.out.println(indexNombre+nombre);
        System.out.println(usuarioModificar.getClase());
        System.out.println(usuarioModificar.getNombreUser());
        textFieldNombre.setText(usuarioModificar.getNombreUser());
        textFieldAula.setText(usuarioModificar.getClase());
        textFieldExpediente.setText(usuarioModificar.getNumeroExpediente());
    }

    public void pintarAlumnos(ActionEvent actionEvent) {
        observableUsuariosString = FXCollections.observableArrayList(listaNombres);
        listaUsuarios.setItems(observableUsuariosString);
        listaUsuarios.refresh();
    }

    public void enviaNota(ActionEvent actionEvent){
        String nota = textFieldNota.getText();
        String comentario = texFieldComentario.getText();
        

    }

}