package orderngo.utilizador;

/**
 *
 * @author grupo1
 */
public abstract class Utilizador
{
    private final String email;
    private String nome;
    private String telemovel;
    private String morada;

    public Utilizador(String email, String nome, String telemovel, String morada)
    {
        if (email == null || !email.matches("^[\\w\\-\\.]+@[\\w\\-]+\\.[\\w\\-\\.]+$"))
            throw new IllegalArgumentException("Email invalido!");
        this.email = email;
        
        setNome(nome);
        setTelemovel(telemovel);
        setMorada(morada);
    }
        
    //<editor-fold defaultstate="collapsed" desc="Getters">
    public String getEmail()
    {
        return email;
    }

    public String getNome()
    {
        return nome;
    }
    
    public String getTelemovel()
    {
        return telemovel;
    }
    
    public String getMorada()
    {
        return morada;
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Setters">
    public void setNome(String nome)
    {
        if (nome == null || nome.isBlank())
            throw new IllegalArgumentException("Nome invalido!");
        
        this.nome = nome;
    }

    public void setTelemovel(String telemovel)
    {
        if (!telemovel.matches("\\d{9}"))
            throw new IllegalArgumentException("Telemovel invalido!");
        
        this.telemovel = telemovel;
    }

    public void setMorada(String morada)
    {
        if (morada == null || morada.isBlank())
            throw new IllegalArgumentException("Morada invalida!");
        
        this.morada = morada;
    }
    //</editor-fold>

    
    @Override
    public boolean equals(Object obj)
    {
        if (obj == this)
            return true;
        
        if (obj == null)
            return false;
        
        if (!(obj instanceof Utilizador))
            return false;
        
        Utilizador other = (Utilizador)obj;
        
        if (!getEmail().equals(other.getEmail()))
            return false;
        
        if (!getNome().equals(other.getNome()))
            return false;
        
        if (!getTelemovel().equals(other.getTelemovel()))
            return false;
        
        return getMorada().equals(other.getMorada());
    }

    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 79 * hash + getEmail().hashCode();
        return hash;
    }
}
