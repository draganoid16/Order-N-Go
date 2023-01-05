package orderngo;

import orderngo.basedados.*;
import orderngo.cardapio.*;
import orderngo.cardapio.Prato.TipoPrato;
import orderngo.exceptions.*;
import orderngo.pedidos.*;
import orderngo.utilizador.*;
import orderngo.utils.*;

import java.time.LocalDateTime;

import java.sql.SQLException;

public class TestarAlteracoes 
{
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
    
    
    public void main()
    {
        // valores em caso de erro da BD
        ConectorBD cbd = null;
        Restaurante rest = new Restaurante("nao@nao.nao", "nao", "000000000", "nao");
        Cardapio card = rest.getCardapio();
        GestorOrderAndGo gest = new GestorOrderAndGo("nao@nao.nao", "nao", "000000000", "nao", 999);
        
        // adiciona items ao cardapio
        card.adicionarItem(new Prato(rest, "Carne nao", "Carne nao", 999, TipoPrato.CARNE, null));
        card.adicionarItem(new Prato(rest, "Peixe nao", "Peixe nao", 999, TipoPrato.PEIXE, null));
        card.adicionarItem(new Bebida(rest, "Bebida nao", "nao", 999, 999));
        
        // --- valores em caso de erro da BD
        Cliente cli = new Cliente("c1@c1.c1", "c1", "111111111", "Morada 1", "111111111");
        Pedido ped = new Pedido(
            cli, "Morada 10", 
            LocalDateTime.of(2022, 10, 10, 10, 10)
        );
       
        // --- adiciona items ao pedido 
        ItemCardapio ic;
        ic = card.getItem(0);
        ped.adicionarItem(ic);
        ped.incrQuantidade(ic, 2);
                
        ic = card.getItem(2);
        ped.adicionarItem(ic);
        ped.alterQuantidade(ic, 2);
        
        try
        {
            cbd = ConectorBD.getInstance();
            
            // validarCredenciais()
            System.out.println("Restaurante.validarCredenciais() - true, false, false");
            System.out.println(Restaurante.validarCredenciais("r1@r1.r1", new char[]{'1'}));
            System.out.println(Restaurante.validarCredenciais("r1@r1.r1", new char[]{'1', '0'}));
            System.out.println(Restaurante.validarCredenciais("g1@g1.g1", new char[]{'1'}));
            System.out.println();
            
            System.out.println("GestorOrderAndGo.validarCredenciais() - true, false, false");
            System.out.println(GestorOrderAndGo.validarCredenciais("g1@g1.g1", new char[]{'1'}));
            System.out.println(GestorOrderAndGo.validarCredenciais("g1@g1.g1", new char[]{'1', '0'}));
            System.out.println(GestorOrderAndGo.validarCredenciais("r1@r1.r1", new char[]{'1'}));
            System.out.println();
            
            // save
            rest.setPassword(new char[]{'n', 'a', 'o'});
            rest.setImagem(ImagemUtils.ficheiroToImage(null));
            rest.save();
            
            rest = Restaurante.getRestaurante(rest.getEmail());
            System.out.println(rest);
            
            // buscarDados (getRestaurante, getGestor, fill)
            rest = Restaurante.getRestaurante("r1@r1.r1");
            gest = GestorOrderAndGo.getGestor("g1@g1.g1");
            
            card = rest.getCardapio();
            card.fill();
        }
        catch (SQLException sqle)
        {
            printSQLException(sqle);
        }
        
        
        // toString() das variáveis
        System.out.println("_.toString()");
        printObjects(cbd, rest, card, gest);

        // --- toString() das variáveis
        printObjects(cli, ped);
        
        
        // getAllItems, getAllPratos, getAllBebidas do Cardapio
        System.out.println("card.getAllItems()");
        printItemCardapioArray(card.getAllItems());
        
        System.out.println("card.getAllPratos()");
        printItemCardapioArray(card.getAllPratos());
        
        System.out.println("card.getAllBebidas()");
        printItemCardapioArray(card.getAllBebidas());
    }
    
    public static void main(String[] args) 
    {
        TestarAlteracoes t = new TestarAlteracoes();
        t.main();
    }
}
