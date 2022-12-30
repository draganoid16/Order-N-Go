package orderngo;

import java.awt.image.BufferedImage;
import orderngo.utilizador.*;

import java.io.FileInputStream;

import orderngo.basedados.ConectorBD;
import orderngo.basedados.BaseDadosUtils;

import java.sql.SQLException;
import java.io.FileNotFoundException;

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
        Restaurante rest10 = new Restaurante("nao@esta.bem", "nao esta bem", "123456789", "nao esta bem");
        
        try
        {
            var con = ConectorBD.getInstance();
            
            // remover r10
            var ps = con.prepareStatement("DELETE FROM restaurante WHERE email = ?");
            ps.setString(1, "r10@r10.r10");
            
            con.executePreparedUpdate(ps);
            
            // adicionar r10
            ps = con.prepareStatement("INSERT INTO restaurante(email, nome, morada, telemovel, palavraPasse, imagem) VALUES (?,?,?,?,?,?)");

            ps.setString(1, "r10@r10.r10");
            ps.setString(2, "r10");
            ps.setString(3, "Morada 10");
            ps.setString(4, "101010101");
            ps.setString(5, BaseDadosUtils.encriptarPassword("10"));
            try
            {
                // imagem food-tray.png, para testar
                ps.setBlob(6, new FileInputStream("src\\imageresources\\food-tray.png"));
            }
            catch (FileNotFoundException fnfe) {}
            
            con.executePreparedUpdate(ps);

            // obtem r10
            rest10 = Restaurante.getRestaurante("r10@r10.r10");
        }
        catch (SQLException sqle)
        {
            printSQLException(sqle);
        }
        
        BufferedImage bimagem = rest10.getImagem();
        System.out.println(bimagem);
    }
    
    public static void main(String[] args) 
    {
        TestarConBD t = new TestarConBD();
        t.main();
    }
}
