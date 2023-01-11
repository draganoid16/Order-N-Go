package orderngo.pedido;

import orderngo.utilizador.Cliente;
import orderngo.cardapio.ItemCardapio;

import java.util.LinkedHashMap;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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


    //<editor-fold defaultstate="collapsed" desc="Adicionar/Remover">
    public void limparItemsPedido()
    {
        itemsPedido.clear();
    }
    
    public void adicionarItem(ItemCardapio item)
    {
        if (item == null)
            throw new IllegalArgumentException("Item invalido!");
            
        itemsPedido.putIfAbsent(item, 0);
        incrQuantidade(item, 1);
    }
    
    public void removerItem(ItemCardapio item)
    {
        if (item == null || !itemsPedido.containsKey(item))
            throw new IllegalArgumentException("Item invalido!");
            
        itemsPedido.remove(item);
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Quantidade">
    private void addRemQuantidade(ItemCardapio item, int addrem)
    {
        if (item == null || !itemsPedido.containsKey(item))
            throw new IllegalArgumentException("Item invalido!");
        
        int quant = itemsPedido.get(item);
        quant += addrem;
        
        if (quant <= 0)
            removerItem(item);
        else
            itemsPedido.replace(item, quant);
    }
    
    public void incrQuantidade(ItemCardapio item, int incr)
    {
        if (incr < 0)
            throw new IllegalArgumentException("Incremento invalido!");
        
        addRemQuantidade(item, incr);
    }
    
    public void decrQuantidade(ItemCardapio item, int decr)
    {
        if (decr < 0)
            throw new IllegalArgumentException("Decremento invalido!");
        
        addRemQuantidade(item, -decr);
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

    
    //<editor-fold defaultstate="collapsed" desc="equals/hashCode/toString">
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
