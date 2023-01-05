package orderngo.cardapio;

import orderngo.utilizador.Restaurante;

import java.util.ArrayList;
import java.util.Collections;

import java.sql.SQLException;

/**
 *
 * @author grupo1
 */
public class Cardapio
{
    private final Restaurante restaurante;
    private final ArrayList<ItemCardapio> items;
    
    public Cardapio(Restaurante restaurante)
    {
        if (restaurante == null)
            throw new IllegalArgumentException("Restaurante invalido!");
        
        this.restaurante = restaurante;
        
        items = new ArrayList<>();
    }

    
    public void limparCardapio()
    {
        items.clear();
    }
    
    public boolean adicionarItem(ItemCardapio item)
    {
        if (item == null)
            return false;
            
        return items.add(item);
    }
    
    public boolean removerItem(ItemCardapio item)
    {
        return items.remove(item);
    }
    
    public ItemCardapio removerItem(int idx)
    {
        return items.remove(idx);
    }
    
    
    public ItemCardapio[] getAllItems()
    {
        return items
            .toArray(ItemCardapio[]::new);
    }
    
    public Prato[] getAllPratos()
    {
        return items.stream()
            .filter(it -> it instanceof Prato)
            .map(p -> (Prato)p)
            .toArray(Prato[]::new);
    }
    
    public Bebida[] getAllBebidas()
    {
        return items.stream()
            .filter(it -> it instanceof Bebida)
            .map(b -> (Bebida)b)
            .toArray(Bebida[]::new);
    }
    
    public ItemCardapio getItem(int idx)
    {
        return items.get(idx);
    }
    
    
    //<editor-fold defaultstate="collapsed" desc="BuscarDados">
    public void fill() throws SQLException
    {
        limparCardapio();
        
        Collections.addAll(items, Prato.from(restaurante));
        Collections.addAll(items, Bebida.from(restaurante));
    }
    //</editor-fold>

    
    @Override
    public boolean equals(Object obj)
    {
        if (obj == this)
            return true;
        
        if (obj == null)
            return false;
        
        if (!(obj instanceof Cardapio))
            return false;
        
        Cardapio other = (Cardapio)obj;
        
        if (!restaurante.equals(other.restaurante))
            return false;
        
        return items.equals(other.items);
    }

    @Override
    public int hashCode()
    {
        int hash = 5;
        hash = 43 * hash + restaurante.hashCode();
        return hash;
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("Cardapio{");
        sb.append("restaurante=").append(restaurante);
        sb.append(", items=").append(items);
        sb.append('}');
        return sb.toString();
    }
}
