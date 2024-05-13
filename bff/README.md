# Serviço de BFF

Este é um serviço baseado em Java para gerenciar o BFF - back end for front end.

## Estrutura do Projeto

O projeto é estruturado em vários pacotes:
- `com.vinhonotas.bff.domain`: Contém as regras de negócio.
- `com.vinhonotas.bff.application`: Contém classes de serviços para as regras de negócio.
- `com.vinhonotas.bff.interfaces`: Contém classes de controle para manipulação de solicitações HTTP.
- `com.vinhonotas.bff.infrastructure`: Contém classes de repositório para acesso a dados.-
- `com.vinhonotas.bff.configuration`: Contém classes de configuração.
- `com.vinhonotas.bff.utils`: Contém classes utilitárias.

## Configuração

Para configurar o projeto, você precisa ter o Java e o Maven instalados. Em seguida, você pode clonar o repositório e executar `mvn install` para instalar as dependências necessárias.

## Uso

Você pode iniciar o aplicativo executando `mvn spring-boot:run`. O serviço estará disponível em `http://localhost:8084/swagger-ui/index.html`.

## Endpoints

### Usuários: Operações relacionadas a usuários

GET
- `/api/v1/users/{id}`: Retorna um usuário pelo seu id

PUT
- `/api/v1/users/{id}`: Atualiza um usuário

DELETE
- `/api/v1/users/{id}`: Deleta um usuário

GET
- `/api/v1/users`: Retorna todos os usuários

POST
- `/api/v1/users`: Cria um usuário

POST
- `/api/v1/users/login`: Realiza o login de um usuário

GET
- `/api/v1/users/name/{name}`: Retorna um usuário pelo seu nome

DELETE
- `/api/v1/users/deleteAll`: Deleta uma lista de usuários

### Países: Operações relacionadas a países

GET
- `/api/v1/countries`: Retorna todos os países

GET
- `/api/v1/countries/{id}`: Retorna um país pelo seu id

GET
- `/api/v1/countries/name/{name}`: Retorna um país pelo seu nome

### Pessoas: Operações relacionadas a pessoas

GET
- `/api/v1/persons/{id}`: Retorna uma pessoa pelo seu id

PUT
- `/api/v1/persons/{id}`: Atualiza uma pessoa

DELETE
- `/api/v1/persons/{id}`: Deleta uma pessoa

GET
- `/api/v1/persons`: Retorna todas as pessoas

POST
- `/api/v1/persons`: Cria uma pessoa

GET
- `/api/v1/persons/name/{name}`: Retorna uma pessoa pelo seu nome

DELETE
- `/api/v1/persons/deleteAll`: Deleta uma lista de pessoas

### Endereços: Operações relacionadas a endereços

GET
- `/api/v1/address/{id}`: Retorna um endereço pelo seu id

PUT
- `/api/v1/address/{id}`: Atualiza um endereço

DELETE
- `/api/v1/address/{id}`: Deleta um endereço

GET
- `/api/v1/address`: Retorna todos os endereços
  POST
- `/api/v1/address`: Cria um endereço

DELETE
- `/api/v1/address/deleteAll`: Deleta uma lista de endereços

### Estados: Operações relacionadas a estados

GET
- `/api/v1/states`: Retorna todos os estados

GET
- `/api/v1/states/{id}`: Retorna um estado pelo seu id

GET
- `/api/v1/states/uf/{uf}`: Retorna um estado pela sua UF

GET
- `/api/v1/states/name/{name}`: Retorna um estado pelo seu nome

### Point Scales: Operações relacionadas a avaliação de vinhos

GET
- `/api/v1/point-scales/{id}`: Retorna uma avaliação de vinho pelo id

PUT
- `/api/v1/point-scales/{id}`: Atualiza uma avaliação de vinho pelo id

DELETE
- `/api/v1/point-scales/{id}`: Deleta uma avaliação de vinho pelo id

GET
- `/api/v1/point-scales`: Retorna todas as avaliações de vinho

POST
- `/api/v1/point-scales`: Cria uma avaliação de vinho

DELETE
- `/api/v1/point-scales/deleteAll`: Deleta uma lista de avaliação de vinho

### Vinhos: Operações relacionadas a vinhos

GET
- `/api/v1/wines/{id}`: Retorna um vinho pelo id

PUT
- `/api/v1/wines/{id}`: Atualiza um vinho pelo id

DELETE
- `/api/v1/wines/{id}`: Deleta um vinho pelo id

GET
- `/api/v1/wines`: Retorna todos os vinhos

POST
- `/api/v1/wines`: Cria um vinho

DELETE
- `/api/v1/wines/deleteAll`: Deleta uma lista de vinhos

### Tasting Card: Operações relacionadas a ficha de degustação do vinho

GET
- `/api/v1/tasting-card/{id}`: Retorna uma ficha de degustação cadastrada pelo id

PUT
- `/api/v1/tasting-card/{id}`: Atualiza uma ficha de degustação cadastrada pelo id

DELETE
- `/api/v1/tasting-card/{id}`: Deleta uma ficha de degustação cadastrada pelo id

GET
- `/api/v1/tasting-card`: Retorna uma lista com todas as fichas de degustação cadastradas

POST
- `/api/v1/tasting-card`: Cadastrar ficha de degustação

DELETE
- `/api/v1/tasting-card/deleteAll`: Deleta uma lista de fichas de degustação

### Pairing: Operações relacionadas a harmonização de vinhos

GET
- `/api/v1/pairing/pairings`: Retorna a harmonização do vinho

GET
- `/api/v1/pairing/menu`: Retorna a harmonização do vinho com o menu

GET
- `/api/v1/pairing/information`: Retorna informações sobre o vinho

## Licença

Este projeto está licenciado sob a Licença MIT - veja o arquivo `LICENSE.md` para detalhes