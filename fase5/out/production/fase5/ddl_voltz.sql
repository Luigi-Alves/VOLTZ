DROP TABLE CARTEIRA CASCADE CONSTRAINTS;
DROP TABLE TRANSACAO CASCADE CONSTRAINTS;
DROP TABLE DASHBOARD CASCADE CONSTRAINTS;
DROP TABLE ATIVO_CRIPTO CASCADE CONSTRAINTS;
DROP TABLE EMPRESA CASCADE CONSTRAINTS;
DROP TABLE USUARIO_VIP CASCADE CONSTRAINTS;
DROP TABLE USUARIO CASCADE CONSTRAINTS;

CREATE TABLE USUARIO (
                         idUsuario          NUMBER(19)     NOT NULL,
                         nome               VARCHAR2(150)  NOT NULL,
                         email              VARCHAR2(200)  NOT NULL,
                         senhaCriptografada VARCHAR2(255)  NOT NULL,
                         is2FAAtivo         NUMBER(1)      DEFAULT 0 NOT NULL,
                         CONSTRAINT pk_usuario PRIMARY KEY (idUsuario),
                         CONSTRAINT uq_usuario_email UNIQUE (email),
                         CONSTRAINT ck_usuario_2fa CHECK (is2FAAtivo IN (0,1))
);

CREATE TABLE USUARIO_VIP (
                             idUsuario NUMBER(19) NOT NULL,
                             status    NUMBER(1)  DEFAULT 1 NOT NULL,
                             CONSTRAINT pk_usuario_vip PRIMARY KEY (idUsuario),
                             CONSTRAINT fk_usuariovip_usuario FOREIGN KEY (idUsuario)
                                 REFERENCES USUARIO(idUsuario),
                             CONSTRAINT ck_usuariovip_status CHECK (status IN (0,1))
);

CREATE TABLE EMPRESA (
                         idEmpresa       NUMBER(19)     NOT NULL,
                         nomeEmpresa     VARCHAR2(150)  NOT NULL,
                         razaoSocial     VARCHAR2(200)  NOT NULL,
                         cnpj            VARCHAR2(18)   NOT NULL,
                         saldoFiduciario NUMBER(15,2)   DEFAULT 0 NOT NULL,
                         idUsuario       NUMBER(19)     NOT NULL,
                         CONSTRAINT pk_empresa PRIMARY KEY (idEmpresa),
                         CONSTRAINT uq_empresa_cnpj UNIQUE (cnpj),
                         CONSTRAINT fk_empresa_usuario FOREIGN KEY (idUsuario)
                             REFERENCES USUARIO(idUsuario)
);

CREATE TABLE ATIVO_CRIPTO (
                              idAtivo    NUMBER(19)    NOT NULL,
                              nomeMoeda  VARCHAR2(100) NOT NULL,
                              simbolo    VARCHAR2(10)  NOT NULL,
                              precoAtual NUMBER(18,8)  NOT NULL,
                              CONSTRAINT pk_ativo_cripto PRIMARY KEY (idAtivo),
                              CONSTRAINT uq_ativo_simbolo UNIQUE (simbolo)
);

CREATE TABLE DASHBOARD (
                           idDashboard        NUMBER(19)   NOT NULL,
                           idUsuario          NUMBER(19)   NOT NULL,
                           saldoTotalGeral    NUMBER(15,2) DEFAULT 0 NOT NULL,
                           lucroPrejuizoTotal NUMBER(15,2) DEFAULT 0 NOT NULL,
                           CONSTRAINT pk_dashboard PRIMARY KEY (idDashboard),
                           CONSTRAINT uq_dashboard_usuario UNIQUE (idUsuario),
                           CONSTRAINT fk_dashboard_usuario FOREIGN KEY (idUsuario)
                               REFERENCES USUARIO(idUsuario)
);

CREATE TABLE TRANSACAO (
                           idTransacao            NUMBER(19)   NOT NULL,
                           idEmpresa              NUMBER(19)   NOT NULL,
                           idAtivo                NUMBER(19)   NOT NULL,
                           tipoDeTransacao        VARCHAR2(10) NOT NULL,
                           quantidade             NUMBER(18,8) NOT NULL,
                           valorUnitarioNoMomento NUMBER(18,8) NOT NULL,
                           dataHora               TIMESTAMP    DEFAULT SYSTIMESTAMP NOT NULL,
                           CONSTRAINT pk_transacao PRIMARY KEY (idTransacao),
                           CONSTRAINT fk_transacao_empresa FOREIGN KEY (idEmpresa)
                               REFERENCES EMPRESA(idEmpresa),
                           CONSTRAINT fk_transacao_ativo FOREIGN KEY (idAtivo)
                               REFERENCES ATIVO_CRIPTO(idAtivo),
                           CONSTRAINT ck_transacao_tipo CHECK (tipoDeTransacao IN ('COMPRA','VENDA'))
);

CREATE TABLE CARTEIRA (
                          idCarteira           NUMBER(19)   NOT NULL,
                          idEmpresa            NUMBER(19)   NOT NULL,
                          idAtivo              NUMBER(19)   NOT NULL,
                          quantidade           NUMBER(18,8) DEFAULT 0 NOT NULL,
                          saldoTotalAtualizado NUMBER(15,2) DEFAULT 0 NOT NULL,
                          CONSTRAINT pk_carteira PRIMARY KEY (idCarteira),
                          CONSTRAINT fk_carteira_empresa FOREIGN KEY (idEmpresa)
                              REFERENCES EMPRESA(idEmpresa),
                          CONSTRAINT fk_carteira_ativo FOREIGN KEY (idAtivo)
                              REFERENCES ATIVO_CRIPTO(idAtivo)
);

ALTER TABLE CARTEIRA
    ADD CONSTRAINT uq_carteira_empresa_ativo UNIQUE (idEmpresa, idAtivo);

ALTER TABLE ATIVO_CRIPTO
    MODIFY (nomeMoeda VARCHAR2(120));