package orderngo.cardapio;

/**
 *
 * @author grupo1
 */
public abstract class ItemCardapio
{
    private final String nome;
    private String detalhes;

    public ItemCardapio(String nome, String detalhes)
    {
        if (nome == null || nome.isBlank())
            throw new IllegalArgumentException("Nome invalido!");
        
        this.nome = nome;
        
        setDetalhes(detalhes);
    }

    //<editor-fold defaultstate="collapsed" desc="Getters">
    public String getNome()    
    {
        return nome;
    }

    public String getDetalhes()
    {
        return detalhes;
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Setters">
    public void setDetalhes(String detalhes)
    {
        if (detalhes == null || detalhes.isBlank())
            throw new IllegalArgumentException("Detalhes invalido!");
        
        this.detalhes = detalhes;
    }
    //</editor-fold>
}
