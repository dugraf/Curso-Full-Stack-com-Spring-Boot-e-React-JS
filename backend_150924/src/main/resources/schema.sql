CREATE DATABASE IF NOT EXISTS minhasfinancas;
USE minhasfinancas;

CREATE TABLE usuario
(
  id BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
  nome VARCHAR(150),
  email VARCHAR(100),
  senha VARCHAR(20),
  data_cadastro DATE
);

CREATE TABLE lancamento
(
  id BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
  descricao VARCHAR(100) NOT NULL,
  mes INT NOT NULL,
  ano INT NOT NULL,
  valor DECIMAL(16,2),
  tipo VARCHAR(20) NOT NULL,
  status VARCHAR(20) NOT NULL,
  id_usuario BIGINT,
  data_cadastro DATE,
  FOREIGN KEY (id_usuario) REFERENCES usuario (id),
  CHECK(tipo IN ('RECEITA', 'DESPESA')),
  CHECK(status IN ('PENDENTE', 'CANCELADO', 'EFETIVADO'))
);