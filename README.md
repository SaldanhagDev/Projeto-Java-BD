# Projeto-Java-BD
🏥 Sistema de Gestão Hospitalar – Java + MySQL
📌 Descrição do Projeto

Este projeto foi desenvolvido em Java com integração a um banco de dados MySQL. O objetivo é gerenciar informações de médicos, enfermeiros, pacientes, formações e endereços, seguindo um modelo orientado a objetos e refletindo essa estrutura no banco de dados relacional.

O sistema foi construído com base em um diagrama de classes UML, onde a classe abstrata Pessoa serve como base para Medico, Enfermeiro e Paciente. Cada entidade possui sua própria tabela no banco de dados e está relacionada conforme o modelo.

🧩 Estrutura do Projeto
✅ Classes Principais (Java)

Pessoa (abstrata)
• cpf (String)
• nome (String)

Medico
• matr (int)
• numConsultorio (int)
• paciente (String)
• formacao (int)

Enfermeiro
• matr (int)
• endereco (int)
• formacao (int)

Paciente
• cpf (String)
• nome (String)
• idade (int)
• sintomas (String)
• endereco (int)

Endereco
• cep (int)
• rua (String)

Formacao
• id (int)
• curso (String)
• instituicao (String)

Cada classe possui um respectivo DAO com operações CRUD utilizando JDBC e GerenteDeConexao para acesso ao MySQL.

🗂 Banco de Dados (MySQL)

As tabelas criadas refletem as entidades do sistema:

Medico

Enfermeiro

Paciente

Formacao

Endereco

As relações utilizam chaves estrangeiras conforme o diagrama, por exemplo:

Medico.formacao → Formacao.id

Enfermeiro.formacao → Formacao.id

Paciente.endereco → Endereco.cep

🔌 Conexão com o Banco

A conexão é feita via JDBC usando uma classe central, por exemplo:

GerenteDeConexao.getConnection()

Cada DAO utiliza essa conexão para executar:

INSERT

SELECT

UPDATE

DELETE

Também foram aplicados operadores como:
LIKE, IN, NOT BETWEEN, AND, NOT IN, conforme exigido na avaliação A2.

🛠️ Tecnologias Utilizadas

Java (NetBeans 8.2)

MySQL

MySQL Connector/J (JDBC)

UML + Workbench (modelagem e MER)

▶️ Objetivo Acadêmico

O projeto foi criado como parte de uma avaliação prática, aplicando:
✔ Programação orientada a objetos
✔ Padrão DAO
✔ Herança e abstração
✔ MySQL com persistência real de dados
✔ Modelagem UML e banco relacional integrado
