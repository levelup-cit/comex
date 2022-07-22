# Comex

## Informações gerais dos pedidos em _pedidos.csv_
- Campos: **CATEGORIA, PRODUTO, PREÇO, QUANTIDADE, DATA, CLIENTE**


### RELATÓRIO DE VALORES TOTAIS
- TOTAL DE PEDIDOS REALIZADOS: 16
- TOTAL DE PRODUTOS VENDIDOS: 35
- TOTAL DE CATEGORIAS: 5
- MONTANTE DE VENDAS: R$ 178.374,49
- PEDIDO MAIS BARATO: R$ 95,17 (Clean Code)
- PEDIDO MAIS CARO: R$ 55.056,00 (iPhone 13 Pro)


### DDD

#### 1 - Quais padrões táticos do DDD cada classe implementa?
As classes possuem linguagem ubíqua, com nomeclaturas claras e oróximas ao mundo real. O código também foi organizado/dividido em 3 pacotes: aplicacao, dominio e infra, facilitando manutenções futuras.

#### 2 - Quais os agregados da aplicação, qual sua raiz e que classes os compõem?
A classe Usuario é uma agregada, tendo como dependente a classe Perfil.

#### 3 - Quais contextos delimitados existem?
Loja e estoque.


### LIDANDO COM PAGAMENTOS

#### 1 - Você criará um serviço separado ou fará no seu projeto atual?
Criarei um serviço separado para ter melhor organização e desempenho.

#### 2 - O Banco de Dados será separado ou será o mesmo do seu projeto atual?
O banco de dados será separado, pois o fluxo de entrada e saída será diferente e assim será possível tornar esse serviço independente.

#### 3 - Você precisará de um API Gateway? Se sim, quais as responsabilidades dessa peça da sua arquitetura?
Sim, utilizarei um API Gateway. Ele realizará um controle de de acesso entre os serviços e dessa forma aumentará a segurança da aplicação.

#### 4 - O sistema de notas fiscais será um projeto separado do de pagamentos ou os times serão unidos?
O sistema de notas fiscais será um projeto separado do de pagamento, pois são coisas diferentes e devem ser independentes.


### Planeje e faça um diagrama da integração entre os serviços:

    Loja (o comex que você vem trabalhando)
    Pagamentos
    Notas fiscais

* Quando um pedido for feito na loja, deve ser criado um pagamento e gerada a nota fiscal.
* Pense em integrações síncronas e assíncronas.
* Para as integrações síncronas, pense em chamadas HTTP entre os serviços.
* Para as integrações assícronas, pense em tópicos, partições, chaves e consumer groups, usando JSON como formato de mensagens no Kafka.

#### Diagramas feitos de acordo com as sugestões apresentadas no plantão do dia 20/07/2022:

<img align="center" alt="Diagrama-servicos" height="1000" width="900" src="src/main/resources/images/Diagrama sem nome.jpg">