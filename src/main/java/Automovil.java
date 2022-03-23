import java.sql.*;
import java.util.Objects;

public class Automovil {
    private final String dominio;
    private final String marca;
    private final String modelo;
    private final String nombrePropietario;
    private final String fechaVencimiento;

    public Automovil(String dominio, String marca, String modelo, String nombrePropietario) {
        this(dominio, marca, modelo, nombrePropietario, null);
    }

    public Automovil(String dominio, String marca, String modelo, String nombrePropietario, String fechaVencimiento){

        this.dominio = dominio;
        this.marca = marca;
        this.modelo = modelo;
        this.nombrePropietario = nombrePropietario;
        this.fechaVencimiento = fechaVencimiento;
    }

    public String getDominio() {
        return dominio;
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public String getNombrePropietario() {
        return nombrePropietario;
    }

    public String getFechaVencimiento() {
        return fechaVencimiento;
    }

    public static Automovil buscarAutomovil(Conexion conexion, String dominio) {
        try {
            Connection cnx = conexion.conectar();
            Automovil auto;

            String sql = "SELECT * FROM automoviles WHERE dominio = ?";

            PreparedStatement pstmt = cnx.prepareStatement(sql);
            pstmt.setString(1, dominio);

            ResultSet rst = pstmt.executeQuery();
            if(rst.next()){
                auto = new Automovil(rst.getString("dominio"), rst.getString("marca"),
                        rst.getString("modelo"), rst.getString("nombrePropietario"),
                        rst.getString("fechaVencimiento"));
            }
            else {
                auto = null;
            }

            return auto;
        } catch (SQLException e) {
            System.err.println("Error! " + e.getMessage());
            return null;
        }
    }

    public boolean agregarAutomovil(Conexion conexion){
        try {
            Connection cnx = conexion.conectar();

            String sql = "INSERT INTO automoviles VALUES (?, ?, ?, ?, ?)";

            PreparedStatement pstmt = cnx.prepareStatement(sql);
            pstmt.setString(1, this.getDominio());
            pstmt.setString(2, this.getMarca());
            pstmt.setString(3, this.getModelo());
            pstmt.setString(4, this.getNombrePropietario());
            if(this.getFechaVencimiento() != null){
                pstmt.setString(5, this.getFechaVencimiento());
            }
            else {
                pstmt.setNull(5, Types.VARCHAR);
            }

            int conteo = pstmt.executeUpdate();
            return conteo > 0;
        }
        catch (SQLException e){
            System.err.println("Error! " + e.getMessage());
            return false;
        }
    }

    public boolean modificarAutomovil(Conexion conexion, String marca, String modelo, String nombre, String fecha){
        try {
            Connection cnx = conexion.conectar();
            if(Objects.equals(fecha, "no")){
                fecha = null;
            }

            String sql = "UPDATE automoviles SET marca = ?, modelo = ?, nombrePropietario = ?, fechaVencimiento = ? WHERE dominio = ?";

            PreparedStatement pstmt = cnx.prepareStatement(sql);
            pstmt.setString(1, marca);
            pstmt.setString(2, modelo);
            pstmt.setString(3, nombre);
            pstmt.setString(4, fecha);
            pstmt.setString(5, this.getDominio());

            int conteo = pstmt.executeUpdate();
            return conteo > 0;
        }
        catch (SQLException e){
            System.err.println("Error! " + e.getMessage());
            return false;
        }
    }

    public boolean borrarAutomovil(Conexion conexion){
        try {
            Connection cnx = conexion.conectar();

            String sql = "DELETE FROM automoviles WHERE dominio = ?";

            PreparedStatement pstmt = cnx.prepareStatement(sql);
            pstmt.setString(1, this.getDominio());

            int conteo = pstmt.executeUpdate();
            return conteo > 0;
        }
        catch (SQLException e){
            System.err.println("Error! " + e.getMessage());
            return false;
        }
    }
}
