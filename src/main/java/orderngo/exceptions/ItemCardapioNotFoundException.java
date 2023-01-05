package orderngo.exceptions;

/**
 *
 * @author grupo1
 */
public class ItemCardapioNotFoundException extends IllegalArgumentException
{
    private final String emailRestauranteProcurado;
    private final String nomeProcurado;

    public ItemCardapioNotFoundException(String emailRestauranteProcurado, String nomeProcurado)
    {
        super(String.format("Nao existe item com emailRestaurante %s e nome %s", emailRestauranteProcurado, nomeProcurado));
        
        this.emailRestauranteProcurado = emailRestauranteProcurado;
        this.nomeProcurado = nomeProcurado;
    }

    public String getEmailRestauranteProcurado()
    {
        return emailRestauranteProcurado;
    }

    public String getNomeProcurado()
    {
        return nomeProcurado;
    }
}
