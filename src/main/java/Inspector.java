import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Inspector {
    private int id;
    private final String nombre;

    public Inspector(String nombre) {
        this.nombre = nombre;
    }

    public Inspector(int id, String nombre) {
        this(nombre);
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public static Inspector buscarInspector(Conexion conexion, String nombre) {
        try {
            Connection cnx = conexion.conectar();
            Inspector inspector;

            String sql = "SELECT * FROM inspectores WHERE nombre = ?";

            PreparedStatement pstmt = cnx.prepareStatement(sql);
            pstmt.setString(1, nombre);

            ResultSet rst = pstmt.executeQuery();
            if(rst.next()){
                inspector = new Inspector(rst.getInt("id"), rst.getString("nombre"));
            }
            else {
                inspector = null;
            }

            return inspector;
        } catch (SQLException e) {
            System.err.println("Error! " + e.getMessage());
            return null;
        }
    }

    public boolean agregarInspector(Conexion conexion){
        try {
            Connection cnx = conexion.conectar();

            String sql = "INSERT INTO inspectores VALUES (DEFAULT, ?)";

            PreparedStatement pstmt = cnx.prepareStatement(sql);
            pstmt.setString(1, this.getNombre());

            int conteo = pstmt.executeUpdate();
            return conteo > 0;
        }
        catch (SQLException e){
            System.err.println("Error! " + e.getMessage());
            return false;
        }
    }

    public boolean modificarInspector(Conexion conexion, String nombre){
        try {
            Connection cnx = conexion.conectar();

            String sql = "UPDATE inspectores SET nombre = ? WHERE id = ?";

            PreparedStatement pstmt = cnx.prepareStatement(sql);
            pstmt.setString(1, nombre);
            pstmt.setInt(2, this.getId());

            int conteo = pstmt.executeUpdate();
            return conteo > 0;
        }
        catch (SQLException e){
            System.err.println("Error! " + e.getMessage());
            return false;
        }
    }

    public boolean borrarInspector(Conexion conexion){
        try {
            Connection cnx = conexion.conectar();

            String sql = "DELETE FROM inspectores WHERE id = ?";

            PreparedStatement pstmt = cnx.prepareStatement(sql);
            pstmt.setInt(1, this.getId());

            int conteo = pstmt.executeUpdate();
            return conteo > 0;
        }
        catch (SQLException e){
            System.err.println("Error! " + e.getMessage());
            return false;
        }
    }
}
