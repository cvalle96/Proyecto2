package Modelo;


public class Usuario extends Gestion {
    // la clase usuario que inicializa los users de nuestra app
    //el rol es estudiante por defecto, se cambia con el set despues de inicializar el objeto

    private String nombreUser;
    private String apellidoUser;
    //private String numeroExpediente;
    private String contrasenia;
    private String carrera;


    public boolean isProfe() {
        return esProfe;
    }

    public void setEsProfe(boolean esProfe) {
        this.esProfe = esProfe;
    }

    private boolean esProfe;

    public Usuario(String nombre, String contrasenia)  {
        this.nombreUser=nombre;
        this.contrasenia =contrasenia;
        //this.numeroExpediente= generarExpediente();
    }

    public String getApellidoUser() {
        return apellidoUser;
    }

    public Usuario(String nombre, String apellidos, String contrasenia, String carrera, String usuario, boolean profe)  {
        this.nombreUser=nombre;
        this.apellidoUser = apellidos;
        this.contrasenia =contrasenia;
        //this.numeroExpediente= expediente;
        this.carrera = carrera;
        this.esProfe = profe;
    }

    public boolean esProfesor(){
        return esProfe;
    }

    public static String generarExpediente(){
        String answer = Math.random() +"";
        answer = answer.substring(2,9);
        return answer;
    }

    //las string de los roles en mayusculas

    public String getNombreUser() {
        return nombreUser;
    }

    /*public String getNumeroExpediente() {
        return numeroExpediente;
    }*/

    public void setNombreUser(String nombreUser) {
        this.nombreUser = nombreUser;
    }

    /*public void setNumeroExpediente(String numeroExpediente) {
        this.numeroExpediente = numeroExpediente;
    }*/

    public void setContrasenia(String contra){
        this.contrasenia =contra;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public String toString(){
        return "Nombre " + this.nombreUser +"";
    }

    public String getClase() {
        return carrera;
    }
}