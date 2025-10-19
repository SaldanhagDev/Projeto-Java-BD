-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Tempo de geração: 25/06/2025 às 14:51
-- Versão do servidor: 10.4.32-MariaDB
-- Versão do PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Banco de dados: `javaprojetoa2`
--

-- --------------------------------------------------------

--
-- Estrutura para tabela `endereco`
--

CREATE TABLE `endereco` (
  `Cep` int(11) NOT NULL,
  `Rua` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `endereco`
--

INSERT INTO `endereco` (`Cep`, `Rua`) VALUES
(101010, 'recanto'),
(202020, 'recanto'),
(303030, 'Areal'),
(404040, 'riacho fundo I'),
(505050, 'riacho fundo II'),
(606060, 'Aguas claras'),
(707070, 'asa sul');

-- --------------------------------------------------------

--
-- Estrutura para tabela `enfermeiro`
--

CREATE TABLE `enfermeiro` (
  `Matr` int(11) NOT NULL,
  `Cpf` varchar(11) DEFAULT NULL,
  `Nome` varchar(45) DEFAULT NULL,
  `Endereco` int(11) DEFAULT NULL,
  `Formacao` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `enfermeiro`
--

INSERT INTO `enfermeiro` (`Matr`, `Cpf`, `Nome`, `Endereco`, `Formacao`) VALUES
(1, '55555555555', 'Larissa', 404040, 2),
(3, '77777777777', 'Igor', 606060, 3);

-- --------------------------------------------------------

--
-- Estrutura para tabela `formacao`
--

CREATE TABLE `formacao` (
  `Id` int(11) NOT NULL,
  `Curso` varchar(45) DEFAULT NULL,
  `Instituicao` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `formacao`
--

INSERT INTO `formacao` (`Id`, `Curso`, `Instituicao`) VALUES
(1, 'enfermeiro', 'unieuro'),
(2, 'enfermagem', 'unieuro'),
(3, 'enfermagem', 'universidade catolica'),
(4, 'enfermagem', 'universidade de brasilia'),
(5, 'Medicina', 'universidade de brasilia'),
(6, 'Medicina', 'universidade de brasilia'),
(7, 'Medicina', 'universidade de brasilia');

-- --------------------------------------------------------

--
-- Estrutura para tabela `medico`
--

CREATE TABLE `medico` (
  `Matr` int(11) NOT NULL,
  `Cpf` varchar(11) DEFAULT NULL,
  `Nome` varchar(45) DEFAULT NULL,
  `NumConsultorio` int(11) DEFAULT NULL,
  `Paciente` varchar(11) DEFAULT NULL,
  `Formacao` int(11) DEFAULT NULL,
  `Endereco` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `medico`
--

INSERT INTO `medico` (`Matr`, `Cpf`, `Nome`, `NumConsultorio`, `Paciente`, `Formacao`, `Endereco`) VALUES
(1, '12345678910', 'felipe', 15, '11111111111', 1, 404040),
(2, '99999999999', 'Maria', 15, '44444444444', 1, 707070),
(5, '33333333333', 'Andre', 15, '22222222222', 5, 606060),
(6, '33333333333', 'Clara', 40, '33333333333', 6, 101010),
(7, '33333333333', 'samara', 99, '11111111111', 7, 101010);

-- --------------------------------------------------------

--
-- Estrutura para tabela `paciente`
--

CREATE TABLE `paciente` (
  `Cpf` varchar(11) NOT NULL,
  `Nome` varchar(45) DEFAULT NULL,
  `Idade` int(11) DEFAULT NULL,
  `Sintomas` varchar(45) DEFAULT NULL,
  `Endereco` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `paciente`
--

INSERT INTO `paciente` (`Cpf`, `Nome`, `Idade`, `Sintomas`, `Endereco`) VALUES
('11111111111', 'Danielle', 19, 'conjuntivite', 303030),
('22222222222', 'rafael', 18, 'conjuntivite', 101010),
('33333333333', 'Gabriel', 18, 'tosse seca', 202020),
('44444444444', 'Isac', 55, 'enxaquecca', 707070);

--
-- Índices para tabelas despejadas
--

--
-- Índices de tabela `endereco`
--
ALTER TABLE `endereco`
  ADD PRIMARY KEY (`Cep`);

--
-- Índices de tabela `enfermeiro`
--
ALTER TABLE `enfermeiro`
  ADD PRIMARY KEY (`Matr`),
  ADD KEY `Endereco` (`Endereco`),
  ADD KEY `Formacao` (`Formacao`);

--
-- Índices de tabela `formacao`
--
ALTER TABLE `formacao`
  ADD PRIMARY KEY (`Id`);

--
-- Índices de tabela `medico`
--
ALTER TABLE `medico`
  ADD PRIMARY KEY (`Matr`),
  ADD KEY `Paciente` (`Paciente`),
  ADD KEY `Formacao` (`Formacao`),
  ADD KEY `fk_medico_endereco` (`Endereco`);

--
-- Índices de tabela `paciente`
--
ALTER TABLE `paciente`
  ADD PRIMARY KEY (`Cpf`),
  ADD KEY `Endereco` (`Endereco`);

--
-- Restrições para tabelas despejadas
--

--
-- Restrições para tabelas `enfermeiro`
--
ALTER TABLE `enfermeiro`
  ADD CONSTRAINT `enfermeiro_ibfk_1` FOREIGN KEY (`Endereco`) REFERENCES `endereco` (`Cep`),
  ADD CONSTRAINT `enfermeiro_ibfk_2` FOREIGN KEY (`Formacao`) REFERENCES `formacao` (`Id`);

--
-- Restrições para tabelas `medico`
--
ALTER TABLE `medico`
  ADD CONSTRAINT `fk_medico_endereco` FOREIGN KEY (`Endereco`) REFERENCES `endereco` (`Cep`),
  ADD CONSTRAINT `medico_ibfk_1` FOREIGN KEY (`Paciente`) REFERENCES `paciente` (`Cpf`),
  ADD CONSTRAINT `medico_ibfk_2` FOREIGN KEY (`Formacao`) REFERENCES `formacao` (`Id`);

--
-- Restrições para tabelas `paciente`
--
ALTER TABLE `paciente`
  ADD CONSTRAINT `paciente_ibfk_1` FOREIGN KEY (`Endereco`) REFERENCES `endereco` (`Cep`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
