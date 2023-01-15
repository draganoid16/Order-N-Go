package orderngo;

import orderngo.basedados.*;
import orderngo.cardapio.*;
import orderngo.cardapio.Prato.TipoPrato;
import orderngo.exception.*;
import orderngo.pedido.*;
import orderngo.utilizador.*;
import orderngo.utils.*;

import java.util.Map;

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
    
    private static void printMap(Map<?, ?> map)
    {
        StringBuilder sb = new StringBuilder("[\n");
        for (Map.Entry entr : map.entrySet())
        {
            sb.append("  ").append(entr.getKey());
            sb.append(" -> ").append(entr.getValue()).append('\n');
        }
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
        Restaurante rest = new Restaurante("r1@r1.r1", "r1", "111111111", "Morada 1");
                
        Cliente cliente = new Cliente("c1@c1.c1", "c1", "111111111", "Morada 1", "111111111");
        cliente.setPassword("1".toCharArray());

        try
        {        
            /* Lançam exceções (não será implementado neste projeto)
            cliente.save(); // throw UnsupportedOperationException
            cliente.delete(); // throw UnsupportedOperationException
            */
            
            // Obtem clientes
            System.out.println("Cliente.all() [apenasVisiveis = true]");
            printArray(Cliente.all());
            
            System.out.println("Cliente.all(false)");
            printArray(Cliente.all(false));
            
            // Obtem cliente
            cliente = Cliente.getCliente(cliente.getEmail());
            
            
            System.out.println();
            
            // Obtem pedidos
            System.out.println("Pedido.from(restaurante)");
            printArray(Pedido.from(rest));

            System.out.println("Pedido.from(cliente)");
            printArray(Pedido.from(cliente));
            
            // Obtem pedido
            Pedido ped = Pedido.getPedido(1);
            ped.fill();
            
            System.out.println("ped.getItemsPedido()");
            var itemsPedido = ped.getItemsPedido();
            printMap(itemsPedido);
            
            System.out.println("ped.getItemsPedido(restaurante)");
            printMap(ped.getItemsPedido(rest)); 
                    
            
            System.out.println();
            
            System.out.println("Pedido.getPratosItems(itemsPedido)");
            printMap(Pedido.getPratosItems(itemsPedido)); 
            
            System.out.println("Pedido.getBebidasItems(itemsPedido)");
            printMap(Pedido.getBebidasItems(itemsPedido)); 
            
            
            System.out.println();
            
            System.out.println("toString() - Cliente, Pedido");
            printObjects(cliente, ped);
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
