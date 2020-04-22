package Controladoras;

import Modelo.Usuario;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import javafx.collections.ObservableList;

public class controladoraNotas extends controladoraPrincipal{
    @FXML
    TextField horaTextfield;
    @FXML
    ListView notasListview;
    @FXML
    ObservableList<String> observableNotas;

    Usuario currentUser;


    public controladoraNotas(){
        //actualizarHora();
        //actualizarNotas();
        currentUser = controladoraPrincipal.currentUser;
    }

    public void actualizarHora(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        horaTextfield.setText(dtf.format(now));
    }

    public void actualizarNotas(){
        //metodo que obtiene de la bbdd las notas de currentUser y las pasa a formato listview
        ArrayList<String> arrayNotas = new ArrayList<String>();
            //aqui paso las notas en un bucle
        observableNotas = FXCollections.observableArrayList(arrayNotas);
        notasListview.setItems(observableNotas);
        notasListview.refresh();
    }
}
