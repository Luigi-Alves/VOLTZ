INSERT INTO USUARIO (idUsuario, nome, email, senhaCriptografada, is2FAAtivo)
VALUES (1, 'Luigi Alves', 'luigi@voltz.com', 'a1b2c3hash', 1);

INSERT INTO USUARIO (idUsuario, nome, email, senhaCriptografada, is2FAAtivo)
VALUES (2, 'Ana Souza', 'ana@voltz.com', 'd4e5f6hash', 0);

INSERT INTO USUARIO_VIP (idUsuario, status)
VALUES (1, 1);

INSERT INTO EMPRESA (idEmpresa, nomeEmpresa, razaoSocial, cnpj, saldoFiduciario, idUsuario)
VALUES (1, 'Voltz Trading', 'Voltz Trading LTDA', '12.345.678/0001-99', 15000.00, 1);

INSERT INTO EMPRESA (idEmpresa, nomeEmpresa, razaoSocial, cnpj, saldoFiduciario, idUsuario)
VALUES (2, 'Ana Invest', 'Ana Invest ME', '98.765.432/0001-11', 5000.00, 2);

INSERT INTO ATIVO_CRIPTO (idAtivo, nomeMoeda, simbolo, precoAtual)
VALUES (1, 'Bitcoin', 'BTC', 350000.00000000);

INSERT INTO ATIVO_CRIPTO (idAtivo, nomeMoeda, simbolo, precoAtual)
VALUES (2, 'Ethereum', 'ETH', 12000.00000000);

INSERT INTO ATIVO_CRIPTO (idAtivo, nomeMoeda, simbolo, precoAtual)
VALUES (3, 'Solana', 'SOL', 650.00000000);

INSERT INTO DASHBOARD (idDashboard, idUsuario, saldoTotalGeral, lucroPrejuizoTotal)
VALUES (1, 1, 15000.00, 0.00);

INSERT INTO DASHBOARD (idDashboard, idUsuario, saldoTotalGeral, lucroPrejuizoTotal)
VALUES (2, 2, 5000.00, 0.00);

INSERT INTO TRANSACAO (idTransacao, idEmpresa, idAtivo, tipoDeTransacao, quantidade, valorUnitarioNoMomento, dataHora)
VALUES (1, 1, 1, 'COMPRA', 0.05000000, 350000.00000000, TIMESTAMP '2026-08-01 10:00:00');

INSERT INTO TRANSACAO (idTransacao, idEmpresa, idAtivo, tipoDeTransacao, quantidade, valorUnitarioNoMomento, dataHora)
VALUES (2, 1, 2, 'COMPRA', 1.20000000, 12000.00000000, TIMESTAMP '2026-08-05 14:30:00');

INSERT INTO TRANSACAO (idTransacao, idEmpresa, idAtivo, tipoDeTransacao, quantidade, valorUnitarioNoMomento, dataHora)
VALUES (3, 2, 3, 'COMPRA', 10.00000000, 650.00000000, TIMESTAMP '2026-08-10 09:15:00');

INSERT INTO CARTEIRA (idCarteira, idEmpresa, idAtivo, quantidade, saldoTotalAtualizado)
VALUES (1, 1, 1, 0.05000000, 17500.00);

INSERT INTO CARTEIRA (idCarteira, idEmpresa, idAtivo, quantidade, saldoTotalAtualizado)
VALUES (2, 1, 2, 1.20000000, 14400.00);

INSERT INTO CARTEIRA (idCarteira, idEmpresa, idAtivo, quantidade, saldoTotalAtualizado)
VALUES (3, 2, 3, 10.00000000, 6500.00);

COMMIT;

UPDATE ATIVO_CRIPTO
SET precoAtual = 365000.00000000
WHERE simbolo = 'BTC';

UPDATE CARTEIRA
SET saldoTotalAtualizado = quantidade * 365000.00
WHERE idEmpresa = 1 AND idAtivo = 1;

UPDATE USUARIO
SET is2FAAtivo = 1
WHERE idUsuario = 2;

COMMIT;

DELETE FROM TRANSACAO
WHERE idTransacao = 3;

COMMIT;

SELECT idEmpresa, nomeEmpresa, cnpj, saldoFiduciario
FROM EMPRESA;

SELECT e.nomeEmpresa, a.nomeMoeda, a.simbolo, c.quantidade, c.saldoTotalAtualizado
FROM CARTEIRA c
         JOIN EMPRESA e      ON e.idEmpresa = c.idEmpresa
         JOIN ATIVO_CRIPTO a ON a.idAtivo   = c.idAtivo
ORDER BY e.nomeEmpresa, a.simbolo;

SELECT t.idTransacao, a.simbolo, t.tipoDeTransacao, t.quantidade,
       t.valorUnitarioNoMomento, t.dataHora
FROM TRANSACAO t
         JOIN ATIVO_CRIPTO a ON a.idAtivo = t.idAtivo
WHERE t.idEmpresa = 1
ORDER BY t.dataHora;

SELECT u.idUsuario, u.nome, u.email, v.status
FROM USUARIO u
         JOIN USUARIO_VIP v ON v.idUsuario = u.idUsuario;