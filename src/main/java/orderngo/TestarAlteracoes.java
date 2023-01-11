package orderngo;

import orderngo.basedados.*;
import orderngo.cardapio.*;
import orderngo.cardapio.Prato.TipoPrato;
import orderngo.exception.*;
import orderngo.pedido.*;
import orderngo.utilizador.*;
import orderngo.utils.*;

import java.sql.SQLException;

public class TestarAlteracoes 
{
    //<editor-fold defaultstate="collapsed" desc="Metodos prints">
    private static String stackTraceToString(Exception ex)
    {
        StringBuilder sb = new StringBuilder();
        
        for (StackTraceElement elem : ex.getStackTrace())
        {
            sb.append(elem).append("\n");
        }
        
        return sb.toString();
    }
    
    private static void printSQLException(SQLException sqle)
    {
        System.out.printf(
            "Message: %s\nErrorCode: %s\nSQLState: %s\nStackTrace:\n%s\n",
            sqle.getMessage(),
            sqle.getErrorCode(),
            sqle.getSQLState(),
            stackTraceToString(sqle)
        );
    }
    
    private static void printItemCardapioArray(ItemCardapio[] ic)
    {
        StringBuilder sb = new StringBuilder("[\n");
        for (ItemCardapio item : ic)
            sb.append("  ").append(item).append('\n');
        sb.append("]\n");
        
        System.out.println(sb);
    }
    
    private static void printObjects(Object... objs)
    {
        StringBuilder sb = new StringBuilder();
        for (Object obj : objs)
            sb.append(obj).append('\n');
        
        System.out.println(sb);
    }
    //</editor-fold>
    
    public void main()
    {   
        Restaurante rest = new Restaurante("teste@teste.teste", "restaurante teste", "000000000", "teste");
        rest.setPassword("teste".toCharArray());
        rest.setImagem(ImagemUtils.ficheiroToImage("src\\imageresources\\profile.png"));
        
        Cardapio card = rest.getCardapio();
        ItemCardapio ic;
        
        ic = new Prato(rest, "carne teste", "carne teste", 999, TipoPrato.CARNE, null);
        ic.setImagem(ImagemUtils.ficheiroToImage("src\\imageresources\\food-tray.png"));
        card.adicionarItem(ic);
        
        ic = new Prato(rest, "peixe teste", "peixe teste", 999, TipoPrato.PEIXE, null);
        card.adicionarItem(ic);
        
        ic = new Bebida(rest, "bebida teste", "bebida teste", 999, 999);
        ic.setImagem(ImagemUtils.ficheiroToImage(null));
        card.adicionarItem(ic);
        
        GestorOrderAndGo gest = new GestorOrderAndGo("teste@teste.teste", "gestorog teste", "000000000", "teste", 999);
        gest.setPassword("teste".toCharArray());
        
        try
        {
            rest.save();
            gest.save();
            card.save();
            
            rest = Restaurante.getRestaurante(rest.getEmail());
            gest = GestorOrderAndGo.getGestor(gest.getEmail());
            card = rest.getCardapio();
            card.fill();
        }
        catch (SQLException sqle)
        {
            printSQLException(sqle);
        }
        
        
        System.out.println("_.toString()");
        printObjects(rest, gest, card);
        
        System.out.println("card.getAllItems()");
        printItemCardapioArray(card.getAllItems());
    }
    public static void main(String[] args) 
    {
        TestarAlteracoes t = new TestarAlteracoes();
        t.main();
    }
}
