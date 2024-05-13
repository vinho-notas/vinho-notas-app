# Serviço de Degustação de Vinho

Este é um serviço baseado em Java para gerenciar o micro serviço de degustação de vinho. Ele usa PostgreSQL para armazenamento de dados e Maven para gerenciamento de projetos.

## Estrutura do Projeto

O projeto é estruturado em vários pacotes:
- `com.vinhonotas.degustacao.domain`: Contém as regras de negócio.
- `com.vinhonotas.degustacao.application`: Contém classes de serviços para as regras de negócio.
- `com.vinhonotas.degustacao.interfaces`: Contém classes de controle para manipulação de solicitações HTTP.
- `com.vinhonotas.degustacao.infrastructure`: Contém classes de repositório para acesso a dados.-
- `com.vinhonotas.degustacao.configuration`: Contém classes de configuração.
- `com.vinhonotas.degustacao.utils`: Contém classes utilitárias.

## Configuração

Para configurar o projeto, você precisa ter o Java e o Maven instalados. Em seguida, você pode clonar o repositório e executar `mvn install` para instalar as dependências necessárias.

## Uso

Você pode iniciar o aplicativo executando `mvn spring-boot:run`. O serviço estará disponível em `http://localhost:8083/swagger-ui/index.html`.

## Endpoints

### Tasting Card: Operações relacionadas a ficha de degustação do vinho

POST
- `/api/v1/tasting-card`: Cadastrar ficha de degustação.

GET
- `/api/v1/tasting-card`: Retorna uma lista com todas as fichas de degustação cadastradas.

GET
- `/api/v1/tasting-card/{id}`: Retorna uma ficha de degustação cadastrada pelo id.

PUT
- `/api/v1/tasting-card/{id}`: Atualiza uma ficha de degustação cadastrada pelo id.

DELETE
- `/api/v1/tasting-card/{id}`: Deleta uma ficha de degustação cadastrada pelo id.

## Licença

Este projeto está licenciado sob a Licença MIT - veja o arquivo `LICENSE.md` para detalhes