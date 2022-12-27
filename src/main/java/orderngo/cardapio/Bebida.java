package orderngo.cardapio;

import orderngo.basedados.ConectorBD;
import orderngo.utilizador.Restaurante;
import java.util.ArrayList;
import java.sql.SQLException;

/**
 *
 * @author grupo1
 */
public class Bebida extends ItemCardapio
{
    private final int capacidadeCL;

    public Bebida(String nome, String detalhes, float precoUnitario, int capacidadeCL)
    {
        super(nome, detalhes, precoUnitario);
        
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
    
    
    public static Bebida[] from(Restaurante rest) throws SQLException
    {
        ArrayList<Bebida> bebidas = new ArrayList<>();
        
        var cbd = ConectorBD.getInstance();
        var ps = cbd.prepareStatement("SELECT * FROM bebida WHERE emailRestaurante = ?");
        ps.setString(1, rest.getEmail());
        var result = cbd.executePreparedQuery(ps);

        while (result.next())
        {
            bebidas.add(new Bebida(
                result.getString("nome"), 
                result.getString("detalhes"),
                result.getFloat("precoUnitario"),
                result.getInt("capacidadeCL")
            ));
        }
        
        
        Bebida[] arr = new Bebida[bebidas.size()];
        bebidas.toArray(arr);
        return arr;
    }
}
