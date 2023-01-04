package orderngo.pedidos;

import orderngo.utilizador.Cliente;
import orderngo.cardapio.ItemCardapio;
import java.util.Date;
import java.util.LinkedHashMap;

/**
 * 
 * @author grupo1
 */
public class Pedido
{
    private final Cliente cliente;
    private int nrPedido;
    private final String moradaEntrega;
    private final Date dataHoraEntrega;
    private final LinkedHashMap<ItemCardapio, Integer> itemsPedido;

    public Pedido(Cliente cliente, String moradaEntrega, Date dataHoraEntrega)
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

    public Date getDataHoraEntrega()
    {
        return dataHoraEntrega;
    }
    
    public LinkedHashMap<ItemCardapio, Integer> getItemsPedido()
    {
        return itemsPedido;
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Setters">
    private void setNrPedido(int nrPedido)
    {
        if (nrPedido <= 0)
            throw new IllegalArgumentException("Numero de pedido invalido!");
            
        this.nrPedido = nrPedido;
    }
    //</editor-fold>

    public void incrQuantidade(ItemCardapio item)
    {
        if (item == null || !itemsPedido.containsKey(item))
            throw new IllegalArgumentException("Item invalido!");
        
        int quant = itemsPedido.get(item);
        quant++;
        itemsPedido.replace(item, quant);
    }
    
    public void decrQuantidade(ItemCardapio item)
    {
        if (item == null || !itemsPedido.containsKey(item))
            throw new IllegalArgumentException("Item invalido!");
        
        int quant = itemsPedido.get(item);
        quant--;
        
        if (quant <= 0)
            removerItem(item);
        else
            itemsPedido.replace(item, quant);
    }
    
    public void adicionarItem(ItemCardapio item)
    {
        if (item == null)
            throw new IllegalArgumentException("Item invalido!");
            
        itemsPedido.putIfAbsent(item, 0);
        incrQuantidade(item);
    }
    
    public void removerItem(ItemCardapio item)
    {
        if (item == null || !itemsPedido.containsKey(item))
            throw new IllegalArgumentException("Item invalido!");
            
        itemsPedido.remove(item);
    }
    

    @Override
    public boolean equals(Object obj)
    {
        if (obj == this)
            return true;
        
        if (obj == null)
            return false;

        if (!(obj instanceof Pedido))
            return false;
        
        Pedido other = (Pedido)obj;
        
        if (!getCliente().equals(other.getCliente()))
            return false;
        
        if (this.nrPedido != other.nrPedido)
            return false;
        
        if (!getMoradaEntrega().equals(other.getMoradaEntrega()))
            return false;
        
        if (!getDataHoraEntrega().equals(other.getDataHoraEntrega()))
            return false;
        
        return itemsPedido.equals(other.itemsPedido);
    }
    
    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 37 * hash + this.nrPedido;
        return hash;
    }
}
