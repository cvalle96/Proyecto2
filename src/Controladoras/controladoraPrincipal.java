package Controladoras;

import Modelo.Usuario;
import javafx.fxml.FXML;
import javafx.scene.control.Tab;

public class controladoraPrincipal {
    @FXML
    Tab editorTab;
    Usuario currentUser = null;


    public controladoraPrincipal(){
        if (currentUser.esProfesor()){
            editorTab.setDisable(false);
            controladoraEditor editor = new controladoraEditor();
            editor.setCurrentUser(currentUser);
        }else{
            editorTab.setDisable(true);
        }
    }

    public void setCurrentUser(Usuario user){
        currentUser=user;
    }
    public Usuario getCurrentUser(){
        return currentUser;
    }

}