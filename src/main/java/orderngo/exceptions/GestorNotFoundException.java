package orderngo.exceptions;

/**
 *
 * @author grupo1
 */
public class GestorNotFoundException extends IllegalArgumentException
{
    private final String emailProcurado;
    private final boolean apenasVisiveis;

    public GestorNotFoundException(String emailProcurado, boolean apenasVisiveis)
    {
        super(String.format("Nao existe gestor com email %s", emailProcurado));
        this.emailProcurado = emailProcurado;
        this.apenasVisiveis = apenasVisiveis;
    }
    public GestorNotFoundException(String emailProcurado)
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
