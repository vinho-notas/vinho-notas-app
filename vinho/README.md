# Serviço de Vinho

Este é um serviço baseado em Java para gerenciar o micro serviço de vinho. Ele usa PostgreSQL para armazenamento de dados e Maven para gerenciamento de projetos.

## Estrutura do Projeto

O projeto é estruturado em vários pacotes:
- `com.vinhonotas.vinho.domain`: Contém as regras de negócio.
- `com.vinhonotas.vinho.application`: Contém classes de serviços para as regras de negócio.
- `com.vinhonotas.vinho.interfaces`: Contém classes de controle para manipulação de solicitações HTTP.
- `com.vinhonotas.vinho.infrastructure`: Contém classes de repositório para acesso a dados.-
- `com.vinhonotas.vinho.configuration`: Contém classes de configuração.
- `com.vinhonotas.vinho.utils`: Contém classes utilitárias.

## Configuração

Para configurar o projeto, você precisa ter o Java e o Maven instalados. Em seguida, você pode clonar o repositório e executar `mvn install` para instalar as dependências necessárias.

## Uso

Você pode iniciar o aplicativo executando `mvn spring-boot:run`. O serviço estará disponível em `http://localhost:8081/swagger-ui/index.html`.

## Endpoints

### Vinhos: Operações relacionadas a vinhos

GET
- `/api/v1/wines/{id}`: Retorna um vinho pelo id.

PUT
- `/api/v1/wines/{id}`: Atualiza um vinho pelo id.

DELETE
- `/api/v1/wines/{id}`: Deleta um vinho pelo id.

GET
- `/api/v1/wines`: Retorna todos os vinhos.

POST
- `/api/v1/wines`: Cria um vinho.

## Licença

Este projeto está licenciado sob a Licença MIT - veja o arquivo `LICENSE.md` para detalhes