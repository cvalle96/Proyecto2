package Controladoras;

import BBDD.OracleBD;
import Modelo.Usuario;
import java.sql.SQLException;
import java.util.ArrayList;

public class controladoraPrincipal {

    public static Usuario currentUser ;

    public controladoraPrincipal(){

    }

    public void setExpediente(int expediente) throws SQLException {
        OracleBD bd = new OracleBD();
        bd.setConnection();
        ArrayList rs = bd.getArrayList("select nombre, apellido, clase, expediente from alumno where expediente = " + expediente );
        currentUser = new Usuario((String) rs.get(0), (String)rs.get(1), null,(String) rs.get(2), (String)rs.get(3), false );
        bd.closeConnection();
    }

}