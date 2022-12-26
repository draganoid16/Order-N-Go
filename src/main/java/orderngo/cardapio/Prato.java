package orderngo.cardapio;

/**
 *
 * @author grupo1
 */
public class Prato extends ItemCardapio
{
    public static enum TipoPrato
    {
        CARNE, PEIXE, VEGETARIANO, VEGANO
    }

    private final TipoPrato tipoPrato;
    private String alergenios;

    public Prato(String nome, String detalhes, TipoPrato tipoPrato, String alergenicos)
    {
        super(nome, detalhes);
        
        if (tipoPrato == null)
            throw new IllegalArgumentException("Tipo de prato invalido!");
        
        this.tipoPrato = tipoPrato;
        
        setAlergenios(alergenios);
    }

    //<editor-fold defaultstate="collapsed" desc="Getters">
    public TipoPrato getTipoPrato()
    {
        return tipoPrato;
    }
    
    
    public String getAlergenios()
    {
        return alergenios;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Setters">
    public void setAlergenios(String alergenios)
    {
        if (alergenios == null)
            throw new IllegalArgumentException("Alergenios invalido!");
        
        this.alergenios = alergenios;
    }
    //</editor-fold>
    
    
}
