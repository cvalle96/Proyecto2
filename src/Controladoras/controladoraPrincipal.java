package Controladoras;

import Modelo.Usuario;

public class controladoraPrincipal {

    public static Usuario currentUser ;


    public controladoraPrincipal(){
        //deberia llegar desde el login a traves de el setter
        currentUser = new Usuario("Miguel", "Fernandez Paradela", "1234", "3", "12345", false);
    }

    public void setCurrentUser(Usuario user){
        currentUser=user;
    }
    public Usuario getCurrentUser(){
        return currentUser;
    }

}