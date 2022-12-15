package orderngo.pedidos;

import java.util.Date;
import java.util.LinkedHashMap;
import orderngo.cardapio.ItemCardapio;

/**
 * 
 * @author grupo1
 */
public class Pedido
{
    private final int nrPedido;
    private final String moradaEntrega;
    private final Date dataHoraEntrega;
    private final LinkedHashMap<ItemCardapio, Integer> itemsPedido;

    public Pedido(int nrPedido, String moradaEntrega, Date dataHoraEntrega)
    {
        if (nrPedido <= 0)
            throw new IllegalArgumentException("N�mero de pedido invalido!");
            
        this.nrPedido = nrPedido;
        
        if (moradaEntrega == null || moradaEntrega.isBlank())
            throw new IllegalArgumentException("Morada de entrega invalida!");
            
        this.moradaEntrega = moradaEntrega;
        
        if (dataHoraEntrega == null)
            throw new IllegalArgumentException("Data/Hora de entrega invalida!");
            
        this.dataHoraEntrega = dataHoraEntrega;
        
        itemsPedido = new LinkedHashMap<>();
    }

    //<editor-fold defaultstate="collapsed" desc="Getters">
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
}
