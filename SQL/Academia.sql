CREATE DATABASE academia
GO
USE academia

CREATE TABLE aluno(
cpf			CHAR(11)		NOT NULL,
turma_id	INT             NOT NULL,
nome		VARCHAR(100)	NOT NULL,
dt_nasc		DATE			NOT NULL,
cod			INT				NOT NULL,
dt_matric	DATE			NOT NULL,
altura		INT				NULL, 
peso		INT				NULL, 
PRIMARY KEY (cpf),
FOREIGN KEY (turma_id) REFERENCES turma(id)
)
GO
CREATE TABLE endereco(
cpf_aluno	CHAR(11)		NOT NULL,
rua			VARCHAR(100) NOT NULL,
numero		INT NOT NULL,
bairro		VARCHAR(100) NOT NULL, 
cidade		VARCHAR(100) NOT NULL, 
uf			CHAR(2) NOT NULL,
FOREIGN KEY (cpf_aluno) REFERENCES aluno(cpf)
)
GO
CREATE TABLE atividade(
nome			VARCHAR(120)	NOT NULL,
turma_id		INT             NOT NULL,
id				INT				NOT NULL,
FOREIGN KEY (turma_id) REFERENCES turma(id)
)
GO
CREATE TABLE instrutor(
id			INT				NOT NULL,
nome		VARCHAR(100)	NOT NULL,
dt_nasc		DATE			NOT NULL, 
formacao	VARCHAR(100)	NOT NULL,
PRIMARY KEY (id)
)
GO
CREATE TABLE turma(
id				INT				NOT NULL, 
instrutor_id	INT             NOT NULL,
horario			TIME			NULL,
duracao			TIME			NULL,
PRIMARY KEY (id),
FOREIGN KEY (instrutor_id) REFERENCES instrutor(id)
)
GO
CREATE TABLE telefone(
instrutor_id	INT						NOT NULL,
ddd				INT						NOT NULL,
numero			INT						NOT NULL,
tipo			VARCHAR(8)				NOT NULL,
FOREIGN KEY (instrutor_id) REFERENCES instrutor(id)
)

