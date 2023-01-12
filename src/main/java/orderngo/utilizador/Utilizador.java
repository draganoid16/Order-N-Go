package orderngo.utilizador;

import orderngo.basedados.SavableInDatabase;
import orderngo.basedados.DeletableInDatabase;
import orderngo.utils.PasswordUtils;

/**
 *
 * @author grupo1
 */
public abstract class Utilizador implements SavableInDatabase, DeletableInDatabase
{
    private final String email;
    private String nome;
    private String telemovel;
    private String morada;
    private String passwordEncriptada;

    public Utilizador(String email, String nome, String telemovel, String morada)
    {
        if (email == null || !email.matches("^[\\w\\-\\.]+@[\\w\\-]+\\.[\\w\\-\\.]+$"))
            throw new IllegalArgumentException("Email invalido!");
        this.email = email;
        
        setNome(nome);
        setTelemovel(telemovel);
        setMorada(morada);
        setPasswordEncriptada(null);
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
    
    public String getPasswordEncriptada()
    {
        return passwordEncriptada;
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

    public void setPasswordEncriptada(String passwordEncriptada)
    {
        if (passwordEncriptada != null && !PasswordUtils.isBCryptHash(passwordEncriptada))
            throw new IllegalArgumentException("Password encriptada nao e uma BCrypt hash!");
        
        this.passwordEncriptada = passwordEncriptada;
    }
    
    public void setPassword(char[] password)
    {
        setPasswordEncriptada(PasswordUtils.encriptarPassword(password));
    }
    //</editor-fold>
    
    
    //<editor-fold defaultstate="collapsed" desc="equals/hashCode/toString">
    @Override
    public boolean equals(Object obj)
    {
        if (obj == this)
            return true;
        
        if (obj == null)
            return false;
        
        if (!(obj instanceof Utilizador other))
            return false;
        
        
        if (!email.equals(other.email))
            return false;
        
        if (!nome.equals(other.nome))
            return false;
        
        if (!telemovel.equals(other.telemovel))
            return false;
        
        return morada.equals(other.morada);
    }

    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 79 * hash + email.hashCode();
        return hash;
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("Utilizador{");
        sb.append("email=").append(email);
        sb.append(", nome=").append(nome);
        sb.append(", telemovel=").append(telemovel);
        sb.append(", morada=").append(morada);
        sb.append('}');
        return sb.toString();
    }
    //</editor-fold>
}
