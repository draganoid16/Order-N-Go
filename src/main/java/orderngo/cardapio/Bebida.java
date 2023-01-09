package orderngo.cardapio;

import orderngo.basedados.ConectorBD;
import orderngo.utilizador.Restaurante;
import orderngo.utils.ImagemUtils;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.sql.ResultSet;

import java.sql.SQLException;
import orderngo.exceptions.ItemCardapioNotFoundException;
import orderngo.exceptions.RestauranteNotFoundException;

/**
 *
 * @author grupo1
 */
public class Bebida extends ItemCardapio
{
    private final int capacidadeCL;

    public Bebida(Restaurante restaurante, String nome, String detalhes, float precoUnitario, int capacidadeCL)
    {
        super(restaurante, nome, detalhes, precoUnitario);
        
        if (capacidadeCL <= 0)
            throw new IllegalArgumentException("Capacidade (em centilitros) invalida!");
            
        this.capacidadeCL = capacidadeCL;
    }

    //<editor-fold defaultstate="collapsed" desc="Getters">
    public int getCapacidadeCL()
    {
        return capacidadeCL;
    }
    //</editor-fold>
    
    
    //<editor-fold defaultstate="collapsed" desc="BuscarDados">
    private static Bebida criarBebida(Restaurante restaurante, ResultSet result) throws SQLException
    {
        Bebida b = new Bebida(
            restaurante,
            result.getString("nome"),
            result.getString("detalhes"),
            result.getFloat("precoUnitario"),
            result.getInt("capacidadeCL")
        );

        BufferedImage imagem = ImagemUtils.blobToImage(
            result.getBlob("imagem")
        );
        b.setImagem(imagem);
        
        return b;
    }
        
    public static Bebida[] from(Restaurante restaurante) throws SQLException
    {
        if (restaurante == null)
            throw new IllegalArgumentException("Restaurante invalido!");
        
        ArrayList<Bebida> bebidas = new ArrayList<>();
        
        var cbd = ConectorBD.getInstance();
        var ps = cbd.prepareStatement("SELECT * FROM bebida WHERE emailRestaurante = ? AND visivel = true");
        ps.setString(1, restaurante.getEmail());
        
        try (ResultSet result = cbd.executePreparedQuery(ps))
        {
            while (result.next())
            {
                bebidas.add(criarBebida(restaurante, result));
            }
        }
        
        return bebidas
            .toArray(Bebida[]::new);
    }
    
    public static Bebida getBebida(Restaurante restaurante, String nome) throws SQLException, RestauranteNotFoundException, ItemCardapioNotFoundException
    {
        if (restaurante == null)
            throw new IllegalArgumentException("Restaurante invalido!");
            
        // verifica se o restaurante existe
        Restaurante.getRestaurante(restaurante.getEmail());
            
        var cbd = ConectorBD.getInstance();
        var ps = cbd.prepareStatement("SELECT * FROM bebida WHERE emailRestaurante = ? AND nome = ? AND visivel = true");
        ps.setString(1, restaurante.getEmail());
        ps.setString(2, nome);
        
        Bebida b;
        try (ResultSet result = cbd.executePreparedQuery(ps))
        {
            if (!result.next())
                throw new ItemCardapioNotFoundException(restaurante.getEmail(), nome);
            
            b = criarBebida(restaurante, result);
        }
        return b;
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Save">
    @Override
    public void save() throws SQLException, RestauranteNotFoundException
    {
        var cbd = ConectorBD.getInstance();
    
        try
        {
            // verifica se a bebida existe
            getBebida(getRestaurante(), getNome());
            
            // update
            var ps = cbd.prepareStatement("UPDATE bebida SET detalhes = ?, precoUnitario = ?, imagem = ?, visivel = true WHERE emailRestaurante = ? AND nome = ?");
            ps.setString(1, getDetalhes());
            ps.setFloat(2, getPrecoUnitario());
            ps.setBlob(3, ImagemUtils.imageToInputStream(getImagem()));
            ps.setString(4, getRestaurante().getEmail());
            ps.setString(5, getNome());
            
            cbd.executePreparedUpdate(ps);
        }
        catch (ItemCardapioNotFoundException icnf)
        {
            // insert
            var ps = cbd.prepareStatement("INSERT INTO bebida(emailRestaurante, nome, detalhes, precoUnitario, capacidadeCL, imagem) VALUES (?, ?, ?, ?, ?, ?)");
            ps.setString(1, getRestaurante().getEmail());
            ps.setString(2, getNome());
            ps.setString(3, getDetalhes());
            ps.setFloat(4, getPrecoUnitario());
            ps.setInt(5, capacidadeCL);
            ps.setBlob(6, ImagemUtils.imageToInputStream(getImagem()));

            cbd.executePreparedUpdate(ps);
        }
    }
    //</editor-fold>
    
    
    @Override
    public boolean equals(Object obj)
    {
        if (!super.equals(obj))
            return false;
        
        if (!(obj instanceof Bebida))
            return false;
        
        Bebida other = (Bebida)obj;
        
        return capacidadeCL == other.capacidadeCL;
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("Bebida{");
        sb.append(super.toString());
        sb.append(", capacidadeCL=").append(capacidadeCL);
        sb.append('}');
        return sb.toString();
    }
}
