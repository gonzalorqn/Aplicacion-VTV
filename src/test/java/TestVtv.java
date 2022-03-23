import junit.framework.TestCase;

public class TestVtv extends TestCase {
    private Conexion conexion;

    public void escenario() {
        conexion = new Conexion();
    }

    public void testConectar(){
        escenario();
        assertNotNull(conexion.conectar());
    }

    public void testBuscarAuto(){
        escenario();
        assertNotNull(Automovil.buscarAutomovil(conexion, "AAA 111"));
    }

    public void testBuscarAutoInexistente(){
        escenario();
        assertNull(Automovil.buscarAutomovil(conexion, ""));
    }

    public void testAgregarAuto(){
        escenario();
        Automovil auto = new Automovil("a", "a", "a", "a");
        assertEquals(true, auto.agregarAutomovil(conexion));
        auto.borrarAutomovil(conexion);
    }

    public void testBorrarAuto(){
        escenario();
        Automovil auto = new Automovil("a", "a", "a", "a");
        auto.agregarAutomovil(conexion);
        assertEquals(true, auto.borrarAutomovil(conexion));
    }

    public void testBorrarAutoInexistente(){
        escenario();
        Automovil auto = new Automovil("a", "a", "a", "a");
        assertEquals(false, auto.borrarAutomovil(conexion));
    }

    public void testModificarAuto(){
        escenario();
        Automovil auto = new Automovil("a", "a", "a", "a");
        auto.agregarAutomovil(conexion);
        assertEquals(true, auto.modificarAutomovil(conexion, "b", "b", "b", null));
        auto.borrarAutomovil(conexion);
    }

    public void testModificarAutoInexistente(){
        escenario();
        Automovil auto = new Automovil("a", "a", "a", "a");
        assertEquals(false, auto.modificarAutomovil(conexion, "b", "b", "b", null));
    }

    public void testBuscarInspeccion(){
        escenario();
        assertNotNull(Inspeccion.buscarInspeccion(conexion, "AAA 111"));
    }

    public void testBuscarInspeccionInexistente(){
        escenario();
        assertNull(Inspeccion.buscarInspeccion(conexion, ""));
    }

    public void testAgregarInspeccion(){
        escenario();
        Inspeccion inspeccion = new Inspeccion(2, "a", "a", false, "AAA 111");
        assertEquals(true, inspeccion.agregarInspeccion(conexion));
        inspeccion.borrarInspeccion(conexion);
    }

    public void testBorrarInspeccion(){
        escenario();
        Inspeccion inspeccion = new Inspeccion(2, "a", "a", false, "AAA 111");
        inspeccion.agregarInspeccion(conexion);
        assertEquals(true, inspeccion.borrarInspeccion(conexion));
    }

    public void testBorrarInspeccionInexistente(){
        escenario();
        Inspeccion inspeccion = new Inspeccion(2, "a", "a", false, "AAA 111");
        assertEquals(false, inspeccion.borrarInspeccion(conexion));
    }

    public void testModificarInspeccion(){
        escenario();
        Inspeccion inspeccion = new Inspeccion(2, "a", "a", false, "AAA 111");
        inspeccion.agregarInspeccion(conexion);
        assertEquals(true, inspeccion.modificarInspeccion(conexion, "b", "b", true));
        inspeccion.borrarInspeccion(conexion);
    }

    public void testModificarInspeccionInexistente(){
        escenario();
        Inspeccion inspeccion = new Inspeccion(2, "a", "a", false, "AAA 111");
        assertEquals(false, inspeccion.modificarInspeccion(conexion, "b", "b", true));
    }

    public void testBuscarInspector(){
        escenario();
        assertNotNull(Inspector.buscarInspector(conexion, "Juan Perez"));
    }

    public void testBuscarInspectorInexistente(){
        escenario();
        assertNull(Inspector.buscarInspector(conexion, ""));
    }

    public void testAgregarInspector(){
        escenario();
        Inspector inspector = new Inspector("a");
        assertEquals(true, inspector.agregarInspector(conexion));
        Inspector aux = Inspector.buscarInspector(conexion, "a");
        aux.borrarInspector(conexion);
    }

    public void testBorrarInspector(){
        escenario();
        Inspector inspector = new Inspector("a");
        inspector.agregarInspector(conexion);
        Inspector aux = Inspector.buscarInspector(conexion, "a");
        assertEquals(true, aux.borrarInspector(conexion));
    }

    public void testBorrarInspectorInexistente(){
        escenario();
        Inspector inspector = new Inspector("a");
        assertEquals(false, inspector.borrarInspector(conexion));
    }

    public void testModificarInspector(){
        escenario();
        Inspector inspector = new Inspector("a");
        inspector.agregarInspector(conexion);
        Inspector aux = Inspector.buscarInspector(conexion, "a");
        assertEquals(true, aux.modificarInspector(conexion, "b"));
        aux.borrarInspector(conexion);
    }

    public void testModificarInspectorInexistente(){
        escenario();
        Inspector inspector = new Inspector("a");
        assertEquals(false, inspector.modificarInspector(conexion, "b"));
    }
}