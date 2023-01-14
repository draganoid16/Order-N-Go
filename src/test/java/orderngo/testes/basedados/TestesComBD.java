package orderngo.testes.basedados;

import java.util.Properties;
import org.h2.tools.TriggerAdapter;
import java.sql.Connection;
import java.sql.ResultSet;

import org.junit.jupiter.api.BeforeAll;

import java.sql.SQLException;
import orderngo.basedados.ConectorBD;

/**
 *
 * @author grupo1
 */

public abstract class TestesComBD
{
    private static boolean isBDCreated = false;
    
    @BeforeAll
    public static void setup() throws SQLException
    {
        if (isBDCreated)
            return;
        
        var cbd = new ConectorBD(getH2Properties()){};
        ConectorBD.setInstance(cbd);
        
        criarTabelas();
        inserirDados();
        
        isBDCreated = true;
    }
    
    private static Properties getH2Properties()
    {
        var prop = ConectorBD.getDefaultProperties();
        prop.setProperty("url", "jdbc:h2:mem:testdb;MODE=MySQL;DATABASE_TO_LOWER=TRUE");
        prop.setProperty("user", "sa");
        
        return prop;
    }
    
    
    //<editor-fold defaultstate="collapsed" desc="setupBD">
    private static void criarTabelas() throws SQLException
    {
        var cbd = ConectorBD.getInstance();
        cbd.executeUpdate("CREATE TABLE gestorog (email VARCHAR(100), nome VARCHAR(100), morada VARCHAR(100), telemovel CHAR(9), palavraPasse CHAR(64) NOT NULL, nrEmpregado INT UNIQUE NOT NULL, visivel BOOLEAN NOT NULL DEFAULT true, CONSTRAINT nrEmpregadoValida CHECK (nrEmpregado > 0), PRIMARY KEY (email))");
        cbd.executeUpdate("CREATE TABLE restaurante (email VARCHAR(100), nome VARCHAR(100) UNIQUE NOT NULL, morada VARCHAR(100), telemovel CHAR(9), palavraPasse CHAR(64) NOT NULL, imagem MEDIUMBLOB, visivel BOOLEAN NOT NULL DEFAULT true, PRIMARY KEY (email))");
        cbd.executeUpdate("CREATE TABLE prato (emailRestaurante VARCHAR(100), nome VARCHAR(100), detalhes VARCHAR(100), precoUnitario FLOAT, tipo ENUM('CARNE', 'PEIXE', 'VEGETARIANO', 'VEGANO') NOT NULL, alergenios VARCHAR(100), imagem MEDIUMBLOB, visivel BOOLEAN NOT NULL DEFAULT true, CONSTRAINT precoPratoValido CHECK (precoUnitario > 0), CONSTRAINT fkRestPrato FOREIGN KEY (emailRestaurante) REFERENCES restaurante(email), PRIMARY KEY (emailRestaurante, nome))");
        cbd.executeUpdate("CREATE TABLE bebida (emailRestaurante VARCHAR(100), nome VARCHAR(100), detalhes VARCHAR(100), precoUnitario FLOAT, capacidadeCL INT, imagem MEDIUMBLOB, visivel BOOLEAN NOT NULL DEFAULT true, CONSTRAINT precoBebidaValido CHECK (precoUnitario > 0), CONSTRAINT capacidadeValida CHECK (capacidadeCL > 0), CONSTRAINT fkRestBebida FOREIGN KEY (emailRestaurante) REFERENCES restaurante(email), PRIMARY KEY (emailRestaurante, nome))");
        
        var ps = cbd.prepareStatement("CREATE TRIGGER restVisivel AFTER UPDATE ON restaurante FOR EACH ROW CALL '" + RestVisivelTrigger.class.getName() + "'");
        cbd.executePreparedUpdate(ps);
    }
    
    private static void inserirDados() throws SQLException
    {
        var cbd = ConectorBD.getInstance();
        cbd.executeUpdate(
            "INSERT INTO restaurante(email, nome, morada, telemovel, palavraPasse) VALUES " +
                "('visivel@rest.com', 'visivel', 'morada visivel', '111111111', '$2a$10$zKIfZPJNVjm2/HXHa.PVJe9LnRnbPttz5kUMwvi.Rhum4D5pPd3aO'), " + // pass: visivel
                "('invisivel@rest.com', 'invisivel', 'morada invisivel', '000000000', '$2a$10$g8P/Aek9gp1nd6bpkm/eFeMG3/pJYdx9M5SsuNmbF6SoHjf4eFvHW')" // pass: invisivel
        );
        cbd.executeUpdate(
            "INSERT INTO gestorog(email, nome, morada, telemovel, palavraPasse, nrEmpregado) VALUES " +
                "('visivel@gong.com', 'visivel', 'morada visivel', '111111111', '$2a$10$zKIfZPJNVjm2/HXHa.PVJe9LnRnbPttz5kUMwvi.Rhum4D5pPd3aO', 1), " + // pass: visivel
                "('invisivel@gong.com', 'invisivel', 'morada invisivel', '000000000', '$2a$10$g8P/Aek9gp1nd6bpkm/eFeMG3/pJYdx9M5SsuNmbF6SoHjf4eFvHW', 2)" // pass: invisivel
        );
        cbd.executeUpdate(
            "INSERT INTO prato(emailRestaurante, nome, detalhes, precoUnitario, tipo, alergenios) VALUES " +
                "('visivel@rest.com', 'prato carne visivel', 'detalhes visivel', 7.80, 'CARNE', 'alergenio visivel'), " +
                "('visivel@rest.com', 'prato peixe invisivel', 'detalhes invisivel', 9.99, 'PEIXE', NULL), " +
                "('invisivel@rest.com', 'prato vegetariano invisivel', 'detalhes invisivel', 12, 'VEGETARIANO', 'alergenio invisivel'), " +
                "('invisivel@rest.com', 'prato vegano invisivel', 'detalhes invisivel', 15, 'VEGANO', NULL)"
        );
        cbd.executeUpdate(
            "INSERT INTO bebida(emailRestaurante, nome, detalhes, precoUnitario, capacidadeCL) VALUES " +
                "('visivel@rest.com', 'bebida visivel', 'detalhes visivel', 3, 50), " +
                "('visivel@rest.com', 'bebida invisivel', 'detalhes invisivel', 5.5, 200), " +
                "('invisivel@rest.com', 'bebida invisivel 1', 'detalhes invisivel', 2.5, 25), " +
                "('invisivel@rest.com', 'bebida invisivel 2', 'detalhes invisivel', 4, 100)"
        );
        
        cbd.executeUpdate("UPDATE restaurante SET visivel = false WHERE email = 'invisivel@rest.com'");
        cbd.executeUpdate("UPDATE gestorog SET visivel = false WHERE email = 'invisivel@gong.com'");
    }    
    
    
    public static class RestVisivelTrigger extends TriggerAdapter
    {
        @Override
        public void fire(Connection con, ResultSet OLD, ResultSet NEW) throws SQLException
        {
            String email = NEW.getString("email");
            boolean visivel = NEW.getBoolean("visivel");
            
            if (visivel)
                return;

            var cbd = ConectorBD.getInstance();
            var ps = cbd.prepareStatement("UPDATE prato SET visivel = false WHERE emailRestaurante = ?");
            ps.setString(1, email);
            cbd.executePreparedUpdate(ps);

            ps = cbd.prepareStatement("UPDATE bebida SET visivel = false WHERE emailRestaurante = ?");
            ps.setString(1, email);
            cbd.executePreparedUpdate(ps);
        }
    }
    //</editor-fold>
}
