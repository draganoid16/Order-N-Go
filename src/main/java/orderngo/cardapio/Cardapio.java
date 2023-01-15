package orderngo.cardapio;

import orderngo.utilizador.Restaurante;
import orderngo.basedados.SavableInDatabase;
import orderngo.basedados.DeletableInDatabase;

import java.util.HashSet; // HashSets, ao contrário de ArrayLists, não permitem duplicados!
import java.util.Collections;

import java.sql.SQLException;

/**
 *
 * @author grupo1
 */
public class Cardapio implements SavableInDatabase, DeletableInDatabase
{
    private final Restaurante restaurante;
    private final HashSet<ItemCardapio> items;
    private final HashSet<ItemCardapio> itemsBackup;
    
    public Cardapio(Restaurante restaurante)
    {
        if (restaurante == null)
            throw new IllegalArgumentException("Restaurante invalido!");
        
        this.restaurante = restaurante;
        
        items = new HashSet<>();
        itemsBackup = new HashSet<>();
    }

    
    private void atualizarCardapioBackup()
    {
        itemsBackup.clear();
        itemsBackup.addAll(items);
    }
    
    //<editor-fold defaultstate="collapsed" desc="Adicionar/Remover">
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
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="getItems">
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
    //</editor-fold>

    
    //<editor-fold defaultstate="collapsed" desc="BuscarDados">
    public void fill(boolean apenasVisiveis) throws SQLException
    {
        limparCardapio();
        
        Collections.addAll(items, Prato.from(restaurante, apenasVisiveis));
        Collections.addAll(items, Bebida.from(restaurante, apenasVisiveis));
        
        atualizarCardapioBackup();
    }
    public void fill() throws SQLException
    {
        fill(true);
    }
    //</editor-fold>
    
    
    @Override
    public void save() throws SQLException
    {
        itemsBackup.removeAll(items);
        
        for (ItemCardapio it : itemsBackup)
            it.delete();
        
        for (ItemCardapio it : items)
            it.save();
        
        atualizarCardapioBackup();
    }

    @Override
    public void delete() throws SQLException
    {
        limparCardapio();
        save();
    }
    
    
    //<editor-fold defaultstate="collapsed" desc="equals/hashCode/toString">
    public boolean canEqual(Object obj) 
    {
        return (obj instanceof Cardapio);
    }
    
    @Override
    public boolean equals(Object obj)
    {
        if (obj == this)
            return true;
        
        if (!(obj instanceof Cardapio other))
            return false;
        
        
        if (!other.canEqual(this))
            return false;
        
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
    //</editor-fold>
}
