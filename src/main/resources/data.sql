INSERT INTO categorias(nome, status) VALUES ('INFORMÁTICA', 'ATIVA');

INSERT INTO categorias(nome, status) VALUES ('FILMES', 'ATIVA');

INSERT INTO produtos(nome, descricao, preco_unitario, quantidade_estoque, categoria_id ) VALUES ("Mouse", "Mouse", 30.50, 15, 34);

INSERT INTO produtos(nome, descricao, preco_unitario, quantidade_estoque, categoria_id ) VALUES ("Moana", "BlueRay", 25, 08, 33);


INSERT INTO clientes(cpf, bairro, cidade, complemento, estado, numero, rua, nome, telefone)
VALUES (154637485766, "Janta Genebra", "Campinas", "H22", "SP", 366, "Rua da esquina", "Gabriel", "199283747474");


INSERT INTO pedidos(data, desconto, tipo_desconto, cliente_id) VALUES ("2022/05/30", "0.00", "NENHUM", 17);
INSERT INTO pedidos(data, desconto, tipo_desconto, cliente_id) VALUES ("2022/05/30", "0.00", "NENHUM", 17);
INSERT INTO pedidos(data, desconto, tipo_desconto, cliente_id) VALUES ("2022/05/30", "0.00", "NENHUM", 17);


INSERT INTO itens_pedido(desconto, preco_unitario, quantidade, tipo_desconto, pedido_id, produto_id)
VALUES (0.00, 30.5, 7, "NENHUM", 39, 33);

INSERT INTO itens_pedido(desconto, preco_unitario, quantidade, tipo_desconto, pedido_id, produto_id)
VALUES (0.00, 30.5, 3, "NENHUM", 40, 33);

INSERT INTO itens_pedido(desconto, preco_unitario, quantidade, tipo_desconto, pedido_id, produto_id)
VALUES (0.00, 25, 3, "NENHUM", 41, 34);