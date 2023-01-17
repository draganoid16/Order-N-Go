package orderngo.testes.utilizador;

import orderngo.testes.basedados.TestesComBD;

import orderngo.utilizador.Cliente;

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
public class ClienteTest extends TestesComBD
{
    private Cliente instance;
    
    @BeforeEach
    public void init()
    {
        instance = new Cliente("teste@cli.com", "teste", "111111111", "morada teste", "696969696");
    }

    //<editor-fold defaultstate="collapsed" desc="testCamposFinal">
    /**
     * Test of constructor and getNif method, of class Cliente.
     */
    @Test
    @SuppressWarnings("ThrowableResultIgnored")
    public void testGetNif()
    {
        // nif valido
        String expResult = "696969696";
        String result = instance.getNif();
        
        assertEquals(expResult, result);
        
        // nif invalido (null)
        assertThrows(IllegalArgumentException.class, () -> {
            String nif = null;
            instance = new Cliente("teste@cli.com", "teste", "111111111", "morada teste", nif);
        });
        
        // nif invalido (nao corresponde a nif)
        assertThrows(IllegalArgumentException.class, () -> {
            String nif = "a1a1a1a1a";
            instance = new Cliente("teste@cli.com", "teste", "111111111", "morada teste", nif);
        });
    }
    //</editor-fold>

    
    //<editor-fold defaultstate="collapsed" desc="testEquals">
    /**
     * Test of equals method, of class Cliente.
     */
    @Test
    public void testEquals() 
    {
        // equals
        EqualsVerifier.simple().forClass(Cliente.class)
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
     * Test of all method, of class Cliente.
     */
    @Test
    public void testAll_apenasVisivel() throws SQLException
    {
        // all (apenasVisivel = true)
        Cliente[] expResult = new Cliente[]{
            new Cliente("visivel@cli.com", "visivel", "111111111", "morada visivel", "111111111")
        };
        Cliente[] result = Cliente.all();
        
        assertArrayEquals(expResult, result);
    }

    /**
     * Test of all method, of class Cliente.
     */
    @Test
    public void testAll() throws SQLException
    {
        // all (apenasVisivel = false)
        Cliente[] expResult = new Cliente[]{
            new Cliente("visivel@cli.com", "visivel", "111111111", "morada visivel", "111111111"),
            new Cliente("invisivel@cli.com", "invisivel", "000000000", "morada invisivel", "000000000")
        };
        Cliente[] result = Cliente.all(false);
        
        assertArrayEquals(expResult, result);
    }
    //</editor-fold>
    

    //<editor-fold defaultstate="collapsed" desc="testGetGestor">
    /**
     * Test of getCliente method, of class Cliente.
     */
    @Test
    @SuppressWarnings("ThrowableResultIgnored")
    public void testGetCliente_naoExiste() throws SQLException
    {
        // getCliente (gestor nao existe)
        String email = "invisivel@cli.com";
        
        assertThrows(IllegalArgumentException.class, () -> {
            Cliente.getCliente(email);
        });
    }

    /**
     * Test of getCliente method, of class Cliente.
     */
    @Test
    public void testGetCliente_existe() throws SQLException
    {
        // getGestor (gestor existe)
        String email = "invisivel@cli.com";
        Cliente expResult = new Cliente("invisivel@cli.com", "invisivel", "000000000", "morada invisivel", "000000000");
        
        Cliente result = Cliente.getCliente(email, false);
        assertEquals(expResult, result);
    }
    //</editor-fold>
}
