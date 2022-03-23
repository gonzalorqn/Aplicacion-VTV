import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Scanner;

public class AplicacionVTV {
    private static final Scanner entrada = new Scanner(System.in);

    public static void main(String[] args) {
        Conexion conexion = new Conexion();

        AplicacionVTV.ejecutarConsola(conexion);
    }

    private static void ejecutarConsola(Conexion conexion) {
        int opcion = -1;
        int opcion2 = -1;

        while(opcion != 0){
            try{
                System.out.println("Elija opcion:\n" +
                                    "1. Mostrar autos inspeccionados hoy.\n" +
                                    "2. Mostrar autos inspeccionados la ultima semana.\n" +
                                    "3. Mostrar inspectores y sus inspecciones realizadas los ultimos 3 dias.\n" +
                                    "4. Buscar propietario y mostrar sus autos e inspecciones realizadas.\n" +
                                    "5. Comprobar vencimiento de obleas.\n" +
                                    "6. Mostrar autos aptos, condicionales y rechazados.\n" +
                                    "7. Alta, modificacion y borrado de autos.\n" +
                                    "8. Alta, modificacion y borrado de inspectores.\n" +
                                    "9. Alta, modificacion y borrado de inspecciones.\n" +
                                    "0. Salir.");
                opcion = Integer.parseInt(entrada.nextLine());

                switch(opcion){
                    case 1:
                        AplicacionVTV.mostrarAutosHoy(conexion);
                        break;
                    case 2:
                        AplicacionVTV.mostrarAutosSemana(conexion);
                        break;
                    case 3:
                        AplicacionVTV.mostrarInspectoresInspecciones(conexion);
                        break;
                    case 4:
                        AplicacionVTV.mostrarAutosPropietario(conexion);
                        break;
                    case 5:
                        AplicacionVTV.comprobarVencimientos(conexion);
                        break;
                    case 6:
                        while(opcion2 != 0){
                            try{
                                System.out.println("Elija opcion:\n" +
                                        "1. Mostrar autos aptos.\n" +
                                        "2. Mostrar autos condicionales.\n" +
                                        "3. Mostrar autos rechazados.\n" +
                                        "0. Volver.");
                                opcion2 = Integer.parseInt(entrada.nextLine());

                                switch(opcion2){
                                    case 1:
                                        AplicacionVTV.mostrarAutosSegunEstado(conexion, "apto");
                                        break;
                                    case 2:
                                        AplicacionVTV.mostrarAutosSegunEstado(conexion, "condicional");
                                        break;
                                    case 3:
                                        AplicacionVTV.mostrarAutosSegunEstado(conexion, "rechazado");
                                        break;
                                    case 0:
                                        break;
                                    default:
                                        System.out.println("Opcion no reconocida");
                                        break;
                                }
                                System.out.println("\n");
                            }
                            catch (Exception e){
                                System.out.println("Error!\n");
                            }
                        }
                        opcion2 = -1;
                        break;
                    case 7:
                        while(opcion2 != 0){
                            try{
                                System.out.println("Elija opcion:\n" +
                                        "1. Agregar auto.\n" +
                                        "2. Modificar auto.\n" +
                                        "3. Borrar auto.\n" +
                                        "0. Volver.");
                                opcion2 = Integer.parseInt(entrada.nextLine());

                                switch(opcion2){
                                    case 1:
                                        AplicacionVTV.altaAuto(conexion);
                                        break;
                                    case 2:
                                        AplicacionVTV.modificacionAuto(conexion);
                                        break;
                                    case 3:
                                        AplicacionVTV.bajaAuto(conexion);
                                        break;
                                    case 0:
                                        break;
                                    default:
                                        System.out.println("Opcion no reconocida");
                                        break;
                                }
                                System.out.println("\n");
                            }
                            catch (Exception e){
                                System.out.println("Error!\n");
                            }
                        }
                        opcion2 = -1;
                        break;
                    case 8:
                        while(opcion2 != 0){
                            try{
                                System.out.println("Elija opcion:\n" +
                                        "1. Agregar inspector.\n" +
                                        "2. Modificar inspector.\n" +
                                        "3. Borrar inspector.\n" +
                                        "0. Volver.");
                                opcion2 = Integer.parseInt(entrada.nextLine());

                                switch(opcion2){
                                    case 1:
                                        AplicacionVTV.altaInspector(conexion);
                                        break;
                                    case 2:
                                        AplicacionVTV.modificacionInspector(conexion);
                                        break;
                                    case 3:
                                        AplicacionVTV.bajaInspector(conexion);
                                        break;
                                    case 0:
                                        break;
                                    default:
                                        System.out.println("Opcion no reconocida");
                                        break;
                                }
                                System.out.println("\n");
                            }
                            catch (Exception e){
                                System.out.println("Error!\n");
                            }
                        }
                        opcion2 = -1;
                        break;
                    case 9:
                        while(opcion2 != 0){
                            try{
                                System.out.println("Elija opcion:\n" +
                                        "1. Agregar inspeccion.\n" +
                                        "2. Modificar inspeccion.\n" +
                                        "3. Borrar inspeccion.\n" +
                                        "0. Volver.");
                                opcion2 = Integer.parseInt(entrada.nextLine());

                                switch(opcion2){
                                    case 1:
                                        AplicacionVTV.altaInspeccion(conexion);
                                        break;
                                    case 2:
                                        AplicacionVTV.modificacionInspeccion(conexion);
                                        break;
                                    case 3:
                                        AplicacionVTV.bajaInspeccion(conexion);
                                        break;
                                    case 0:
                                        break;
                                    default:
                                        System.out.println("Opcion no reconocida");
                                        break;
                                }
                                System.out.println("\n");
                            }
                            catch (Exception e){
                                System.out.println("Error!\n");
                            }
                        }
                        opcion2 = -1;
                        break;
                    case 0:
                        System.out.println("Adios!");
                        break;
                    default:
                        System.out.println("Opcion no reconocida");
                        break;
                }
                System.out.println("\n");
            }
            catch(Exception e){
                System.out.println("Error!\n");
            }
        }
    }

    public static void mostrarAutosHoy(Conexion conexion){
        System.out.println("Autos inspeccionados hoy:");
        try {
            Connection cnx = conexion.conectar();

            String sql = "SELECT a.dominio, a.marca, a.modelo, a.nombrePropietario, a.fechaVencimiento FROM automoviles a, inspecciones i WHERE a.dominio = i.dominioAuto AND i.fecha = CURDATE()";

            PreparedStatement pstmt = cnx.prepareStatement(sql);
            ResultSet rst = pstmt.executeQuery();
            while (rst.next()){
                System.out.println("Dominio: " + rst.getString("dominio"));
                System.out.println("Marca: " + rst.getString("marca"));
                System.out.println("Modelo: " + rst.getString("modelo"));
                System.out.println("Propietario: " + rst.getString("nombrePropietario"));
                System.out.println("Vencimiento: " + rst.getString("fechaVencimiento"));
                System.out.println();
            }
        }
        catch (SQLException e){
            System.err.println("Error! " + e.getMessage());
        }
        System.out.println("0. Volver.");
        entrada.nextLine();
    }

    public static void mostrarAutosSemana(Conexion conexion){
        System.out.println("Autos inspeccionados la ultima semana:");
        try {
            Connection cnx = conexion.conectar();

            String sql = "SELECT a.dominio, a.marca, a.modelo, a.nombrePropietario, a.fechaVencimiento FROM automoviles a, inspecciones i WHERE a.dominio = i.dominioAuto AND i.fecha BETWEEN date_sub(now(), INTERVAL 1 WEEK) AND now()";

            PreparedStatement pstmt = cnx.prepareStatement(sql);
            ResultSet rst = pstmt.executeQuery();
            while (rst.next()){
                System.out.println("Dominio: " + rst.getString("dominio"));
                System.out.println("Marca: " + rst.getString("marca"));
                System.out.println("Modelo: " + rst.getString("modelo"));
                System.out.println("Propietario: " + rst.getString("nombrePropietario"));
                System.out.println("Vencimiento: " + rst.getString("fechaVencimiento"));
                System.out.println();
            }
        }
        catch (SQLException e){
            System.err.println("Error! " + e.getMessage());
        }
        System.out.println("0. Volver.");
        entrada.nextLine();
    }

    public static void mostrarInspectoresInspecciones(Conexion conexion){
        System.out.println("Inspectores y sus inspecciones de los ultimos 3 dias:");
        try {
            Connection cnx = conexion.conectar();

            String sql = "SELECT nombre FROM inspectores";

            PreparedStatement pstmt = cnx.prepareStatement(sql);
            ResultSet rst = pstmt.executeQuery();
            while (rst.next()){
                System.out.println("Inspector " + rst.getString("nombre") + ":");
                String sql2 = "SELECT i.numero, i.fecha, i.estado, i.exento, i.dominioAuto FROM inspectores r, inspecciones i WHERE r.nombre = i.nombreInspector AND r.nombre = ? AND i.fecha BETWEEN date_sub(now(), INTERVAL 3 DAY) AND now()";

                PreparedStatement pstmt2 = cnx.prepareStatement(sql2);
                pstmt2.setString(1, rst.getString("nombre"));
                ResultSet rst2 = pstmt2.executeQuery();
                while (rst2.next()){
                    System.out.println("Numero: " + rst2.getInt("numero"));
                    System.out.println("Fecha: " + rst2.getString("fecha"));
                    System.out.println("Estado: " + rst2.getString("estado"));
                    System.out.println("Exento: " + rst2.getBoolean("exento"));
                    System.out.println("Dominio: " + rst2.getString("dominioAuto"));
                    System.out.println();
                }
                System.out.println();
            }
        }
        catch (SQLException e){
            System.err.println("Error! " + e.getMessage());
        }
        System.out.println("0. Volver.");
        entrada.nextLine();
    }

    public static void mostrarAutosPropietario(Conexion conexion){
        System.out.print("Ingrese nombre de propietario a buscar: ");
        String nombre = entrada.nextLine();
        System.out.println("Auto/s inspeccionado/s de " + nombre + ":");
        try {
            Connection cnx = conexion.conectar();

            String sql = "SELECT * FROM automoviles a, inspecciones i WHERE a.dominio = i.dominioAuto AND a.nombrePropietario = ?";

            PreparedStatement pstmt = cnx.prepareStatement(sql);
            pstmt.setString(1, nombre);
            ResultSet rst = pstmt.executeQuery();
            while (rst.next()){
                System.out.println("Dominio: " + rst.getString("dominio"));
                System.out.println("Marca: " + rst.getString("marca"));
                System.out.println("Modelo: " + rst.getString("modelo"));
                System.out.println("Vencimiento: " + rst.getString("fechaVencimiento"));
                System.out.println("Numero de inspeccion: " + rst.getInt("numero"));
                System.out.println("Fecha de inspeccion: " + rst.getString("fecha"));
                System.out.println("Estado de inspeccion: " + rst.getString("estado"));
                System.out.println("Inspector: " + rst.getString("nombreInspector"));
                System.out.println("Exento: " + rst.getBoolean("exento"));
                System.out.println();
            }
        }
        catch (SQLException e){
            System.err.println("Error! " + e.getMessage());
        }
        System.out.println("0. Volver.");
        entrada.nextLine();
    }

    public static void comprobarVencimientos(Conexion conexion){
        System.out.println("Autos aptos:");
        try {
            Connection cnx = conexion.conectar();

            String sql = "SELECT a.dominio, i.fecha, a.fechaVencimiento FROM automoviles a, inspecciones i WHERE a.dominio = i.dominioAuto AND estado = 'apto'";

            PreparedStatement pstmt = cnx.prepareStatement(sql);
            ResultSet rst = pstmt.executeQuery();
            while (rst.next()){
                System.out.println("Dominio: " + rst.getString("dominio"));
                System.out.println("Fecha de inspeccion: " + rst.getString("fecha"));
                System.out.println("Fecha de vencimiento: " + rst.getString("fechaVencimiento"));
                System.out.println();
            }
        }
        catch (SQLException e){
            System.err.println("Error! " + e.getMessage());
        }
        System.out.println("0. Volver.");
        entrada.nextLine();
    }

    public static void mostrarAutosSegunEstado(Conexion conexion, String estado){
        if(!Objects.equals(estado, "condicional")) {
            System.out.println("Autos " + estado + "s:");
        }
        else {
            System.out.println("Autos condicionales:");
        }
        try {
            Connection cnx = conexion.conectar();

            String sql = "SELECT a.dominio, a.marca, a.modelo, a.nombrePropietario, a.fechaVencimiento FROM automoviles a, inspecciones i WHERE a.dominio = i.dominioAuto AND estado = ?";

            PreparedStatement pstmt = cnx.prepareStatement(sql);
            pstmt.setString(1, estado);
            ResultSet rst = pstmt.executeQuery();
            while (rst.next()){
                System.out.println("Dominio: " + rst.getString("dominio"));
                System.out.println("Marca: " + rst.getString("marca"));
                System.out.println("Modelo: " + rst.getString("modelo"));
                System.out.println("Propietario: " + rst.getString("nombrePropietario"));
                System.out.println("Vencimiento: " + rst.getString("fechaVencimiento"));
                System.out.println();
            }
        }
        catch (SQLException e){
            System.err.println("Error! " + e.getMessage());
        }
        System.out.println("0. Volver.");
        entrada.nextLine();
    }

    public static void altaAuto(Conexion conexion){
        Automovil auto;
        System.out.println("Agregar auto nuevo:");
        System.out.print("Ingrese dominio: ");
        String dominio = entrada.nextLine();
        System.out.print("Ingrese marca: ");
        String marca = entrada.nextLine();
        System.out.print("Ingrese modelo: ");
        String modelo = entrada.nextLine();
        System.out.print("Ingrese nombre y apellido del propietario: ");
        String nombre = entrada.nextLine();
        System.out.print("Ingrese vencimiento de oblea (formato aaaa-mm-dd o coloque 'no' si no esta apto): ");
        String fecha = entrada.nextLine();

        if(!Objects.equals(fecha, "no")) {
            auto = new Automovil(dominio, marca, modelo, nombre, fecha);
        }
        else {
            auto = new Automovil(dominio, marca, modelo, nombre);
        }

        if(auto.agregarAutomovil(conexion)){
            System.out.println("Auto agregado correctamente.");
        }
        else {
            System.out.println("No se pudo agregar el auto.");
        }
        System.out.println("0. Volver.");
        entrada.nextLine();
    }

    public static void modificacionAuto(Conexion conexion){
        Automovil auto;
        System.out.println("Modificar auto:");
        System.out.print("Ingrese dominio a modificar: ");
        String dominio = entrada.nextLine();

        auto = Automovil.buscarAutomovil(conexion, dominio);
        if(auto != null){
            System.out.println("Auto encontrado: " + auto.getDominio() + " - " + auto.getMarca() + " - " +
                    auto.getModelo() + " - " + auto.getNombrePropietario() + " - " + auto.getFechaVencimiento());

            System.out.print("Ingrese marca: ");
            String marca = entrada.nextLine();
            System.out.print("Ingrese modelo: ");
            String modelo = entrada.nextLine();
            System.out.print("Ingrese nombre y apellido del propietario: ");
            String nombre = entrada.nextLine();
            System.out.print("Ingrese vencimiento de oblea (formato aaaa-mm-dd o coloque 'no' si no esta apto): ");
            String fecha = entrada.nextLine();

            if(auto.modificarAutomovil(conexion, marca, modelo, nombre, fecha)){
                System.out.println("Auto modificado correctamente.");
            }
            else {
                System.out.println("No se pudo modificar el auto.");
            }
        }
        else {
            System.out.println("Automovil no encontrado.");
        }
        System.out.println("0. Volver.");
        entrada.nextLine();
    }

    public static void bajaAuto(Conexion conexion){
        Automovil auto;
        System.out.println("Borrar auto:");
        System.out.print("Ingrese dominio a borrar: ");
        String dominio = entrada.nextLine();

        auto = Automovil.buscarAutomovil(conexion, dominio);
        if(auto != null){
            System.out.println("Auto encontrado: " + auto.getDominio() + " - " + auto.getMarca() + " - " +
                    auto.getModelo() + " - " + auto.getNombrePropietario() + " - " + auto.getFechaVencimiento());

            System.out.print("Esta seguro que desea borrarlo? (si/no): ");
            String conf = entrada.nextLine();

            if(Objects.equals(conf, "si")){
                if(auto.borrarAutomovil(conexion)){
                    System.out.println("Auto borrado correctamente.");
                }
                else {
                    System.out.println("No se pudo borrar el auto.");
                }
            }
            else if(Objects.equals(conf, "no")){
                System.out.println("Se cancelo el borrado del auto.");
            }
            else {
                System.out.println("Respuesta invalida.");
            }
        }
        else {
            System.out.println("Automovil no encontrado.");
        }
        System.out.println("0. Volver.");
        entrada.nextLine();
    }

    public static void altaInspector(Conexion conexion){
        Inspector inspector;
        System.out.println("Agregar inspector nuevo:");
        System.out.print("Ingrese nombre y apellido: ");
        String nombre = entrada.nextLine();

        inspector = new Inspector(nombre);

        if(inspector.agregarInspector(conexion)){
            System.out.println("Inspector agregado correctamente.");
        }
        else {
            System.out.println("No se pudo agregar el inspector.");
        }
        System.out.println("0. Volver.");
        entrada.nextLine();
    }

    public static void modificacionInspector(Conexion conexion){
        Inspector inspector;
        System.out.println("Modificar inspector:");
        System.out.print("Ingrese nombre y apellido a modificar: ");
        String nombre = entrada.nextLine();

        inspector = Inspector.buscarInspector(conexion, nombre);
        if(inspector != null){
            System.out.println("Inspector encontrado: " + inspector.getNombre());

            System.out.print("Ingrese nombre y apellido: ");
            String nombreNuevo = entrada.nextLine();

            if(inspector.modificarInspector(conexion, nombreNuevo)){
                System.out.println("Inspector modificado correctamente.");
            }
            else {
                System.out.println("No se pudo modificar el inspector.");
            }
        }
        else {
            System.out.println("Inspector no encontrado.");
        }
        System.out.println("0. Volver.");
        entrada.nextLine();
    }

    public static void bajaInspector(Conexion conexion){
        Inspector inspector;
        System.out.println("Borrar inspector:");
        System.out.print("Ingrese nombre y apellido a borrar: ");
        String nombre = entrada.nextLine();

        inspector = Inspector.buscarInspector(conexion, nombre);
        if(inspector != null){
            System.out.println("Inspector encontrado: " + inspector.getNombre());

            System.out.print("Esta seguro que desea borrarlo? (si/no): ");
            String conf = entrada.nextLine();

            if(Objects.equals(conf, "si")){
                if(inspector.borrarInspector(conexion)){
                    System.out.println("Inspector borrado correctamente.");
                }
                else {
                    System.out.println("No se pudo borrar el inspector.");
                }
            }
            else if(Objects.equals(conf, "no")){
                System.out.println("Se cancelo el borrado del inspector.");
            }
            else {
                System.out.println("Respuesta invalida.");
            }
        }
        else {
            System.out.println("Inspector no encontrado.");
        }
        System.out.println("0. Volver.");
        entrada.nextLine();
    }

    public static void altaInspeccion(Conexion conexion){
        Inspeccion inspeccion;
        boolean exento;
        System.out.println("Agregar inspeccion nueva:");
        System.out.print("Ingrese numero: ");
        int numero = Integer.parseInt(entrada.nextLine());
        System.out.print("Ingrese fecha (formato aaaa-mm-dd o coloque 'no' si es de hoy): ");
        String fecha = entrada.nextLine();
        System.out.print("Ingrese estado (apto, rechazado o condicional): ");
        String estado = entrada.nextLine();
        System.out.print("Ingrese nombre y apellido del inspector: ");
        String nombre = entrada.nextLine();
        System.out.print("Ingrese si el propietario esta exento (si/no , no por defecto): ");
        String exentoString = entrada.nextLine();
        System.out.print("Ingrese dominio del auto: ");
        String dominio = entrada.nextLine();

        if(Objects.equals(exentoString, "si")){
            exento = true;
        }
        else {
            exento = false;
        }

        if(!Objects.equals(fecha, "no")) {
            inspeccion = new Inspeccion(numero, estado, nombre, exento, dominio, fecha);
        }
        else {
            inspeccion = new Inspeccion(numero, estado, nombre, exento, dominio);
        }

        if(inspeccion.agregarInspeccion(conexion)){
            System.out.println("Inspeccion agregada correctamente.");
        }
        else {
            System.out.println("No se pudo agregar la inspeccion.");
        }
        System.out.println("0. Volver.");
        entrada.nextLine();
    }

    public static void modificacionInspeccion(Conexion conexion){
        Inspeccion inspeccion;
        boolean exento;
        System.out.println("Modificar inspeccion:");
        System.out.print("Ingrese dominio a modificar: ");
        String dominio = entrada.nextLine();

        inspeccion = Inspeccion.buscarInspeccion(conexion, dominio);
        if(inspeccion != null){
            System.out.println("Inspeccion encontrada: " + inspeccion.getNumero() + " - " + inspeccion.getFecha() + " - " +
                    inspeccion.getEstado() + " - " + inspeccion.getNombreInspector() + " - " +
                    inspeccion.getExento() + " - " + inspeccion.getDominioAuto());

            System.out.print("Ingrese estado (apto, rechazado o condicional): ");
            String estado = entrada.nextLine();
            System.out.print("Ingrese nombre y apellido del inspector: ");
            String nombre = entrada.nextLine();
            System.out.print("Ingrese si el propietario esta exento (si/no , no por defecto): ");
            String exentoString = entrada.nextLine();

            if(Objects.equals(exentoString, "si")){
                exento = true;
            }
            else {
                exento = false;
            }

            if(inspeccion.modificarInspeccion(conexion, estado, nombre, exento)){
                System.out.println("Inspeccion modificada correctamente.");
            }
            else {
                System.out.println("No se pudo modificar la inspeccion.");
            }
        }
        else {
            System.out.println("Inspeccion no encontrada.");
        }
        System.out.println("0. Volver.");
        entrada.nextLine();
    }

    public static void bajaInspeccion(Conexion conexion){
        Inspeccion inspeccion;
        System.out.println("Borrar inspeccion:");
        System.out.print("Ingrese dominio a borrar: ");
        String dominio = entrada.nextLine();

        inspeccion = Inspeccion.buscarInspeccion(conexion, dominio);
        if(inspeccion != null){
            System.out.println("Inspeccion encontrada: " + inspeccion.getNumero() + " - " + inspeccion.getFecha() + " - " +
                    inspeccion.getEstado() + " - " + inspeccion.getNombreInspector() + " - " +
                    inspeccion.getExento() + " - " + inspeccion.getDominioAuto());

            System.out.print("Esta seguro que desea borrarla? (si/no): ");
            String conf = entrada.nextLine();

            if(Objects.equals(conf, "si")){
                if(inspeccion.borrarInspeccion(conexion)){
                    System.out.println("Inspeccion borrada correctamente.");
                }
                else {
                    System.out.println("No se pudo borrar la inspeccion.");
                }
            }
            else if(Objects.equals(conf, "no")){
                System.out.println("Se cancelo el borrado de la inspeccion.");
            }
            else {
                System.out.println("Respuesta invalida.");
            }
        }
        else {
            System.out.println("Inspeccion no encontrada.");
        }
        System.out.println("0. Volver.");
        entrada.nextLine();
    }
}
