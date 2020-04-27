package BBDD;

import java.sql.*;
import java.util.ArrayList;
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

    public ResultSet makeQuery(String query) throws SQLException {
        try (Statement statement = con.createStatement()) {
            ResultSet rs = statement.executeQuery(query);
            while(rs.next()){
                return rs;
            }
        }
        return null;
    }

    public ArrayList<Double> getDoubleList(String query) throws SQLException{
        ArrayList<Double> resultados = new ArrayList<>();
        try (Statement statement = con.createStatement()) {
            ResultSet rs = statement.executeQuery(query);
            ResultSetMetaData rsmd =rs.getMetaData();
            int max = rsmd.getColumnCount();

            while(rs.next()){
                //a traves de un bucle, obtener los double del rs y pasarlos al arraylist resultados
                for(int i =3; i<max;i++){
                    resultados.add(rs.getDouble(i));
                }
            }
        }
        return resultados;
    }

    public void makeInsert(String query) throws SQLException{
        try (Statement statement = con.createStatement()) {
            try (ResultSet resultSet = statement.executeQuery(query)) {
                System.out.println("insertado");
            }catch (SQLException e){
                System.out.println(e.getSQLState() + "\n" + e.getMessage() );
            }
        }

    }
}