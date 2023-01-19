package orderngo.testes.cardapio;

import orderngo.testes.basedados.TestesComBD;
import orderngo.basedados.ConectorBD;

import orderngo.cardapio.Prato;
import orderngo.cardapio.Prato.TipoPrato;
import orderngo.utilizador.Restaurante;
import orderngo.utils.ImagemUtils;

import java.awt.image.BufferedImage;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;

import java.sql.SQLException;
import orderngo.exception.ItemCardapioNotFoundException;
import orderngo.exception.RestauranteNotFoundException;

/**
 *
 * @author grupo1
 */
public class PratoTest extends TestesComBD
{
    private Prato instance;
    
    @BeforeEach
    public void init()
    {
        Restaurante rest = new Restaurante("visivel@rest.com", "visivel", "111111111", "morada visivel");
        instance = new Prato(rest, "prato teste", "detalhes teste", 69, TipoPrato.CARNE, "teste");
    }
        
    
    //<editor-fold defaultstate="collapsed" desc="testSet">
    /**
     * Test of setTipoPrato method, of class Prato.
     */
    @Test
    @SuppressWarnings("ThrowableResultIgnored")
    public void testSetTipoPrato()
    {
        // TipoPrato invalido (null)
        assertThrows(IllegalArgumentException.class, () -> {
            TipoPrato tipoPrato = null;
            instance.setTipoPrato(tipoPrato);
        });
        
        
        // TipoPrato valido
        TipoPrato tipoPrato = TipoPrato.PEIXE;
        instance.setTipoPrato(tipoPrato);
        assertEquals(tipoPrato, instance.getTipoPrato());
    }

    /**
     * Test of setAlergenios method, of class Prato.
     */
    @Test
    public void testSetAlergenios()
    {
        // Alergenios (null)
        instance.setAlergenios(null);
        assertEquals("", instance.getAlergenios());
        
        
        // Alergenios
        String alergenios = "alergenios";
        instance.setAlergenios(alergenios);
        assertEquals(alergenios, instance.getAlergenios());
    }
    //</editor-fold>

    
    //<editor-fold defaultstate="collapsed" desc="testSave/Delete">
    private boolean existePratoEspecifico(Prato instance, boolean visivel) throws SQLException
    {
        var cbd = ConectorBD.getInstance();
        var ps = cbd.prepareStatement("SELECT COUNT(nome) = 1 AS existe FROM prato WHERE emailRestaurante = ? AND nome = ? AND detalhes = ? AND precoUnitario = ? AND tipo = ? AND alergenios = ? AND visivel = ?");
        
        ps.setString(1, instance.getRestaurante().getEmail());
        ps.setString(2, instance.getNome());
        ps.setString(3, instance.getDetalhes());
        ps.setFloat(4, instance.getPrecoUnitario());
        ps.setString(5, instance.getTipoPrato().toString());
        ps.setString(6, instance.getAlergenios());
        ps.setBoolean(7, visivel);
        
        
        try(var result = cbd.executePreparedQuery(ps))
        {
            if (!result.next())
                return false;
            
            return result.getBoolean("existe");
        }
    }
    
    private void removerPrato(Prato instance) throws SQLException
    {
        var cbd = ConectorBD.getInstance();
        var ps = cbd.prepareStatement("DELETE FROM prato WHERE emailRestaurante = ? AND nome = ?");
         
        ps.setString(1, instance.getRestaurante().getEmail());
        ps.setString(2, instance.getNome());
        
        cbd.executePreparedUpdate(ps);
    }
    
    /**
     * Test of save and delete methods, of class Prato.
     */
    @Test
    public void testSaveDelete() throws SQLException
    {
        // save (insert)
        instance.save();
        assertTrue(existePratoEspecifico(instance, true));
        
        
        // delete
        instance.delete();
        assertTrue(existePratoEspecifico(instance, false));
        
        
        // save (update)
        instance.setDetalhes("detalhes altera");
        instance.setPrecoUnitario(999);
        instance.setTipoPrato(TipoPrato.PEIXE);
        instance.setAlergenios("");
        instance.save();
        
        assertTrue(existePratoEspecifico(instance, true));
        removerPrato(instance);
    }
    
    /**
     * Test of save and delete methods, of class Prato.
     */
    @Test
    @SuppressWarnings("ThrowableResultIgnored")
    public void testSaveRestauranteNaoExiste() throws SQLException
    {
        // save (restaurante nao existe)
        Restaurante rest = new Restaurante("naoexiste@rest.com", "naoexiste", "999999999", "morada naoexiste");
        instance = new Prato(rest, "prato erro", "detalhes erro", 999, TipoPrato.VEGANO, "");
        
        assertThrows(RestauranteNotFoundException.class, () -> {    
            instance.save();
        });
    }
    //</editor-fold>
    
    
    //<editor-fold defaultstate="collapsed" desc="testEquals">
    /**
     * Test of equals method, of class Prato.
     */
    @Test
    public void testEquals() 
    {
        // equals
        Restaurante r1 = new Restaurante("rest1@rest.com", "Restaurante 1", "912345678", "Primeira Morada");
        Restaurante r2 = new Restaurante("rest2@rest.com", "Restaurante 2", "923456789", "Segunda Morada");
        
        BufferedImage b1 = ImagemUtils.createImage(100, 100);
        BufferedImage b2 = ImagemUtils.createImage(200, 200);
        
        EqualsVerifier.simple().forClass(Prato.class)
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
    
    
    //<editor-fold defaultstate="collapsed" desc="testFrom">
    /**
     * Test of from method, of class Prato.
     */
    @Test
    @SuppressWarnings("ThrowableResultIgnored")
    public void testFrom_restauranteNull() throws SQLException
    {
        // from (restaurante = null)
        Restaurante rest = null;
        assertThrows(IllegalArgumentException.class, () -> {
            Prato.from(rest);
        });
    }

    /**
     * Test of from method, of class Prato.
     */
    @Test
    public void testFrom_apenasVisivelSemValores() throws SQLException
    {
        // from (apenasVisivel = true) -> array vazio
        Restaurante rest = new Restaurante("invisivel@rest.com", "invisivel", "000000000", "morada invisivel");
        Prato[] expResult = new Prato[0];
        Prato[] result = Prato.from(rest);
        
        assertArrayEquals(expResult, result);
    }
    
    /**
     * Test of from method, of class Prato.
     */
    @Test
    public void testFrom() throws SQLException
    {
        // from (apenasVisivel = false) -> array com valores
        Restaurante rest = new Restaurante("invisivel@rest.com", "invisivel", "000000000", "morada invisivel");
        boolean apenasVisivel = false;
        
        Prato[] expResult = new Prato[]
        {
            new Prato(rest, "prato vegetariano invisivel", "detalhes invisivel", 12, TipoPrato.VEGETARIANO, "alergenios invisivel"),
            new Prato(rest, "prato vegano invisivel", "detalhes invisivel", 15, TipoPrato.VEGANO, "")
        };
        Prato[] result = Prato.from(rest, apenasVisivel);
        
        assertArrayEquals(expResult, result);
    }
    //</editor-fold>
    

    //<editor-fold defaultstate="collapsed" desc="testGetPrato">
    /**
     * Test of getPrato method, of class Prato.
     */
    @Test
    @SuppressWarnings("ThrowableResultIgnored")
    public void testGetPrato_restauranteNull() throws SQLException
    {
        // getPrato (restaurate = null)
        Restaurante rest = null;
        String nome = "prato vegetariano invisivel";
        
        assertThrows(IllegalArgumentException.class, () -> {
            Prato.getPrato(rest, nome);
        });
    }
    
    /**
     * Test of getPrato method, of class Prato.
     */
    @Test
    @SuppressWarnings("ThrowableResultIgnored")
    public void testGetPrato_restauranteNaoExiste() throws SQLException
    {
        // getPrato (restaurate nao existe)
        Restaurante rest = new Restaurante("invisivel@rest.com", "invisivel", "000000000", "morada invisivel");
        String nome = "prato vegetariano invisivel";

        assertThrows(RestauranteNotFoundException.class, () -> {
            Prato.getPrato(rest, nome);
        });
    }
    
    /**
     * Test of getPrato method, of class Prato.
     */
    @Test
    @SuppressWarnings("ThrowableResultIgnored")
    public void testGetPrato_pratoNaoExiste() throws SQLException
    {
        // getPrato (item nao existe)
        Restaurante rest = instance.getRestaurante();
        String nome = "prato vegetariano invisivel";
        
        assertThrows(ItemCardapioNotFoundException.class, () -> {
            Prato.getPrato(rest, nome);
        });
    }
    
    /**
     * Test of getPrato method, of class Prato.
     */
    @Test
    public void testGetPrato() throws SQLException
    {
        // getPrato (apenasVisiveis = false e item existe)
        Restaurante rest = new Restaurante("invisivel@rest.com", "invisivel", "000000000", "morada invisivel");
        String nome = "prato vegetariano invisivel";
        boolean apenasVisiveis = false;
        
        Prato expResult = new Prato(rest, "prato vegetariano invisivel", "detalhes invisivel", 12, TipoPrato.VEGETARIANO, "alergenios invisivel");
        Prato result = Prato.getPrato(rest, nome, apenasVisiveis);
        assertEquals(expResult, result);
    }
    //</editor-fold>
}
