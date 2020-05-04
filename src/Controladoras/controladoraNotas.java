package Controladoras;

import BBDD.OracleBD;
import Modelo.Usuario;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import javafx.collections.ObservableList;

public class controladoraNotas extends controladoraPrincipal{
    @FXML
    Label labelHora;
    @FXML
    ListView notasListview;
    @FXML
    ObservableList<String> observableNotas;

    Usuario currentUser;


    public controladoraNotas() throws SQLException {
        actualizarHora();
        getNotas();
        currentUser = controladoraPrincipal.currentUser;
    }

    public void actualizarHora(){
        labelHora = new Label();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        labelHora.setText(dtf.format(now));
    }

    private void getNotas() throws SQLException {
        OracleBD bd = new OracleBD();
        bd.setConnection();
        //hacer el expediente una variable
        String query = "SELECT id_user FROM alumno WHERE EXPEDIENTE = '0256022' ";
        ArrayList rs = bd.getArrayList(query);
        System.out.println(rs.get(0));
        String idAlumno = (String) rs.get(0);
        query = "SELECT nota, asignatura, prueba FROM notas ";
        String query2 = "WHERE id_alumno = "+  8  ;
        query += query2;
        System.out.println(query);
        rs = bd.getArrayList(query);
        actualizarNotas(rs);
    }

    public void actualizarNotas(ArrayList rs){
        System.out.println(rs.toString());
        ArrayList<String> arrayNotas = new ArrayList<String>();
        for(int i =0; i<rs.size(); i+=3){
            arrayNotas.add(rs.get(i) + " --> " + rs.get(i+2) + " en la asignatura: " + rs.get(i+1));
        }
        System.out.println(arrayNotas);
        observableNotas = FXCollections.observableArrayList(arrayNotas);
        notasListview.setItems(observableNotas);
        notasListview.refresh();

    }
}
