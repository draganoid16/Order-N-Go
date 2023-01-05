DELIMITER ;;
CREATE PROCEDURE gestorogTeste()
BEGIN
    INSERT INTO gestorog(email, nome, morada, telemovel, palavraPasse, nrEmpregado) VALUES
        ("g1@g1.g1", "g1", "Morada 1", "111111111", "$2a$10$p3J1skLXREcRMYoZ2D/h5efcd4JvG5CSbPHCM.zWXlGJzweEFjTJG", 1), /* pass: 1 */
        ("g2@g2.g2", "g2", "Morada 2", "222222222", "$2a$10$Szr7iuqFo2cEYinLP5jmLO2Dkl2AADnNCq.SMTI/GPwc4I0FtRhr2", 2), /* pass: 2 */
        ("g3@g3.g3", "g3", "Morada 3", "333333333", "$2a$10$9uva0Z7J3hFrFHEzip8vI.AHNp7/pJTI3kwBMvXngLpcb50h3kNLW", 3), /* pass: 3 */
        ("g4@g4.g4", "g4", "Morada 4", "444444444", "$2a$10$u0S/U.6u.r1DOWDp0u9rwecZUU3HQnmsb/8w94KatQ1I8PJdYb/Vy", 4); /* pass: 4 */

    /*
    INSERT INTO gestorog(email, nome, morada, telemovel, palavraPasse, nrEmpregado) VALUES
        (NULL, "g10", "Morada 10", "101010101", "$2a$10$LMRK7r4h5zBykv89wk0Ccea0sIVXVHA2xDSu0mVu.7XJug/lVkfjG", 10);
    INSERT INTO gestorog(email, nome, morada, telemovel, palavraPasse, nrEmpregado) VALUES
        ("g10@g10.g10", NULL, "Morada 10", "101010101", "$2a$10$LMRK7r4h5zBykv89wk0Ccea0sIVXVHA2xDSu0mVu.7XJug/lVkfjG", 10);
    INSERT INTO gestorog(email, nome, morada, telemovel, palavraPasse, nrEmpregado) VALUES
        ("g10@g10.g10", "10", "Morada 10", "101010101", NULL, 10);
    INSERT INTO gestorog(email, nome, morada, telemovel, palavraPasse, nrEmpregado) VALUES
        ("g10@g10.g10", "10", "Morada 10", "101010101", "$2a$10$LMRK7r4h5zBykv89wk0Ccea0sIVXVHA2xDSu0mVu.7XJug/lVkfjG", NULL);
    INSERT INTO gestorog(email, nome, morada, telemovel, palavraPasse, nrEmpregado) VALUES
        ("g1@g1.g1", "10", "Morada 10", "101010101", "$2a$10$LMRK7r4h5zBykv89wk0Ccea0sIVXVHA2xDSu0mVu.7XJug/lVkfjG", 10);
    INSERT INTO gestorog(email, nome, morada, telemovel, palavraPasse, nrEmpregado) VALUES
        ("g10@g10.g10", "10", "Morada 10", "101010101", "$2a$10$LMRK7r4h5zBykv89wk0Ccea0sIVXVHA2xDSu0mVu.7XJug/lVkfjG", 1);
    */
END;;

CREATE PROCEDURE restauranteTeste()
BEGIN
    INSERT INTO restaurante(email, nome, morada, telemovel, palavraPasse, imagem) VALUES
        ("r1@r1.r1", "r1", "Morada 1", "111111111", "$2a$10$p3J1skLXREcRMYoZ2D/h5efcd4JvG5CSbPHCM.zWXlGJzweEFjTJG", NULL), /* pass: 1 */
        ("r2@r2.r2", "r2", "Morada 2", "222222222", "$2a$10$Szr7iuqFo2cEYinLP5jmLO2Dkl2AADnNCq.SMTI/GPwc4I0FtRhr2", NULL), /* pass: 2 */
        ("r3@r3.r3", "r3", "Morada 3", "333333333", "$2a$10$9uva0Z7J3hFrFHEzip8vI.AHNp7/pJTI3kwBMvXngLpcb50h3kNLW", NULL), /* pass: 3 */
        ("r4@r4.r4", "r4", "Morada 4", "444444444", "$2a$10$u0S/U.6u.r1DOWDp0u9rwecZUU3HQnmsb/8w94KatQ1I8PJdYb/Vy", NULL); /* pass: 4 */

    /*
    INSERT INTO restaurante(email, nome, morada, telemovel, palavraPasse, imagem) VALUES
        (NULL, "r10", "Morada 10", "101010101", "$2a$10$LMRK7r4h5zBykv89wk0Ccea0sIVXVHA2xDSu0mVu.7XJug/lVkfjG", NULL);
    INSERT INTO restaurante(email, nome, morada, telemovel, palavraPasse, imagem) VALUES
        ("r10@r10.r10", NULL, "Morada 10", "101010101", "$2a$10$LMRK7r4h5zBykv89wk0Ccea0sIVXVHA2xDSu0mVu.7XJug/lVkfjG", NULL);
    INSERT INTO restaurante(email, nome, morada, telemovel, palavraPasse, imagem) VALUES
        ("r10@r10.r10", "r10", "Morada 10", "101010101", NULL, NULL);
    INSERT INTO restaurante(email, nome, morada, telemovel, palavraPasse, imagem) VALUES
        ("r1@r1.r1", "r10", "Morada 10", "101010101", "$2a$10$LMRK7r4h5zBykv89wk0Ccea0sIVXVHA2xDSu0mVu.7XJug/lVkfjG", NULL);
    INSERT INTO restaurante(email, nome, morada, telemovel, palavraPasse, imagem) VALUES
        ("r10@r10.r10", "r1", "Morada 10", "101010101", "$2a$10$LMRK7r4h5zBykv89wk0Ccea0sIVXVHA2xDSu0mVu.7XJug/lVkfjG", NULL);
    */
END;;

CREATE PROCEDURE pratoTeste()
BEGIN
    INSERT INTO prato(emailRestaurante, nome, detalhes, precoUnitario, tipo, alergenios, imagem) VALUES
        ("r1@r1.r1", "Prato 1", "p1", 7.5, "CARNE", "NOZES", NULL),
        ("r2@r2.r2", "Prato 1", "p1", 10, "PEIXE", NULL, NULL),
        ("r3@r3.r3", "Prato 1", "p1", 20, "VEGETARIANO", "NOZES", NULL),
        ("r1@r1.r1", "Prato 2", "p2", 9.5, "VEGANO", NULL, NULL);

    /*
    INSERT INTO prato(emailRestaurante, nome, detalhes, tipo, alergenios, imagem) VALUES
        (NULL, "Prato 3", "p3", 10, "CARNE", NULL, NULL);
    INSERT INTO prato(emailRestaurante, nome, detalhes, tipo, alergenios, imagem) VALUES
        ("r10@r10.r10", "Prato 1", "p1", 10, "CARNE", NULL, NULL);
    INSERT INTO prato(emailRestaurante, nome, detalhes, tipo, alergenios, imagem) VALUES
        ("r1@r1.r1", "Prato 2", "p3", 10, "CARNE", NULL, NULL);
    INSERT INTO prato(emailRestaurante, nome, detalhes, tipo, alergenios, imagem) VALUES
        ("r1@r1.r1", "Prato 3", "p3", 10, NULL, NULL, NULL);
    INSERT INTO prato(emailRestaurante, nome, detalhes, tipo, alergenios, imagem) VALUES
        ("r1@r1.r1", "Prato 3", "p3", 10, "NOT", NULL, NULL);
    INSERT INTO prato(emailRestaurante, nome, detalhes, tipo, alergenios, imagem) VALUES
        ("r1@r1.r1", "Prato 3", "p3", 0, "CARNE", NULL, NULL);
    */
END;;

CREATE PROCEDURE bebidaTeste()
BEGIN
    INSERT INTO bebida(emailRestaurante, nome, detalhes, precoUnitario, capacidadeCL, imagem) VALUES
        ("r1@r1.r1", "Bebida 1", "b1", 2.5, 25, NULL),
        ("r2@r2.r2", "Bebida 1", "b1", 5, 25, NULL),
        ("r1@r1.r1", "Bebida 2", "b2", 1.5, 35, NULL);

    /*
    INSERT INTO bebida(emailRestaurante, nome, detalhes, capacidadeCL, imagem) VALUES
        (NULL, "Bebida 1", "b1", 5, 25, NULL);
    INSERT INTO bebida(emailRestaurante, nome, detalhes, capacidadeCL, imagem) VALUES
        ("r10@r10.r10", "Bebida 1", "b1", 5, 25, NULL);
    INSERT INTO bebida(emailRestaurante, nome, detalhes, capacidadeCL, imagem) VALUES
        ("r1@r1.r1", NULL, "b1", 25, NULL);
    INSERT INTO bebida(emailRestaurante, nome, detalhes, capacidadeCL, imagem) VALUES
        ("r1@r1.r1", "Bebida 1", "b1", 5, 25, NULL);
    INSERT INTO bebida(emailRestaurante, nome, detalhes, capacidadeCL, imagem) VALUES
        ("r1@r1.r1", "Bebida 10", "b10", 5, 0, NULL);
    INSERT INTO bebida(emailRestaurante, nome, detalhes, capacidadeCL, imagem) VALUES
        ("r3@r3.r3", "Bebida 1", "b1", 0, 25, NULL);
    */
END;;


CREATE PROCEDURE clienteTeste()
BEGIN
    INSERT INTO cliente(email, nome, morada, telemovel, palavraPasse, nif) VALUES
        ("c1@c1.c1", "c1", "Morada 1", "111111111", "$2a$10$p3J1skLXREcRMYoZ2D/h5efcd4JvG5CSbPHCM.zWXlGJzweEFjTJG", "111111111"), /* pass: 1 */
        ("c2@c2.c2", "c2", "Morada 2", "222222222", "$2a$10$Szr7iuqFo2cEYinLP5jmLO2Dkl2AADnNCq.SMTI/GPwc4I0FtRhr2", "222222222"), /* pass: 2 */
        ("c3@c3.c3", "c3", "Morada 3", "333333333", "$2a$10$9uva0Z7J3hFrFHEzip8vI.AHNp7/pJTI3kwBMvXngLpcb50h3kNLW", "333333333"), /* pass: 3 */
        ("c4@c4.c4", "c4", "Morada 4", "444444444", "$2a$10$u0S/U.6u.r1DOWDp0u9rwecZUU3HQnmsb/8w94KatQ1I8PJdYb/Vy", "444444444"); /* pass: 4 */

    /*
    INSERT INTO cliente(email, nome, morada, telemovel, palavraPasse, nif) VALUES
        (NULL, "c10", "Morada 10", "101010101", "$2a$10$LMRK7r4h5zBykv89wk0Ccea0sIVXVHA2xDSu0mVu.7XJug/lVkfjG", "101010101");
    INSERT INTO cliente(email, nome, morada, telemovel, palavraPasse, nif) VALUES
        ("c10@c10.c10", "c10", "Morada 10", "101010101", NULL, "101010101");
    INSERT INTO cliente(email, nome, morada, telemovel, palavraPasse, nif) VALUES
        ("c10@c10.c10", "c10", "Morada 10", "101010101", "$2a$10$LMRK7r4h5zBykv89wk0Ccea0sIVXVHA2xDSu0mVu.7XJug/lVkfjG", NULL);
    INSERT INTO cliente(email, nome, morada, telemovel, palavraPasse, nif) VALUES
        ("c1@c1.c1", "c10", "Morada 10", "101010101", "$2a$10$LMRK7r4h5zBykv89wk0Ccea0sIVXVHA2xDSu0mVu.7XJug/lVkfjG", "101010101");
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