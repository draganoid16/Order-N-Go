package orderngo.cardapio;

import java.awt.image.BufferedImage;

import orderngo.basedados.ConectorBD;
import orderngo.utilizador.Restaurante;
import java.util.ArrayList;
import java.sql.ResultSet;
import orderngo.basedados.BaseDadosUtils;

import java.sql.SQLException;

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

        BufferedImage imagem = BaseDadosUtils.blobToImage(
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
