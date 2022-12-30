package orderngo.utilizador;

import orderngo.basedados.ConectorBD;
import java.sql.ResultSet;
import orderngo.basedados.BaseDadosUtils;

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
    
    
    //<editor-fold defaultstate="collapsed" desc="BuscarDados">
    public static GestorOrderAndGo getGestor(String email) throws SQLException, GestorNotFoundException
    {
        var cbd = ConectorBD.getInstance();
        var ps = cbd.prepareStatement("SELECT * FROM gestorog WHERE email = ?");
        ps.setString(1, email);
        
        GestorOrderAndGo g;
        try (ResultSet result = cbd.executePreparedQuery(ps))
        {
            if (!result.next())
                throw new GestorNotFoundException(email);
            
            g = new GestorOrderAndGo(
                result.getString("email"),
                result.getString("nome"),
                result.getString("telemovel"),
                result.getString("morada"),
                result.getInt("nrEmpregado")
            );
        }
        
        return g;
    }
    //</editor-fold>
    
    public static boolean validCredentials(String email, String password) throws SQLException
    {
        String encriptedPassword = BaseDadosUtils.encriptarPassword(password);
        
        var cbd = ConectorBD.getInstance();
        var ps = cbd.prepareStatement("SELECT * FROM gestorog WHERE email = ? AND palavraPasse = ?");
        ps.setString(1, email);
        ps.setString(2, encriptedPassword);
        
        boolean isValid;
        try (ResultSet result = cbd.executePreparedQuery(ps))
        {
            isValid = result.next();
        }
        return isValid;
    }
}
