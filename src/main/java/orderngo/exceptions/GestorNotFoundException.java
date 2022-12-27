package orderngo.exceptions;

/**
 *
 * @author grupo1
 */
public class GestorNotFoundException extends IllegalArgumentException
{
    private final String emailProcurado;

    public GestorNotFoundException(String emailProcurado)
    {
        super(String.format("Nao existe gestor com email %s", emailProcurado));
        this.emailProcurado = emailProcurado;
    }

    public String getEmailProcurado()
    {
        return emailProcurado;
    }
}
