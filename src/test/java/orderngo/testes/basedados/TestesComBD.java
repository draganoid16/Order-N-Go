package orderngo.testes.basedados;

import orderngo.basedados.ConectorBD;

import java.util.Properties;
import org.h2.tools.TriggerAdapter;
import java.sql.Connection;
import java.sql.ResultSet;

import org.junit.jupiter.api.BeforeAll;

import java.sql.SQLException;

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
        criarTriggersProcedures();
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
        
        cbd.executeUpdate("""
            CREATE TABLE gestorog (
                email VARCHAR(100),
                nome VARCHAR(100),
                morada VARCHAR(100),
                telemovel CHAR(9),
                palavraPasse CHAR(64) NOT NULL,
                nrEmpregado INT UNIQUE NOT NULL,
                visivel BOOLEAN NOT NULL DEFAULT true,

                CONSTRAINT nrEmpregadoValida CHECK (nrEmpregado > 0),

                PRIMARY KEY (email)
            );
            CREATE UNIQUE INDEX idxNrEmpregado ON gestorog(nrEmpregado);

            CREATE TABLE restaurante (
                email VARCHAR(100),
                nome VARCHAR(100) UNIQUE NOT NULL,
                morada VARCHAR(100),
                telemovel CHAR(9),
                palavraPasse CHAR(64) NOT NULL,
                imagem MEDIUMBLOB,
                visivel BOOLEAN NOT NULL DEFAULT true,

                PRIMARY KEY (email)
            );
            CREATE UNIQUE INDEX idxNomeRestaurante ON restaurante(nome);

            CREATE TABLE prato (
                emailRestaurante VARCHAR(100),
                nome VARCHAR(100),
                detalhes VARCHAR(100),
                precoUnitario FLOAT,
                tipo ENUM('CARNE', 'PEIXE', 'VEGETARIANO', 'VEGANO') NOT NULL,
                alergenios VARCHAR(100),
                imagem MEDIUMBLOB,
                visivel BOOLEAN NOT NULL DEFAULT true,

                CONSTRAINT precoPratoValido CHECK (precoUnitario > 0),

                CONSTRAINT fkRestPrato FOREIGN KEY (emailRestaurante) REFERENCES restaurante(email),
                PRIMARY KEY (emailRestaurante, nome)
            );

            CREATE TABLE bebida (
                emailRestaurante VARCHAR(100),
                nome VARCHAR(100),
                detalhes VARCHAR(100),
                precoUnitario FLOAT,
                capacidadeCL INT,
                imagem MEDIUMBLOB,
                visivel BOOLEAN NOT NULL DEFAULT true,

                CONSTRAINT precoBebidaValido CHECK (precoUnitario > 0),
                CONSTRAINT capacidadeValida CHECK (capacidadeCL > 0),

                CONSTRAINT fkRestBebida FOREIGN KEY (emailRestaurante) REFERENCES restaurante(email),
                PRIMARY KEY (emailRestaurante, nome)
            );


            CREATE TABLE cliente (
                email VARCHAR(100),
                nome VARCHAR(100),
                morada VARCHAR(100),
                telemovel CHAR(9),
                palavraPasse VARCHAR(100) NOT NULL,
                nif CHAR(9) UNIQUE NOT NULL,
                visivel BOOLEAN NOT NULL DEFAULT true,

                PRIMARY KEY (email)
            );
            CREATE UNIQUE INDEX idxNifCliente ON cliente(nif);

            CREATE TABLE pedido (
                nrPedido INT,
                moradaEntrega VARCHAR(100),
                dataHoraEntrega DATETIME,
                emailCliente VARCHAR(100) NOT NULL,

                CONSTRAINT fkCliente FOREIGN KEY (emailCliente) REFERENCES cliente(email),
                PRIMARY KEY (nrPedido)
            );

            CREATE TABLE pedidoprato (
                nrPedido INT,
                emailRestaurante VARCHAR(100),
                nomePrato VARCHAR(100),
                quantidade INT NOT NULL,

                CONSTRAINT quantPratoValida CHECK (quantidade > 0),

                CONSTRAINT fkpedidoprato FOREIGN KEY (nrPedido) REFERENCES pedido(nrPedido),
                CONSTRAINT fkPrato FOREIGN KEY (emailRestaurante, nomePrato) REFERENCES prato(emailRestaurante, nome),
                PRIMARY KEY (nrPedido, emailRestaurante, nomePrato)
            );

            CREATE TABLE pedidobebida (
                nrPedido INT,
                emailRestaurante VARCHAR(100),
                nomeBebida VARCHAR(100),
                quantidade INT NOT NULL,

                CONSTRAINT quantBebidaValida CHECK (quantidade > 0),

                CONSTRAINT fkpedidobebida FOREIGN KEY (nrPedido) REFERENCES pedido(nrPedido),
                CONSTRAINT fkBebida FOREIGN KEY (emailRestaurante, nomeBebida) REFERENCES bebida(emailRestaurante, nome),
                PRIMARY KEY (nrPedido, emailRestaurante, nomeBebida)
            );
        """);
    }
    
    private static void criarTriggersProcedures() throws SQLException
    {
        var cbd = ConectorBD.getInstance();
        
        cbd.executeUpdate(String.format("""
            CREATE ALIAS getPedidosFromRestaurante FOR '%1$s.getPedidosFromRestaurante';
            CREATE ALIAS getPedidoPratos FOR '%1$s.getPedidoPratos';
            CREATE ALIAS getPedidoBebidas FOR '%1$s.getPedidoBebidas';   

            CREATE TRIGGER restVisivel AFTER UPDATE ON restaurante FOR EACH ROW CALL '%2$s';                  
            """, 
            Procedures.class.getName(),
            RestVisivelTrigger.class.getName()
        ));
    }
    
    private static void inserirDados() throws SQLException
    {
        var cbd = ConectorBD.getInstance();
        
        cbd.executeUpdate("""
            INSERT INTO restaurante(email, nome, morada, telemovel, palavraPasse) VALUES
                ('visivel@rest.com', 'visivel', 'morada visivel', '111111111', '$2a$10$zKIfZPJNVjm2/HXHa.PVJe9LnRnbPttz5kUMwvi.Rhum4D5pPd3aO'), /* pass: visivel */
                ('invisivel@rest.com', 'invisivel', 'morada invisivel', '000000000', '$2a$10$g8P/Aek9gp1nd6bpkm/eFeMG3/pJYdx9M5SsuNmbF6SoHjf4eFvHW'); /* pass: invisivel */
            
            INSERT INTO gestorog(email, nome, morada, telemovel, palavraPasse, nrEmpregado) VALUES
                ('visivel@gong.com', 'visivel', 'morada visivel', '111111111', '$2a$10$zKIfZPJNVjm2/HXHa.PVJe9LnRnbPttz5kUMwvi.Rhum4D5pPd3aO', 1), /* pass: visivel */
                ('invisivel@gong.com', 'invisivel', 'morada invisivel', '000000000', '$2a$10$g8P/Aek9gp1nd6bpkm/eFeMG3/pJYdx9M5SsuNmbF6SoHjf4eFvHW', 2); /* pass: invisivel */
            
            INSERT INTO prato(emailRestaurante, nome, detalhes, precoUnitario, tipo, alergenios) VALUES
                ('visivel@rest.com', 'prato carne visivel', 'detalhes visivel', 7.80, 'CARNE', 'alergenio visivel'),
                ('visivel@rest.com', 'prato peixe invisivel', 'detalhes invisivel', 9.99, 'PEIXE', NULL),
                ('invisivel@rest.com', 'prato vegetariano invisivel', 'detalhes invisivel', 12, 'VEGETARIANO', 'alergenio invisivel'),
                ('invisivel@rest.com', 'prato vegano invisivel', 'detalhes invisivel', 15, 'VEGANO', NULL);
            
            INSERT INTO bebida(emailRestaurante, nome, detalhes, precoUnitario, capacidadeCL) VALUES
                ('visivel@rest.com', 'bebida visivel', 'detalhes visivel', 3, 50),
                ('visivel@rest.com', 'bebida invisivel', 'detalhes invisivel', 5.5, 200),
                ('invisivel@rest.com', 'bebida invisivel 1', 'detalhes invisivel', 2.5, 25),
                ('invisivel@rest.com', 'bebida invisivel 2', 'detalhes invisivel', 4, 100);
                          
                          
            INSERT INTO cliente(email, nome, morada, telemovel, nif, palavraPasse) VALUES
                ('visivel@cli.com', 'visivel', 'morada visivel', '111111111', '111111111', '$2a$10$zKIfZPJNVjm2/HXHa.PVJe9LnRnbPttz5kUMwvi.Rhum4D5pPd3aO'), /* pass: visivel */
                ('invisivel@cli.com', 'invisivel', 'morada invisivel', '000000000', '000000000', '$2a$10$g8P/Aek9gp1nd6bpkm/eFeMG3/pJYdx9M5SsuNmbF6SoHjf4eFvHW'); /* pass: invisivel */
                          
            INSERT INTO pedido(nrPedido, moradaEntrega, dataHoraEntrega, emailCliente) VALUES
                (11, 'morada 1', '2022-12-15 01:01:01', 'visivel@cli.com'),
                (12, 'morada 2', '2022-12-15 02:02:02', 'visivel@cli.com'),
                (21, 'morada 1', '2022-12-15 01:01:01', 'invisivel@cli.com'),
                (31, 'morada 1', '2022-12-15 01:01:01', 'invisivel@cli.com');
                          
            INSERT INTO pedidoprato(nrPedido, emailRestaurante, nomePrato, quantidade) VALUES
                (11, 'visivel@rest.com', 'prato carne visivel', 1),
                (11, 'visivel@rest.com', 'prato peixe invisivel', 1),
                (21, 'invisivel@rest.com', 'prato vegetariano invisivel', 2),
                (31, 'visivel@rest.com', 'prato carne visivel', 3);
                          
            INSERT INTO pedidobebida(nrPedido, emailRestaurante, nomeBebida, quantidade) VALUES
                (11, 'visivel@rest.com', 'bebida visivel', 1),
                (11, 'visivel@rest.com', 'bebida invisivel', 1),
                (21, 'invisivel@rest.com', 'bebida invisivel 1', 2),
                (31, 'visivel@rest.com', 'bebida visivel', 3);
        """);
        
        cbd.executeUpdate("""
            UPDATE restaurante SET visivel = false WHERE email = 'invisivel@rest.com';
            UPDATE gestorog SET visivel = false WHERE email = 'invisivel@gong.com';
                          
            UPDATE prato SET visivel = false WHERE nome LIKE '%invisivel%';
            UPDATE bebida SET visivel = false WHERE nome LIKE '%invisivel%';
            
            UPDATE cliente SET visivel = false WHERE email = 'invisivel@cli.com';
        """);
    }    
    
    
    public static class Procedures
    {
        public static ResultSet getPedidosFromRestaurante(Connection con, String emailRest) throws SQLException
        {
            String sql = "SELECT DISTINCT ped.* FROM pedido ped INNER JOIN (pedidoprato pprato INNER JOIN pedidobebida pbebida ON pprato.emailRestaurante = pbebida.emailRestaurante) ON ped.nrPedido = pprato.nrPedido WHERE pprato.emailRestaurante = ? ORDER BY nrPedido";
            
            var cbd = ConectorBD.getInstance();
            var ps = cbd.prepareStatement(sql);
            
            ps.setString(1, emailRest);
            return cbd.executePreparedQuery(ps);
        }
        
        public static ResultSet getPedidoPratos(Connection con, int numeroPedido) throws SQLException
        {
            String sql = "SELECT prato.*, quantidade FROM pedidoprato pprato INNER JOIN prato ON (pprato.emailRestaurante = prato.emailRestaurante AND nomePrato = nome) WHERE (nrPedido = ?)";
            
            var cbd = ConectorBD.getInstance();
            var ps = cbd.prepareStatement(sql);
            
            ps.setInt(1, numeroPedido);
            return cbd.executePreparedQuery(ps);
        }
        
        public static ResultSet getPedidoBebidas(Connection con, int numeroPedido) throws SQLException
        {
            String sql = "SELECT bebida.*, quantidade FROM pedidobebida pbebida INNER JOIN bebida ON (pbebida.emailRestaurante = bebida.emailRestaurante AND nomeBebida = nome) WHERE (nrPedido = ?)";
            
            var cbd = ConectorBD.getInstance();
            var ps = cbd.prepareStatement(sql);
            
            ps.setInt(1, numeroPedido);
            return cbd.executePreparedQuery(ps);
        }
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
