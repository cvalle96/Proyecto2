package Controladoras;

import Modelo.Usuario;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.fxml.FXML;
import javafx.scene.control.ProgressBar;


public class controladoraSensores extends controladoraPrincipal{

    double temperaturaActual, ruidoActual, humedadActual;

    @FXML
    Button actualizarButton;
    @FXML
    ProgressBar progressRuido, progressTemperatura, progressHumedad;

    @Override
    public void setCurrentUser(Usuario currentUser) {
        this.currentUser = currentUser;
    }

    Usuario currentUser;
    String claseActual ;

    /*
    public controladoraSensores(){
        claseActual = currentUser.getClase();
        connectBBDD();
        actualizar();
    }
*/
    private void connectBBDD() {
        //sentencia SQL que obtenga la clase del currentUser y la coloque en la variable global

        //CONECTAR CON LA BBDD EN QUESTION
    }

    public void setTemp() {
        //sentencia SQL que obtenga el ultimo temp
        temperaturaActual = 0;
    }
    public void setRuido() {
        //sentencia SQL que obtenga el ultimo ruido
        ruidoActual = 0;
    }
    public void setHumedad(){
        //sentencia SQL que obtenga el ultimo dato
        humedadActual= 0;
    }


    public void actualizar() {
        setRuido();
        setTemp();
        setHumedad();
        dibujar();
    }

    private void dibujar() {
        progressHumedad.setProgress(temperaturaActual);
        progressRuido.setProgress(ruidoActual);
        progressTemperatura.setProgress(temperaturaActual);
    }

    public void act(ActionEvent actionEvent) {
        actualizar();
    }
}
