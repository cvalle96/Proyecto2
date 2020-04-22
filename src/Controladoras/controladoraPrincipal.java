
package Controladoras;

import Modelo.Usuario;
import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.layout.AnchorPane;

public class controladoraPrincipal {

    Usuario currentUser ;


    public controladoraPrincipal(){


    }

    public void setCurrentUser(Usuario user){
        currentUser=user;
    }
    public Usuario getCurrentUser(){
        return currentUser;
    }

}