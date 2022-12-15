package orderngo.cardapio;

/**
 *
 * @author grupo1
 */
public class Bebida extends ItemCardapio
{
    private final int capacidadeCL;

    public Bebida(String nome, String detalhes, int capacidadeCL)
    {
        super(nome, detalhes);
        
        if (capacidadeCL < 0)
            throw new IllegalArgumentException("Capacidade (em centilitros) invalida!");
            
        this.capacidadeCL = capacidadeCL;
    }

    //<editor-fold defaultstate="collapsed" desc="Getters">
    public int getCapacidadeCL()
    {
        return capacidadeCL;
    }
    //</editor-fold>
}
