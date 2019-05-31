CREATE TABLE cliente(
	id SERIAL PRIMARY KEY,
    nome varchar (50), 
    cpf varchar (15)
);

CREATE TABLE produto(
	id SERIAL PRIMARY KEY,
    descricao varchar (100), 
    valor NUMERIC (10,2)
);