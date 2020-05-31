package Controladoras;

import BBDD.OracleBD;
import Modelo.Usuario;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import javafx.collections.ObservableList;
import javafx.scene.layout.AnchorPane;
import jfxtras.styles.jmetro.JMetro;

public class controladoraNotas extends controladoraPrincipal{
    @FXML
    Label labelHora;
    @FXML
    ListView notasListview;
    @FXML
    ObservableList<String> observableNotas;
    @FXML
    Button actualizarButton;
    @FXML
    AnchorPane ap;

    Usuario currentUser;


    public controladoraNotas() {
        currentUser = controladoraPrincipal.currentUser;

        notasListview = new ListView();
        labelHora = new Label();
    }

    public void actualizarHora(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        labelHora.setText(dtf.format(now));
    }

    private void getNotas() throws SQLException {
        OracleBD bd = new OracleBD();
        bd.setConnection();
        String query = "SELECT id_alumno FROM alumno WHERE EXPEDIENTE = '" + currentUser.getNumeroExpediente() + "'";
        ArrayList rs = bd.getArrayList(query);
        String idAlumno = (String) rs.get(0);
        query = "SELECT nota, asignatura, prueba FROM notas ";
        String query2 = "WHERE id_alumno = "+  idAlumno  + " ORDER BY asignatura";
        query += query2;
        rs = bd.getArrayList(query);
        bd.closeConnection();
        pintarNotas(rs);
    }

    private void pintarNotas(ArrayList rs){
        ArrayList<String> arrayNotas = new ArrayList<String>();
        if(rs.isEmpty()){
            arrayNotas.add("No hay notas por mostrar para este alumno");
        }else{
            for(int i =0; i<rs.size(); i+=3) {
                String tupla = rs.get(i+1) + getEspacios((String) rs.get(i+1));
                tupla += rs.get(i+2) + getEspacios((String) rs.get(i+2));
                tupla += rs.get(i) + getEspacios((String) rs.get(i));
                arrayNotas.add(tupla);
            }
        }
        observableNotas = FXCollections.observableArrayList(arrayNotas);
        notasListview.setItems(observableNotas);
        notasListview.refresh();

    }

    private String getEspacios(String palabra){
        int numero = 50 - palabra.length();
        String espacio = "";
        for(; numero>=0; numero--){
            espacio += " ";
        }
        return espacio;
    }


    public void actualizar(ActionEvent actionEvent) throws SQLException {
        actualizarHora();
        getNotas();
    }
}