package orderngo.utilizador;

import orderngo.cardapio.Cardapio;

/**
 *
 * @author grupo
 */
public class Restaurante extends Utilizador
{
    private final Cardapio cardapio;
    
    public Restaurante(String email, String nome, String telemovel, String morada, Cardapio cardapio)
    {
        super(email, nome, telemovel, morada);
        
        if (cardapio == null)
            throw new IllegalArgumentException("Cardapio invalido!");
        
        this.cardapio = cardapio;
    }
    
    public Restaurante(String email, String nome, String telemovel, String morada)
    {
        this(email, nome, telemovel, morada, new Cardapio());
    }

    //<editor-fold defaultstate="collapsed" desc="Getters">
    public Cardapio getCardapio()
    {
        return cardapio;
    }
    //</editor-fold>
}
