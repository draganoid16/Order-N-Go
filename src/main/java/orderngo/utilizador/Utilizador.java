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
}
