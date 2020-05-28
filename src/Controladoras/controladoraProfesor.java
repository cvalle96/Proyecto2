package Controladoras;

import BBDD.OracleBD;
import Modelo.Usuario;
import java.sql.SQLException;
import java.util.ArrayList;

public class controladoraProfesor{

    public static Usuario currentUser ;

    public controladoraProfesor(){

    }

    public void setExpediente(int expediente) throws SQLException {
        OracleBD bd = new OracleBD();
        bd.setConnection();
        ArrayList rs = bd.getArrayList("select nombre, apellido, clase, expediente from profesor where expediente = " + expediente );
        currentUser = new Usuario((String) rs.get(0), (String)rs.get(1), null,(String) rs.get(2), (String)rs.get(3), true );
        bd.closeConnection();
    }

}