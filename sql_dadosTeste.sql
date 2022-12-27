DELIMITER ;;
CREATE PROCEDURE gestorogTeste()
BEGIN
    INSERT INTO gestorog(email, nome, morada, telemovel, palavraPasse, nrEmpregado) VALUES
        ("g1@g1.g1", "g1", "Morada 1", "111111111", "67b176705b46206614219f47a05aee7ae6a3edbe850bbbe214c536b989aea4d2", 1), /* pass: 1 */
        ("g2@g2.g2", "g2", "Morada 2", "222222222", "b1b1bd1ed240b1496c81ccf19ceccf2af6fd24fac10ae42023628abbe2687310", 2), /* pass: 2 */
        ("g3@g3.g3", "g3", "Morada 3", "333333333", "1bf0b26eb2090599dd68cbb42c86a674cb07ab7adc103ad3ccdf521bb79056b9", 3), /* pass: 3 */
        ("g4@g4.g4", "g4", "Morada 4", "444444444", "b410677b84ed73fac43fcf1abd933151dd417d932a0ef9b0260ecf8b7b72ecb9", 4); /* pass: 4 */

    /*
    INSERT INTO gestorog(email, nome, morada, telemovel, palavraPasse, nrEmpregado) VALUES
        (NULL, "g10", "Morada 10", "101010101", "dd121e36961a04627eacff629765dd3528471ed745c1e32222db4a8a5f3421c4", 10);
    INSERT INTO gestorog(email, nome, morada, telemovel, palavraPasse, nrEmpregado) VALUES
        ("g10@g10.g10", NULL, "Morada 10", "101010101", "dd121e36961a04627eacff629765dd3528471ed745c1e32222db4a8a5f3421c4", 10);
    INSERT INTO gestorog(email, nome, morada, telemovel, palavraPasse, nrEmpregado) VALUES
        ("g10@g10.g10", "10", "Morada 10", "101010101", NULL, 10);
    INSERT INTO gestorog(email, nome, morada, telemovel, palavraPasse, nrEmpregado) VALUES
        ("g10@g10.g10", "10", "Morada 10", "101010101", "dd121e36961a04627eacff629765dd3528471ed745c1e32222db4a8a5f3421c4", NULL);
    INSERT INTO gestorog(email, nome, morada, telemovel, palavraPasse, nrEmpregado) VALUES
        ("g1@g1.g1", "10", "Morada 10", "101010101", "dd121e36961a04627eacff629765dd3528471ed745c1e32222db4a8a5f3421c4", 10);
    INSERT INTO gestorog(email, nome, morada, telemovel, palavraPasse, nrEmpregado) VALUES
        ("g10@g10.g10", "10", "Morada 10", "101010101", "dd121e36961a04627eacff629765dd3528471ed745c1e32222db4a8a5f3421c4", 1);
    */
END;;

CREATE PROCEDURE restauranteTeste()
BEGIN
    INSERT INTO restaurante(email, nome, morada, telemovel, palavraPasse) VALUES
        ("r1@r1.r1", "r1", "Morada 1", "111111111", "67b176705b46206614219f47a05aee7ae6a3edbe850bbbe214c536b989aea4d2"), /* pass: 1 */
        ("r2@r2.r2", "r2", "Morada 2", "222222222", "b1b1bd1ed240b1496c81ccf19ceccf2af6fd24fac10ae42023628abbe2687310"), /* pass: 2 */
        ("r3@r3.r3", "r3", "Morada 3", "333333333", "1bf0b26eb2090599dd68cbb42c86a674cb07ab7adc103ad3ccdf521bb79056b9"), /* pass: 3 */
        ("r4@r4.r4", "r4", "Morada 4", "444444444", "b410677b84ed73fac43fcf1abd933151dd417d932a0ef9b0260ecf8b7b72ecb9"); /* pass: 4 */

    /*
    INSERT INTO restaurante(email, nome, morada, telemovel, palavraPasse) VALUES
        (NULL, "r10", "Morada 10", "101010101", "dd121e36961a04627eacff629765dd3528471ed745c1e32222db4a8a5f3421c4");
    INSERT INTO restaurante(email, nome, morada, telemovel, palavraPasse) VALUES
        ("r10@r10.r10", NULL, "Morada 10", "101010101", "dd121e36961a04627eacff629765dd3528471ed745c1e32222db4a8a5f3421c4");
    INSERT INTO restaurante(email, nome, morada, telemovel, palavraPasse) VALUES
        ("r10@r10.r10", "r10", "Morada 10", "101010101", NULL);
    INSERT INTO restaurante(email, nome, morada, telemovel, palavraPasse) VALUES
        ("r1@r1.r1", "r10", "Morada 10", "101010101", "dd121e36961a04627eacff629765dd3528471ed745c1e32222db4a8a5f3421c4");
    INSERT INTO restaurante(email, nome, morada, telemovel, palavraPasse) VALUES
        ("r10@r10.r10", "r1", "Morada 10", "101010101", "dd121e36961a04627eacff629765dd3528471ed745c1e32222db4a8a5f3421c4");
    */
END;;

CREATE PROCEDURE pratoTeste()
BEGIN
    INSERT INTO prato(emailRestaurante, nome, detalhes, precoUnitario, tipo, alergenios) VALUES
        ("r1@r1.r1", "Prato 1", "p1", 7.5, "CARNE", "NOZES"),
        ("r2@r2.r2", "Prato 1", "p1", 10, "PEIXE", NULL),
        ("r3@r3.r3", "Prato 1", "p1", 20, "VEGETARIANO", "NOZES"),
        ("r1@r1.r1", "Prato 2", "p2", 9.5, "VEGANO", NULL);

    /*
    INSERT INTO prato(emailRestaurante, nome, detalhes, tipo, alergenios) VALUES
        (NULL, "Prato 3", "p3", 10, "CARNE", NULL);
    INSERT INTO prato(emailRestaurante, nome, detalhes, tipo, alergenios) VALUES
        ("r10@r10.r10", "Prato 1", "p1", 10, "CARNE", NULL);
    INSERT INTO prato(emailRestaurante, nome, detalhes, tipo, alergenios) VALUES
        ("r1@r1.r1", "Prato 2", "p3", 10, "CARNE", NULL);
    INSERT INTO prato(emailRestaurante, nome, detalhes, tipo, alergenios) VALUES
        ("r1@r1.r1", "Prato 3", "p3", 10, NULL, NULL);
    INSERT INTO prato(emailRestaurante, nome, detalhes, tipo, alergenios) VALUES
        ("r1@r1.r1", "Prato 3", "p3", 10, "NOT", NULL);
    INSERT INTO prato(emailRestaurante, nome, detalhes, tipo, alergenios) VALUES
        ("r1@r1.r1", "Prato 3", "p3", 0, "CARNE", NULL);
    */
END;;

CREATE PROCEDURE bebidaTeste()
BEGIN
    INSERT INTO bebida(emailRestaurante, nome, detalhes, precoUnitario, capacidadeCL) VALUES
        ("r1@r1.r1", "Bebida 1", "b1", 2.5, 25),
        ("r2@r2.r2", "Bebida 1", "b1", 5, 25),
        ("r1@r1.r1", "Bebida 2", "b2", 1.5, 35);

    /*
    INSERT INTO bebida(emailRestaurante, nome, detalhes, capacidadeCL) VALUES
        (NULL, "Bebida 1", "b1", 5, 25);
    INSERT INTO bebida(emailRestaurante, nome, detalhes, capacidadeCL) VALUES
        ("r10@r10.r10", "Bebida 1", "b1", 5, 25);
    INSERT INTO bebida(emailRestaurante, nome, detalhes, capacidadeCL) VALUES
        ("r1@r1.r1", NULL, "b1", 25);
    INSERT INTO bebida(emailRestaurante, nome, detalhes, capacidadeCL) VALUES
        ("r1@r1.r1", "Bebida 1", "b1", 5, 25);
    INSERT INTO bebida(emailRestaurante, nome, detalhes, capacidadeCL) VALUES
        ("r1@r1.r1", "Bebida 10", "b10", 5, 0);
    INSERT INTO bebida(emailRestaurante, nome, detalhes, capacidadeCL) VALUES
        ("r3@r3.r3", "Bebida 1", "b1", 0, 25);
    */
END;;


CREATE PROCEDURE clienteTeste()
BEGIN
    INSERT INTO cliente(email, nome, morada, telemovel, palavraPasse, nif) VALUES
        ("c1@c1.c1", "c1", "Morada 1", "111111111", "67b176705b46206614219f47a05aee7ae6a3edbe850bbbe214c536b989aea4d2", "111111111"), /* pass: 1 */
        ("c2@c2.c2", "c2", "Morada 2", "222222222", "b1b1bd1ed240b1496c81ccf19ceccf2af6fd24fac10ae42023628abbe2687310", "222222222"), /* pass: 2 */
        ("c3@c3.c3", "c3", "Morada 3", "333333333", "1bf0b26eb2090599dd68cbb42c86a674cb07ab7adc103ad3ccdf521bb79056b9", "333333333"), /* pass: 3 */
        ("c4@c4.c4", "c4", "Morada 4", "444444444", "b410677b84ed73fac43fcf1abd933151dd417d932a0ef9b0260ecf8b7b72ecb9", "444444444"); /* pass: 4 */

    /*
    INSERT INTO cliente(email, nome, morada, telemovel, palavraPasse, nif) VALUES
        (NULL, "c10", "Morada 10", "101010101", "dd121e36961a04627eacff629765dd3528471ed745c1e32222db4a8a5f3421c4", "101010101");
    INSERT INTO cliente(email, nome, morada, telemovel, palavraPasse, nif) VALUES
        ("c10@c10.c10", "c10", "Morada 10", "101010101", NULL, "101010101");
    INSERT INTO cliente(email, nome, morada, telemovel, palavraPasse, nif) VALUES
        ("c10@c10.c10", "c10", "Morada 10", "101010101", "dd121e36961a04627eacff629765dd3528471ed745c1e32222db4a8a5f3421c4", NULL);
    INSERT INTO cliente(email, nome, morada, telemovel, palavraPasse, nif) VALUES
        ("c1@c1.c1", "c10", "Morada 10", "101010101", "dd121e36961a04627eacff629765dd3528471ed745c1e32222db4a8a5f3421c4", "101010101");
    */
END;;

CREATE PROCEDURE pedidoTeste()
BEGIN
    INSERT INTO pedido(nrPedido, moradaEntrega, dataHoraEntrega, emailCliente) VALUES
        (1, "Morada 1", "2022-12-15 01:01:01", "c1@c1.c1"),
        (2, "Morada 2", "2022-12-15 02:02:02", "c2@c2.c2"),
        (3, "Morada 3", "2022-12-15 03:03:03", "c3@c3.c3"),
        (4, "Morada 1", "2022-12-15 04:04:04", "c1@c1.c1");
	
    /*
    INSERT INTO pedido(nrPedido, moradaEntrega, dataHoraEntrega, emailCliente) VALUES
        (1, "Morada 10", "2022-12-15 10:10:10", "c1@c1.c1");
    INSERT INTO pedido(nrPedido, moradaEntrega, dataHoraEntrega, emailCliente) VALUES
        (10, "Morada 10", "2022-12-15 10:10:10", NULL);
    INSERT INTO pedido(nrPedido, moradaEntrega, dataHoraEntrega, emailCliente) VALUES
        (10, "Morada 10", "2022-12-15 10:10:10", "r10@r10.r10");
    */
END;;

CREATE PROCEDURE pedidoPratoTeste()
BEGIN
    INSERT INTO pedidoprato(nrPedido, emailRestaurante, nomePrato, quantidade) VALUES
        (1, "r1@r1.r1", "Prato 1", 1),
        (1, "r2@r2.r2", "Prato 1", 1),
        (2, "r1@r1.r1", "Prato 2", 2),
        (3, "r1@r1.r1", "Prato 1", 3);
	
    /*
    INSERT INTO pedidoPrato(nrPedido, emailRestaurante, nomePrato, quantidade) VALUES
        (NULL, "r1@r1.r1", "Prato 1", 1);
    INSERT INTO pedidoPrato(nrPedido, emailRestaurante, nomePrato, quantidade) VALUES
        (10, "r1@r1.r1", "Prato 1", 1);
    INSERT INTO pedidoPrato(nrPedido, emailRestaurante, nomePrato, quantidade) VALUES
        (1, NULL, "Prato 1", 1);
    INSERT INTO pedidoPrato(nrPedido, emailRestaurante, nomePrato, quantidade) VALUES
        (1, "r10@r10.r10", "Prato 1", 1);
    INSERT INTO pedidoPrato(nrPedido, emailRestaurante, nomePrato, quantidade) VALUES
        (1, "r1@r1.r1", NULL, 1);
    INSERT INTO pedidoPrato(nrPedido, emailRestaurante, nomePrato, quantidade) VALUES
        (1, "r1@r1.r1", "Prato 10", 1);
    INSERT INTO pedidoPrato(nrPedido, emailRestaurante, nomePrato, quantidade) VALUES
        (1, "r1@r1.r1", "Prato 2", 0);
    */
END;;

CREATE PROCEDURE pedidoBebidaTeste()
BEGIN
    INSERT INTO pedidobebida(nrPedido, emailRestaurante, nomeBebida, quantidade) VALUES
        (1, "r1@r1.r1", "Bebida 1", 1),
        (1, "r2@r2.r2", "Bebida 1", 1),
        (2, "r1@r1.r1", "Bebida 2", 2),
        (3, "r1@r1.r1", "Bebida 1", 3);
	
    /*
    INSERT INTO pedidobebida(nrPedido, emailRestaurante, nomeBebida, quantidade) VALUES
        (NULL, "r1@r1.r1", "Bebida 1", 1);
    INSERT INTO pedidobebida(nrPedido, emailRestaurante, nomeBebida, quantidade) VALUES
        (10, "r1@r1.r1", "Bebida 1", 1);
    INSERT INTO pedidobebida(nrPedido, emailRestaurante, nomeBebida, quantidade) VALUES
        (1, NULL, "Bebida 1", 1);
    INSERT INTO pedidobebida(nrPedido, emailRestaurante, nomeBebida, quantidade) VALUES
        (1, "r10@r10.r10", "Bebida 1", 1);
    INSERT INTO pedidobebida(nrPedido, emailRestaurante, nomeBebida, quantidade) VALUES
        (1, "r1@r1.r1", NULL, 1);
    INSERT INTO pedidobebida(nrPedido, emailRestaurante, nomeBebida, quantidade) VALUES
        (1, "r1@r1.r1", "Bebida 10", 1);
    INSERT INTO pedidobebida(nrPedido, emailRestaurante, nomeBebida, quantidade) VALUES
        (1, "r1@r1.r1", "Bebida 2", 0);
    */
END;;
DELIMITER ;

CALL limparTabelas();

CALL gestorogTeste();
CALL restauranteTeste();
CALL pratoTeste();
CALL bebidaTeste();
CALL clienteTeste();
CALL pedidoTeste();
CALL pedidoPratoTeste();
CALL pedidoBebidaTeste();

DROP PROCEDURE IF EXISTS gestorogTeste;
DROP PROCEDURE IF EXISTS restauranteTeste;
DROP PROCEDURE IF EXISTS pratoTeste;
DROP PROCEDURE IF EXISTS bebidaTeste;
DROP PROCEDURE IF EXISTS clienteTeste;
DROP PROCEDURE IF EXISTS pedidoTeste;
DROP PROCEDURE IF EXISTS pedidoPratoTeste;
DROP PROCEDURE IF EXISTS pedidoBebidaTeste;