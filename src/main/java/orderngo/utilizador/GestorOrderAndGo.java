package orderngo.utilizador;

import orderngo.basedados.ConectorBD;
import orderngo.utils.PasswordUtils;

import java.util.ArrayList;
import java.sql.ResultSet;

import java.sql.SQLException;
import orderngo.exception.GestorNotFoundException;

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
    public static GestorOrderAndGo criarGestor(ResultSet result) throws SQLException
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
    
    public static GestorOrderAndGo[] all(boolean apenasVisiveis) throws SQLException
    {
        StringBuilder sql = new StringBuilder("SELECT * FROM gestorog");
        if (apenasVisiveis) sql.append(" WHERE visivel = true");
        
        ArrayList<GestorOrderAndGo> gests = new ArrayList<>();
        
        try (ResultSet result = ConectorBD.getInstance().executeQuery(sql.toString()))
        {
            while (result.next())
            {
                gests.add(criarGestor(result));
            }
        }
        
        return gests
            .toArray(GestorOrderAndGo[]::new);
    }
    public static GestorOrderAndGo[] all() throws SQLException
    {
        return all(true);
    }
    
    public static GestorOrderAndGo getGestor(String email, boolean apenasVisiveis) throws SQLException, GestorNotFoundException
    {
        StringBuilder sql = new StringBuilder("SELECT * FROM gestorog WHERE email = ?");
        if (apenasVisiveis) sql.append(" AND visivel = true");
        
        var cbd = ConectorBD.getInstance();
        var ps = cbd.prepareStatement(sql.toString());
        ps.setString(1, email);
        
        GestorOrderAndGo g;
        try (ResultSet result = cbd.executePreparedQuery(ps))
        {
            if (!result.next())
                throw new GestorNotFoundException(email, apenasVisiveis);
            
            g = criarGestor(result);
        }
        
        return g;
    }
    public static GestorOrderAndGo getGestor(String email) throws SQLException, GestorNotFoundException
    {
        return getGestor(email, true);
    }
    //</editor-fold>
    
    @Override
    public void save() throws SQLException
    {
        if (!PasswordUtils.isBCryptHash(getPasswordEncriptada()))
            throw new IllegalStateException("GestorOrderAndGo com password inválida!");
        
        var cbd = ConectorBD.getInstance();
    
        try
        {
            // verifica se o gestor existe (visivel ou invisivel)
            getGestor(getEmail(), false);
            
            // update
            var ps = cbd.prepareStatement("UPDATE gestorog SET telemovel = ?, morada = ?, palavraPasse = ?, visivel = true WHERE email = ?");
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
    
    @Override
    public void delete() throws SQLException
    {
        var cbd = ConectorBD.getInstance();

        // "delete" - visivel passa de true para false
        var ps = cbd.prepareStatement("UPDATE gestorog SET visivel = false WHERE email = ?");
        ps.setString(1, getEmail());

        cbd.executePreparedUpdate(ps);
    }
    
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

    
    //<editor-fold defaultstate="collapsed" desc="equals/hashCode/toString">
    @Override
    public boolean canEqual(Object obj) 
    {
        return (obj instanceof GestorOrderAndGo);
    }
    
    @Override
    public boolean equals(Object obj)
    {
        if (obj == this)
            return true;
        
        if (!(obj instanceof GestorOrderAndGo other))
            return false;
        
        
        if (!other.canEqual(this))
            return false;
        
        if (nrEmpregado != other.nrEmpregado)
            return false;
        
        return super.equals(obj);
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
    //</editor-fold>
}
