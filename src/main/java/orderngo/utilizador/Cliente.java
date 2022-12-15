package orderngo.utilizador;

/**
 *
 * @author grupo1
 */
public class Cliente extends Utilizador
{
    private final String nif;

    public Cliente(String email, String nome, String telemovel, String morada, String nif)
    {
        super(email, nome, telemovel, morada);
        
        if (nif == null || !nif.matches("\\d{9}"))
            throw new IllegalArgumentException("Nif invalido!");
        
        this.nif = nif;
    }
    
    //<editor-fold defaultstate="collapsed" desc="Getters">
    public String getNif()
    {
        return nif;
    }
    //</editor-fold>
}
