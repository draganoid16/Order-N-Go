package orderngo.cardapio;

import java.util.ArrayList;

import orderngo.utilizador.Restaurante;
import java.sql.SQLException;

/**
 *
 * @author grupo1
 */
public class Cardapio
{
    private final ArrayList<ItemCardapio> items;
    
    public Cardapio()
    {
        items = new ArrayList<>();
    }

    public ItemCardapio[] getAllItems()
    {
        ItemCardapio[] itemsArr = new ItemCardapio[items.size()];
        items.toArray(itemsArr);
        
        return itemsArr;
    }
    
    public ItemCardapio getItem(int idx)
    {
        return items.get(idx);
    }
    
    public void adicionarItem(ItemCardapio item)
    {
        items.add(item);
    }
    
    public void removerItem(ItemCardapio item)
    {
        items.remove(item);
    }
    
    public void removerItem(int idx)
    {
        items.remove(idx);
    }
    
    
    public static Cardapio fillFrom(Restaurante rest) throws SQLException
    {
        Cardapio card = rest.getCardapio();
        
        ItemCardapio[] items = Prato.from(rest);
        for (ItemCardapio item : items)
        {
            card.adicionarItem(item);
        }
        
        items = Bebida.from(rest);
        for (ItemCardapio item : items)
        {
            card.adicionarItem(item);
        }

        return card;
    }
}
