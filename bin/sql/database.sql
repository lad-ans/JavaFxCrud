-- Criando o banco de daods
create database [DmiInf2AnoTech]
go

use [DmiInf2AnoTech]
go

-- Criando a tabela [usuario]
create table usuario(
    id int not null identity,
    nome varchar(256) not null,
    email varchar(50) not null,
    nome_usuario varchar(32) not null,
    biografia text not null,
    senha varchar(256) not null,
-- 
    profissao varchar(256),
    telefone varchar(12),
    idade int,

    constraint [pk_usuario] primary key ([id]),
)
go
