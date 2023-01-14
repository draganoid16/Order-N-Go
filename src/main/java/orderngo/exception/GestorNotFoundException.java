package orderngo.exception;

/**
 *
 * @author grupo1
 */
public class GestorNotFoundException extends UtilizadorNotFoundException
{
    public GestorNotFoundException(String emailProcurado, boolean apenasVisiveis)
    {
        super(emailProcurado, apenasVisiveis, "gestor");
    }
    public GestorNotFoundException(String emailProcurado)
    {
        this(emailProcurado, true); 
    }
}