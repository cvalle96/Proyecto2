package Controladoras;

import Modelo.Usuario;


public class controladoraPrincipal {

    public static Usuario currentUser ;

    public controladoraPrincipal(){
        //deberia llegar desde el login a traves de el setter

    }

    public void setCurrentUser(Usuario user){
        currentUser=user;
        System.out.println(currentUser.getNumeroExpediente());

    }
    public Usuario getCurrentUser(){
        return currentUser;
    }

}