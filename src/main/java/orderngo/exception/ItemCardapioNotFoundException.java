package orderngo.exception;

/**
 *
 * @author grupo1
 */
public class ItemCardapioNotFoundException extends IllegalArgumentException implements IsApenasVisiveisInException
{
    private final String emailRestauranteProcurado;
    private final String nomeProcurado;
    private final boolean apenasVisiveis;

    public ItemCardapioNotFoundException(String emailRestauranteProcurado, String nomeProcurado, boolean apenasVisiveis)
    {
        super(String.format("Nao existe item com emailRestaurante %s e nome %s", emailRestauranteProcurado, nomeProcurado));
        
        this.emailRestauranteProcurado = emailRestauranteProcurado;
        this.nomeProcurado = nomeProcurado;
        this.apenasVisiveis = apenasVisiveis;
    }
    public ItemCardapioNotFoundException(String emailRestauranteProcurado, String nomeProcurado)
    {
        this(emailRestauranteProcurado, nomeProcurado, true);
    }

    
    //<editor-fold defaultstate="collapsed" desc="Getters">
    public String getEmailRestauranteProcurado()
    {
        return emailRestauranteProcurado;
    }

    public String getNomeProcurado()
    {
        return nomeProcurado;
    }
    
    @Override
    public boolean isApenasVisiveis()
    {
        return apenasVisiveis;
    }
    //</editor-fold>
}
