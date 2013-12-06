CREATE TABLE Area ( 
	id int NOT NULL,
	valor varchar(50)
)
;

CREATE TABLE ClassificacaoRequisito ( 
	id int identity(1,1)  NOT NULL,
	valor varchar(50)
)
;

CREATE TABLE Comentario ( 
	id int NOT NULL,
	comentario varchar(512)
)
;

CREATE TABLE Pacote ( 
	id int identity(1,1)  NOT NULL,
	projetoId int,
	numero int,
	status char(1)
)
;

CREATE TABLE PapelUsuario ( 
	id int identity(1,1)  NOT NULL,
	valor varchar(50)
)
;

CREATE TABLE Projeto ( 
	id int identity(1,1)  NOT NULL,
	responsavelId int,
	areaResponsavelId int,
	focalProjetoId int,
	nome varchar(100),
	dataInicio datetime,
	dataFim datetime,
	logotipo image,
	objetivo varchar(1048),
	escopo varchar(1048),
	descricao varchar(2096)
)
;

CREATE TABLE ProjetoRequisito ( 
	id int identity(1,1)  NOT NULL,
	projetoId int,
	requisitoId int,
	pacoteId int
)
;

CREATE TABLE ProjetoUsuario ( 
	id int identity(1,1)  NOT NULL,
	usuarioId int,
	projetoId int,
	papelUsuarioId int,
	ordemFluxoAprovacao int
)
;

CREATE TABLE Requisito ( 
	id int identity(1,1)  NOT NULL,
	projetoId int,
	classificacaoRequisitoId int,
	nome varchar(50),
	tipoRequisito char(1),
	versaoSistema decimal(8,3),
	solicitante varchar(50),
	complexidade char(1),
	descricao varchar(4096)
)
;

CREATE TABLE TipoUsuario ( 
	id int identity(1,1)  NOT NULL,
	valor varchar(50)
)
;

CREATE TABLE Usuario ( 
	id int identity(1,1)  NOT NULL,
	tipoUsuarioID int,
	nome varchar(255),
	sobrenome varchar(255),
	email varchar(80),
	senha varchar(16),
	empresa varchar(50)
)
;


ALTER TABLE Area ADD CONSTRAINT PK_Area 
	PRIMARY KEY CLUSTERED (id)
;

ALTER TABLE ClassificacaoRequisito ADD CONSTRAINT PK_ClassificacaoRequisito 
	PRIMARY KEY CLUSTERED (id)
;

ALTER TABLE Comentario ADD CONSTRAINT PK_Comentario 
	PRIMARY KEY CLUSTERED (id)
;

ALTER TABLE Pacote ADD CONSTRAINT PK_Pacote 
	PRIMARY KEY CLUSTERED (id)
;

ALTER TABLE PapelUsuario ADD CONSTRAINT PK_PapelUsuario 
	PRIMARY KEY CLUSTERED (id)
;

ALTER TABLE Projeto ADD CONSTRAINT PK_Projeto 
	PRIMARY KEY CLUSTERED (id)
;

ALTER TABLE ProjetoRequisito ADD CONSTRAINT PK_projetoRequisitoProjetoRequisito 
	PRIMARY KEY CLUSTERED (id)
;

ALTER TABLE ProjetoUsuario ADD CONSTRAINT PK_ProjetoUsuario 
	PRIMARY KEY CLUSTERED (id)
;

ALTER TABLE Requisito ADD CONSTRAINT PK_Requisito 
	PRIMARY KEY CLUSTERED (id)
;

ALTER TABLE TipoUsuario ADD CONSTRAINT PK_TipoUsuario 
	PRIMARY KEY CLUSTERED (id)
;

ALTER TABLE Usuario ADD CONSTRAINT PK_Usuario 
	PRIMARY KEY CLUSTERED (id)
;



ALTER TABLE Pacote ADD CONSTRAINT FK_projetoId 
	FOREIGN KEY (projetoId) REFERENCES Projeto (id)
;

ALTER TABLE Projeto ADD CONSTRAINT FK_areaResponsavelId 
	FOREIGN KEY (areaResponsavelId) REFERENCES Area (id)
;

ALTER TABLE ProjetoRequisito ADD CONSTRAINT FK_requisitoId 
	FOREIGN KEY (requisitoId) REFERENCES Requisito (id)
;

ALTER TABLE ProjetoUsuario ADD CONSTRAINT FK_papelUsuarioId 
	FOREIGN KEY (papelUsuarioId) REFERENCES PapelUsuario (id)
;

ALTER TABLE Requisito ADD CONSTRAINT FK_classificacaoRequisitoId 
	FOREIGN KEY (classificacaoRequisitoId) REFERENCES ClassificacaoRequisito (id)
;
