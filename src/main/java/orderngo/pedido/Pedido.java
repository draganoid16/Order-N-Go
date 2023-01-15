package orderngo.pedido;

import orderngo.basedados.ConectorBD;
import orderngo.cardapio.*;
import orderngo.utilizador.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.util.LinkedHashMap;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.util.Map;
import java.util.stream.Collectors;

import java.sql.SQLException;
import orderngo.exception.PedidoNotFoundException;
import orderngo.exception.UtilizadorNotFoundException;
import orderngo.exception.ClienteNotFoundException;

/**
 * 
 * @author grupo1
 */
public class Pedido
{
    private final Cliente cliente;
    private int nrPedido;
    private final String moradaEntrega;
    private final LocalDateTime dataHoraEntrega;
    private final LinkedHashMap<ItemCardapio, Integer> itemsPedido;
    
    private static final LinkedHashMap<String, Cliente> clienteCache = new LinkedHashMap<>();
    private static final LinkedHashMap<String, Restaurante> restauranteCache = new LinkedHashMap<>();

    public Pedido(Cliente cliente, String moradaEntrega, LocalDateTime dataHoraEntrega)
    {
        if (cliente == null)
            throw new IllegalArgumentException("Cliente invalido!");
        
        this.cliente = cliente;
        
        if (moradaEntrega == null || moradaEntrega.isBlank())
            throw new IllegalArgumentException("Morada de entrega invalida!");
            
        this.moradaEntrega = moradaEntrega;
        
        if (dataHoraEntrega == null)
            throw new IllegalArgumentException("Data/Hora de entrega invalida!");
            
        this.dataHoraEntrega = dataHoraEntrega;
        
        setNrPedido(0);
        
        itemsPedido = new LinkedHashMap<>();
    }

    //<editor-fold defaultstate="collapsed" desc="Getters">
    public Cliente getCliente()
    {
        return cliente;
    }
    
    public int getNrPedido()
    {
        return nrPedido;
    }

    public String getMoradaEntrega()
    {
        return moradaEntrega;
    }

    public LocalDateTime getDataHoraEntrega()
    {
        return dataHoraEntrega;
    }
    
    public String getDataHoraEntregaString()
    {
        return DateTimeFormatter.ofPattern("dd-MM-yyyy kk:mm").format(dataHoraEntrega);
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Setters">
    private void setNrPedido(int nrPedido)
    {
        if (nrPedido < 0)
            throw new IllegalArgumentException("Numero de pedido invalido!");
            
        this.nrPedido = nrPedido;
    }
    //</editor-fold>
    
    
    //<editor-fold defaultstate="collapsed" desc="ItemsPedido">
    public LinkedHashMap<ItemCardapio, Integer> getItemsPedido()
    {
        return new LinkedHashMap<>(itemsPedido);
    }
    
    public LinkedHashMap<ItemCardapio, Integer> getItemsPedido(Restaurante restaurante)
    {
        return new LinkedHashMap<>(
            itemsPedido.entrySet().stream()
            .filter(ip -> 
                ip.getKey()
                .getRestaurante()
                .equals(restaurante)
            )
            .collect(Collectors.toMap(
                Map.Entry::getKey,
                Map.Entry::getValue
            ))
        );
    }
    
    public static LinkedHashMap<Prato, Integer> getPratosItems(LinkedHashMap<ItemCardapio, Integer> itemsPedido)
    {
        return new LinkedHashMap<>(
            (Map)itemsPedido.entrySet().stream()
            .filter(ip -> ip.getKey() instanceof Prato)
            .collect(Collectors.toMap(
                Map.Entry::getKey,
                Map.Entry::getValue
            ))
        );
    }
    
    public static LinkedHashMap<Bebida, Integer> getBebidasItems(LinkedHashMap<ItemCardapio, Integer> itemsPedido)
    {
        return new LinkedHashMap<>(
            (Map)itemsPedido.entrySet().stream()
            .filter(ip -> ip.getKey() instanceof Bebida)
            .collect(Collectors.toMap(
                Map.Entry::getKey,
                Map.Entry::getValue
            ))
        );
    }
    //</editor-fold>
    
    
    //<editor-fold defaultstate="collapsed" desc="Adicionar/Remover">
    public void limparItemsPedido()
    {
        itemsPedido.clear();
    }
    
    public void adicionarItem(ItemCardapio item, int quantidade)
    {
        if (item == null)
            throw new IllegalArgumentException("Item invalido!");
            
        itemsPedido.putIfAbsent(item, 0);
        alterQuantidade(item, quantidade);
    }
    public void adicionarItem(ItemCardapio item)
    {
        adicionarItem(item, 1);
    }
    
    public void removerItem(ItemCardapio item)
    {
        if (item == null || !itemsPedido.containsKey(item))
            throw new IllegalArgumentException("Item invalido!");
            
        itemsPedido.remove(item);
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Quantidade">
    public void incrQuantidade(ItemCardapio item, int incr)
    {
        if (incr < 0)
            throw new IllegalArgumentException("Incremento invalido!");
        
        int quant = itemsPedido.getOrDefault(item, 0);
        quant += incr;
        
        alterQuantidade(item, quant);
    }
    
    public void decrQuantidade(ItemCardapio item, int decr)
    {
        if (decr < 0)
            throw new IllegalArgumentException("Decremento invalido!");
        
        int quant = itemsPedido.getOrDefault(item, 0);
        quant -= decr;
        
        alterQuantidade(item, quant);
    }
    
    public void alterQuantidade(ItemCardapio item, int quant)
    {
        if (item == null || !itemsPedido.containsKey(item))
            throw new IllegalArgumentException("Item invalido!");
        
        if (quant <= 0)
            removerItem(item);
        else
            itemsPedido.replace(item, quant);
    }
    //</editor-fold>

    
    //<editor-fold defaultstate="collapsed" desc="BuscarDados">
    public static Pedido criarPedido(Cliente cliente, ResultSet result) throws SQLException
    {
        Pedido p = new Pedido(
            cliente, 
            result.getString("moradaEntrega"),
            result.getDate("dataHoraEntrega").toLocalDate().atStartOfDay()
        );
        
        p.setNrPedido(result.getInt("nrPedido"));
        
        return p;
    }
    
    //<editor-fold defaultstate="collapsed" desc="from">
    private static ArrayList<Pedido> getPedidosRestaurante(Restaurante restaurante) throws SQLException
    {
        ArrayList<Pedido> pedidos = new ArrayList<>();
        String sql = "{CALL getPedidosFromRestaurante(?)}";
        
        var cbd = ConectorBD.getInstance();
        var cs = cbd.prepareCall(sql);
        cs.setString(1, restaurante.getEmail());
        
        try (ResultSet result = cbd.executePreparedQuery(cs))
        {
            while (result.next())
            {
                try
                {
                    String emailCliente = result.getString("emailCliente");
                    clienteCache.putIfAbsent(
                        emailCliente, 
                        Cliente.getCliente(emailCliente, false)
                    );
                    Cliente c = clienteCache.get(emailCliente);

                    pedidos.add(criarPedido(c, result));
                }
                catch (UtilizadorNotFoundException ignored) {}
            }
        }
        
        return pedidos;
    }
    
    public static Pedido[] from(Restaurante restaurante) throws SQLException
    {
        if (restaurante == null)
            throw new IllegalArgumentException("Restaurante invalido!");
        
        ArrayList<Pedido> pedidos = getPedidosRestaurante(restaurante);
        clienteCache.clear();
        
        return pedidos
            .toArray(Pedido[]::new);
    }
    
    public static Pedido[] from(Cliente cliente) throws SQLException
    {
        if (cliente == null)
            throw new IllegalArgumentException("Cliente invalido!");
        
        ArrayList<Pedido> pedidos = new ArrayList<>();
        String sql = "SELECT * FROM pedido WHERE emailCliente = ?";
        
        var cbd = ConectorBD.getInstance();
        var ps = cbd.prepareStatement(sql);
        ps.setString(1, cliente.getEmail());
        
        try (ResultSet result = cbd.executePreparedQuery(ps))
        {
            while (result.next())
            {
                pedidos.add(criarPedido(cliente, result));
            }
        }
        
        return pedidos
            .toArray(Pedido[]::new);
    }
    //</editor-fold>
    
    public static Pedido getPedido(int nrPedido) throws SQLException, PedidoNotFoundException, ClienteNotFoundException
    {
        if (nrPedido <= 0)
            throw new IllegalArgumentException("Numero do pedido invalido!");
            
        String sql = "SELECT * FROM pedido WHERE nrPedido = ?";
            
        var cbd = ConectorBD.getInstance();
        var ps = cbd.prepareStatement(sql);
        ps.setInt(1, nrPedido);
        
        Pedido p;
        try (ResultSet result = cbd.executePreparedQuery(ps))
        {
            if (!result.next())
                throw new PedidoNotFoundException(nrPedido);
            
            String emailCliente = result.getString("emailCliente");
            p = criarPedido(
                Cliente.getCliente(emailCliente, false),
                result
            );
        }
        
        return p;
    }
    
    //<editor-fold defaultstate="collapsed" desc="fill">
    private LinkedHashMap<Prato, Integer> getPedidoPratos() throws SQLException
    {
        LinkedHashMap<Prato, Integer> pratosPedidos = new LinkedHashMap<>();
        String sql = "{CALL getPedidoPratos(?)}";
        
        var cbd = ConectorBD.getInstance();
        var cs = cbd.prepareCall(sql);
        cs.setInt(1, getNrPedido());
        
        try (ResultSet result = cbd.executePreparedQuery(cs))
        {
            while (result.next())
            {
                try
                {
                    String emailRestaurante = result.getString("emailRestaurante");
                    restauranteCache.putIfAbsent(
                        emailRestaurante, 
                        Restaurante.getRestaurante(emailRestaurante, false)
                    );
                    Restaurante r = restauranteCache.get(emailRestaurante);
                    
                    pratosPedidos.put(
                        Prato.criarPrato(r, result), 
                        result.getInt("quantidade")
                    );
                }
                catch (UtilizadorNotFoundException ignored) {}
            }
        }
        
        return pratosPedidos;
    }
    
    private LinkedHashMap<Bebida, Integer> getPedidoBebidas() throws SQLException
    {
        LinkedHashMap<Bebida, Integer> pratosBebidas = new LinkedHashMap<>();
        String sql = "{CALL getPedidoBebidas(?)}";
        
        var cbd = ConectorBD.getInstance();
        var cs = cbd.prepareCall(sql);
        cs.setInt(1, getNrPedido());
        
        try (ResultSet result = cbd.executePreparedQuery(cs))
        {
            while (result.next())
            {
                try
                {
                    String emailRestaurante = result.getString("emailRestaurante");
                    restauranteCache.putIfAbsent(
                        emailRestaurante, 
                        Restaurante.getRestaurante(emailRestaurante, false)
                    );
                    Restaurante r = restauranteCache.get(emailRestaurante);
                    
                    pratosBebidas.put(
                        Bebida.criarBebida(r, result), 
                        result.getInt("quantidade")
                    );
                }
                catch (UtilizadorNotFoundException ignored) {}
            }
        }
        
        return pratosBebidas;
    }
    
    public void fill() throws SQLException
    {
        limparItemsPedido();
        
        itemsPedido.putAll(getPedidoPratos());
        itemsPedido.putAll(getPedidoBebidas());
        
        restauranteCache.clear();
    }
    //</editor-fold>
    //</editor-fold>
    
    
    //<editor-fold defaultstate="collapsed" desc="equals/hashCode/toString">
    public boolean canEqual(Object obj) 
    {
        return (obj instanceof Pedido);
    }
    
    @Override
    public boolean equals(Object obj)
    {
        if (obj == this)
            return true;
        
        if (!(obj instanceof Pedido other))
            return false;
        
        
        if (!other.canEqual(this))
            return false;
        
        if (!cliente.equals(other.cliente))
            return false;
        
        if (this.nrPedido != other.nrPedido)
            return false;
        
        if (!moradaEntrega.equals(other.moradaEntrega))
            return false;
        
        if (!dataHoraEntrega.equals(other.dataHoraEntrega))
            return false;
        
        return itemsPedido.equals(other.itemsPedido);
    }
    
    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 37 * hash + nrPedido;
        return hash;
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("Pedido{");
        sb.append("nrPedido=").append(nrPedido);
        sb.append(", cliente=").append(cliente);
        sb.append(", moradaEntrega=").append(moradaEntrega);
        sb.append(", dataHoraEntrega=").append(getDataHoraEntregaString());
        sb.append(", itemsPedido=").append(itemsPedido);
        sb.append('}');
        return sb.toString();
    }   
    //</editor-fold>
}
