package orderngo.exception;

/**
 *
 * @author grupo1
 */
public class RestauranteNotFoundException extends UtilizadorNotFoundException
{
    public RestauranteNotFoundException(String emailProcurado, boolean apenasVisiveis)
    {
        super(emailProcurado, apenasVisiveis, "restaurante");
    }
    public RestauranteNotFoundException(String emailProcurado)
    {
        this(emailProcurado, true); 
    }
}