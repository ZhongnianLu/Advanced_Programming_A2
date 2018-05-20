package Database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ViewData {
   
   public static void main(String[] args) {
      Connection con = null;
      Statement stmt = null;
      ResultSet result = null;
      
      try {
         Class.forName("org.hsqldb.jdbc.JDBCDriver");
         con = DriverManager.getConnection(
            "jdbc:hsqldb:hsql://localhost/testdb", "SA", "");
         stmt = con.createStatement();
         result = stmt.executeQuery(
            "SELECT * FROM people");
         
         while(result.next()){
            System.out.println(result.getInt("age")+" | "+
               result.getString("name")+" | "+
               result.getString("status"));
         }
      } catch (Exception e) {
         e.printStackTrace(System.out);
      }
   }
}