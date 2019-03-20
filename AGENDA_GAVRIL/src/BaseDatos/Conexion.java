package BaseDatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

	final private String DIR = "jdbc:sqlite:Agenda.sqlite";
	private Connection conn = null;
	
	public Conexion() {
		
		try {
			conn = DriverManager.getConnection(DIR);
			conn.setAutoCommit(true);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
	}
	
	public Connection get() { 
		return conn;
	}
	
	public void salir() {
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
}
