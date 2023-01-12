package orderngo.cardapio;

import orderngo.utilizador.Restaurante;
import orderngo.basedados.SavableInDatabase;
import orderngo.basedados.DeletableInDatabase;

import java.awt.image.BufferedImage;
import java.util.Objects;

/**
 *
 * @author grupo1
 */
public abstract class ItemCardapio implements SavableInDatabase, DeletableInDatabase
{
    private final Restaurante restaurante;
    private final String nome;
    private String detalhes;
    private float precoUnitario;
    private BufferedImage imagem;

    public ItemCardapio(Restaurante restaurante, String nome, String detalhes, float precoUnitario)
    {
        if (nome == null || nome.isBlank())
            throw new IllegalArgumentException("Nome invalido!");
        
        this.nome = nome;
        
        if (restaurante == null)
            throw new IllegalArgumentException("Restaurante invalido!");
        
        this.restaurante = restaurante;
        
        setDetalhes(detalhes);
        setPrecoUnitario(precoUnitario);
        setImagem(null);
    }

    //<editor-fold defaultstate="collapsed" desc="Getters">
    public Restaurante getRestaurante()
    {
        return restaurante;
    }
    
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

    
    //<editor-fold defaultstate="collapsed" desc="equals/hashCode/toString">
    @Override
    public boolean equals(Object obj)
    {
        if (obj == this)
            return true;
        
        if (obj == null)
            return false;
        
        if (!(obj instanceof ItemCardapio other))
            return false;
        
        
        if (!restaurante.equals(other.restaurante))
            return false;
        
        if (!nome.equals(other.nome))
            return false;
        
        if (!detalhes.equals(other.detalhes))
            return false;

        if (precoUnitario != other.precoUnitario)
            return false;
        
        return Objects.equals(imagem, other.imagem);
    }

    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 89 * hash + restaurante.hashCode();
        hash = 89 * hash + nome.hashCode();
        return hash;
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("ItemCardapio{");
        sb.append("nome=").append(nome);
        sb.append(", detalhes=").append(detalhes);
        sb.append(", precoUnitario=").append(precoUnitario);
        sb.append(", imagem=").append(imagem);
        sb.append('}');
        return sb.toString();
    }
    //</editor-fold>
}
