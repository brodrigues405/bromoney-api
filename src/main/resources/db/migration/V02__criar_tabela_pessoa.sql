CREATE TABLE pessoa(
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(50) NOT NULL,
    logradouro VARCHAR(50) NOT NULL,
    numero VARCHAR(50) NOT NULL,
    complemento VARCHAR(50) NULL,
    bairro VARCHAR(50) NOT NULL,
    cep VARCHAR(20) NOT NULL,
    cidade VARCHAR(50) NOT NULL,
    estado VARCHAR(50) NOT NULL,
    ativo BOOLEAN NOT NULL
)ENGINE=innoDB DEFAULT CHARSET=utf8;

INSERT INTO pessoa(nome, logradouro, numero, complemento, bairro, cep, cidade, estado, ativo)
     VALUES('Andre Rodrigues','QS 414 conjunto E', '102', 'BL B', 'Samambaia Norte','72320585','Samambaia', 'DF',true);

INSERT INTO pessoa(nome, logradouro, numero, complemento, bairro, cep, cidade, estado, ativo)
     VALUES('Ludiana Soares','QS 414 conjunto E', '102', 'BL B', 'Samambaia Norte','72320585','Samambaia', 'DF',true);

INSERT INTO pessoa(nome, logradouro, numero, complemento, bairro, cep, cidade, estado, ativo)
     VALUES('Ádrian Soares Brito Rodrigues','QS 414 conjunto E', '102', 'BL B', 'Samambaia Norte','72320585','Samambaia', 'DF',true);

INSERT INTO pessoa(nome, logradouro, numero, complemento, bairro, cep, cidade, estado, ativo)
     VALUES('Francisca Brito Rodrigues','QS 414 conjunto E', '102', 'BL B', 'Samambaia Norte','72320585','Samambaia', 'DF',true);

INSERT INTO pessoa(nome, logradouro, numero, complemento, bairro, cep, cidade, estado, ativo)
     VALUES('Maria Eduarda Brito','QS 2 Lote 5', 'Terreo', 'Casa 06', 'Taguatinga','75000000','Taguatinga Sul', 'DF',true);   
