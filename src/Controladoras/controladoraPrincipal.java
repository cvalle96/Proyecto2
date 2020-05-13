package Controladoras;

import Modelo.Usuario;


public class controladoraPrincipal {

    public static Usuario currentUser ;

    public controladoraPrincipal(){
        //deberia llegar desde el login a traves de el setter
        currentUser = new Usuario("alejandro", "salas", null, "12", "4796869", true );
    }

    public void setCurrentUser(Usuario user){
        currentUser=user;
    }
    public Usuario getCurrentUser(){
        return currentUser;
    }

}