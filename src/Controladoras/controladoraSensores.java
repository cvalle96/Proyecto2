package Controladoras;

import BBDD.OracleBD;
import Modelo.Usuario;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.fxml.FXML;
import javafx.scene.control.ProgressBar;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class controladoraSensores extends controladoraPrincipal{

    double temperaturaActual, ruidoActual, humedadActual;

    @FXML
    Button actualizarButton;
    @FXML
    ProgressBar progressRuido, progressTemperatura, progressHumedad;

    @Override
    public void setCurrentUser(Usuario currentUser) {
        this.currentUser = controladoraPrincipal.currentUser;
    }

    Usuario currentUser = new Usuario("Miguel", "Fernandez", "jnjn", "jjnj", "77766", false);
    String claseActual ;
    ResultSet resultados;

    public controladoraSensores() throws SQLException {

        claseActual = currentUser.getClase();
        OracleBD bd = new OracleBD();
        bd.setConnection();
        resultados = bd.makeQuery("SELECT * FROM registro WHERE clase = " + claseActual) ;
        while (!resultados.isLast()){
            resultados.next();
        }
        actualizar();
        bd.closeConnection();


    }

    public void setTemp() throws SQLException {
        temperaturaActual = resultados.getDouble(2);
    }

    public void setRuido() throws SQLException {
        temperaturaActual = resultados.getDouble(3);

    }

    public void setHumedad() throws SQLException {
        temperaturaActual = resultados.getDouble(4);
    }


    public void actualizar() throws SQLException {
        setRuido();
        setTemp();
        setHumedad();
        dibujar();
    }

    private void dibujar() {
        progressHumedad.setProgress(humedadActual / 100);
        progressRuido.setProgress((ruidoActual * 1.02) /100);         //voltios
        progressTemperatura.setProgress((temperaturaActual *1.25) /100);
    }

    public void act(ActionEvent actionEvent) throws SQLException {
        actualizar();
    }
}
