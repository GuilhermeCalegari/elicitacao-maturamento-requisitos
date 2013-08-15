-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.0.38-Ubuntu_0ubuntu1-log


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema estoque
--

CREATE DATABASE IF NOT EXISTS estoque;
USE estoque;

--
-- Definition of table `estoque`.`categoria`
--

DROP TABLE IF EXISTS `estoque`.`categoria`;
CREATE TABLE  `estoque`.`categoria` (
  `idCategoria` int(11) NOT NULL default '0',
  `descCategoria` varchar(30) NOT NULL default '',
  `sitCategoria` int(11) NOT NULL default '0',
  PRIMARY KEY  (`idCategoria`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `estoque`.`categoria`
--

/*!40000 ALTER TABLE `categoria` DISABLE KEYS */;
LOCK TABLES `categoria` WRITE;
INSERT INTO `estoque`.`categoria` VALUES  (1,'Material de Limpeza',1),
 (2,'Material de Escritório',1),
 (3,'Matéria-Prima',2),
 (4,'Material de Embalagem',1),
 (5,'Material em Processo',1);
UNLOCK TABLES;
/*!40000 ALTER TABLE `categoria` ENABLE KEYS */;


--
-- Definition of table `estoque`.`produto`
--

DROP TABLE IF EXISTS `estoque`.`produto`;
CREATE TABLE  `estoque`.`produto` (
  `idProduto` int(11) NOT NULL default '0',
  `descProduto` varchar(50) NOT NULL default '',
  `idCategoria` int(11) NOT NULL default '0',
  `dataInclusao` date NOT NULL,
  `valor` decimal(10,2) NOT NULL,
  `observacao` varchar(100) NOT NULL,
  `sitProduto` int(11) NOT NULL default '0',
  `precoVenda` decimal(10,2) NOT NULL,
  PRIMARY KEY  (`idProduto`),
  KEY `fk_categoria` (`idCategoria`),
  CONSTRAINT `fk_categoria` FOREIGN KEY (`idCategoria`) REFERENCES `categoria` (`idCategoria`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `estoque`.`produto`
--

/*!40000 ALTER TABLE `produto` DISABLE KEYS */;
LOCK TABLES `produto` WRITE;
INSERT INTO `estoque`.`produto` VALUES  (1,'Agua Sanitaria',1,'2007-01-01','150.00','AJAX',1,'247.50'),
 (2,'Detergente',1,'2007-01-01','150.00','Limpol',1,'247.50'),
 (3,'Alcool',1,'2007-01-01','120.00','Alcool 90%',0,'132.00'),
 (4,'Grampeador',2,'2007-02-01','125.00','',2,'137.50'),
 (5,'Caixa individual',4,'2007-06-30','10.20','Caixa simples',0,'11.22'),
 (6,'Caixa Coletiva',4,'2007-07-01','15.33','Caixa coletiva que cabe 5 caixas individuais',0,'16.86'),
 (7,'Produto semi-elaborado',5,'2006-07-25','10.00','',0,'11.00'),
 (8,'Componente',5,'2007-06-23','70.00','',0,'77.00'),
 (9,'Tinta especial',3,'2007-05-24','1320.00','Tinta sintética',0,'1188.00'),
 (10,'Couro',3,'2007-02-25','125.00','Couro com recorte',0,'112.50');
UNLOCK TABLES;
/*!40000 ALTER TABLE `produto` ENABLE KEYS */;




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
