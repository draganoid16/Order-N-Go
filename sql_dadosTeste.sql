DELIMITER ;;
CREATE PROCEDURE restauranteTeste()
BEGIN
	INSERT INTO restaurante(email, nome, morada, telemovel, palavraPasse) VALUES
		("1@1.1", "1", "Morada 1", "111111111", md5("1")),
		("2@2.2", "2", "Morada 2", "222222222", md5("2")),
		("3@3.3", "3", "Morada 3", "333333333", md5("3")),
		("4@4.4", "4", "Morada 4", "444444444", md5("4"));
		
	/*
	INSERT INTO restaurante(email, nome, morada, telemovel, palavraPasse) VALUES
		(NULL, "10", "Morada 10", "101010101", md5("10"));
	INSERT INTO restaurante(email, nome, morada, telemovel, palavraPasse) VALUES
		("10@10.10", NULL, "Morada 10", "101010101", md5("10"));
	INSERT INTO restaurante(email, nome, morada, telemovel, palavraPasse) VALUES
		("10@10.10", "10", "Morada 10", "101010101", NULL);
	INSERT INTO restaurante(email, nome, morada, telemovel, palavraPasse) VALUES
		("1@1.1", "10", "Morada 10", "101010101", md5("10"));
	INSERT INTO restaurante(email, nome, morada, telemovel, palavraPasse) VALUES
		("10@10.10", "0", "Morada 10", "101010101", md5("10"));
	*/
END;;

CREATE PROCEDURE pratoTeste()
BEGIN
	INSERT INTO prato(emailRestaurante, nome, detalhes, tipo, alergenios) VALUES
		("1@1.1", "Prato 1", "1", "CARNE", "NOT"),
		("2@2.2", "Prato 1", "1", "PEIXE", NULL),
		("3@3.3", "Prato 1", "1", "VEGETARIANO", "NOT"),
		("1@1.1", "Prato 2", "2", "VEGANO", NULL);
		
	/*
	INSERT INTO prato(emailRestaurante, nome, detalhes, tipo, alergenios) VALUES
		(NULL, "Prato 3", "3", "CARNE", NULL);
	INSERT INTO prato(emailRestaurante, nome, detalhes, tipo, alergenios) VALUES
		("10@10.10", "Prato 1", "1", "CARNE", NULL);
	INSERT INTO prato(emailRestaurante, nome, detalhes, tipo, alergenios) VALUES
		("1@1.1", "Prato 2", "3", "CARNE", NULL);
	INSERT INTO prato(emailRestaurante, nome, detalhes, tipo, alergenios) VALUES
		("1@1.1", "Prato 3", "3", NULL, NULL);
	INSERT INTO prato(emailRestaurante, nome, detalhes, tipo, alergenios) VALUES
		("1@1.1", "Prato 3", "3", "NOT", NULL);
	*/
END;;

CREATE PROCEDURE bebidaTeste()
BEGIN
	INSERT INTO bebida(emailRestaurante, nome, detalhes, capacidadeCL) VALUES
		("1@1.1", "Bebida 1", "1", 25),
		("2@2.2", "Bebida 1", "1", 25),
		("1@1.1", "Bebida 2", "2", 35);
		
	/*
	INSERT INTO bebida(emailRestaurante, nome, detalhes, capacidadeCL) VALUES
		(NULL, "Bebida 1", "1", 25);
	INSERT INTO bebida(emailRestaurante, nome, detalhes, capacidadeCL) VALUES
		("10@10.10", "Bebida 1", "1", 25);
	INSERT INTO bebida(emailRestaurante, nome, detalhes, capacidadeCL) VALUES
		("1@1.1", NULL, "1", 25);
	INSERT INTO bebida(emailRestaurante, nome, detalhes, capacidadeCL) VALUES
		("1@1.1", "Bebida 1", "1", 25);
	INSERT INTO bebida(emailRestaurante, nome, detalhes, capacidadeCL) VALUES
		("1@1.1", "Bebida 10", "10", 0);
	*/
END;;


CREATE PROCEDURE clienteTeste()
BEGIN
	INSERT INTO cliente(email, nome, morada, telemovel, palavraPasse, nif) VALUES
		("1@1.1", "1", "Morada 1", "111111111", md5("1"), "111111111"),
		("2@2.2", "2", "Morada 2", "222222222", md5("2"), "222222222"),
		("3@3.3", "3", "Morada 3", "333333333", md5("3"), "333333333"),
		("4@4.4", "4", "Morada 4", "444444444", md5("4"), "444444444");
		
	/*
	INSERT INTO cliente(email, nome, morada, telemovel, palavraPasse, nif) VALUES
		(NULL, "10", "Morada 10", "101010101", md5("10"), "101010101");
	INSERT INTO cliente(email, nome, morada, telemovel, palavraPasse, nif) VALUES
		("10@10.10", "10", "Morada 10", "101010101", NULL, "101010101");
	INSERT INTO cliente(email, nome, morada, telemovel, palavraPasse, nif) VALUES
		("10@10.10", "10", "Morada 10", "101010101", md5("10"), NULL);
	INSERT INTO cliente(email, nome, morada, telemovel, palavraPasse, nif) VALUES
		("1@1.1", "10", "Morada 10", "101010101", md5("10"), "101010101");
	*/
END;;

CREATE PROCEDURE pedidoTeste()
BEGIN
	INSERT INTO pedido(nrPedido, moradaEntrega, dataHoraEntrega, emailCliente) VALUES
		(1, "Morada 1", "2022-12-15 01:01:01", "1@1.1"),
        (2, "Morada 2", "2022-12-15 02:02:02", "2@2.2"),
        (3, "Morada 3", "2022-12-15 03:03:03", "3@3.3"),
        (4, "Morada 1", "2022-12-15 04:04:04", "1@1.1");
	
    /*
	INSERT INTO pedido(nrPedido, moradaEntrega, dataHoraEntrega, emailCliente) VALUES
		(1, "Morada 10", "2022-12-15 10:10:10", "1@1.1");
	INSERT INTO pedido(nrPedido, moradaEntrega, dataHoraEntrega, emailCliente) VALUES
		(10, "Morada 10", "2022-12-15 10:10:10", NULL);
	INSERT INTO pedido(nrPedido, moradaEntrega, dataHoraEntrega, emailCliente) VALUES
		(10, "Morada 10", "2022-12-15 10:10:10", "10@10.10");
	*/
END;;

CREATE PROCEDURE pedidoPratoTeste()
BEGIN
	INSERT INTO pedidoprato(nrPedido, emailRestaurante, nomePrato, quantidade) VALUES
		(1, "1@1.1", "Prato 1", 1),
        (1, "2@2.2", "Prato 1", 1),
        (2, "1@1.1", "Prato 2", 2),
        (3, "1@1.1", "Prato 1", 3);
	
    /*
	INSERT INTO pedidoPrato(nrPedido, emailRestaurante, nomePrato, quantidade) VALUES
		(NULL, "1@1.1", "Prato 1", 1);
	INSERT INTO pedidoPrato(nrPedido, emailRestaurante, nomePrato, quantidade) VALUES
		(10, "1@1.1", "Prato 1", 1);
	INSERT INTO pedidoPrato(nrPedido, emailRestaurante, nomePrato, quantidade) VALUES
		(1, NULL, "Prato 1", 1);
	INSERT INTO pedidoPrato(nrPedido, emailRestaurante, nomePrato, quantidade) VALUES
		(1, "10@10.10", "Prato 1", 1);
	INSERT INTO pedidoPrato(nrPedido, emailRestaurante, nomePrato, quantidade) VALUES
		(1, "1@1.1", NULL, 1);
	INSERT INTO pedidoPrato(nrPedido, emailRestaurante, nomePrato, quantidade) VALUES
		(1, "1@1.1", "Prato 10", 1);
	INSERT INTO pedidoPrato(nrPedido, emailRestaurante, nomePrato, quantidade) VALUES
		(1, "1@1.1", "Prato 2", 0);
	*/
END;;

CREATE PROCEDURE pedidoBebidaTeste()
BEGIN
	INSERT INTO pedidobebida(nrPedido, emailRestaurante, nomeBebida, quantidade) VALUES
		(1, "1@1.1", "Bebida 1", 1),
        (1, "2@2.2", "Bebida 1", 1),
        (2, "1@1.1", "Bebida 2", 2),
        (3, "1@1.1", "Bebida 1", 3);
	
    /*
	INSERT INTO pedidobebida(nrPedido, emailRestaurante, nomeBebida, quantidade) VALUES
		(NULL, "1@1.1", "Bebida 1", 1);
	INSERT INTO pedidobebida(nrPedido, emailRestaurante, nomeBebida, quantidade) VALUES
		(10, "1@1.1", "Bebida 1", 1);
	INSERT INTO pedidobebida(nrPedido, emailRestaurante, nomeBebida, quantidade) VALUES
		(1, NULL, "Bebida 1", 1);
	INSERT INTO pedidobebida(nrPedido, emailRestaurante, nomeBebida, quantidade) VALUES
		(1, "10@10.10", "Bebida 1", 1);
	INSERT INTO pedidobebida(nrPedido, emailRestaurante, nomeBebida, quantidade) VALUES
		(1, "1@1.1", NULL, 1);
	INSERT INTO pedidobebida(nrPedido, emailRestaurante, nomeBebida, quantidade) VALUES
		(1, "1@1.1", "Bebida 10", 1);
	INSERT INTO pedidobebida(nrPedido, emailRestaurante, nomeBebida, quantidade) VALUES
		(1, "1@1.1", "Bebida 2", 0);
	*/
END;;
DELIMITER ;

CALL limparTabelas();

CALL restauranteTeste();
CALL pratoTeste();
CALL bebidaTeste();
CALL clienteTeste();
CALL pedidoTeste();
CALL pedidoPratoTeste();
CALL pedidoBebidaTeste();

DROP PROCEDURE IF EXISTS restauranteTeste;
DROP PROCEDURE IF EXISTS pratoTeste;
DROP PROCEDURE IF EXISTS bebidaTeste;
DROP PROCEDURE IF EXISTS clienteTeste;
DROP PROCEDURE IF EXISTS pedidoTeste;
DROP PROCEDURE IF EXISTS pedidoPratoTeste;
DROP PROCEDURE IF EXISTS pedidoBebidaTeste;