package orderngo.utilizador;

import orderngo.basedados.ConectorBD;
import orderngo.utils.PasswordUtils;

import java.sql.ResultSet;

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
    private static GestorOrderAndGo criarGestor(ResultSet result) throws SQLException
    {
        GestorOrderAndGo g = new GestorOrderAndGo(
            result.getString("email"),
            result.getString("nome"),
            result.getString("telemovel"),
            result.getString("morada"),
            result.getInt("nrEmpregado")
        );
        
        g.setPasswordEncriptada(result.getString("palavraPasse"));
        
        return g;
    }
    
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
            
            g = criarGestor(result);
        }
        
        return g;
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Save">
    @Override
    public void save() throws SQLException
    {
        var cbd = ConectorBD.getInstance();
    
        try
        {
            // verifica se o gestor existe
            getGestor(getEmail());
            
            // update
            var ps = cbd.prepareStatement("UPDATE gestorog SET telemovel = ?, morada = ?, palavraPasse = ? WHERE email = ?");
            ps.setString(1, getTelemovel());
            ps.setString(2, getMorada());
            ps.setString(3, getPasswordEncriptada());
            ps.setString(4, getEmail());
            
            cbd.executePreparedUpdate(ps);
        }
        catch (GestorNotFoundException gnfe)
        {
            // insert
            var ps = cbd.prepareStatement("INSERT INTO gestorog(email, nome, telemovel, morada, nrEmpregado, palavraPasse) VALUES (?, ?, ?, ?, ?, ?)");
            ps.setString(1, getEmail());
            ps.setString(2, getNome());
            ps.setString(3, getTelemovel());
            ps.setString(4, getMorada());
            ps.setInt(5, nrEmpregado);
            ps.setString(6, getPasswordEncriptada());

            cbd.executePreparedUpdate(ps);
        }
    }
    //</editor-fold>
    
    public static boolean validarCredenciais(String email, char[] password) throws SQLException
    {
        try
        {
            String encriptada = getGestor(email).getPasswordEncriptada();
            return PasswordUtils.verificarPassword(password, encriptada);
        }
        catch (GestorNotFoundException gnfe)
        {
            return false;
        }
    }

    
    @Override
    public boolean equals(Object obj)
    {
        if (!super.equals(obj))
            return false;
        
        if (!(obj instanceof GestorOrderAndGo))
            return false;
        
        GestorOrderAndGo other = (GestorOrderAndGo)obj;
        
        return nrEmpregado == other.nrEmpregado;
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("GestorOrderAndGo{");
        sb.append(super.toString());
        sb.append(", nrEmpregado=").append(nrEmpregado);
        sb.append('}');
        return sb.toString();
    }
}
