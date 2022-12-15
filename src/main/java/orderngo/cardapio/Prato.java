package orderngo.cardapio;

/**
 *
 * @author grupo1
 */
public class Prato extends ItemCardapio
{
    private final TipoPrato tipoPrato;
    private String alergenicos;

    public Prato(String nome, String detalhes, TipoPrato tipoPrato, String alergenicos)
    {
        super(nome, detalhes);
        
        if (tipoPrato == null)
            throw new IllegalArgumentException("Tipo de prato invalido!");
        
        this.tipoPrato = tipoPrato;
        
        setAlergenicos(alergenicos);
    }

    //<editor-fold defaultstate="collapsed" desc="Getters">
    public TipoPrato getTipoPrato()
    {
        return tipoPrato;
    }
    
    
    public String getAlergenicos()
    {
        return alergenicos;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Setters">
    public void setAlergenicos(String alergenicos)
    {
        if (alergenicos == null || alergenicos.isBlank())
            throw new IllegalArgumentException("Alergenicos invalido!");
        
        this.alergenicos = alergenicos;
    }
    //</editor-fold>
    
    public static enum TipoPrato
    {
        CARNE, PEIXE, VEGETARIANO, VEGANO
    }
}
