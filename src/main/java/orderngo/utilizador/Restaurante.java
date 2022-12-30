package orderngo.utilizador;

import orderngo.cardapio.Cardapio;

import orderngo.basedados.ConectorBD;
import java.util.ArrayList;
import org.apache.commons.codec.digest.DigestUtils;
import java.sql.SQLException;
import orderngo.exceptions.RestauranteNotFoundException;

/**
 *
 * @author grupo1
 */
public class Restaurante extends Utilizador
{
    private final String email;
    private final String nome;
    private final String telemovel;
    private final String morada;
    private final Cardapio cardapio;
    public Restaurante(String email, String nome, String telemovel, String morada, Cardapio cardapio)
    {
        super(email, nome, telemovel, morada);
        
        if (cardapio == null)
            throw new IllegalArgumentException("Cardapio invalido!");

        this.email = email;
        this.nome = nome;
        this.telemovel = telemovel;
        this.morada = morada;
        this.cardapio = cardapio;

    }
    
    public Restaurante(String email, String nome, String telemovel, String morada)
    {
        this(email, nome, telemovel, morada, new Cardapio());
    }

    public String getEmail() {
        return email;
    }

    public String getNome() {
        return nome;
    }

    public String getTelemovel() {
        return telemovel;
    }

    public String getMorada() {
        return morada;
    }

    public Cardapio getCardapio()
    {
        return cardapio;
    }


    public static Restaurante[] all() throws SQLException
    {
        ArrayList<Restaurante> rests = new ArrayList<>();
        
        var result = ConectorBD.getInstance().executeQuery("SELECT * FROM restaurante");
        while (result.next())
        {
            rests.add(new Restaurante(
                result.getString("email"), 
                result.getString("nome"), 
                result.getString("telemovel"),
                result.getString("morada")
            ));
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
        var result = cbd.executePreparedQuery(ps);
        
        if (!result.next())
            throw new RestauranteNotFoundException(email);
        
        return new Restaurante(
            result.getString("email"), 
            result.getString("nome"), 
            result.getString("telemovel"),
            result.getString("morada")
        );
    }
    
    public static boolean validCredentials(String email, String password) throws SQLException
    {
        String encriptedPassword = new DigestUtils("SHA3-256").digestAsHex(password);
        
        var cbd = ConectorBD.getInstance();
        var ps = cbd.prepareStatement("SELECT * FROM restaurante WHERE email = ? AND palavraPasse = ?");
        ps.setString(1, email);
        ps.setString(2, encriptedPassword);
        var result = cbd.executePreparedQuery(ps);
        
        return result.next();
    }
}
