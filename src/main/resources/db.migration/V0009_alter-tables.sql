alter table usuarios
       add constraint UK_suh64qcx0h83ynm45sm2r2lk7 unique (cliente_id);

alter table itens_pedido
       add constraint FK42mycompce3b7yt3l6ukdwsxy
       foreign key (pedido_id)
       references pedidos (id);

alter table itens_pedido
       add constraint FKxytdlekpdaobqphujy9bmuhl
       foreign key (produto_id)
       references produtos (id);

alter table pedidos
       add constraint FKg7202lk0hwxn04bmdl2thth5b
       foreign key (cliente_id)
       references clientes (id);

alter table produtos
       add constraint FK8rqw0ljwdaom34jr2t46bjtrn
       foreign key (categoria_id)
       references categorias (id);

alter table usuarios
       add constraint FKdx76w4skuwj8wldmr7ebfyegq
       foreign key (cliente_id)
       references clientes (id);

alter table usuarios_perfis
       add constraint FKebaxerhtjtge268fwjphj1kpt
       foreign key (perfis_id)
       references perfil (id);

alter table usuarios_perfis
       add constraint FKotpfkn8c9nmhqqy4pb8hkgp18
       foreign key (usuario_id)
       references usuarios (id);