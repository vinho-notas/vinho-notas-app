# Serviço de Degustação de Vinho

Este é um serviço baseado em Java para gerenciar o micro serviço de degustação de vinho. Ele usa PostgreSQL para armazenamento de dados e Maven para gerenciamento de projetos.

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

Você pode iniciar o aplicativo executando `mvn spring-boot:run`. O serviço estará disponível em `http://localhost:8083/swagger-ui/index.html`.

## Endpoints

### Tasting: Operações relacionadas a degustação do vinho

POST
- `/api/v1/tasting`: Cadastrar uma degustação.

GET
- `/api/v1/tasting`: Retorna uma lista com todas as degustações cadastradas.

GET
- `/api/v1/tasting/{id}`: Retorna uma degustação cadastrada pelo id.

PUT
- `/api/v1/tasting/{id}`: Atualiza uma degustação cadastrada pelo id.

DELETE
- `/api/v1/tasting/{id}`: Deleta uma degustação cadastrada pelo id.

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

### Aromas: Operações relacionadas a percepção de Aromas dos vinhos

POST
- `/api/v1/aromas`: Cadastrar os aromas.

GET
- `/api/v1/aromas`: Retorna uma lista com todos os aromas cadastrados.

GET
- `/api/v1/aromas/{id}`: Retorna um aroma cadastrado pelo id.

PUT
- `/api/v1/aromas/{id}`: Atualiza um aroma cadastrado pelo id.

DELETE
- `/api/v1/aromas/{id}`: Deleta um aroma cadastrado pelo id.

### Olfactory Inspection: Operações relacionadas a percepção olfativa na degustação do vinho

POST
- `/api/v1/olfactory-inspection`: Cadastrar percepções olfativas.

GET
- `/api/v1/olfactory-inspection`: Retorna uma lista com todas as percepções olfativas cadastradas.

GET
- `/api/v1/olfactory-inspection/{id}`: Retorna uma percepção olfativa cadastrada pelo id.

PUT
- `/api/v1/olfactory-inspection/{id}`: Atualiza uma percepção olfativa cadastrada pelo id.

DELETE
- `/api/v1/olfactory-inspection/{id}`: Deleta uma percepção olfativa cadastrada pelo id.

### Visual Inspection: Operações relacionadas a percepção visual na degustação do vinho

POST
- `/api/v1/visual-inspection`: Cadastrar percepções visuais.

GET
- `/api/v1/visual-inspection`: Retorna uma lista com todas as percepções visuais cadastradas.

GET
- `/api/v1/visual-inspection/{id}`: Retorna uma percepção visual cadastrada pelo id.

PUT
- `/api/v1/visual-inspection/{id}`: Atualiza uma percepção visual cadastrada pelo id.

DELETE
- `/api/v1/visual-inspection/{id}`: Deleta uma percepção visual cadastrada pelo id.

### Gustatory Inspection: Operações relacionadas a percepção gustativa na degustação do vinho

POST
- `/api/v1/gustatory-inspection`: Cadastrar percepções gustativas.

GET
- `/api/v1/gustatory-inspection`: Retorna uma lista com todas as percepções gustativas cadastradas.

GET
- `/api/v1/gustatory-inspection/{id}`: Retorna uma percepção gustativa cadastrada pelo id.

PUT
- `/api/v1/gustatory-inspection/{id}`: Atualiza uma percepção gustativa cadastrada pelo id.

DELETE
- `/api/v1/gustatory-inspection/{id}`: Deleta uma percepção gustativa cadastrada pelo id.

## Licença

Este projeto está licenciado sob a Licença MIT - veja o arquivo `LICENSE.md` para detalhes