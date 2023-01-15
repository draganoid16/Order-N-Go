package orderngo.utilizador;

import orderngo.basedados.ConectorBD;

import java.sql.ResultSet;
import java.util.ArrayList;

import java.sql.SQLException;
import orderngo.exception.ClienteNotFoundException;

/**
 *
 * @author grupo1
 */
public class Cliente extends Utilizador
{
    private final String nif;

    public Cliente(String email, String nome, String telemovel, String morada, String nif)
    {
        super(email, nome, telemovel, morada);
        
        if (nif == null || !nif.matches("\\d{9}"))
            throw new IllegalArgumentException("Nif invalido!");
        
        this.nif = nif;
    }
    
    //<editor-fold defaultstate="collapsed" desc="Getters">
    public String getNif()
    {
        return nif;
    }
    //</editor-fold>

    
    //<editor-fold defaultstate="collapsed" desc="BuscarDados">
    public static Cliente criarCliente(ResultSet result) throws SQLException
    {
        Cliente c = new Cliente(
            result.getString("email"),
            result.getString("nome"),
            result.getString("telemovel"),
            result.getString("morada"),
            result.getString("nif")
        );
        
        c.setPasswordEncriptada(result.getString("palavraPasse"));
        
        return c;
    }
    
    public static Cliente[] all(boolean apenasVisiveis) throws SQLException
    {
        StringBuilder sql = new StringBuilder("SELECT * FROM cliente");
        if (apenasVisiveis) sql.append(" WHERE visivel = true");
        
        ArrayList<Cliente> clientes = new ArrayList<>();
        
        try (ResultSet result = ConectorBD.getInstance().executeQuery(sql.toString()))
        {
            while (result.next())
            {
                clientes.add(criarCliente(result));
            }
        }
        
        return clientes
            .toArray(Cliente[]::new);
    }
    public static Cliente[] all() throws SQLException
    {
        return all(true);
    }
    
    public static Cliente getCliente(String email, boolean apenasVisiveis) throws SQLException, ClienteNotFoundException
    {
        StringBuilder sql = new StringBuilder("SELECT * FROM cliente WHERE email = ?");
        if (apenasVisiveis) sql.append(" AND visivel = true");
        
        var cbd = ConectorBD.getInstance();
        var ps = cbd.prepareStatement(sql.toString());
        ps.setString(1, email);
        
        Cliente c;
        try (ResultSet result = cbd.executePreparedQuery(ps))
        {
            if (!result.next())
                throw new ClienteNotFoundException(email, apenasVisiveis);
            
            c = criarCliente(result);
        }
        
        return c;
    }
    public static Cliente getCliente(String email) throws SQLException, ClienteNotFoundException
    {
        return getCliente(email, true);
    }
    //</editor-fold>
    
    
    @Override
    public void save() throws SQLException
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void delete() throws SQLException
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    
    //<editor-fold defaultstate="collapsed" desc="equals/hashCode/toString">
    @Override
    public boolean canEqual(Object obj) 
    {
        return (obj instanceof Cliente);
    }
    
    @Override
    public boolean equals(Object obj)
    {
        if (obj == this)
            return true;
        
        if (!(obj instanceof Cliente other))
            return false;
        
        
        if (!other.canEqual(this))
            return false;
        
        if (!nif.equals(other.nif))
            return false;
        
        return super.equals(obj);
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("Cliente{");
        sb.append(super.toString());
        sb.append(", nif=").append(nif);
        sb.append('}');
        return sb.toString();
    }
    //</editor-fold>
}
