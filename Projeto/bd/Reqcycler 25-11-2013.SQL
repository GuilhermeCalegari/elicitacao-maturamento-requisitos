SET FOREIGN_KEY_CHECKS=0;



CREATE TABLE Agrega
(
	responsabilidadeFluxoID Integer,
	fluxoPacoteID Integer,
	KEY (responsabilidadeFluxoID),
	KEY (fluxoPacoteID)
) 
;


CREATE TABLE Alerta
(
	alertaID INTEGER NOT NULL,
	PRIMARY KEY (alertaID)
) 
;


CREATE TABLE ClassificacaoRequisito
(
	id INTEGER NOT NULL,
	classificacao VARCHAR(100),
	PRIMARY KEY (id)
) 
;


CREATE TABLE Comentario
(
	comentario VARCHAR(50),
	data Date,
	pacoteID Integer NOT NULL,
	comentarioID INTEGER NOT NULL,
	PRIMARY KEY (comentarioID),
	KEY (pacoteID)
) 
;


CREATE TABLE Convite
(
	conviteID INTEGER NOT NULL,
	PRIMARY KEY (conviteID)
) 
;


CREATE TABLE Detalhe
(
	detalheID INTEGER NOT NULL,
	PRIMARY KEY (detalheID)
) 
;


CREATE TABLE Elicitacao
(
	dataConclusao Date,
	dataCriacao Date,
	objetivo VARCHAR(50),
	projetoID Integer NOT NULL,
	elicitacaoID INTEGER NOT NULL,
	PRIMARY KEY (elicitacaoID),
	KEY (projetoID),
	KEY (elicitacaoID)
) 
;


CREATE TABLE elicitacao_requisito
(
	elicitacaoID Integer,
	requisitoID INTEGER,
	KEY (elicitacaoID),
	KEY (requisitoID)
) 
;


CREATE TABLE FluxoPacote
(
	nivelAtual INTEGER,
	totalNiveis INTEGER,
	fluxoPacoteID INTEGER NOT NULL,
	PRIMARY KEY (fluxoPacoteID)
) 
;


CREATE TABLE ItemVersionavel
(
	itemVersionavelID INTEGER NOT NULL,
	PRIMARY KEY (itemVersionavelID)
) 
;


CREATE TABLE Pacote
(
	dataCriacao Date,
	nome VARCHAR(50),
	fluxoPacoteID Integer NOT NULL,
	pacoteID INTEGER NOT NULL,
	PRIMARY KEY (pacoteID),
	KEY (fluxoPacoteID)
) 
;


CREATE TABLE Perfil
(
	Permissoes List<String>,
	tipo INTEGER,
	perfilID INTEGER NOT NULL,
	PRIMARY KEY (perfilID)
) 
;


CREATE TABLE Pergunta
(
	descricao VARCHAR(50),
	obrigatorio bool,
	pergunta VARCHAR(50),
	questionarioID Integer NOT NULL,
	respostaID Integer NOT NULL,
	perguntaID INTEGER NOT NULL,
	PRIMARY KEY (perguntaID),
	KEY (questionarioID),
	KEY (perguntaID)
) 
;


CREATE TABLE Projeto
(
	projetoID INTEGER NOT NULL,
	sponsorID INTEGER,
	dataFim DATETIME,
	dataInicio DATETIME,
	descricao VARCHAR(4096),
	escopo VARCHAR(4096),
	nome VARCHAR(300),
	objetivoProjeto VARCHAR(2048),
	PRIMARY KEY (projetoID),
	KEY (sponsorID)
) 
;


CREATE TABLE Questionario
(
	dataCriacao Date,
	questionarioFinalizado bool,
	elicitacaoID Integer NOT NULL,
	questionarioID INTEGER NOT NULL,
	PRIMARY KEY (questionarioID),
	KEY (elicitacaoID)
) 
;


CREATE TABLE Requisito
(
	requisitoID INTEGER NOT NULL,
	classificacaoID INTEGER,
	projetoID INTEGER NOT NULL,
	complexidade CHAR(1),
	descricao VARCHAR(4096),
	nome VARCHAR(200),
	tipo CHAR(1),
	versaoSistema DECIMAL(2,2),
	PRIMARY KEY (requisitoID),
	KEY (classificacaoID),
	KEY (requisitoID),
	KEY (projetoID),
	KEY (requisitoID)
) 
;


CREATE TABLE requisitopacote
(
	pacoteID Integer,
	requisitoID Integer,
	KEY (pacoteID),
	KEY (requisitoID)
) 
;


CREATE TABLE Responsabilidade
(
	tipo INTEGER,
	responsabilidadeID INTEGER NOT NULL,
	PRIMARY KEY (responsabilidadeID)
) 
;


CREATE TABLE ResponsabilidadeFluxo
(
	nivel INTEGER,
	responsabilidadeFluxoID INTEGER NOT NULL,
	PRIMARY KEY (responsabilidadeFluxoID),
	KEY (responsabilidadeFluxoID),
	KEY (responsabilidadeFluxoID)
) 
;


CREATE TABLE ResponsabilidadeProjeto
(
	papel INTEGER,
	responsabilidadeProjetoID INTEGER NOT NULL,
	PRIMARY KEY (responsabilidadeProjetoID),
	KEY (responsabilidadeProjetoID),
	KEY (responsabilidadeProjetoID)
) 
;


CREATE TABLE responsabilidadeprojeto_projeto
(
	projetoID Integer,
	responsabilidadeProjetoID Integer,
	KEY (projetoID),
	KEY (responsabilidadeProjetoID)
) 
;


CREATE TABLE Resposta
(
	dataResposta Date,
	resposta VARCHAR(50),
	respostaID INTEGER NOT NULL,
	PRIMARY KEY (respostaID)
) 
;


CREATE TABLE Usuario
(
	dataCriacao Date,
	email VARCHAR(50),
	nome VARCHAR(50),
	senha INTEGER,
	sobrenome VARCHAR(50),
	usuarioID INTEGER NOT NULL,
	entidadeID Integer NOT NULL,
	respostaID Integer NOT NULL,
	comentarioID INTEGER,
	requisitoID INTEGER,
	PRIMARY KEY (usuarioID),
	KEY (respostaID),
	KEY (comentarioID),
	KEY (usuarioID),
	KEY (requisitoID)
) 
;


CREATE TABLE usuario_responsabilidade_fluxo
(
	responsabilidadeFluxoID Integer,
	usuarioID Integer,
	KEY (responsabilidadeFluxoID),
	KEY (usuarioID)
) 
;


CREATE TABLE usuario_responsabilidade_projeto
(
	responsabilidadeProjetoID Integer,
	usuarioID Integer,
	KEY (responsabilidadeProjetoID),
	KEY (usuarioID)
) 
;


CREATE TABLE UsuarioProjeto
(
	UsuarioProjetoID INTEGER NOT NULL,
	projetoID INTEGER,
	usuarioID INTEGER,
	PRIMARY KEY (UsuarioProjetoID),
	KEY (projetoID),
	KEY (usuarioID)
) 
;



SET FOREIGN_KEY_CHECKS=1;


ALTER TABLE Agrega ADD CONSTRAINT ResponsabilidadeFluxo 
	FOREIGN KEY (responsabilidadeFluxoID) REFERENCES ResponsabilidadeFluxo (responsabilidadeFluxoID)
;

ALTER TABLE Agrega ADD CONSTRAINT FluxoPacote 
	FOREIGN KEY (fluxoPacoteID) REFERENCES FluxoPacote (fluxoPacoteID)
;

ALTER TABLE Comentario ADD CONSTRAINT Contem 
	FOREIGN KEY (pacoteID) REFERENCES Pacote (pacoteID)
;

ALTER TABLE Elicitacao ADD CONSTRAINT FK_Elicitacao_Projeto 
	FOREIGN KEY (projetoID) REFERENCES Projeto (projetoID)
;

ALTER TABLE Elicitacao ADD CONSTRAINT FK_Elicitacao_ItemVersionavel 
	FOREIGN KEY (elicitacaoID) REFERENCES ItemVersionavel (itemVersionavelID)
;

ALTER TABLE elicitacao_requisito ADD CONSTRAINT Elicitacao 
	FOREIGN KEY (elicitacaoID) REFERENCES Elicitacao (elicitacaoID)
;

ALTER TABLE elicitacao_requisito ADD CONSTRAINT Requisito 
	FOREIGN KEY (requisitoID) REFERENCES Requisito (requisitoID)
;

ALTER TABLE Pacote ADD CONSTRAINT Contem 
	FOREIGN KEY (fluxoPacoteID) REFERENCES FluxoPacote (fluxoPacoteID)
;

ALTER TABLE Pergunta ADD CONSTRAINT Contem 
	FOREIGN KEY (questionarioID) REFERENCES Questionario (questionarioID)
;

ALTER TABLE Pergunta ADD CONSTRAINT FK_Pergunta_ItemVersionavel 
	FOREIGN KEY (perguntaID) REFERENCES ItemVersionavel (itemVersionavelID)
;

ALTER TABLE Questionario ADD CONSTRAINT Gera 
	FOREIGN KEY (elicitacaoID) REFERENCES Elicitacao (elicitacaoID)
;

ALTER TABLE Requisito ADD CONSTRAINT FK_Requisito_ItemVersionavel 
	FOREIGN KEY (requisitoID) REFERENCES ItemVersionavel (itemVersionavelID)
;

ALTER TABLE Requisito ADD CONSTRAINT Contem 
	FOREIGN KEY (projetoID) REFERENCES Projeto (projetoID)
;

ALTER TABLE Requisito ADD CONSTRAINT FK_Requisito_ItemVersionavel 
	FOREIGN KEY (requisitoID) REFERENCES ItemVersionavel (itemVersionavelID)
;

ALTER TABLE requisitopacote ADD CONSTRAINT Pacote 
	FOREIGN KEY (pacoteID) REFERENCES Pacote (pacoteID)
;

ALTER TABLE requisitopacote ADD CONSTRAINT Requisito 
	FOREIGN KEY (requisitoID) REFERENCES Requisito (requisitoID)
;

ALTER TABLE ResponsabilidadeFluxo ADD CONSTRAINT FK_ResponsabilidadeFluxo_Responsabilidade 
	FOREIGN KEY (responsabilidadeFluxoID) REFERENCES Responsabilidade (responsabilidadeID)
;

ALTER TABLE ResponsabilidadeFluxo ADD CONSTRAINT FK_ResponsabilidadeFluxo_Responsabilidade 
	FOREIGN KEY (responsabilidadeFluxoID) REFERENCES Responsabilidade (responsabilidadeID)
;

ALTER TABLE ResponsabilidadeProjeto ADD CONSTRAINT FK_ResponsabilidadeProjeto_Responsabilidade 
	FOREIGN KEY (responsabilidadeProjetoID) REFERENCES Responsabilidade (responsabilidadeID)
;

ALTER TABLE ResponsabilidadeProjeto ADD CONSTRAINT FK_ResponsabilidadeProjeto_Responsabilidade 
	FOREIGN KEY (responsabilidadeProjetoID) REFERENCES Responsabilidade (responsabilidadeID)
;

ALTER TABLE responsabilidadeprojeto_projeto ADD CONSTRAINT Projeto 
	FOREIGN KEY (projetoID) REFERENCES Projeto (projetoID)
;

ALTER TABLE responsabilidadeprojeto_projeto ADD CONSTRAINT ResponsabilidadeProjeto 
	FOREIGN KEY (responsabilidadeProjetoID) REFERENCES ResponsabilidadeProjeto (responsabilidadeProjetoID)
;

ALTER TABLE Usuario ADD CONSTRAINT FK_Usuario_Resposta 
	FOREIGN KEY (respostaID) REFERENCES Resposta (respostaID)
;

ALTER TABLE Usuario ADD CONSTRAINT FK_Usuario_Comentario 
	FOREIGN KEY (comentarioID) REFERENCES Comentario (comentarioID)
;

ALTER TABLE Usuario ADD CONSTRAINT FK_Usuario_Perfil 
	FOREIGN KEY (usuarioID) REFERENCES Perfil (perfilID)
;

ALTER TABLE Usuario ADD CONSTRAINT FK_Usuario_Requisito 
	FOREIGN KEY (requisitoID) REFERENCES Requisito (requisitoID)
;

ALTER TABLE usuario_responsabilidade_fluxo ADD CONSTRAINT ResponsabilidadeFluxo 
	FOREIGN KEY (responsabilidadeFluxoID) REFERENCES ResponsabilidadeFluxo (responsabilidadeFluxoID)
;

ALTER TABLE usuario_responsabilidade_fluxo ADD CONSTRAINT Usuario 
	FOREIGN KEY (usuarioID) REFERENCES Usuario (usuarioID)
;

ALTER TABLE usuario_responsabilidade_projeto ADD CONSTRAINT ResponsabilidadeProjeto 
	FOREIGN KEY (responsabilidadeProjetoID) REFERENCES ResponsabilidadeProjeto (responsabilidadeProjetoID)
;

ALTER TABLE usuario_responsabilidade_projeto ADD CONSTRAINT Usuario 
	FOREIGN KEY (usuarioID) REFERENCES Usuario (usuarioID)
;

ALTER TABLE UsuarioProjeto ADD CONSTRAINT Projeto 
	FOREIGN KEY (projetoID) REFERENCES Projeto (projetoID)
;

ALTER TABLE UsuarioProjeto ADD CONSTRAINT Usuario 
	FOREIGN KEY (usuarioID) REFERENCES Usuario (usuarioID)
;
