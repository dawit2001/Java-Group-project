package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class databaseconnector {
	public static final String DB_URL = "jdbc:mysql://localhost/DAWIT";
	public   static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
	public   static final String DB_USER = "root";
	public   static final String DB_PASSWORD = "thisisabebe";
	public static Connection con;
	public databaseconnector() throws SQLException {
		con = DriverManager.getConnection(databaseconnector.DB_URL,databaseconnector.DB_USER,databaseconnector.DB_PASSWORD);
		System.out.println("success connection");
	}
	   
	   

}
