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

    public ArrayList getArrayList(String query) throws SQLException{
        ArrayList resultados = new ArrayList<>();
        try (Statement statement = con.createStatement()) {
            ResultSet rs = statement.executeQuery(query);
            ResultSetMetaData rsmd =rs.getMetaData();
            int max = rsmd.getColumnCount();

            while(rs.next()){
                //no se deja pillar la primera columna ¿error en el tipo de dato?
                for(int i =1; i<=max;i++){
                    resultados.add( rs.getString(i));
                }
            }
        }
        return resultados;
    }

    public ArrayList<Double> getDoubleList(String query) throws SQLException{
        ArrayList<Double> resultados = new ArrayList<>();
        try (Statement statement = con.createStatement()) {
            ResultSet rs = statement.executeQuery(query);
            ResultSetMetaData rsmd =rs.getMetaData();
            int max = rsmd.getColumnCount();

            while(rs.next()){
                //no se deja pillar la primera columna ¿error en el tipo de dato?
                for(int i =1; i<=max;i++){
                    resultados.add( rs.getDouble(i));
                }
            }
        }
        return resultados;
    }

    public void ejecutarst(PreparedStatement stmt) throws SQLException {
        stmt.executeUpdate();
    }

    public PreparedStatement prepareStatement(String s) {
        try (Statement statement = con.createStatement()) {
            PreparedStatement stmt = con.prepareStatement(s);
            return stmt;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public Blob getImagen(String query) throws SQLException {
        try(Statement statement = con.createStatement()){
            ResultSet sentencia = statement.executeQuery(query);
            Blob foto = null;
            if(sentencia.next())
                foto = sentencia.getBlob(1);
            return foto;
        }
        catch(SQLException e){
            System.out.println("Error ejecutando consulta");
            e.printStackTrace();
            return null;
        }
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