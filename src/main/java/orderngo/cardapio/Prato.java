package orderngo.cardapio;

import orderngo.utilizador.Restaurante;
import orderngo.basedados.ConectorBD;
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
public class Prato extends ItemCardapio
{
    public static enum TipoPrato
    {
        CARNE, PEIXE, VEGETARIANO, VEGANO
    }

    private final TipoPrato tipoPrato;
    private String alergenios;

    public Prato(Restaurante restaurante, String nome, String detalhes, float precoUnitario, TipoPrato tipoPrato, String alergenios)
    {
        super(restaurante, nome, detalhes, precoUnitario);
        
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
            alergenios = "";
        
        this.alergenios = alergenios;
    }
    //</editor-fold>
    
    
    //<editor-fold defaultstate="collapsed" desc="BuscarDados">
    private static Prato criarPrato(Restaurante restaurante, ResultSet result) throws SQLException
    {
        Prato p = new Prato(
            restaurante,
            result.getString("nome"), 
            result.getString("detalhes"),
            result.getFloat("precoUnitario"),
            TipoPrato.valueOf(
                result.getString("tipo")
            ), 
            result.getString("alergenios")
        );

        BufferedImage imagem = ImagemUtils.blobToImage(
            result.getBlob("imagem")
        );
        p.setImagem(imagem);
        
        return p;
    }
        
    public static Prato[] from(Restaurante restaurante) throws SQLException
    {
        if (restaurante == null)
            throw new IllegalArgumentException("Restaurante invalido!");
        
        ArrayList<Prato> pratos = new ArrayList<>();
        
        var cbd = ConectorBD.getInstance();
        var ps = cbd.prepareStatement("SELECT * FROM prato WHERE emailRestaurante = ?");
        ps.setString(1, restaurante.getEmail());
        
        try (ResultSet result = cbd.executePreparedQuery(ps))
        {
            while (result.next())
            {
                pratos.add(criarPrato(restaurante, result));
            }
        }
        
        return pratos
            .toArray(Prato[]::new);
    }
    
    public static Prato getPrato(Restaurante restaurante, String nome) throws SQLException, RestauranteNotFoundException, ItemCardapioNotFoundException
    {
        if (restaurante == null)
            throw new IllegalArgumentException("Restaurante invalido!");
            
        // verifica se o restaurante existe
        Restaurante.getRestaurante(restaurante.getEmail());
            
        var cbd = ConectorBD.getInstance();
        var ps = cbd.prepareStatement("SELECT * FROM prato WHERE emailRestaurante = ? AND nome = ?");
        ps.setString(1, restaurante.getEmail());
        ps.setString(2, nome);
        
        Prato p;
        try (ResultSet result = cbd.executePreparedQuery(ps))
        {
            if (!result.next())
                throw new ItemCardapioNotFoundException(restaurante.getEmail(), nome);
            
            p = criarPrato(restaurante, result);
        }
        return p;
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Save">
    @Override
    public void save() throws SQLException
    {
        var cbd = ConectorBD.getInstance();
    
        try
        {
            // verifica se o prato existe
            getPrato(getRestaurante(), getNome());
            
            // update
            var ps = cbd.prepareStatement("UPDATE prato SET detalhes = ?, precoUnitario = ?, alergenios = ?, imagem = ? WHERE emailRestaurante = ? AND nome = ?");
            ps.setString(1, getDetalhes());
            ps.setFloat(2, getPrecoUnitario());
            ps.setString(3, alergenios);
            ps.setBlob(4, ImagemUtils.imageToInputStream(getImagem()));
            ps.setString(5, getRestaurante().getEmail());
            ps.setString(6, getNome());
            
            cbd.executePreparedUpdate(ps);
        }
        catch (ItemCardapioNotFoundException icnf)
        {
            // insert
            var ps = cbd.prepareStatement("INSERT INTO prato(emailRestaurante, nome, detalhes, precoUnitario, tipo, alergenios, imagem) VALUES (?, ?, ?, ?, ?, ?, ?)");
            ps.setString(1, getRestaurante().getEmail());
            ps.setString(2, getNome());
            ps.setString(3, getDetalhes());
            ps.setFloat(4, getPrecoUnitario());
            ps.setString(5, tipoPrato.toString());
            ps.setString(6, alergenios);
            ps.setBlob(7, ImagemUtils.imageToInputStream(getImagem()));

            cbd.executePreparedUpdate(ps);
        }
    }
    //</editor-fold>
    
    
    @Override
    public boolean equals(Object obj)
    {
        if (!super.equals(obj))
            return false;
        
        if (!(obj instanceof Prato))
            return false;
        
        Prato other = (Prato)obj;
        
        if (!tipoPrato.equals(other.tipoPrato))
            return false;
        
        return alergenios.equals(other.alergenios);
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("Prato{");
        sb.append(super.toString());
        sb.append(", tipoPrato=").append(tipoPrato);
        sb.append(", alergenios=").append(alergenios);
        sb.append('}');
        return sb.toString();
    }
}
