package orderngo.cardapio;

import orderngo.basedados.ConectorBD;
import orderngo.utilizador.Restaurante;
import orderngo.utils.ImagemUtils;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.sql.ResultSet;

import java.sql.SQLException;

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
        var ps = cbd.prepareStatement("SELECT * FROM bebida WHERE emailRestaurante = ?");
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
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Save">
    @Override
    public void save() throws SQLException
    {
        throw new UnsupportedOperationException("Not supported yet.");
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
