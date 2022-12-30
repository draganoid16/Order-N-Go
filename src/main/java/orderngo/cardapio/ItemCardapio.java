package orderngo.cardapio;

import java.awt.image.BufferedImage;

/**
 *
 * @author grupo1
 */
public abstract class ItemCardapio
{
    private final String nome;
    private String detalhes;
    private float precoUnitario;
    private BufferedImage imagem;

    public ItemCardapio(String nome, String detalhes, float precoUnitario)
    {
        if (nome == null || nome.isBlank())
            throw new IllegalArgumentException("Nome invalido!");
        
        this.nome = nome;
        
        setDetalhes(detalhes);
        setPrecoUnitario(precoUnitario);
        setImagem(null);
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

    public float getPrecoUnitario()
    {
        return precoUnitario;
    }
    
    public BufferedImage getImagem()
    {
        return imagem;
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Setters">
    public void setDetalhes(String detalhes)
    {
        if (detalhes == null || detalhes.isBlank())
            throw new IllegalArgumentException("Detalhes invalido!");
        
        this.detalhes = detalhes;
    }
    
    public void setPrecoUnitario(float precoUnitario)
    {
        if (precoUnitario <= 0)
            throw new IllegalArgumentException("Preco unitario invalido!");
        
        this.precoUnitario = precoUnitario;
    }
    
    public void setImagem(BufferedImage imagem)
    {
        this.imagem = imagem;
    }
    //</editor-fold>

}
