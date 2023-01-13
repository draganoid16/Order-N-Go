package orderngo.utilizador;

import java.sql.SQLException;

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
