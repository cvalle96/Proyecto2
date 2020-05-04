package Modelo;

import java.util.ArrayList;

public class Gestion {

    private ArrayList<Usuario> listaUsuarios;
    private ArrayList<Usuario> listaProfesores;
    private ArrayList<String> listaExpedientes;
    private Usuario currentUser;


    public Gestion(){
        listaUsuarios=new ArrayList<Usuario>();
        listaProfesores=new ArrayList<Usuario>();
        listaExpedientes = new ArrayList<String>();
    }

    public void añadirEstudiante(Usuario user){
        if (comprobadorUsuario(user))
            return;
        user.setEsProfe(false);
        while(comprobadorExpediente(user)){
            user.setNumeroExpediente(user.generarExpediente());
        }
        listaUsuarios.add(user);
        listaExpedientes.add(user.getNumeroExpediente());
    }

    public void añadirProfesor(Usuario user){
        if (comprobadorUsuario(user))
            return;
        user.setEsProfe(true);
        while(comprobadorExpediente(user)){
            user.setNumeroExpediente(user.generarExpediente());
        }
        listaProfesores.add(user);
        listaExpedientes.add(user.getNumeroExpediente());
    }

    public boolean comprobadorUsuario(Usuario user){
        //devuelve true si hay coincidencia
        for (Usuario x : listaUsuarios){
            if (user == x){
                return true;
            }
        }
        for (Usuario x : listaProfesores){
            if (user == x){
                return true;
            }
        }
        return false;
    }

    public void imprimirClase(){
        System.out.println("Profesores: ");
        for (Usuario x : listaProfesores){
            System.out.println(x);
        }
        System.out.println("Alumnos:");
        for (Usuario x : listaUsuarios){
            System.out.println(x);
        }
    }
    public boolean comprobadorExpediente(Usuario user){
        String expediente = user.getNumeroExpediente();
        for ( String x : listaExpedientes){
            if (x.equals(expediente)){
                return true;
            }
        }
        return false;
    }

    public ArrayList<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }

    public ArrayList<Usuario> getListaProfesores() {
        return listaProfesores;
    }

    public void setCurrentUser(Usuario user){ currentUser=user;}
}