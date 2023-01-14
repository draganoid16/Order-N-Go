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
        Cliente cliente = new Cliente("c1@c1.c1", "c1", "111111111", "Morada 1", "111111111");
        cliente.setPassword("1".toCharArray());

        
        try
        {        
            /* Lançam exceções (não será implementado neste projeto)
            cliente.save(); // throw UnsupportedOperationException
            cliente.delete(); // throw UnsupportedOperationException
            */
            
            // Busca dados visiveis da BD
            System.out.println("Cliente.all() [apenasVisiveis = true]");
            printArray(Cliente.all());
            
            // Busca dados da BD (visiveis/invisiveis "removidos")
            System.out.println("Cliente.all(false)");
            printArray(Cliente.all(false));
            
            // Obtem cliente
            cliente = Cliente.getCliente(cliente.getEmail());
            
            System.out.println("toString() - Cliente");
            printObjects(cliente);
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
