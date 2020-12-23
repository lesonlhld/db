package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCConnection {
	public static Connection getJDBCConnection() {
		Connection connection = null;
		try {
		    Class.forName("com.mysql.cj.jdbc.Driver");
		    String dbName = "SFCS";
		    String userName = "root";
		    String hostName = "localhost";
		    String password = "123456a@";
		    String connectionURL = "jdbc:mysql://" + hostName + ":3306/" + dbName + "?requireSSL=false&useUnicode=true&characterEncoding=UTF-8";
		    //String hostName = "node235993-leson.j.layershift.co.uk";
		    //String password = "NLYfsd26975";
		    //String connectionURL = "jdbc:mysql://" + hostName + "/" + dbName + "?requireSSL=false&useUnicode=true&characterEncoding=UTF-8";
			   
		    connection = DriverManager.getConnection(connectionURL, userName, password);
			//System.out.println("thanh cong");
			
		} catch (ClassNotFoundException | SQLException ex) {
			ex.printStackTrace();
		}

		return connection;
	}
}
