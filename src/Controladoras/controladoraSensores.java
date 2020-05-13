package Controladoras;

import BBDD.OracleBD;
import Modelo.Usuario;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.fxml.FXML;
import javafx.scene.control.ProgressBar;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;


public class controladoraSensores extends controladoraPrincipal{

    @FXML
    Button actualizarButton;
    @FXML
    ProgressBar progressRuido, progressTemperatura, progressHumedad;

    Double temperaturaActual, ruidoActual, humedadActual;
    Usuario currentUser =  controladoraPrincipal.currentUser;
    String claseActual ;
    ArrayList<Double> resultados;

    public controladoraSensores() throws SQLException {
        progressRuido = new ProgressBar();
        progressTemperatura = new ProgressBar();
        progressHumedad = new ProgressBar();
        claseActual = currentUser.getClase();

        actualizar();
    }


    public void actualizar() throws SQLException {
//falta que discrimine clases, que solo muestre la clase del usuario logueado
        OracleBD bd = new OracleBD();
        bd.setConnection();
        resultados = bd.getDoubleList("SELECT temperatura, ruido, humedad FROM registro ") ;
        Collections.reverse(resultados);
        temperaturaActual = resultados.get(0);
        ruidoActual = resultados.get(1);
        humedadActual = resultados.get(2);
        bd.closeConnection();

        dibujar();
    }

    private void dibujar() {
        progressHumedad.setProgress(humedadActual / 100);
        progressRuido.setProgress((ruidoActual * 102) /100);         //voltios
        progressTemperatura.setProgress((temperaturaActual *1.25) /100);

    }

    public void act(ActionEvent actionEvent) throws SQLException {
        actualizar();
    }

    public void howtoinsert() throws SQLException {

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String hora = dtf.format(now);

        OracleBD BD = new OracleBD();
        BD.setConnection();
        String query = "insert into registro (aula, temperatura,ruido,humedad,hora) VALUES (1,30,0.8,32,'"+dtf.format(now) +"')";
        BD.makeInsert(query);
        System.out.println(query);
        BD.closeConnection();
    }
}
