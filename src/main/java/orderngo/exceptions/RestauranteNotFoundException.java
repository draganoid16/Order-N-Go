package orderngo.exceptions;

/**
 *
 * @author grupo1
 */
public class RestauranteNotFoundException extends IllegalArgumentException
{
    private final String emailProcurado;

    public RestauranteNotFoundException(String emailProcurado)
    {
        super(String.format("Nao existe restaurante com email %s", emailProcurado));
        this.emailProcurado = emailProcurado;
    }

    public String getEmailProcurado()
    {
        return emailProcurado;
    }
}
