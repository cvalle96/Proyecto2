package Controladoras;

import BBDD.OracleBD;
import Modelo.Usuario;
import java.sql.SQLException;
import java.util.ArrayList;

public class controladoraPrincipal {

    public static Usuario currentUser ;
    boolean profesor;

    public controladoraPrincipal(){

    }

    public void setProfesor(boolean prof){
        profesor = prof;
    }

    public void setExpediente(int expediente) throws SQLException {
        OracleBD bd = new OracleBD();
        bd.setConnection();

        if(profesor){
            ArrayList rs = bd.getArrayList("select nombre, apellido, clase, expediente from profesor where expediente = " + expediente );
            currentUser = new Usuario((String) rs.get(0), (String)rs.get(1), null,(String) rs.get(2), (String)rs.get(3), true );
        }else{
            ArrayList rs = bd.getArrayList("select nombre, apellido, clase, expediente from alumno where expediente = " + expediente );
            currentUser = new Usuario((String) rs.get(0), (String)rs.get(1), null,(String) rs.get(2), (String)rs.get(3), false );
        }
        bd.closeConnection();
    }

}