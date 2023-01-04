package orderngo;

import orderngo.basedados.*;
import orderngo.cardapio.*;
import orderngo.exceptions.*;
import orderngo.utilizador.*;

import java.sql.SQLException;
import java.util.Arrays;

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
    
    
    public void main()
    {
        try
        {
            Restaurante r = Restaurante.getRestaurante("r1@r1.r1");
            Cardapio c = r.getCardapio();
            c.fill();
            
            Arrays.toString(c.getAllPratos());
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
