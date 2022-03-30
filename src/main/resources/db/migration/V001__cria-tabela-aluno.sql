create table aluno (
	id bigint not null auto_increment,
    nome varchar(60) not null,
    matricula varchar(20) not null,
    serie_ano varchar(20) not null,
    
    primary key (id)
);