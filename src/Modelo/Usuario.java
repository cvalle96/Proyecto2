package Modelo;


public class Usuario extends Gestion {

    private String nombreUser;
    private String apellidoUser;
    private String numeroExpediente;
    private String contrasenia;
    private String clase;
    private boolean esProfe;

    public Usuario(String nombre, String contrasenia)  {
        this.nombreUser=nombre;
        this.contrasenia =contrasenia;
        this.numeroExpediente= generarExpediente();
    }


    public Usuario(String nombre, String apellidos, String contrasenia, String clase, String expediente, boolean profe)  {
        this.nombreUser=nombre;
        this.apellidoUser = apellidos;
        this.contrasenia =contrasenia;
        this.numeroExpediente= expediente;
        this.clase = clase;
        this.esProfe = profe;
    }

    public boolean esProfesor(){
        return esProfe;
    }

    public String generarExpediente(){
        String answer = Math.random() +"";
        answer = answer.substring(2,9);
        return answer;
    }

    public void setEsProfe(boolean esProfe) {
        this.esProfe = esProfe;
    }

    public String getApellidoUser() {
        return apellidoUser;
    }

    public String getNombreUser() {
        return nombreUser;
    }

    public String getNumeroExpediente() {
        return numeroExpediente;
    }

    public void setNombreUser(String nombreUser) {
        this.nombreUser = nombreUser;
    }

    public void setNumeroExpediente(String numeroExpediente) {
        this.numeroExpediente = numeroExpediente;
    }

    public void setContrasenia(String contra){
        this.contrasenia =contra;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public String toString(){
        return "Nombre " + this.nombreUser +"\nExpediente: " + this.numeroExpediente + "\n";
    }

    public String getClase() {
        return clase;
    }
}