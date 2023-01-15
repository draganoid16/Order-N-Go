package orderngo.exception;

/**
 *
 * @author grupo1
 */
public class PedidoNotFoundException extends IllegalArgumentException //implements IsApenasVisiveisInException
{
    private final int nrPedidoProcurado;

    public PedidoNotFoundException(int nrPedidoProcurado)
    {
        super(String.format("Nao existe pedido com nr pedido %d", nrPedidoProcurado));
        
        this.nrPedidoProcurado = nrPedidoProcurado;
    }

    
    //<editor-fold defaultstate="collapsed" desc="Getters">
    public int getNrPedidoProcurado()
    {
        return nrPedidoProcurado;
    }
    //</editor-fold>
}
