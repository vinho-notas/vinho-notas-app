# Serviço de Cadastro

Este é um serviço baseado em Java para gerenciar o registro de usuários e operações relacionadas. Ele usa PostgreSQL para armazenamento de dados e Maven para gerenciamento de projetos.

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

Você pode iniciar o aplicativo executando `mvn spring-boot:run`. O serviço estará disponível em `http://localhost:8080/api/v1/users`.

## Endpoints

- `POST /api/v1/users`: Cria um novo usuário.
- `GET /api/v1/users`: Obtém todos os usuários.
- `GET /api/v1/users/{id}`: Obtém um usuário pelo seu ID.
- `GET /api/v1/users/name/{name}`: Obtém um usuário pelo seu nome.
- `PUT /api/v1/users/{id}`: Atualiza um usuário.
- `DELETE /api/v1/users/{id}`: Deleta um usuário.

## Licença

Este projeto está licenciado sob a Licença MIT - veja o arquivo `LICENSE.md` para detalhes