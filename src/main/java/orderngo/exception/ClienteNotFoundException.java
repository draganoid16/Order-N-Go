package orderngo.exception;

/**
 *
 * @author grupo1
 */
public class ClienteNotFoundException extends UtilizadorNotFoundException
{
    public ClienteNotFoundException(String emailProcurado, boolean apenasVisiveis)
    {
        super(emailProcurado, apenasVisiveis, "cliente");
    }
    public ClienteNotFoundException(String emailProcurado)
    {
        this(emailProcurado, true); 
    }
}
