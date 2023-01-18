package orderngo.testes.pedido;

import orderngo.testes.basedados.TestesComBD;
import orderngo.testes.cardapio.ItemCardapioTest.ItemCardapioImpl;

import orderngo.pedido.Pedido;
import orderngo.cardapio.*;
import orderngo.cardapio.Prato.TipoPrato;
import orderngo.utilizador.*;
import orderngo.utils.ImagemUtils;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.awt.image.BufferedImage;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;

import java.sql.SQLException;
import orderngo.exception.PedidoNotFoundException;

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
    @SuppressWarnings("ThrowableResultIgnored")
    public void testGetNrPedido()
    {
        // nrPedido valido (por preencher)
        int expResult = 0;
        int result = instance.getNrPedido();
        
        assertEquals(expResult, result);
        
        // nrPedido invalido (< 0)
        assertThrows(IllegalArgumentException.class, () -> {
            int nrPedido = -1;
            instance = new Pedido(cli, "morada teste", LocalDateTime.of(2023, 01, 16, 20, 00), nrPedido){};
        });
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
     * Test of getItemsPedido method, of class Pedido.
     */
    @Test
    public void testGetItemsPedido()
    {
        Restaurante rest1 = new Restaurante("visivel@rest.com", "visivel", "111111111", "morada visivel");
        Restaurante rest2 = new Restaurante("invisivel@rest.com", "invisivel", "000000000", "morada invisivel");
        
        ItemCardapio item1 = new ItemCardapioImpl(rest1, "item teste", "detalhes teste", 69);
        ItemCardapio item2 = new ItemCardapioImpl(rest2, "item teste 2", "detalhes teste 2", 69);
        
        instance.adicionarItem(item1, 10);
        instance.adicionarItem(item2, 20);
        
        // getItemsPedido
        LinkedHashMap<ItemCardapio, Integer> expResult = new LinkedHashMap<>();
        expResult.put(item1, 10);
        expResult.put(item2, 20);
        LinkedHashMap<ItemCardapio, Integer> result = instance.getItemsPedido();
        
        assertEquals(expResult, result);
        
        // getItemsPedido (rest2)
        expResult.remove(item1);
        result = instance.getItemsPedido(rest2);
        
        assertEquals(expResult, result);
    }
    
    /**
     * Test of limparItemsPedido methods, of class Pedido.
     */
    @Test
    public void testLimparItemsPedido()
    {
        Restaurante rest1 = new Restaurante("visivel@rest.com", "visivel", "111111111", "morada visivel");
        Restaurante rest2 = new Restaurante("invisivel@rest.com", "invisivel", "000000000", "morada invisivel");
        
        ItemCardapio item1 = new ItemCardapioImpl(rest1, "item teste", "detalhes teste", 69);
        ItemCardapio item2 = new ItemCardapioImpl(rest2, "item teste 2", "detalhes teste 2", 69);
        
        instance.adicionarItem(item1, 10);
        instance.adicionarItem(item2, 20);
        
        // limparItemsPedido
        instance.limparItemsPedido();
        
        LinkedHashMap<ItemCardapio, Integer> expResult = new LinkedHashMap<>();
        LinkedHashMap<ItemCardapio, Integer> result = instance.getItemsPedido();
        
        assertEquals(expResult, result);
    }
    //</editor-fold>
    

    //<editor-fold defaultstate="collapsed" desc="testAdicionarRemover">
    /**
     * Test of adicionarItem and removerItem methods, of class Pedido.
     */
    @Test
    @SuppressWarnings("ThrowableResultIgnored")
    public void testAdicionarRemoverItem()
    {
        Restaurante rest = new Restaurante("visivel@rest.com", "visivel", "111111111", "morada visivel");
        ItemCardapio item = new ItemCardapioImpl(rest, "item teste", "detalhes teste", 69);
        
        // adicionar item válido
        instance.adicionarItem(item);
        
        LinkedHashMap<ItemCardapio, Integer> expResult = new LinkedHashMap<>();
        expResult.put(item, 1);
        LinkedHashMap<ItemCardapio, Integer> result = instance.getItemsPedido();
        
        assertEquals(expResult, result);
        
        // adicionar item inválido
        assertThrows(IllegalArgumentException.class, () -> {
            ItemCardapio itemCardapio = null;
            instance.adicionarItem(itemCardapio);
        });
        
        
        // remover item
        instance.removerItem(item);
        expResult.remove(item);
        result = instance.getItemsPedido();
        
        assertEquals(expResult, result);
    }
    //</editor-fold>

    
    //<editor-fold defaultstate="collapsed" desc="testQuantidade">
    /**
     * Test of alterQuantidade method, of class Pedido.
     */
    @Test
    @SuppressWarnings("ThrowableResultIgnored")
    public void testAlterQuantidade()
    {
        Restaurante rest = new Restaurante("visivel@rest.com", "visivel", "111111111", "morada visivel");
        ItemCardapio item = new ItemCardapioImpl(rest, "item teste", "detalhes teste", 69);
        
        instance.adicionarItem(item);
        
        // item valido, quantidade > 0
        int quant = 5;
        instance.alterQuantidade(item, quant);
        
        LinkedHashMap<ItemCardapio, Integer> expResult = new LinkedHashMap<>();
        expResult.put(item, 5);
        LinkedHashMap<ItemCardapio, Integer> result = instance.getItemsPedido();
        
        assertEquals(expResult, result);
        
        // item valido, quantidade <= 0
        quant = 0;
        instance.alterQuantidade(item, quant);
        
        expResult.clear();
        result = instance.getItemsPedido();
        
        assertEquals(expResult, result);
        
        
        // item invalido (nao existe)
        assertThrows(IllegalArgumentException.class, () -> {
            ItemCardapio itemCardapio = item;
            int quantidade = 5;
            instance.alterQuantidade(itemCardapio, quantidade);
        });
        
        // item invalido (null)
        assertThrows(IllegalArgumentException.class, () -> {
            ItemCardapio itemCardapio = null;
            int quantidade = 5;
            instance.alterQuantidade(itemCardapio, quantidade);
        });
    }
    
    /**
     * Test of incrQuantidade method, of class Pedido.
     */
    @Test
    @SuppressWarnings("ThrowableResultIgnored")
    public void testIncrQuantidade()
    {
        Restaurante rest = new Restaurante("visivel@rest.com", "visivel", "111111111", "morada visivel");
        ItemCardapio item = new ItemCardapioImpl(rest, "item teste", "detalhes teste", 69);
        
        instance.adicionarItem(item);
        
        // incremento valido
        int incr = 2;
        instance.incrQuantidade(item, incr);
        
        LinkedHashMap<ItemCardapio, Integer> expResult = new LinkedHashMap<>();
        expResult.put(item, 3);
        LinkedHashMap<ItemCardapio, Integer> result = instance.getItemsPedido();
        
        assertEquals(expResult, result);
        
        // incremento invalido (< 0)
        assertThrows(IllegalArgumentException.class, () -> {
            int incremento = -1;
            instance.incrQuantidade(item, incremento);
        });
    }

    /**
     * Test of decrQuantidade method, of class Pedido.
     */
    @Test
    @SuppressWarnings("ThrowableResultIgnored")
    public void testDecrQuantidade()
    {
        Restaurante rest = new Restaurante("visivel@rest.com", "visivel", "111111111", "morada visivel");
        ItemCardapio item = new ItemCardapioImpl(rest, "item teste", "detalhes teste", 69);
        
        instance.adicionarItem(item, 5);
        
        // decremento valido
        int decr = 2;
        instance.decrQuantidade(item, decr);
        
        LinkedHashMap<ItemCardapio, Integer> expResult = new LinkedHashMap<>();
        expResult.put(item, 3);
        LinkedHashMap<ItemCardapio, Integer> result = instance.getItemsPedido();
        
        assertEquals(expResult, result);
        
        // decremento invalido (< 0)
        assertThrows(IllegalArgumentException.class, () -> {
            int decremento = -1;
            instance.decrQuantidade(item, decremento);
        });
    }
    //</editor-fold>


    //<editor-fold defaultstate="collapsed" desc="testFill">
    /**
     * Test of fill method, of class Pedido.
     */
    @Test
    public void testFill() throws SQLException
    {
        Restaurante rest = new Restaurante("visivel@rest.com", "visivel", "111111111", "morada visivel");
        instance = Pedido.getPedido(31);
        
        // fill
        LinkedHashMap<ItemCardapio, Integer> expResult = new LinkedHashMap<>();
        expResult.put(new Prato(rest, "prato carne visivel", "detalhes visivel", 7.8f, TipoPrato.CARNE, "alergenios visivel"), 3);
        expResult.put(new Bebida(rest, "bebida visivel", "detalhes visivel", 3, 50), 3);
        
        instance.fill();
        LinkedHashMap<ItemCardapio, Integer> result = instance.getItemsPedido();
        
        assertEquals(expResult, result);
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
     * Test of getPratosItems and getBebidasItems methods, of class Pedido.
     */
    @Test
    public void testGetPratosBebidasItems()
    {
        Restaurante rest = new Restaurante("visivel@rest.com", "visivel", "111111111", "morada visivel");
        Prato prato = new Prato(rest, "prato carne visivel", "detalhes visivel", 7.8f, TipoPrato.CARNE, "alergenios visivel");
        Bebida bebida = new Bebida(rest, "bebida visivel", "detalhes visivel", 3, 50);
        
        LinkedHashMap<ItemCardapio, Integer> itemsPedido = new LinkedHashMap<>();
        itemsPedido.put(prato, 3);
        itemsPedido.put(bebida, 3);
        
        // getPratosItems
        LinkedHashMap<Prato, Integer> expResultPrato = new LinkedHashMap<>();
        expResultPrato.put(prato, 3);
        LinkedHashMap<Prato, Integer> resultPrato = Pedido.getPratosItems(itemsPedido);
        
        assertEquals(expResultPrato, resultPrato);
        
        // getBebidasItems
        LinkedHashMap<Bebida, Integer> expResultBebida = new LinkedHashMap<>();
        expResultBebida.put(bebida, 3);
        LinkedHashMap<Bebida, Integer> resultBebida = Pedido.getBebidasItems(itemsPedido);
        
        assertEquals(expResultBebida, resultBebida);
    }
    //</editor-fold>
    
    
    //<editor-fold defaultstate="collapsed" desc="testFrom">
    /**
     * Test of from method, of class Pedido.
     */
    @Test
    @SuppressWarnings("ThrowableResultIgnored")
    public void testFrom_restauranteNull() throws SQLException
    {
        // restaurante invalido (null)
        Restaurante rest = null;
        
        assertThrows(IllegalArgumentException.class, () -> {
            Pedido.from(rest);
        });
    }
    
    /**
     * Test of from method, of class Pedido.
     */
    @Test
    public void testFrom_restaurante() throws SQLException
    {
        // restaurante valido
        Cliente cli2 = new Cliente("invisivel@cli.com", "invisivel", "000000000", "morada invisivel", "000000000"); 
        Restaurante rest = new Restaurante("visivel@rest.com", "visivel", "111111111", "morada visivel");
        
        Pedido[] expResult = new Pedido[] {
            new Pedido(cli, "morada 1", LocalDateTime.of(2022, 12, 15, 01, 01), 11){},
            new Pedido(cli2, "morada 1", LocalDateTime.of(2022, 12, 15, 01, 01), 31){}
        };
        Pedido[] result = Pedido.from(rest);
        
        assertArrayEquals(expResult, result);
    }

    /**
     * Test of from method, of class Pedido.
     */
    @Test
    @SuppressWarnings("ThrowableResultIgnored")
    public void testFrom_clienteNull() throws SQLException
    {
        // cliente invalido (null)
        Cliente cliente = null;
        
        assertThrows(IllegalArgumentException.class, () -> {
            Pedido.from(cliente);
        });
    }
    
    /**
     * Test of from method, of class Pedido.
     */
    @Test
    public void testFrom_cliente() throws SQLException
    {
        // cliente valido
        Pedido[] expResult = new Pedido[] {
            new Pedido(cli, "morada 1", LocalDateTime.of(2022, 12, 15, 01, 01), 11){},
            new Pedido(cli, "morada 2", LocalDateTime.of(2022, 12, 15, 02, 02), 12){}
        };
        Pedido[] result = Pedido.from(cli);
        
        assertArrayEquals(expResult, result);
    }
    //</editor-fold>

    
    //<editor-fold defaultstate="collapsed" desc="testGetPedido">
    /**
     * Test of getPedido method, of class Pedido.
     */
    @Test
    @SuppressWarnings("ThrowableResultIgnored")
    public void testGetPedido_invalido() throws SQLException
    {
        // pedido invalido (nrPedido <= 0)
        int nrPedido = 0;
        
        assertThrows(IllegalArgumentException.class, () -> {
            Pedido.getPedido(nrPedido);
        });
    }
    
    /**
     * Test of getPedido method, of class Pedido.
     */
    @Test
    @SuppressWarnings("ThrowableResultIgnored")
    public void testGetPedido_naoExiste() throws SQLException
    {
        // pedido nao existe
        int nrPedido = 69;
        
        assertThrows(PedidoNotFoundException.class, () -> {
            Pedido.getPedido(nrPedido);
        });
    }
    
    /**
     * Test of getPedido method, of class Pedido.
     */
    @Test
    public void testGetPedido() throws SQLException
    {
        // pedido existe
        int nrPedido = 11;
        Pedido expResult = new Pedido(cli, "morada 1", LocalDateTime.of(2022, 12, 15, 01, 01), nrPedido){};
        Pedido result = Pedido.getPedido(nrPedido);
        
        assertEquals(expResult, result);
    }
    //</editor-fold>
}
