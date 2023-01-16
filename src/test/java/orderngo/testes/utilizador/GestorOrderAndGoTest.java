package orderngo.testes.utilizador;

import orderngo.testes.basedados.TestesComBD;
import orderngo.basedados.ConectorBD;

import orderngo.utilizador.GestorOrderAndGo;

import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

/**
 *
 * @author grupo1
 */
public class GestorOrderAndGoTest extends TestesComBD
{
    private GestorOrderAndGo instance;
    
    @BeforeEach
    public void init()
    {
        instance = new GestorOrderAndGo("teste@gong.com", "teste", "111111111", "morada teste", 69);
    }
    
    //<editor-fold defaultstate="collapsed" desc="testCamposFinal">
    /**
     * Test of constructor and getNrEmpregado method, of class GestorOrderAndGo.
     */
    @Test
    @SuppressWarnings("ThrowableResultIgnored")
    public void testGetNrEmpregado()
    {
        // nrEmpregado valido
        int expResult = 69;
        int result = instance.getNrEmpregado();
        assertEquals(expResult, result);

        // nrEmpregado invalido
        assertThrows(IllegalArgumentException.class, () -> {
            int nrEmpregado = 0;
            instance = new GestorOrderAndGo("teste@gong.com", "teste", "111111111", "morada teste", nrEmpregado);
        });
    }
    //</editor-fold>
    
    
    //<editor-fold defaultstate="collapsed" desc="testSave/Delete">
    private boolean existeGestorEspecifico(GestorOrderAndGo instance, boolean visivel) throws SQLException
    {
        var cbd = ConectorBD.getInstance();
        
        var ps = cbd.prepareStatement("SELECT COUNT(nome) = 1 AS existe FROM gestorog WHERE email = ? AND nome = ? AND morada = ? AND telemovel = ? AND nrEmpregado = ? AND visivel = ?");
        
        ps.setString(1, instance.getEmail());
        ps.setString(2, instance.getNome());
        ps.setString(3, instance.getMorada());
        ps.setString(4, instance.getTelemovel());
        ps.setInt(5, instance.getNrEmpregado());
        ps.setBoolean(6, visivel);
        
        try(var result = cbd.executePreparedQuery(ps))
        {
            if (!result.next())
                return false;
            
            return result.getBoolean("existe");
        }
    }
    
    private void removerGestor(GestorOrderAndGo instance) throws SQLException
    {
        var cbd = ConectorBD.getInstance();
        var ps = cbd.prepareStatement("DELETE FROM gestorog WHERE email = ?");

        ps.setString(1, instance.getEmail());
        
        cbd.executePreparedUpdate(ps);
    }
    
    /**
     * Test of save and delete methods, of class GestorOrderAndGo.
     */
    @Test
    public void testSaveDelete() throws SQLException
    {
        instance.setPassword("teste".toCharArray());
        
        // save (insert)
        instance.save();
        assertTrue(existeGestorEspecifico(instance, true));
        
        
        // delete
        instance.delete();
        assertTrue(existeGestorEspecifico(instance, false));
        
        
        // save (update)
        instance.setMorada("morada altera");
        instance.setTelemovel("999999999");
        instance.setPassword("altera".toCharArray());
        instance.save();
        
        assertTrue(existeGestorEspecifico(instance, true));
        removerGestor(instance);
    }
    
    /**
     * Test of save and delete methods, of class GestorOrderAndGo.
     */
    @Test
    @SuppressWarnings("ThrowableResultIgnored")
    public void testSave_semPassword() throws SQLException
    {
        // save (sem password)
        assertThrows(IllegalStateException.class, () -> {    
            instance.save();
        });
    }
    //</editor-fold>
    
    
    //<editor-fold defaultstate="collapsed" desc="testEquals">
    /**
     * Test of equals method, of class GestorOrderAndGo.
     */
    @Test
    public void testEquals() 
    {
        // equals
        EqualsVerifier.simple().forClass(GestorOrderAndGo.class)
            .suppress(
                Warning.NULL_FIELDS, 
                Warning.STRICT_HASHCODE, 
                Warning.ALL_FIELDS_SHOULD_BE_USED
            )
            .verify();
    }
    //</editor-fold>
    
    
    //<editor-fold defaultstate="collapsed" desc="testAll">
    /**
     * Test of all method, of class GestorOrderAndGo.
     */
    @Test
    public void testAll_apenasVisivel() throws SQLException
    {
        // all (apenasVisivel = true)
        GestorOrderAndGo[] expResult = new GestorOrderAndGo[]{
            new GestorOrderAndGo("visivel@gong.com", "visivel", "111111111", "morada visivel", 1)
        };
        GestorOrderAndGo[] result = GestorOrderAndGo.all();
        
        assertArrayEquals(expResult, result);
    }

    /**
     * Test of all method, of class GestorOrderAndGo.
     */
    @Test
    public void testAll() throws SQLException
    {
        // all (apenasVisivel = false)
        GestorOrderAndGo[] expResult = new GestorOrderAndGo[]{
            new GestorOrderAndGo("visivel@gong.com", "visivel", "111111111", "morada visivel", 1),
            new GestorOrderAndGo("invisivel@gong.com", "invisivel", "000000000", "morada invisivel", 2)
        };
        GestorOrderAndGo[] result = GestorOrderAndGo.all(false);
        
        assertArrayEquals(expResult, result);
    }
    //</editor-fold>

    
    //<editor-fold defaultstate="collapsed" desc="testGetGestor">
    /**
     * Test of getGestor method, of class GestorOrderAndGo.
     */
    @Test
    @SuppressWarnings("ThrowableResultIgnored")
    public void testGetGestor_naoExiste() throws SQLException
    {
        // getGestor (gestor nao existe)
        String email = "invisivel@gong.com";
        
        assertThrows(IllegalArgumentException.class, () -> {
            GestorOrderAndGo.getGestor(email);
        });
    }

    /**
     * Test of getGestor method, of class GestorOrderAndGo.
     */
    @Test
    public void testGetGestor_existe() throws SQLException
    {
        // getGestor (gestor existe)
        String email = "invisivel@gong.com";
        GestorOrderAndGo expResult = new GestorOrderAndGo("invisivel@gong.com", "invisivel", "000000000", "morada invisivel", 2);
        
        GestorOrderAndGo result = GestorOrderAndGo.getGestor(email, false);
        assertEquals(expResult, result);
    }
    //</editor-fold>
    

    //<editor-fold defaultstate="collapsed" desc="testValidarCredenciais">
    /**
     * Test of validarCredenciais method, of class GestorOrderAndGo.
     */
    @Test
    @SuppressWarnings("ThrowableResultIgnored")
    public void testValidarCredenciais_naoExiste() throws SQLException
    {
        // validarCredenciais (nao existe)
        String email = "invisivel@gong.com";
        char[] password = "invisivel".toCharArray();
        
        assertFalse(GestorOrderAndGo.validarCredenciais(email, password));
    }
    
    /**
     * Test of validarCredenciais method, of class GestorOrderAndGo.
     */
    @Test
    public void testValidarCredenciais_existe() throws SQLException
    {
        // validarCredenciais (existe)
        String email = "visivel@gong.com";
        char[] password = "visivel".toCharArray();
        
        assertTrue(GestorOrderAndGo.validarCredenciais(email, password));
    }
    //</editor-fold>
}
