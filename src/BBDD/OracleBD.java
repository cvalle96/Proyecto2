package BBDD;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import oracle.jdbc.pool.OracleDataSource;
import oracle.jdbc.OracleConnection;

import java.sql.DatabaseMetaData;

public class OracleBD {

    final static String DB_URL= "jdbc:oracle:thin:@dbpusi_medium?TNS_ADMIN=src/Wallet_Conexion";
    final static String DB_USER = "admin";
    final static String DB_PASSWORD = "Proyecto2PUSI";

    public OracleBD(){

    }

    public ResultSet newQueryBD(String query) throws SQLException {
        Properties info = new Properties();
        info.put(OracleConnection.CONNECTION_PROPERTY_USER_NAME, DB_USER);
        info.put(OracleConnection.CONNECTION_PROPERTY_PASSWORD, DB_PASSWORD);
        info.put(OracleConnection.CONNECTION_PROPERTY_DEFAULT_ROW_PREFETCH, "20");
        OracleDataSource ods = new OracleDataSource();
        ods.setURL(DB_URL);
        ods.setConnectionProperties(info);

        try (OracleConnection connection = (OracleConnection) ods.getConnection()) {
            // Get the JDBC driver name and version
            DatabaseMetaData dbmd = connection.getMetaData();
            return makeQuery(connection, query);
        }
    }

    private ResultSet makeQuery(Connection connection, String query) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            try (ResultSet resultSet = statement.executeQuery(query)) {
                return resultSet;
            }
        } catch (SQLException e){
            System.out.println(e.getErrorCode() + "\n\n DEVOLVIENDO NULL...");
        }
        return null;
    }
}