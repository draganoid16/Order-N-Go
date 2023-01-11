package orderngo.exceptions;

/**
 *
 * @author grupo1
 */
public class RestauranteNotFoundException extends IllegalArgumentException
{
    private final String emailProcurado;
    private final boolean apenasVisiveis;

    public RestauranteNotFoundException(String emailProcurado, boolean apenasVisiveis)
    {
        super(String.format("Nao existe restaurante com email %s", emailProcurado));
        this.emailProcurado = emailProcurado;
        this.apenasVisiveis = apenasVisiveis;
    }
    public RestauranteNotFoundException(String emailProcurado)
    {
        this(emailProcurado, true);
    }
    

    public String getEmailProcurado()
    {
        return emailProcurado;
    }
    
    public boolean isApenasVisiveis()
    {
        return apenasVisiveis;
    }
}
