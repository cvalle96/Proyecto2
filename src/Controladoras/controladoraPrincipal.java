package Controladoras;

import Modelo.Usuario;

public class controladoraPrincipal {

    public static Usuario currentUser ;


    public controladoraPrincipal(){
        //deberia llegar desde el login a traves de el setter
        //String nombre, String apellidos, String contrasenia, String clase, String expediente, boolean profe
        currentUser = new Usuario("Carlos", "Valle","1234","1","12345678",true );
    }

    public void setCurrentUser(Usuario user){
        currentUser=user;
    }
    public Usuario getCurrentUser(){
        return currentUser;
    }

}