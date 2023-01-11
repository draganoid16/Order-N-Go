package orderngo.exception;

/**
 *
 * @author grupo1
 */
public class GestorNotFoundException extends IllegalArgumentException implements IsApenasVisiveisInException
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

    
    //<editor-fold defaultstate="collapsed" desc="Getters">
    public String getEmailProcurado()
    {
        return emailProcurado;
    }

    @Override
    public boolean isApenasVisiveis()
    {
        return apenasVisiveis;
    }
    //</editor-fold>
}
