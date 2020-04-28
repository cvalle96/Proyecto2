package BBDD;

import java.sql.*;
import java.util.Properties;
import oracle.jdbc.pool.OracleDataSource;
import oracle.jdbc.OracleConnection;

public class OracleBD {

    final static String DB_URL= "jdbc:oracle:thin:@dbpusi_medium?TNS_ADMIN=src/Wallet_Conexion";
    final static String DB_USER = "admin";
    final static String DB_PASSWORD = "Proyecto2PUSI";
    private Connection con;
    private Properties info;

    public OracleBD(){
        con = null;
        info = null;
    }
    public boolean setConnection(){
        boolean bRet=false;
        try {
            con = setOds().getConnection();
            System.out.println(con);
            bRet=true;
            System.out.println("conexion realizada");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bRet;
    }

    public void closeConnection() {
        try {
            if (con != null)
                System.out.println(con);
                con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private OracleDataSource setOds() throws SQLException {
        info = new Properties();
        info.put(OracleConnection.CONNECTION_PROPERTY_USER_NAME, DB_USER);
        info.put(OracleConnection.CONNECTION_PROPERTY_PASSWORD, DB_PASSWORD);
        info.put(OracleConnection.CONNECTION_PROPERTY_DEFAULT_ROW_PREFETCH, "20");
        OracleDataSource ods = new OracleDataSource();
        ods.setURL(DB_URL);
        ods.setConnectionProperties(info);
        return ods;
    }

    public int ejecutarQuery(String query) throws SQLException {
        try(Statement statement = con.createStatement()){
            PreparedStatement stmt = con.prepareStatement(query);
            return stmt.executeUpdate();
        }
        catch(SQLException e){
            System.out.println("Error ejecutando consulta");
            e.printStackTrace();
            return -1;
        }
    }

    public int selectQuery(String query) throws SQLException {
        try(Statement statement = con.createStatement()){
            ResultSet sentencia = statement.executeQuery(query);
            int resultado = 0;
            if(sentencia.next())
                resultado = sentencia.getInt(1);
            return resultado;
        }
        catch(SQLException e){
            System.out.println("Error ejecutando consulta");
            e.printStackTrace();
            return -1;
        }
    }
}