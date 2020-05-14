package Modelo;

public class Asignatura {

    String nombre;
    String id_asignatura;

    public Asignatura(String nombre, String id){
        this.nombre = nombre;
        this.id_asignatura = id;
    }
    public String getNombre() {
        return nombre;

    }
    public void setNombre(String nombre) {
        this.nombre = nombre;

    }

    public String getId_asignatura(){
        return id_asignatura;
    }

    public void setId_asignatura(String id){
        this.id_asignatura=id;

    }

}