package orderngo.testes.pedido;

import orderngo.testes.basedados.TestesComBD;

import orderngo.pedido.Pedido;
import orderngo.cardapio.*;
import orderngo.utilizador.*;
import orderngo.utils.ImagemUtils;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.awt.image.BufferedImage;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Disabled;

import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;

import java.sql.SQLException;

/**
 *
 * @author grupo1
 */
public class PedidoTest extends TestesComBD
{
    private Cliente cli;
    private Pedido instance;
    
    @BeforeEach
    public void init()
    {
        cli = new Cliente("visivel@cli.com", "visivel", "111111111", "morada visivel", "111111111");
        instance = new Pedido(cli, "morada teste", LocalDateTime.of(2023, 01, 16, 20, 00));
    }
    

    //<editor-fold defaultstate="collapsed" desc="testCamposFinal">
    /**
     * Test of constructor and getCliente method, of class Pedido.
     */
    @Test
    @SuppressWarnings("ThrowableResultIgnored")
    public void testGetCliente()
    {
        // cliente valido
        Cliente expResult = cli;
        Cliente result = instance.getCliente();
        
        assertEquals(expResult, result);
        
        // cliente invalido (null)
        assertThrows(IllegalArgumentException.class, () -> {
            Cliente cliente = null;
            instance = new Pedido(cliente, "morada teste", LocalDateTime.of(2023, 01, 16, 20, 00));
        });
    }

    /**
     * Test of constructor and getNrPedido method, of class Pedido.
     */
    @Test
    public void testGetNrPedido()
    {
        // nrPedido valido (por preencher)
        int expResult = 0;
        int result = instance.getNrPedido();
        
        assertEquals(expResult, result);
    }

    /**
     * Test of constructor and getMoradaEntrega method, of class Pedido.
     */
    @Test
    @SuppressWarnings("ThrowableResultIgnored")
    public void testGetMoradaEntrega()
    {
        // moradaEntrega valida
        String expResult = "morada teste";
        String result = instance.getMoradaEntrega();
        
        assertEquals(expResult, result);
        
        // moradaEntrega invalida (null)
        assertThrows(IllegalArgumentException.class, () -> {
            String morada = null;
            instance = new Pedido(cli, morada, LocalDateTime.of(2023, 01, 16, 20, 00));
        });
        
        // moradaEntrega invalida (vazia)
        assertThrows(IllegalArgumentException.class, () -> {
            String morada = "";
            instance = new Pedido(cli, morada, LocalDateTime.of(2023, 01, 16, 20, 00));
        });
    }

    /**
     * Test of constructor and getDataHoraEntrega method, of class Pedido.
     */
    @Test
    @SuppressWarnings("ThrowableResultIgnored")
    public void testGetDataHoraEntrega()
    {
        // dataHoraEntrega valida
        LocalDateTime expResult = LocalDateTime.of(2023, 01, 16, 20, 00);
        LocalDateTime result = instance.getDataHoraEntrega();
        
        assertEquals(expResult, result);
        
        // dataHoraEntrega invalida (null)
        assertThrows(IllegalArgumentException.class, () -> {
            LocalDateTime dataHoraEntrada = null;
            instance = new Pedido(cli, "morada teste", dataHoraEntrada);
        });
    }

    /**
     * Test of getDataHoraEntregaString method, of class Pedido.
     */
    @Test
    public void testGetDataHoraEntregaString()
    {
        // getDataHoraEntregaString
        String expResult = "16-01-2023 20:00";
        String result = instance.getDataHoraEntregaString();
        
        assertEquals(expResult, result);
    }
    //</editor-fold>
    
    
    //<editor-fold defaultstate="collapsed" desc="testGetItemsPedido">
    /**
     * Test of getItemsPedido and limparItemsPedido methods, of class Pedido.
     */
    @Test
    @Disabled
    public void testGetItemsPedido_0args()
    {
        System.out.println("getItemsPedido");
        Pedido instance = null;
        LinkedHashMap<ItemCardapio, Integer> expResult = null;
        LinkedHashMap<ItemCardapio, Integer> result = instance.getItemsPedido();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
    /**
     * Test of getItemsPedido method, of class Pedido.
     */
    @Test
    @Disabled
    public void testGetItemsPedido_Restaurante()
    {
        System.out.println("getItemsPedido");
        Restaurante restaurante = null;
        Pedido instance = null;
        LinkedHashMap<ItemCardapio, Integer> expResult = null;
        LinkedHashMap<ItemCardapio, Integer> result = instance.getItemsPedido(restaurante);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    //</editor-fold>
    

    //<editor-fold defaultstate="collapsed" desc="testAdicionarRemover">
    /**
     * Test of adicionarItem method, of class Pedido.
     */
    @Test
    @Disabled
    public void testAdicionarItem_ItemCardapio_int()
    {
        System.out.println("adicionarItem");
        ItemCardapio item = null;
        int quantidade = 0;
        Pedido instance = null;
        instance.adicionarItem(item, quantidade);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of adicionarItem method, of class Pedido.
     */
    @Test
    @Disabled
    public void testAdicionarItem_ItemCardapio()
    {
        System.out.println("adicionarItem");
        ItemCardapio item = null;
        Pedido instance = null;
        instance.adicionarItem(item);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removerItem method, of class Pedido.
     */
    @Test
    @Disabled
    public void testRemoverItem()
    {
        System.out.println("removerItem");
        ItemCardapio item = null;
        Pedido instance = null;
        instance.removerItem(item);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    //</editor-fold>

    
    //<editor-fold defaultstate="collapsed" desc="testQuantidade">
    /**
     * Test of alterQuantidade method, of class Pedido.
     */
    @Test
    @Disabled
    public void testAlterQuantidade()
    {
        System.out.println("alterQuantidade");
        ItemCardapio item = null;
        int quant = 0;
        Pedido instance = null;
        instance.alterQuantidade(item, quant);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
    /**
     * Test of incrQuantidade method, of class Pedido.
     */
    @Test
    @Disabled
    public void testIncrQuantidade()
    {
        System.out.println("incrQuantidade");
        ItemCardapio item = null;
        int incr = 0;
        Pedido instance = null;
        instance.incrQuantidade(item, incr);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of decrQuantidade method, of class Pedido.
     */
    @Test
    @Disabled
    public void testDecrQuantidade()
    {
        System.out.println("decrQuantidade");
        ItemCardapio item = null;
        int decr = 0;
        Pedido instance = null;
        instance.decrQuantidade(item, decr);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    //</editor-fold>


    //<editor-fold defaultstate="collapsed" desc="testFill">
    /**
     * Test of fill method, of class Pedido.
     */
    @Test
    @Disabled
    public void testFill() throws SQLException
    {
        System.out.println("fill");
        Pedido instance = null;
        instance.fill();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    //</editor-fold>
    
    
    //<editor-fold defaultstate="collapsed" desc="testEquals">
    /**
     * Test of equals method, of class Pedido.
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
    
    
    //<editor-fold defaultstate="collapsed" desc="testGetPratos/Bebidas">
    /**
     * Test of getPratosItems method, of class Pedido.
     */
    @Test
    @Disabled
    public void testGetPratosItems()
    {
        System.out.println("getPratosItems");
        LinkedHashMap<ItemCardapio, Integer> itemsPedido = null;
        LinkedHashMap<Prato, Integer> expResult = null;
        LinkedHashMap<Prato, Integer> result = Pedido.getPratosItems(itemsPedido);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getBebidasItems method, of class Pedido.
     */
    @Test
    @Disabled
    public void testGetBebidasItems()
    {
        System.out.println("getBebidasItems");
        LinkedHashMap<ItemCardapio, Integer> itemsPedido = null;
        LinkedHashMap<Bebida, Integer> expResult = null;
        LinkedHashMap<Bebida, Integer> result = Pedido.getBebidasItems(itemsPedido);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    //</editor-fold>
    
    
    //<editor-fold defaultstate="collapsed" desc="testFrom">
    /**
     * Test of from method, of class Pedido.
     */
    @Test
    @Disabled
    public void testFrom_Restaurante() throws SQLException
    {
        System.out.println("from");
        Restaurante restaurante = null;
        Pedido[] expResult = null;
        Pedido[] result = Pedido.from(restaurante);
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of from method, of class Pedido.
     */
    @Test
    @Disabled
    public void testFrom_Cliente() throws SQLException
    {
        System.out.println("from");
        Cliente cliente = null;
        Pedido[] expResult = null;
        Pedido[] result = Pedido.from(cliente);
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    //</editor-fold>

    
    //<editor-fold defaultstate="collapsed" desc="testGetPedido">
    /**
     * Test of getPedido method, of class Pedido.
     */
    @Test
    @Disabled
    public void testGetPedido() throws SQLException
    {
        System.out.println("getPedido");
        int nrPedido = 0;
        Pedido expResult = null;
        Pedido result = Pedido.getPedido(nrPedido);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    //</editor-fold>
}
