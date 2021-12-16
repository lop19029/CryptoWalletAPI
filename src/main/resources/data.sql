-- Populate profiles
INSERT INTO tb_perfil
VALUES(1,'ADMIN');

--Populate users
INSERT INTO tb_usuario
VALUES(1, 'admin_test@gft.com', '$2a$10$pIoXTwyMc9f3kIi8lrjeJupiDDpt5wcZsXbi.cpSRK1iytwuILwOK', 1);

--Populate criptos
INSERT INTO tb_cripto
VALUES
(1, 'Bitcoin', 'BTC', 50000),
(2, 'Ethereum', 'ETH', 4000),
(3, 'Cardano', 'ADA', 1.50),
(4, 'Solana', 'SOL', 180);

-- Populate cliente
INSERT INTO tb_cliente
VALUES(1, 0981237632, 'Alex López', 'alex@gft.com', 'Rua A', '22', '', '45909400');

-- Populate carteira
INSERT INTO tb_carteira
VALUES(1, 1);

-- Populate carteira_tem_cripto
INSERT INTO tb_carteira_tem_cripto
VALUES
(1, 1, 1, 0.045),
(2, 1, 2, 3);
