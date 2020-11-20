package TRMS.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.Logger;

public class ConnectionUtil {
	
	private static Logger log = Logger.getRootLogger();
	
	private Connection conn;
	
	public Connection createConnection() throws SQLException {
		
		log.info("ConnectionUtil.createConnection[Attempting to start connection using DriverManager]");
		
		Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres?", "postgres", "hunter2");
		
		return conn;
		
	}
	
}
