import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Inspeccion {
    private final int numero;
    private final String fecha;
    private final String estado;
    private final String nombreInspector;
    private final boolean exento;
    private final String dominioAuto;

    public Inspeccion(int numero, String estado, String nombreInspector, boolean exento, String dominioAuto) {
        this(numero, estado, nombreInspector, exento, dominioAuto, null);
    }

    public Inspeccion(int numero, String estado, String nombreInspector, boolean exento, String dominioAuto, String fecha){
        this.numero = numero;
        this.estado = estado;
        this.nombreInspector = nombreInspector;
        this.exento = exento;
        this.dominioAuto = dominioAuto;
        this.fecha = fecha;
    }

    public int getNumero() {
        return numero;
    }

    public String getFecha() {
        return fecha;
    }

    public String getEstado() {
        return estado;
    }

    public String getNombreInspector() {
        return nombreInspector;
    }

    public boolean getExento() {
        return exento;
    }

    public String getDominioAuto() {
        return dominioAuto;
    }

    public static Inspeccion buscarInspeccion(Conexion conexion, String dominioAuto) {
        try {
            Connection cnx = conexion.conectar();
            Inspeccion inspeccion;

            String sql = "SELECT * FROM inspecciones WHERE dominioAuto = ?";

            PreparedStatement pstmt = cnx.prepareStatement(sql);
            pstmt.setString(1, dominioAuto);

            ResultSet rst = pstmt.executeQuery();
            if(rst.next()){
                inspeccion = new Inspeccion(rst.getInt("numero"), rst.getString("estado"),
                        rst.getString("nombreInspector"), rst.getBoolean("exento"),
                        rst.getString("dominioAuto"), rst.getString("fecha"));
            }
            else {
                inspeccion = null;
            }

            return inspeccion;
        } catch (SQLException e) {
            System.err.println("Error! " + e.getMessage());
            return null;
        }
    }

    public boolean agregarInspeccion(Conexion conexion){
        try {
            Connection cnx = conexion.conectar();

            String sql = "INSERT INTO inspecciones VALUES (?, COALESCE(?, DEFAULT(fecha)), ?, ?, ?, ?)";

            PreparedStatement pstmt = cnx.prepareStatement(sql);
            pstmt.setInt(1, this.getNumero());
            pstmt.setString(2, this.getFecha());
            pstmt.setString(3, this.getEstado());
            pstmt.setString(4, this.getNombreInspector());
            pstmt.setBoolean(5, this.getExento());
            pstmt.setString(6, this.getDominioAuto());

            int conteo = pstmt.executeUpdate();
            return conteo > 0;
        }
        catch (SQLException e){
            System.err.println("Error! " + e.getMessage());
            return false;
        }
    }

    public boolean modificarInspeccion(Conexion conexion, String estado, String inspector, boolean exento){
        try {
            Connection cnx = conexion.conectar();

            String sql = "UPDATE inspecciones SET estado = ?, nombreInspector = ?, exento = ? WHERE numero = ?";

            PreparedStatement pstmt = cnx.prepareStatement(sql);
            pstmt.setString(1, estado);
            pstmt.setString(2, inspector);
            pstmt.setBoolean(3, exento);
            pstmt.setInt(4, this.getNumero());

            int conteo = pstmt.executeUpdate();
            return conteo > 0;
        }
        catch (SQLException e){
            System.err.println("Error! " + e.getMessage());
            return false;
        }
    }

    public boolean borrarInspeccion(Conexion conexion){
        try {
            Connection cnx = conexion.conectar();

            String sql = "DELETE FROM inspecciones WHERE numero = ?";

            PreparedStatement pstmt = cnx.prepareStatement(sql);
            pstmt.setInt(1, this.getNumero());

            int conteo = pstmt.executeUpdate();
            return conteo > 0;
        }
        catch (SQLException e){
            System.err.println("Error! " + e.getMessage());
            return false;
        }
    }
}
