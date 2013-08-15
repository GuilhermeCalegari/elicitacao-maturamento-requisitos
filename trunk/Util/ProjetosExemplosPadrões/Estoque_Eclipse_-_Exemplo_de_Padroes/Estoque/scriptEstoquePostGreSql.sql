CREATE TABLE categoria (
    "idcategoria" numeric NOT NULL,
    "desccategoria" character varying(255) NOT NULL,
    "sitcategoria" numeric NOT NULL
);

ALTER TABLE ONLY categoria
    ADD CONSTRAINT categoria_pkey PRIMARY KEY ("idcategoria");


ALTER INDEX public.categoria_pkey OWNER TO postgres;


CREATE TABLE produto (
    "idproduto" numeric NOT NULL,
    "descproduto" character varying(50) NOT NULL,
    "idcategoria" numeric NOT NULL,
    "datainclusao" date NOT NULL,
    valor numeric(10,2) NOT NULL,
    observacao character varying(100) NOT NULL,
    "sitproduto" numeric NOT NULL,
    "precovenda" numeric(10,2) NOT NULL
);

ALTER TABLE ONLY produto
    ADD CONSTRAINT produto_pkey PRIMARY KEY ("idproduto");


ALTER INDEX public.produto_pkey OWNER TO postgres;

ALTER TABLE ONLY produto
    ADD CONSTRAINT "produto_idcategoria_fkey" FOREIGN KEY ("idcategoria") REFERENCES categoria("idcategoria") ON UPDATE RESTRICT ON DELETE RESTRICT;

INSERT INTO categoria VALUES  (1,'Material de Limpeza',1);
INSERT INTO categoria VALUES  (2,'Material de Escritório',1);
INSERT INTO categoria VALUES  (3,'Matéria-Prima',2);
INSERT INTO categoria VALUES  (4,'Material de Embalagem',1);
INSERT INTO categoria VALUES  (5,'Material em Processo',1);

INSERT INTO produto VALUES  (1,'Agua Sanitaria',1,'2007-01-01','150.00','AJAX',1,'247.50');
INSERT INTO produto VALUES   (2,'Detergente',1,'2007-01-01','150.00','Limpol',1,'247.50');
INSERT INTO produto VALUES   (3,'Alcool',1,'2007-01-01','120.00','Alcool 90%',0,'132.00');
INSERT INTO produto VALUES   (4,'Grampeador',2,'2007-02-01','125.00','',2,'137.50');
INSERT INTO produto VALUES   (5,'Caixa individual',4,'2007-06-30','10.20','Caixa simples',0,'11.22');
INSERT INTO produto VALUES   (6,'Caixa Coletiva',4,'2007-07-01','15.33','Caixa coletiva que cabe 5 caixas individuais',0,'16.86');
INSERT INTO produto VALUES   (7,'Produto semi-elaborado',5,'2006-07-25','10.00','',0,'11.00');
INSERT INTO produto VALUES   (8,'Componente',5,'2007-06-23','70.00','',0,'77.00');
INSERT INTO produto VALUES   (9,'Tinta especial',3,'2007-05-24','1320.00','Tinta sintética',0,'1188.00');
INSERT INTO produto VALUES   (10,'Couro',3,'2007-02-25','125.00','Couro com recorte',0,'112.50');
