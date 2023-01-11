package orderngo.exceptions;

/**
 *
 * @author grupo1
 */
public class ItemCardapioNotFoundException extends IllegalArgumentException
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

    
    public String getEmailRestauranteProcurado()
    {
        return emailRestauranteProcurado;
    }

    public String getNomeProcurado()
    {
        return nomeProcurado;
    }
    
    public boolean isApenasVisiveis()
    {
        return apenasVisiveis;
    }
}
