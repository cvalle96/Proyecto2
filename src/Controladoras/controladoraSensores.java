package Controladoras;

import BBDD.OracleBD;
import Modelo.Usuario;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;


public class controladoraSensores extends controladoraPrincipal{

    @FXML
    Button actualizarButton;
    @FXML
    ProgressBar progressRuido, progressTemperatura, progressHumedad;
    @FXML
    Label labelSensores;


    Double temperaturaActual, ruidoActual, humedadActual;
    Usuario currentUser =  controladoraPrincipal.currentUser;
    String claseActual ;
    ArrayList<Double> resultados;

    public controladoraSensores() {
        progressRuido = new ProgressBar();
        progressTemperatura = new ProgressBar();
        progressHumedad = new ProgressBar();
        claseActual = currentUser.getClase();

    }


    public void actualizar() throws SQLException {
        OracleBD bd = new OracleBD();
        bd.setConnection();
        resultados = bd.getDoubleList("SELECT temperatura, ruido, humedad FROM registro WHERE aula = " + currentUser.getClase()) ;
        Collections.reverse(resultados);
        temperaturaActual = resultados.get(0);
        ruidoActual = resultados.get(1);
        humedadActual = resultados.get(2);
        bd.closeConnection();

        dibujar();
    }

    private void dibujar() {
        labelSensores.setText("mostrando aula: " + currentUser.getClase());
        progressHumedad.setProgress(humedadActual / 100);
        progressRuido.setProgress((ruidoActual * 102) /100);         //voltios
        progressTemperatura.setProgress((temperaturaActual *1.25) /100);

    }

    public void act(ActionEvent actionEvent) throws SQLException {
        actualizar();
    }

}
