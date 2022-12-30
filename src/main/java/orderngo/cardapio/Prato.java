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

    public Prato(String nome, String detalhes, float precoUnitario, TipoPrato tipoPrato, String alergenios)
    {
        super(nome, detalhes, precoUnitario);
        
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
    private static Prato createPrato(ResultSet result) throws SQLException
    {
        Prato p = new Prato(
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
        
    public static Prato[] from(Restaurante rest) throws SQLException
    {
        ArrayList<Prato> pratos = new ArrayList<>();
        
        var cbd = ConectorBD.getInstance();
        var ps = cbd.prepareStatement("SELECT * FROM prato WHERE emailRestaurante = ?");
        ps.setString(1, rest.getEmail());
        
        try (ResultSet result = cbd.executePreparedQuery(ps))
        {
            while (result.next())
            {
                pratos.add(createPrato(result));
            }
        }
        
        
        Prato[] arr = new Prato[pratos.size()];
        pratos.toArray(arr);
        return arr;
    }
    //</editor-fold>
}
