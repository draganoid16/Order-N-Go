package orderngo.exception;

/**
 *
 * @author grupo1
 */
public class UtilizadorNotFoundException extends IllegalArgumentException implements IsApenasVisiveisInException
{
    private final String emailProcurado;
    private final boolean apenasVisiveis;

    public UtilizadorNotFoundException(String emailProcurado, boolean apenasVisiveis, String tipoUtilizador)
    {
        super(String.format("Nao existe %s com email %s", tipoUtilizador, emailProcurado));
        this.emailProcurado = emailProcurado;
        this.apenasVisiveis = apenasVisiveis;
    }
    public UtilizadorNotFoundException(String emailProcurado, String tipoUtilizador)
    {
        this(emailProcurado, true, tipoUtilizador); 
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
