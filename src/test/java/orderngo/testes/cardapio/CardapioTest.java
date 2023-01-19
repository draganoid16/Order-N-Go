package orderngo.testes.cardapio;

import orderngo.testes.basedados.TestesComBD;
import orderngo.testes.cardapio.ItemCardapioTest.ItemCardapioImpl;

import orderngo.utilizador.Restaurante;
import orderngo.cardapio.*;
import orderngo.cardapio.Prato.TipoPrato;
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
public class CardapioTest extends TestesComBD
{
    private Restaurante rest;
    private Cardapio instance;
    
    @BeforeEach
    public void init()
    {
        rest = new Restaurante("visivel@rest.com", "visivel", "111111111", "morada visivel");
        instance = new Cardapio(rest);
    }

    //<editor-fold defaultstate="collapsed" desc="testCamposFinal">
    /**
     * Test of constructor, of class ItemCardapio.
     */
    @Test
    @SuppressWarnings("ThrowableResultIgnored")
    public void testRestaurante()
    {
        // restaurante valido -> init()
        
        // restaurante invalido
        rest = null;
        assertThrows(IllegalArgumentException.class, () -> {
            instance = new Cardapio(rest);
        });
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Adicionar/Remover">
    /**
     * Test of retrocederCardapio and limparCardapio methods, of class Cardapio.
     */
    @Test
    public void testRetrocederCardapio()
    {
        for (int i = 1; i <= 5; i++)
        {
            ItemCardapio ic = new ItemCardapioImpl(rest, "nome" + i, "detalhes" + i, i);
            instance.adicionarItem(ic);
        }
        
        // retrocederCardapio & limparCardapio
        instance.retrocederCardapio();
        ItemCardapio[] expResult = new ItemCardapio[0];
        ItemCardapio[] result = instance.getAllItems();
        
        assertArrayEquals(expResult, result);
    }

    /**
     * Test of adicionarItem and removerItem methods, of class Cardapio.
     */
    @Test
    public void testAdicionarRemoverItemValido()
    {
        // adicionarItem (valido)
        ItemCardapio item = new ItemCardapioImpl(rest, "nome teste", "detalhes teste", 69);
        boolean expResult = true;
        ItemCardapio[] expItems = new ItemCardapio[] {item};
        
        boolean result = instance.adicionarItem(item);
        ItemCardapio[] items = instance.getAllItems();
        
        assertEquals(expResult, result);
        assertArrayEquals(expItems, items);
        
        
        // removerItem (valido)
        expResult = true;
        expItems = new ItemCardapio[0];
        
        result = instance.removerItem(item);
        items = instance.getAllItems();
        
        assertEquals(expResult, result);
        assertArrayEquals(expItems, items);
    }
    
    /**
     * Test of adicionarItem and removerItem methods, of class Cardapio.
     */
    @Test
    public void testAdicionarRemoverItemInvalido()
    {
        ItemCardapio item = null;
        ItemCardapio[] expItems = new ItemCardapio[0];
        boolean expResult = false;
        
        // adicionarItem (invalido)
        boolean result = instance.adicionarItem(item);
        ItemCardapio[] items = instance.getAllItems();
        
        assertEquals(expResult, result);
        assertArrayEquals(expItems, items);
        
        
        // removerItem (invalido)
        result = instance.removerItem(item);
        items = instance.getAllItems();
        
        assertEquals(expResult, result);
        assertArrayEquals(expItems, items);
    }
    //</editor-fold>

    
    //<editor-fold defaultstate="collapsed" desc="getItems">
    /**
     * Test of getAllItems, getAllPratos, getAllBebidas methods, of class Cardapio.
     */
    @Test
    public void testGetAll()
    {
        // items expectaveis
        ItemCardapio[] expItems = new ItemCardapio[]{
            new ItemCardapioImpl(rest, "ItemCardapio", "ItemCardapio", 69),
            new Prato(rest, "ItemCardapio", "ItemCardapio", 69, TipoPrato.CARNE, ""),
            new Bebida(rest, "ItemCardapio", "ItemCardapio", 69, 69)
        };
        
        Prato[] expPratos = new Prato[]{
            new Prato(rest, "ItemCardapio", "ItemCardapio", 69, TipoPrato.CARNE, "")
        };
        
        Bebida[] expBebidas = new Bebida[]{
            new Bebida(rest, "ItemCardapio", "ItemCardapio", 69, 69)
        };
        
        
        // adicionar items
        for (ItemCardapio item : expItems)
            instance.adicionarItem(item);
        
        
        // getAllItems
        ItemCardapio[] resultItems = instance.getAllItems();
        assertArrayEquals(expItems, resultItems);
        
        // getAllPratos
        Prato[] resultPratos = instance.getAllPratos();
        assertArrayEquals(expPratos, resultPratos);
        
        // getAllBebidas
        Bebida[] resultBebidas = instance.getAllBebidas();
        assertArrayEquals(expBebidas, resultBebidas);
    }
    //</editor-fold>

    
    //<editor-fold defaultstate="collapsed" desc="testSave/Delete">
    /**
     * Test of save and delete methods, of class Cardapio.
     */
    @Test
    public void testSaveDelete() throws SQLException
    {
        // adicionarItems
        ItemCardapio[] expResult = new ItemCardapio[5];
        for (int i = 1; i <= 5; i++)
        {
            ItemCardapio ic = new ItemCardapioImpl(rest, "nome" + i, "detalhes" + i, i);
            expResult[i - 1] = ic;
            
            instance.adicionarItem(ic);
        }
        
        // save, limparCardapio e retrocederCardapio
        instance.save();
        instance.limparCardapio();
        instance.retrocederCardapio();
        
        ItemCardapio[] result = instance.getAllItems();
        assertArrayEquals(expResult, result);
        
        
        // delete, adicionarItems e retrocederCardapio
        instance.delete();
        for(ItemCardapio item : expResult)
            instance.adicionarItem(item);
        instance.retrocederCardapio();
        
        expResult = new ItemCardapio[0];
        result = instance.getAllItems();
        assertArrayEquals(expResult, result);
    }
    //</editor-fold>

    
    //<editor-fold defaultstate="collapsed" desc="testEquals">
    /**
     * Test of equals method, of class Cardapio.
     */
    @Test
    public void testEquals()
    {
        // equals
        Restaurante r1 = new Restaurante("rest1@rest.com", "Restaurante 1", "912345678", "Primeira Morada");
        Restaurante r2 = new Restaurante("rest2@rest.com", "Restaurante 2", "923456789", "Segunda Morada");
        
        BufferedImage b1 = ImagemUtils.createImage(100, 100);
        BufferedImage b2 = ImagemUtils.createImage(200, 200);
        
        EqualsVerifier.simple().forClass(Bebida.class)
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
    
    
    //<editor-fold defaultstate="collapsed" desc="testFill">
    /**
     * Test of fill method, of class Cardapio.
     */
    @Test
    public void testFill() throws SQLException
    {
        // fill
        instance.fill(false);

        ItemCardapio[] expResult = new ItemCardapio[]{
            new Prato(rest, "prato peixe invisivel", "detalhes invisivel", 9.99f, TipoPrato.PEIXE, null),
            new Bebida(rest, "bebida invisivel", "detalhes invisivel", 5.5f, 200),        
            new Bebida(rest, "bebida visivel", "detalhes visivel", 3, 50),
            new Prato(rest, "prato carne visivel", "detalhes visivel", 7.80f, TipoPrato.CARNE, "alergenios visivel")
        };
        ItemCardapio[] result = instance.getAllItems();
        
        assertArrayEquals(expResult, result);
    }
    //</editor-fold>
}
