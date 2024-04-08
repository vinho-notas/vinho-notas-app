# Serviço de Cadastro

Este é um serviço baseado em Java para gerenciar o cadastro de usuários, pessoas, endereços, estados, países e cidades. Ele usa PostgreSQL para armazenamento de dados e Maven para gerenciamento de projetos.

## Estrutura do Projeto

O projeto é estruturado em vários pacotes:
- `com.vinhonotas.cadastro.domain`: Contém as regras de negócio.
- `com.vinhonotas.cadastro.application`: Contém classes de serviços para as regras de negócio.
- `com.vinhonotas.cadastro.interfaces`: Contém classes de controle para manipulação de solicitações HTTP.
- `com.vinhonotas.cadastro.infrastructure`: Contém classes de repositório para acesso a dados.- 
- `com.vinhonotas.cadastro.configuration`: Contém classes de configuração.
- `com.vinhonotas.cadastro.utils`: Contém classes utilitárias.

## Configuração

Para configurar o projeto, você precisa ter o Java e o Maven instalados. Em seguida, você pode clonar o repositório e executar `mvn install` para instalar as dependências necessárias.

## Uso

Você pode iniciar o aplicativo executando `mvn spring-boot:run`. O serviço estará disponível em `http://localhost:8080/swagger-ui/index.html`.

## Endpoints

### Países:  Operações relacionadas a países

GET
- `/api/v1/countries/{id}`: Retorna um país pelo seu id.

PUT
- `/api/v1/countries/{id}`: Atualiza um país.

DELETE
- `/api/v1/countries/{id}`: Deleta um país.

GET
- `/api/v1/countries`: Retorna todos os países.

POST
- `/api/v1/countries`: Cria um país.

GET
- `/api/v1/countries/name/{name}`: Retorna um país pelo seu nome.

GET
- `/api/v1/countries/continent/{continent}`: Retorna uma lista de países pelo seu continente.

### Estados: Operações relacionadas a estados

GET
- `/api/v1/states/{id}`: Retorna um estado pelo seu id.

PUT
- `/api/v1/states/{id}`: Atualiza um estado.

DELETE
- `/api/v1/states/{id}`: Deleta um estado.

GET
- `/api/v1/states`: Retorna todos os estados.

POST
- `/api/v1/states`: Cria um estado.

GET
- `/api/v1/states/uf/{uf}`: Retorna um estado pela sua UF.

GET
- `/api/v1/states/name/{name}`: Retorna um estado pelo seu nome

### Endereços: Operações relacionadas a endereços

GET
- `/api/v1/address/{id}`: Retorna um endereço pelo seu id.

PUT
- `/api/v1/address/{id}`: Atualiza um endereço.

DELETE
- `/api/v1/address/{id}`: Deleta um endereço.

GET
- `/api/v1/address`: Retorna todos os endereços.

POST
- `/api/v1/address`: Cria um endereço.

### Pessoas: Operações relacionadas a pessoas

GET
- `/api/v1/persons/{id}`: Retorna uma pessoa pelo seu id.

PUT
- `/api/v1/persons/{id}`: Atualiza uma pessoa.

DELETE
- `/api/v1/persons/{id}`: Deleta uma pessoa.

GET
- `/api/v1/persons`: Retorna todas as pessoas.

POST
- `/api/v1/persons`: Cria uma pessoa.

GET
- `/api/v1/persons/name/{name}`: Retorna uma pessoa pelo seu nome.

### Usuários: Operações relacionadas a usuários

GET
- `/api/v1/users/{id}`: Retorna um usuário pelo seu id.

PUT
- `/api/v1/users/{id}`: Atualiza um usuário.

DELETE
- `/api/v1/users/{id}`: Deleta um usuário.

GET
- `/api/v1/users`: Retorna todos os usuários.

POST
- `/api/v1/users`: Cria um usuário.

GET
- `/api/v1/users/name/{name}`: Retorna um usuário pelo seu nome.

## Licença

Este projeto está licenciado sob a Licença MIT - veja o arquivo `LICENSE.md` para detalhes