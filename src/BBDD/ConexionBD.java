package BBDD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {

	public static Connection conexion;

	public ConexionBD(){
		this.conexion=conexion();
	}

	public static Connection getConexion() {
		return conexion;
	}

	public static void setConexion(Connection conexion) {
		BBDD.ConexionBD.conexion = conexion;
	}

	public static Connection conexion(){
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost/pusidb","root","");
			System.out.println("Conexion con base de datos realizada.");
			return con;

		} catch (InstantiationException e) {
			System.out.println("Error 1");
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			System.out.println("Error 2");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			System.out.println("Error 3");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("Error 4");
			e.printStackTrace();
		}
		return null;
	}
}