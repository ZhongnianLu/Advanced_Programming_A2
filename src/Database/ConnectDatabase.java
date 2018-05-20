package Database;
import java.sql.Connection;
import java.sql.DriverManager;

/*
 * This class is to connect to database created from DBTest
 * 
 */
public class ConnectDatabase {
	public static void connecting() {
		Connection con = null;
		
		DBTest test = new DBTest();
		test.dbtest();
//		
		try {
			
			//Registering the HSQLDB JDBC driver
			Class.forName("org.hsqldb.jdbc.JDBCDriver");
			//Creating the connection with HSQLDB
			con = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/testdb", "SA", "");
			if (con!= null){
				System.out.println("Connection created successfully");
				
			}else{
				System.out.println("Problem with creating connection");
			}
		
		}  catch (Exception e) {
			e.printStackTrace(System.out);
		}
	}
}