package orderngo.cardapio;

import java.util.ArrayList;

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
}
