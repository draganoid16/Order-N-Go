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
    
    private static void printArray(Object[] arr)
    {
        StringBuilder sb = new StringBuilder("[\n");
        for (Object item : arr)
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
        ItemCardapio ic, ic2;
        
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
            // Guarda dados na BD
            rest.save();
            gest.save();
            card.save();
            
            // "Remove" dados da BD
            rest.delete();
            gest.delete();
            card.delete(); // limpa os items do cardápio
            
            /* Lançam exceções (quando o rest foi "removido")
            card.save(); // throw RestauranteNotFoundException
            ic.save(); // throw RestauranteNotFoundException
            */
            
            // Busca dados visiveis da BD
            System.out.println("Restaurante.all() [apenasVisiveis = true]");
            printArray(Restaurante.all());
            
            System.out.println("GestorOrderAndGo.all() [apenasVisiveis = true]");
            printArray(GestorOrderAndGo.all());
            
            System.out.println("card.getAllItems() [apenasVisiveis = true]");
            card.fill();
            printArray(card.getAllItems());
            
            System.out.println("Prato.from(rest) [apenasVisiveis = true]");
            printArray(Prato.from(rest));
            
            System.out.println("Bebida.from(rest) [apenasVisiveis = true]");
            printArray(Bebida.from(rest));
            
            /* Lançam exceções
            Restaurante.getRestaurante(rest.getEmail()); // throw RestauranteNotFoundException
            GestorOrderAndGo.getGestor(gest.getEmail()); // throw GestorOrderAndGoNotFoundException
            Prato.getPrato(rest, "carne teste"); // throw RestauranteNotFoundException
            Bebida.getBebida(rest, "bebida teste"); // throw RestauranteNotFoundException
            */
            
            System.out.println();
            System.out.println();
            
            // Busca dados da BD (visiveis/invisiveis "removidos")
            System.out.println("Restaurante.all(false)");
            printArray(Restaurante.all(false));
            
            System.out.println("GestorOrderAndGo.all(false)");
            printArray(GestorOrderAndGo.all(false));
            
            System.out.println("card.getAllItems() [apenasVisiveis = false]");
            card.fill(false);
            printArray(card.getAllItems());
            
            System.out.println("Prato.from(rest, false)");
            printArray(Prato.from(rest, false));
            
            System.out.println("Bebida.from(rest, false)");
            printArray(Bebida.from(rest, false));
            
            // Lançavam exceções
            rest = Restaurante.getRestaurante(rest.getEmail(), false);
            gest = GestorOrderAndGo.getGestor(gest.getEmail(), false);
            ic = Prato.getPrato(rest, "carne teste", false);
            ic2 = Bebida.getBebida(rest, "bebida teste", false);
            
            System.out.println("toString() - Restaurante, Gestor, Prato, Bebida");
            printObjects(rest, gest, ic, ic2);
        }
        catch (SQLException sqle)
        {
            printSQLException(sqle);
        }
    }
    public static void main(String[] args) 
    {
        TestarAlteracoes t = new TestarAlteracoes();
        t.main();
    }
}
