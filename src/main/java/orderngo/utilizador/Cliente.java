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

    //<editor-fold defaultstate="collapsed" desc="Save">
    @Override
    public void save() throws SQLException
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    //</editor-fold>
    
    
    @Override
    public boolean equals(Object obj)
    {
        if (!super.equals(obj))
            return false;
        
        if (!(obj instanceof Cliente))
            return false;
        
        Cliente other = (Cliente)obj;
        
        return nif.equals(other.nif);
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
}
