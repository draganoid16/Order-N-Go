package orderngo.testes.utilizador;

import orderngo.testes.basedados.TestesComBD;
import orderngo.basedados.ConectorBD;

import orderngo.utilizador.Restaurante;
import orderngo.cardapio.Cardapio;
import orderngo.utils.ImagemUtils;

import java.awt.image.BufferedImage;
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
public class RestauranteTest extends TestesComBD
{
    private Restaurante instance;
    
    @BeforeEach
    public void init()
    {
        instance = new Restaurante("teste@rest.com", "teste", "111111111", "morada teste");
    }

    
    //<editor-fold defaultstate="collapsed" desc="testCamposFinal">
    /**
     * Test of getCardapio method, of class Restaurante.
     */
    @Test
    public void testGetCardapio()
    {
        // getCardapio
        Cardapio expResult = new Cardapio(instance);
        
        Cardapio result = instance.getCardapio();
        assertEquals(expResult, result);
    }
    //</editor-fold>
    
    
    //<editor-fold defaultstate="collapsed" desc="testSet">
    /**
     * Test of setImagem method, of class Restaurante.
     */
    @Test
    public void testSetImagem()
    {
        // sem imagem (null)
        BufferedImage expResult = null;
        BufferedImage result = instance.getImagem();
        assertEquals(expResult, result);
        
        // com imagem
        BufferedImage imagem = ImagemUtils.createImage(69, 69);
        expResult = imagem;
        
        instance.setImagem(imagem);
        result = instance.getImagem();
        assertEquals(expResult, result);
    }
    //</editor-fold>
    
    
    //<editor-fold defaultstate="collapsed" desc="testSave/Delete">
    private boolean existeRestauranteEspecifico(Restaurante instance, boolean visivel) throws SQLException
    {
        var cbd = ConectorBD.getInstance();
        
        var ps = cbd.prepareStatement("SELECT COUNT(nome) = 1 AS existe FROM restaurante WHERE email = ? AND nome = ? AND morada = ? AND telemovel = ? AND visivel = ?");
        
        ps.setString(1, instance.getEmail());
        ps.setString(2, instance.getNome());
        ps.setString(3, instance.getMorada());
        ps.setString(4, instance.getTelemovel());
        ps.setBoolean(5, visivel);
        
        try(var result = cbd.executePreparedQuery(ps))
        {
            if (!result.next())
                return false;
            
            return result.getBoolean("existe");
        }
    }
    
    private void removerRestaurante(Restaurante instance) throws SQLException
    {
        var cbd = ConectorBD.getInstance();
        var ps = cbd.prepareStatement("DELETE FROM restaurante WHERE email = ?");

        ps.setString(1, instance.getEmail());
        
        cbd.executePreparedUpdate(ps);
    }
    
    /**
     * Test of save and delete methods, of class Restaurante.
     */
    @Test
    public void testSaveDelete() throws SQLException
    {
        instance.setPassword("teste".toCharArray());
        
        // save (insert)
        instance.save();
        assertTrue(existeRestauranteEspecifico(instance, true));
        
        
        // delete
        instance.delete();
        assertTrue(existeRestauranteEspecifico(instance, false));
        
        
        // save (update)
        instance.setMorada("morada altera");
        instance.setTelemovel("999999999");
        instance.setImagem(ImagemUtils.createImage(69, 69));
        instance.setPassword("altera".toCharArray());
        instance.save();
        
        assertTrue(existeRestauranteEspecifico(instance, true));
        removerRestaurante(instance);
    }
    
    /**
     * Test of save and delete methods, of class Restaurante.
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
     * Test of equals method, of class Restaurante.
     */
    @Test
    public void testEquals() 
    {
        // equals
        Restaurante r1 = new Restaurante("rest1@rest.com", "Restaurante 1", "912345678", "Primeira Morada");
        Restaurante r2 = new Restaurante("rest2@rest.com", "Restaurante 2", "923456789", "Segunda Morada");
        
        BufferedImage b1 = ImagemUtils.createImage(100, 100);
        BufferedImage b2 = ImagemUtils.createImage(200, 200);
        
        EqualsVerifier.simple().forClass(Restaurante.class)
            .suppress(
                Warning.NULL_FIELDS, 
                Warning.STRICT_HASHCODE, 
                Warning.ALL_FIELDS_SHOULD_BE_USED
            )
            .withPrefabValues(Restaurante.class, r1, r2)
            .withPrefabValues(BufferedImage.class, b1, b2)
            .verify();
    }
    //</editor-fold>
    
    
    //<editor-fold defaultstate="collapsed" desc="testAll">
    /**
     * Test of all method, of class Restaurante.
     */
    @Test
    public void testAll_apenasVisivel() throws SQLException
    {
        // from (apenasVisivel = true)
        Restaurante[] expResult = new Restaurante[]{
            new Restaurante("visivel@rest.com", "visivel", "111111111", "morada visivel")
        };
        Restaurante[] result = Restaurante.all();
        
        assertArrayEquals(expResult, result);
    }

    /**
     * Test of all method, of class Restaurante.
     */
    @Test
    public void testAll() throws SQLException
    {
        // from (apenasVisivel = true)
        Restaurante[] expResult = new Restaurante[]{
            new Restaurante("visivel@rest.com", "visivel", "111111111", "morada visivel"),
            new Restaurante("invisivel@rest.com", "invisivel", "000000000", "morada invisivel")
        };
        Restaurante[] result = Restaurante.all(false);
        
        assertArrayEquals(expResult, result);
    }
    //</editor-fold>
    

    //<editor-fold defaultstate="collapsed" desc="testGetRestaurante">
    /**
     * Test of getRestaurante method, of class Restaurante.
     */
    @Test
    @SuppressWarnings("ThrowableResultIgnored")
    public void testGetRestaurante_naoExiste() throws SQLException
    {
        // getRestaurate (restaurate nao existe)
        String email = "invisivel@rest.com";
        
        assertThrows(IllegalArgumentException.class, () -> {
            Restaurante.getRestaurante(email);
        });
    }

    /**
     * Test of getRestaurante method, of class Restaurante.
     */
    @Test
    public void testGetRestaurante_existe() throws SQLException
    {
        // getRestaurate (restaurate existe)
        String email = "invisivel@rest.com";
        Restaurante expResult = new Restaurante("invisivel@rest.com", "invisivel", "000000000", "morada invisivel");;
        
        Restaurante result = Restaurante.getRestaurante(email, false);
        assertEquals(expResult, result);
    }
    //</editor-fold>

    
    //<editor-fold defaultstate="collapsed" desc="testValidarCredenciais">
    /**
     * Test of validarCredenciais method, of class Restaurante.
     */
    @Test
    @SuppressWarnings("ThrowableResultIgnored")
    public void testValidarCredenciais_naoExiste() throws SQLException
    {
        // validarCredenciais (nao existe)
        String email = "invisivel@rest.com";
        char[] password = "invisivel".toCharArray();
        
        assertFalse(Restaurante.validarCredenciais(email, password));
    }
    
    /**
     * Test of validarCredenciais method, of class Restaurante.
     */
    @Test
    public void testValidarCredenciais_existe() throws SQLException
    {
        // validarCredenciais (existe)
        String email = "visivel@rest.com";
        char[] password = "visivel".toCharArray();
        
        assertTrue(Restaurante.validarCredenciais(email, password));
    }
    //</editor-fold>
}
