package Controladoras;

import Modelo.Usuario;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import sun.plugin.javascript.navig.Anchor;


public class controladoraPrincipal {

    public static Usuario currentUser ;

    public controladoraPrincipal(){
        //deberia llegar desde el login a traves de el setter
        currentUser = new Usuario("Miguel", "Fernandez Paradela", "1234", "3", "6422765", false);
    }

    public void setCurrentUser(Usuario user){
        currentUser=user;
    }
    public Usuario getCurrentUser(){
        return currentUser;
    }

}