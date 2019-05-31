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

CREATE TABLE venda(
	id SERIAL PRIMARY KEY,
	id_cliente INT,
	total NUMERIC(10,2),
	FOREIGN KEY (id_cliente) REFERENCES cliente(id) ON DELETE CASCADE
);

CREATE TABLE itemVenda(
	id SERIAL PRIMARY KEY,
	id_venda INT,
	id_produto INT,
	quant INT,
	subTotal NUMERIC(10,2),
	FOREIGN KEY (id_venda) REFERENCES venda(id) ON DELETE CASCADE,
	FOREIGN KEY (id_produto) REFERENCES produto(id) ON DELETE CASCADE
);