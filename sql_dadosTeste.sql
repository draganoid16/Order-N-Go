DROP PROCEDURE IF EXISTS inserirDadosTeste;

DELIMITER ;;
CREATE PROCEDURE inserirDadosTeste()
BEGIN
    INSERT INTO gestorog(email, nome, morada, telemovel, nrEmpregado, palavraPasse) VALUES
        ("gestor1@gong.com", "Gestor1", "Morada 1", "111111111", 1, "$2a$10$p3J1skLXREcRMYoZ2D/h5efcd4JvG5CSbPHCM.zWXlGJzweEFjTJG"), /* pass: 1 */
        ("gestor2@gong.com", "Gestor2", "Morada 2", "222222222", 2, "$2a$10$Szr7iuqFo2cEYinLP5jmLO2Dkl2AADnNCq.SMTI/GPwc4I0FtRhr2"), /* pass: 2 */
        ("gestor3@gong.com", "Gestor3", "Morada 3", "333333333", 3, "$2a$10$9uva0Z7J3hFrFHEzip8vI.AHNp7/pJTI3kwBMvXngLpcb50h3kNLW"), /* pass: 3 */
        ("gestor4@gong.com", "Gestor4", "Morada 4", "444444444", 4, "$2a$10$u0S/U.6u.r1DOWDp0u9rwecZUU3HQnmsb/8w94KatQ1I8PJdYb/Vy"); /* pass: 4 */

    INSERT INTO restaurante(email, nome, morada, telemovel, palavraPasse) VALUES
        ("rest1@rest.com", "Restaurante1", "Morada 1", "111111111", "$2a$10$p3J1skLXREcRMYoZ2D/h5efcd4JvG5CSbPHCM.zWXlGJzweEFjTJG"), /* pass: 1 */
        ("rest2@rest.com", "Restaurante2", "Morada 2", "222222222", "$2a$10$Szr7iuqFo2cEYinLP5jmLO2Dkl2AADnNCq.SMTI/GPwc4I0FtRhr2"), /* pass: 2 */
        ("rest3@rest.com", "Restaurante3", "Morada 3", "333333333", "$2a$10$9uva0Z7J3hFrFHEzip8vI.AHNp7/pJTI3kwBMvXngLpcb50h3kNLW"), /* pass: 3 */
        ("rest4@rest.com", "Restaurante4", "Morada 4", "444444444", "$2a$10$u0S/U.6u.r1DOWDp0u9rwecZUU3HQnmsb/8w94KatQ1I8PJdYb/Vy"); /* pass: 4 */


    INSERT INTO prato(emailRestaurante, nome, detalhes, precoUnitario, tipo, alergenios) VALUES
        ("rest1@rest.com", "Prato 1R1", "Detalhes 1R1", 7.5, "CARNE", "NOZES"),
        ("rest2@rest.com", "Prato 1R2", "Detalhes 1R2", 10, "PEIXE", NULL),
        ("rest3@rest.com", "Prato 1R3", "Detalhes 1R3", 20, "VEGETARIANO", "NOZES"),
        ("rest1@rest.com", "Prato 2R1", "Detalhes 2R1", 9.5, "VEGANO", NULL);

    INSERT INTO bebida(emailRestaurante, nome, detalhes, precoUnitario, capacidadeCL) VALUES
        ("rest1@rest.com", "Bebida 1R1", "Detalhes 1R1", 2.5, 25),
        ("rest2@rest.com", "Bebida 1R2", "Detalhes 1R2", 5, 25),
        ("rest1@rest.com", "Bebida 2R1", "Detalhes 2R1", 1.5, 35);
    

    INSERT INTO cliente(email, nome, morada, telemovel, nif, palavraPasse) VALUES
        ("cli1@cli.com", "Cliente1", "Morada 1", "111111111", "111111111", "$2a$10$p3J1skLXREcRMYoZ2D/h5efcd4JvG5CSbPHCM.zWXlGJzweEFjTJG"), /* pass: 1 */
        ("cli2@cli.com", "Cliente2", "Morada 2", "222222222", "222222222", "$2a$10$Szr7iuqFo2cEYinLP5jmLO2Dkl2AADnNCq.SMTI/GPwc4I0FtRhr2"), /* pass: 2 */
        ("cli3@cli.com", "Cliente3", "Morada 3", "333333333", "333333333", "$2a$10$9uva0Z7J3hFrFHEzip8vI.AHNp7/pJTI3kwBMvXngLpcb50h3kNLW"), /* pass: 3 */
        ("cli4@cli.com", "Cliente4", "Morada 4", "444444444", "444444444", "$2a$10$u0S/U.6u.r1DOWDp0u9rwecZUU3HQnmsb/8w94KatQ1I8PJdYb/Vy"); /* pass: 4 */

    INSERT INTO pedido(nrPedido, moradaEntrega, dataHoraEntrega, emailCliente) VALUES
        (1, "Morada 1", "2022-12-15 01:01", "cli1@cli.com"),
        (2, "Morada 2", "2022-12-15 02:02", "cli2@cli.com"),
        (3, "Morada 3", "2022-12-15 03:03", "cli3@cli.com"),
        (4, "Morada 1", "2022-12-15 04:04", "cli1@cli.com");

    INSERT INTO pedidoprato(nrPedido, emailRestaurante, nomePrato, quantidade) VALUES
        (1, "rest1@rest.com", "Prato 1R1", 1),
        (1, "rest2@rest.com", "Prato 1R2", 1),
        (2, "rest1@rest.com", "Prato 2R1", 2),
        (3, "rest1@rest.com", "Prato 1R1", 3);
	
    INSERT INTO pedidobebida(nrPedido, emailRestaurante, nomeBebida, quantidade) VALUES
        (1, "rest1@rest.com", "Bebida 1R1", 1),
        (1, "rest2@rest.com", "Bebida 1R2", 1),
        (2, "rest1@rest.com", "Bebida 2R1", 2),
        (3, "rest1@rest.com", "Bebida 1R1", 3);
END;;
DELIMITER ;

CALL limparTabelas();
CALL inserirDadosTeste();