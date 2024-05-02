# Serviço de Avaliação de Vinho

Este é um serviço baseado em Java para gerenciar o micro serviço de avaliação de vinho. Ele usa PostgreSQL para armazenamento de dados e Maven para gerenciamento de projetos.

## Estrutura do Projeto

O projeto é estruturado em vários pacotes:
- `com.vinhonotas.avaliacao.domain`: Contém as regras de negócio.
- `com.vinhonotas.avaliacao.application`: Contém classes de serviços para as regras de negócio.
- `com.vinhonotas.avaliacao.interfaces`: Contém classes de controle para manipulação de solicitações HTTP.
- `com.vinhonotas.avaliacao.infrastructure`: Contém classes de repositório para acesso a dados.-
- `com.vinhonotas.avaliacao.configuration`: Contém classes de configuração.
- `com.vinhonotas.avaliacao.utils`: Contém classes utilitárias.

## Configuração

Para configurar o projeto, você precisa ter o Java e o Maven instalados. Em seguida, você pode clonar o repositório e executar `mvn install` para instalar as dependências necessárias.

## Uso

Você pode iniciar o aplicativo executando `mvn spring-boot:run`. O serviço estará disponível em `http://localhost:8082/swagger-ui/index.html`.

## Endpoints

### Point Scales: Operações relacionadas a avaliação de vinhos

GET
- `/api/v1/point-scales/{id}`: Retorna uma avaliação de vinho pelo id.

PUT
- `/api/v1/point-scales/{id}`: Atualiza uma avaliação de vinho pelo id.

DELETE
- `/api/v1/point-scales/{id}`: Deleta uma avaliação de vinho pelo id.

GET
- `/api/v1/point-scales`: Retorna todas as avaliações de vinho.

POST
- `/api/v1/point-scales`: Cria uma avaliação de vinho.

## Licença

Este projeto está licenciado sob a Licença MIT - veja o arquivo `LICENSE.md` para detalhes