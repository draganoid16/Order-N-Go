package orderngo.utilizador;

import orderngo.cardapio.Cardapio;
import java.awt.image.BufferedImage;

import orderngo.basedados.ConectorBD;
import java.util.ArrayList;
import java.sql.ResultSet;
import orderngo.basedados.BaseDadosUtils;

import java.sql.SQLException;
import orderngo.exceptions.RestauranteNotFoundException;

/**
 *
 * @author grupo1
 */
public class Restaurante extends Utilizador
{
    private final Cardapio cardapio;
    private BufferedImage imagem;
    
    public Restaurante(String email, String nome, String telemovel, String morada)
    {
        super(email, nome, telemovel, morada);
        
        this.cardapio = new Cardapio();
        setImagem(null);
    }

    //<editor-fold defaultstate="collapsed" desc="Getters">
    public Cardapio getCardapio()
    {
        return cardapio;
    }

    public BufferedImage getImagem()
    {
        return imagem;
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Setters">
    public void setImagem(BufferedImage imagem)
    {
        this.imagem = imagem;
    }
    //</editor-fold>

    
    //<editor-fold defaultstate="collapsed" desc="BuscarDados">
    private static Restaurante createRestaurante(ResultSet result) throws SQLException
    {
        Restaurante r = new Restaurante(
            result.getString("email"),
            result.getString("nome"),
            result.getString("telemovel"),
            result.getString("morada")
        );

        BufferedImage imagem = BaseDadosUtils.blobToImage(
            result.getBlob("imagem")
        );
        r.setImagem(imagem);
        
        return r;
    }
    
    public static Restaurante[] all() throws SQLException
    {
        ArrayList<Restaurante> rests = new ArrayList<>();
        
        try (ResultSet result = ConectorBD.getInstance().executeQuery("SELECT * FROM restaurante"))
        {
            while (result.next())
            {
                rests.add(createRestaurante(result));
            }
        }
        
        Restaurante[] arr = new Restaurante[rests.size()];
        rests.toArray(arr);
        return arr;
    }
    
    public static Restaurante getRestaurante(String email) throws SQLException, RestauranteNotFoundException
    {
        var cbd = ConectorBD.getInstance();
        var ps = cbd.prepareStatement("SELECT * FROM restaurante WHERE email = ?");
        ps.setString(1, email);
        
        Restaurante r;
        try (ResultSet result = cbd.executePreparedQuery(ps))
        {
            if (!result.next())
                throw new RestauranteNotFoundException(email);
            
            r = createRestaurante(result);
        }
        return r;
    }
    //</editor-fold>
    
    public static boolean validCredentials(String email, String password) throws SQLException
    {
        String encriptedPassword = BaseDadosUtils.encriptarPassword(password);
        
        var cbd = ConectorBD.getInstance();
        var ps = cbd.prepareStatement("SELECT * FROM restaurante WHERE email = ? AND palavraPasse = ?");
        ps.setString(1, email);
        ps.setString(2, encriptedPassword);
        
        boolean isValid;
        try (ResultSet result = cbd.executePreparedQuery(ps))
        {
            isValid = result.next();
        }
        
        return isValid;
    }
}
