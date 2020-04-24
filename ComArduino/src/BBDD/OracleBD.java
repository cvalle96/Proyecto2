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

    public OracleBD(String query) throws SQLException {
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
            //System.out.println("Driver Name: " + dbmd.getDriverName());
            //System.out.println("Driver Version: " + dbmd.getDriverVersion());
            // Print some connection properties
            //System.out.println("Default Row Prefetch Value is: " + connection.getDefaultRowPrefetch());
            //System.out.println("Database Username is: " + connection.getUserName());
            //System.out.println();
            nuevaQuery(connection,query);
            System.out.println("Querry enviada");
        }
    }

    private void nuevaQuery(Connection connection, String query) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            try (ResultSet resultSet = statement.executeQuery(query)) {
                while (resultSet.next())
                    System.out.println(resultSet.getString(0));
        }
        }
        System.out.println("Recibido");
    }
}
