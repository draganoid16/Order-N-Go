DROP PROCEDURE IF EXISTS criarBD;
DROP PROCEDURE IF EXISTS removerBD;
DROP PROCEDURE IF EXISTS limparTabelas;

DELIMITER ;;
CREATE PROCEDURE criarBD()
BEGIN
    CREATE TABLE gestorog (
        email VARCHAR(100),
        nome VARCHAR(100),
        morada VARCHAR(100),
        telemovel CHAR(9),
        palavraPasse CHAR(64) NOT NULL,
        nrEmpregado INT UNIQUE NOT NULL,
        
        CONSTRAINT nrEmpregadoValida CHECK (nrEmpregado > 0),
        
        CONSTRAINT pk PRIMARY KEY (email)
    );
    CREATE UNIQUE INDEX idxNrEmpregado ON gestorog(nrEmpregado);

    CREATE TABLE restaurante (
        email VARCHAR(100),
        nome VARCHAR(100) UNIQUE NOT NULL,
        morada VARCHAR(100),
        telemovel CHAR(9),
        palavraPasse CHAR(64) NOT NULL,
        imagem MEDIUMBLOB,
        
        CONSTRAINT pk PRIMARY KEY (email)
    );
    CREATE UNIQUE INDEX idxNomeRestaurante ON restaurante(nome);

    CREATE TABLE prato (
        emailRestaurante VARCHAR(100),
        nome VARCHAR(100),
        detalhes VARCHAR(100),
        precoUnitario FLOAT,
        tipo ENUM("CARNE", "PEIXE", "VEGETARIANO", "VEGANO") NOT NULL,
        alergenios VARCHAR(100),
        imagem MEDIUMBLOB,
        
        CONSTRAINT precoPratoValido CHECK (precoUnitario > 0),

        CONSTRAINT fkRestPrato FOREIGN KEY (emailRestaurante) REFERENCES restaurante(email),
        CONSTRAINT pk PRIMARY KEY (emailRestaurante, nome)
    );
    
    CREATE TABLE bebida (
        emailRestaurante VARCHAR(100),
        nome VARCHAR(100),
        detalhes VARCHAR(100),
        precoUnitario FLOAT,
        capacidadeCL INT,
        imagem MEDIUMBLOB,
        
        CONSTRAINT precoBebidaValido CHECK (precoUnitario > 0),
        CONSTRAINT capacidadeValida CHECK (capacidadeCL > 0),
        
        CONSTRAINT fkRestBebida FOREIGN KEY (emailRestaurante) REFERENCES restaurante(email),
        CONSTRAINT pk PRIMARY KEY (emailRestaurante, nome)
    );
    
    
    CREATE TABLE cliente (
        email VARCHAR(100),
        nome VARCHAR(100),
        morada VARCHAR(100),
        telemovel CHAR(9),
        palavraPasse VARCHAR(100) NOT NULL,
        nif CHAR(9) UNIQUE NOT NULL,
        
        CONSTRAINT pk PRIMARY KEY (email)
    );
    CREATE UNIQUE INDEX idxNifCliente ON cliente(nif);
    
    CREATE TABLE pedido (
        nrPedido INT AUTO_INCREMENT,
        moradaEntrega VARCHAR(100),
        dataHoraEntrega DATETIME,
        emailCliente VARCHAR(100) NOT NULL,
        
        CONSTRAINT fkCliente FOREIGN KEY (emailCliente) REFERENCES cliente(email),
        CONSTRAINT pk PRIMARY KEY (nrPedido)
    );
    
    CREATE TABLE pedidoprato (
        nrPedido INT,
        emailRestaurante VARCHAR(100),
        nomePrato VARCHAR(100),
        quantidade INT,
        
        CONSTRAINT quantPratoValida CHECK (quantidade > 0),
        
        CONSTRAINT fkPedidoPrato FOREIGN KEY (nrPedido) REFERENCES pedido(nrPedido),
        CONSTRAINT fkPrato FOREIGN KEY (emailRestaurante, nomePrato) REFERENCES prato(emailRestaurante, nome),
        CONSTRAINT pk PRIMARY KEY (nrPedido, emailRestaurante, nomePrato)
    );
    
    CREATE TABLE pedidobebida (
        nrPedido INT,
        emailRestaurante VARCHAR(100),
        nomeBebida VARCHAR(100),
        quantidade INT,
        
        CONSTRAINT quantBebidaValida CHECK (quantidade > 0),
        
        CONSTRAINT fkPedidoBebida FOREIGN KEY (nrPedido) REFERENCES pedido(nrPedido),
        CONSTRAINT fkBebida FOREIGN KEY (emailRestaurante, nomeBebida) REFERENCES bebida(emailRestaurante, nome),
        CONSTRAINT pk PRIMARY KEY (nrPedido, emailRestaurante, nomeBebida)
    );
END;;

CREATE PROCEDURE removerBD()
BEGIN
    DROP TABLES IF EXISTS pedidoprato, pedidobebida, pedido, cliente;
    DROP TABLES IF EXISTS prato, bebida, restaurante, gestorog;
END;;

CREATE PROCEDURE limparTabelas()
BEGIN
    DELETE FROM pedidoprato;
    DELETE FROM pedidobebida;
    DELETE FROM pedido;
    DELETE FROM cliente;
    DELETE FROM prato;
    DELETE FROM bebida;
    DELETE FROM restaurante;
    DELETE FROM gestorog;
END;;
DELIMITER ;

CALL removerBD();
CALL criarBD();