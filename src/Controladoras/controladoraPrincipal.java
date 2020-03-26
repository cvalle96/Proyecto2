package Controladoras;

import Modelo.Usuario;
import javafx.fxml.FXML;
import javafx.scene.control.Tab;

public class controladoraPrincipal {
    @FXML
    Tab editorTab;

    Usuario currentUser = null;

    public controladoraPrincipal(){
        if (currentUser.getRolUser().equals("profesor")){
            editorTab.setDisable(true);
        }else{
            editorTab.setDisable(false);
        }


    }

}