package Modelo;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class Usuario extends Gestion {
    // la clase usuario que inicializa los users de nuestra app
    //el rol es estudiante por defecto, se cambia con el set despues de inicializar el objeto

    private String nombreUser;
    private String numeroExpediente;
    private String rolUser;
    private String contrasenia;
    private String clase;

    public Usuario(String nombre, String contrasenia) throws FileNotFoundException {
        this.nombreUser=nombre;
        this.rolUser="ESTUDIANTE";
        this.contrasenia =contrasenia;
        this.numeroExpediente= generarExpediente();

    }
    public Usuario(String nombre, String contrasenia, String numExpediente, String clase) throws FileNotFoundException {
        this.nombreUser=nombre;
        this.rolUser="ESTUDIANTE";
        this.contrasenia =contrasenia;
        this.numeroExpediente= numExpediente;
        this.clase=clase;
    }

    public String generarExpediente(){
        String answer = Math.random() +"";
        answer = answer.substring(2,9);
        return answer;
    }

    //las string de los roles en mayusculas

    public String getNombreUser() {
        return nombreUser;
    }

    public String getNumeroExpediente() {
        return numeroExpediente;
    }

    public String getRolUser() {
        return rolUser;
    }

    public void setNombreUser(String nombreUser) {
        this.nombreUser = nombreUser;
    }

    public void setNumeroExpediente(String numeroExpediente) {
        this.numeroExpediente = numeroExpediente;
    }

    public void setRolUser(String rolUser) {
        this.rolUser = rolUser;
    }

    public void setContrasenia(String contra){
        this.contrasenia =contra;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public String toString(){
        if (this.rolUser.equals("PROFESOR")){
            return "Profesor: " + this.nombreUser +"\nExpediente: " + this.numeroExpediente + "\n\n";
        }
        return "Nombre " + this.nombreUser +"\nExpediente: " + this.numeroExpediente + "\n";
    }

    public String getClase() {
        return clase;
    }
}