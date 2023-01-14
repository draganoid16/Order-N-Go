package orderngo.testes.cardapio;

import orderngo.cardapio.ItemCardapio;
import orderngo.utilizador.Restaurante;
import orderngo.utils.ImagemUtils;

import java.awt.image.BufferedImage;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;

import java.sql.SQLException;

/**
 *
 * @author grupo1
 */
public class ItemCardapioTest
{
    private ItemCardapio instance;
    
    @BeforeEach
    public void setup()
    {
        Restaurante rest = new Restaurante("visivel@rest.com", "visivel", "111111111", "morada visivel");
        instance = new ItemCardapioImpl(rest, "item teste", "detalhes teste", 69);
    }
    
    
    //<editor-fold defaultstate="collapsed" desc="testCamposFinal">
    /**
     * Test of constructor and getRestaurante method, of class ItemCardapio.
     */
    @Test
    @SuppressWarnings("ThrowableResultIgnored")
    public void testRestaurante()
    {
        // restaurante valido
        Restaurante expResult = new Restaurante("visivel@rest.com", "visivel", "111111111", "morada visivel");
        Restaurante result = instance.getRestaurante();
        assertEquals(expResult, result);
        
        // restaurante invalido
        Restaurante rest = null;
        assertThrows(IllegalArgumentException.class, () -> {
            instance = new ItemCardapioImpl(rest, "item teste", "detalhes teste", 69);
        });
    }
    
    /**
     * Test of constructor and getNome method, of class ItemCardapio.
     */
    @Test
    @SuppressWarnings("ThrowableResultIgnored")
    public void testNome()
    {
        Restaurante rest = instance.getRestaurante();
        
        // nome valido
        String expResult = "item teste";
        String result = instance.getNome();
        assertEquals(expResult, result);
        
        // nome invalido (null)
        assertThrows(IllegalArgumentException.class, () -> {
            String nome = null;
            instance = new ItemCardapioImpl(rest, nome, "detalhes teste", 69);
        });
        
        // nome invalido (vazio)
        assertThrows(IllegalArgumentException.class, () -> {
            String nome = "";
            instance = new ItemCardapioImpl(rest, nome, "detalhes teste", 69);
        });
    }
    //</editor-fold>
    
    
    //<editor-fold defaultstate="collapsed" desc="testSet">
    /**
     * Test of setDetalhes method, of class ItemCardapio.
     */
    @Test
    @SuppressWarnings("ThrowableResultIgnored")
    public void testSetDetalhes()
    {
        // detahes valido
        String expResult = "detalhes teste";
        String result = instance.getDetalhes();
        assertEquals(expResult, result);
        
        // detahes invalido (null)
        assertThrows(IllegalArgumentException.class, () -> {
            String detahes = null;
            instance.setDetalhes(detahes);
        });
        
        // detahes invalido (vazio)
        assertThrows(IllegalArgumentException.class, () -> {
            String detahes = "";
            instance.setDetalhes(detahes);
        });
    }

    /**
     * Test of setPrecoUnitario method, of class ItemCardapio.
     */
    @Test
    @SuppressWarnings("ThrowableResultIgnored")
    public void testSetPrecoUnitario()
    {
        // preco unitario valido
        float expResult = 69;
        float result = instance.getPrecoUnitario();
        assertEquals(expResult, result);

        // preco unitario invalido
        assertThrows(IllegalArgumentException.class, () -> {
            float precoUnitario = 0;
            instance.setPrecoUnitario(precoUnitario);
        });
    }

    /**
     * Test of setImagem method, of class ItemCardapio.
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
        
        EqualsVerifier.simple().forClass(ItemCardapio.class)
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

    
    //<editor-fold defaultstate="collapsed" desc="ItemCardapioImpl">
    public class ItemCardapioImpl extends ItemCardapio
    {
        public ItemCardapioImpl(Restaurante restaurante, String nome, String detalhes, float precoUnitario)
        {
            super(restaurante, nome, detalhes, precoUnitario);
        }
        
        @Override
        public void save() throws SQLException
        {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public void delete() throws SQLException
        {
            throw new UnsupportedOperationException("Not supported yet.");
        }
    }
    //</editor-fold>
}
