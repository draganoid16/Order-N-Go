package orderngo.utilizador;

import orderngo.cardapio.Cardapio;
import orderngo.basedados.ConectorBD;
import orderngo.utils.PasswordUtils;
import orderngo.utils.ImagemUtils;

import java.awt.image.BufferedImage;
import java.util.Objects;

import java.util.ArrayList;
import java.sql.ResultSet;

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
        
        this.cardapio = new Cardapio(this);
        
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
    private static Restaurante criarRestaurante(ResultSet result) throws SQLException
    {
        Restaurante r = new Restaurante(
            result.getString("email"),
            result.getString("nome"),
            result.getString("telemovel"),
            result.getString("morada")
        );

        BufferedImage imagem = ImagemUtils.blobToImage(
            result.getBlob("imagem")
        );
        r.setImagem(imagem);
        
        r.setPasswordEncriptada(result.getString("palavraPasse"));
        
        return r;
    }
    
    public static Restaurante[] all() throws SQLException
    {
        ArrayList<Restaurante> rests = new ArrayList<>();
        
        try (ResultSet result = ConectorBD.getInstance().executeQuery("SELECT * FROM restaurante"))
        {
            while (result.next())
            {
                rests.add(criarRestaurante(result));
            }
        }
        
        return rests
            .toArray(Restaurante[]::new);
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
            
            r = criarRestaurante(result);
        }
        return r;
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Save">
    @Override
    public void save() throws SQLException
    {
        var cbd = ConectorBD.getInstance();
    
        try
        {
            // verifica se o restaurante existe
            getRestaurante(getEmail());
            
            // update
            var ps = cbd.prepareStatement("UPDATE restaurante SET telemovel = ?, morada = ?, imagem = ?, palavraPasse = ? WHERE email = ?");
            ps.setString(1, getTelemovel());
            ps.setString(2, getMorada());
            ps.setBlob(3, ImagemUtils.imageToInputStream(imagem));
            ps.setString(4, getPasswordEncriptada());
            ps.setString(5, getEmail());
            
            cbd.executePreparedUpdate(ps);
        }
        catch (RestauranteNotFoundException rnfe)
        {
            // insert
            var ps = cbd.prepareStatement("INSERT INTO restaurante(email, nome, telemovel, morada, imagem, palavraPasse) VALUES (?, ?, ?, ?, ?, ?)");
            ps.setString(1, getEmail());
            ps.setString(2, getNome());
            ps.setString(3, getTelemovel());
            ps.setString(4, getMorada());
            ps.setBlob(5, ImagemUtils.imageToInputStream(imagem));
            ps.setString(6, getPasswordEncriptada());

            cbd.executePreparedUpdate(ps);
        }
    }
    //</editor-fold>
    
    public static boolean validarCredenciais(String email, char[] password) throws SQLException
    {
        try
        {
            String encriptada = getRestaurante(email).getPasswordEncriptada();
            return PasswordUtils.verificarPassword(password, encriptada);
        }
        catch (RestauranteNotFoundException rnfe)
        {
            return false;
        }
    }
    
    
    @Override
    public boolean equals(Object obj)
    {
        if (!super.equals(obj))
            return false;
        
        if (!(obj instanceof Restaurante))
            return false;
        
        Restaurante other = (Restaurante)obj;
        
        return Objects.equals(imagem, other.imagem);
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("Restaurante{");
        sb.append(super.toString());
        sb.append(", imagem=").append(imagem);
        sb.append('}');
        return sb.toString();
    }
}
