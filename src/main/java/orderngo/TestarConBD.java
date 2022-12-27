package orderngo;

import orderngo.cardapio.*;
import orderngo.utilizador.*;

import java.sql.SQLException;

public class TestarConBD 
{
    public static String stackTraceToString(Exception ex)
    {
        StringBuilder sb = new StringBuilder();
        
        for (StackTraceElement elem : ex.getStackTrace())
        {
            sb.append(elem).append("\n");
        }
        
        return sb.toString();
    }
    
    public static void printSQLException(SQLException sqle)
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
        Restaurante[] rests = new Restaurante[0];
        GestorOrderAndGo gestor1 = new GestorOrderAndGo("nao@esta.bem", "nao esta bem", "123456789", "nao esta bem", 404);
        Restaurante rest1 = new Restaurante("nao@esta.bem", "nao esta bem", "123456789", "nao esta bem");
        
        try
        {
            rests = Restaurante.all();
            
            for (Restaurante r : rests)
            {
                Cardapio.fillFrom(r);
            }
            
            gestor1 = GestorOrderAndGo.getGestor("g1@g1.g1");
            rest1 = Restaurante.getRestaurante("r1@r1.r1");
        }
        catch (SQLException sqle)
        {
            printSQLException(sqle);
        }
        
        System.out.printf(
            "Restaurantes:\n%-20s | %-20s | %-20s | %-9s\n", 
            "Email", 
            "Nome", 
            "Morada", 
            "Telemovel"
        );
        for (Restaurante r : rests)
        {
            System.out.printf(
                "%-20s | %-20s | %-20s | %-9s\n",
                r.getEmail(),
                r.getNome(),
                r.getMorada(),
                r.getTelemovel()
            );
        }
        System.out.println();
        
        System.out.printf(
            "Cardapios:\n%-20s | %-20s | %14s | %-20s | %-20s | %-20s\n", 
            "Nome", 
            "Detalhes",
            "Preco Unitario",
            "Tipo_Prato", 
            "Alergenios_Prato",
            "CapacidadeCL_Bebida"
        );
        for (Restaurante r : rests)
        {
            System.out.printf(
                "%-20s%-108s\n", 
                r.getNome(),
                "-".repeat(108)
            );
            
            for (ItemCardapio item : r.getCardapio().getAllItems())
            {
                boolean isPrato = item instanceof Prato;
                
                System.out.printf(
                    "%-20s | %-20s | %14.2f | %-20s | %-20s | %-20s\n", 
                    item.getNome(), 
                    item.getDetalhes(), 
                    item.getPrecoUnitario(),
                    !isPrato ? "" : ((Prato)item).getTipoPrato(), 
                    !isPrato ? "" : ((Prato)item).getAlergenios(), 
                    isPrato ? "" : ((Bebida)item).getCapacidadeCL() 
                );
            }
        }
        System.out.println();
        
        
        System.out.printf(
            "Gestor 1:\nEmail: %s\nNome: %s\nMorada: %s\nTelemovel: %s\nNrEmpregado: %d\n",
            gestor1.getEmail(),
            gestor1.getNome(),
            gestor1.getMorada(),
            gestor1.getTelemovel(),
            gestor1.getNrEmpregado()
        );
        System.out.println();
        
        System.out.printf(
            "Restaurante 1:\nEmail: %s\nNome: %s\nMorada: %s\nTelemovel: %s\n",
            rest1.getEmail(),
            rest1.getNome(),
            rest1.getMorada(),
            rest1.getTelemovel()
        );
        System.out.println();
        
        
        try
        {
            System.out.println(GestorOrderAndGo.validCredentials("g1@g1.g1", "incorreta"));
            System.out.println(GestorOrderAndGo.validCredentials("g1@g1.g1", "1"));
            System.out.println(GestorOrderAndGo.validCredentials("r2@r2.r2", "2"));
            System.out.println();
            
            System.out.println(Restaurante.validCredentials("r1@r1.r1", "incorreta"));
            System.out.println(Restaurante.validCredentials("r1@r1.r1", "1"));
            System.out.println(Restaurante.validCredentials("g2@g2.g2", "2"));
            System.out.println();
        }
        catch (SQLException sqle)
        {
            printSQLException(sqle);
        }
    }
    
    public static void main(String[] args) 
    {
        TestarConBD t = new TestarConBD();
        t.main();
    }
}
