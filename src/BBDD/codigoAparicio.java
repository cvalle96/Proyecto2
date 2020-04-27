package BBDD;

import java.sql.*;
import java.util.List;

public class codigoAparicio {
    private Connection con;
    final static String DB_URL= "jdbc:oracle:thin:@dbpusi_medium?TNS_ADMIN=src/Wallet_Conexion";
    final static String DB_USER = "admin";
    final static String DB_PW = "Proyecto2PUSI";

    public codigoAparicio(){
        con=null;

    }

    public boolean setConnection(){
        boolean bRet=false;
        try {
            con = DriverManager.getConnection(DB_URL, DB_USER, DB_PW);
            bRet=true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bRet;
    }

    public void closeConnection() {
        try {
            if (con != null)
                con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public ResultSet execQueries(String query) throws SQLException {
        ResultSet rsRet=null;
        try{
            PreparedStatement preparedStatement = con.prepareStatement(query);
            System.out.println(preparedStatement);
            rsRet = preparedStatement.executeQuery();

        }catch (Exception e) {
            e.printStackTrace();
        }
        return rsRet;
    }
}
