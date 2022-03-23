import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    public Connection conectar(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");

            String url = "jdbc:mysql://localhost/vtv";
            String user = "root";
            String password = "";

            Connection conexion = DriverManager.getConnection(url, user, password);
            return conexion;
        }
        catch (ClassNotFoundException e){
            System.err.println("Error! " + e.getMessage());
        }
        catch (SQLException e){
            System.err.println("Error! " + e.getMessage());
        }

        return null;
    }
}