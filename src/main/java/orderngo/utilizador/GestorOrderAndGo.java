package orderngo.utilizador;

import orderngo.basedados.ConectorBD;
import org.apache.commons.codec.digest.DigestUtils;
import java.sql.SQLException;
import orderngo.exceptions.GestorNotFoundException;

/**
 *
 * @author grupo1
 */
public class GestorOrderAndGo extends Utilizador
{
    private final int nrEmpregado;
    
    public GestorOrderAndGo(String email, String nome, String telemovel, String morada, int nrEmpregado)
    {
        super(email, nome, telemovel, morada);
        
        if (nrEmpregado <= 0)
            throw new IllegalArgumentException("Numero de empregado invalido!");
            
        this.nrEmpregado = nrEmpregado;
    }

    //<editor-fold defaultstate="collapsed" desc="Getters">
    public int getNrEmpregado()
    {
        return nrEmpregado;
    }
    //</editor-fold>
    
    
    public static GestorOrderAndGo getGestor(String email) throws SQLException, GestorNotFoundException
    {
        var cbd = ConectorBD.getInstance();
        var ps = cbd.prepareStatement("SELECT * FROM gestorog WHERE email = ?");
        ps.setString(1, email);
        var result = cbd.executePreparedQuery(ps);
        
        if (!result.next())
            throw new GestorNotFoundException(email);
        
        return new GestorOrderAndGo(
            result.getString("email"), 
            result.getString("nome"), 
            result.getString("telemovel"),
            result.getString("morada"),
            result.getInt("nrEmpregado")
        );
    }
    
    public static boolean validCredentials(String email, String password) throws SQLException
    {
        String encriptedPassword = new DigestUtils("SHA3-256").digestAsHex(password);
        
        var cbd = ConectorBD.getInstance();
        var ps = cbd.prepareStatement("SELECT * FROM gestorog WHERE email = ? AND palavraPasse = ?");
        ps.setString(1, email);
        ps.setString(2, encriptedPassword);
        var result = cbd.executePreparedQuery(ps);
        
        return result.next();
    }
}
