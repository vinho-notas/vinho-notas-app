# Harmonização de Vinhos

Este é um projeto Java Spring Boot que fornece uma API REST para harmonização de vinhos. A API permite que os usuários obtenham informações sobre vinhos, obtenham sugestões de harmonização de vinhos e obtenham sugestões de harmonização de vinhos com o menu.
Para executar esse serviço é utilizado o Spring AI, que é uma biblioteca Java para interagir com a API OpenAI.

## Tecnologias Utilizadas

- Java
- Spring Boot
- Spring AI
- Maven

## Configuração

Para configurar o projeto, você precisa ter o Java e o Maven instalados. Em seguida, você pode clonar o repositório e executar `mvn install` para instalar as dependências necessárias.

## Uso

Você pode iniciar o aplicativo executando `mvn spring-boot:run`. O serviço estará disponível em `http://localhost:8085/swagger-ui/index.html`.

## Endpoints

### Pairing: Operações relacionadas a harmonização de vinhos

GET
- `/api/v1/pairing/pairings`: Retorna a harmonização do vinho

GET
- `/api/v1/pairing/menu`: Retorna a harmonização do vinho com o menu

GET
- `/api/v1/pairing/information`: Retorna informações sobre o vinho

## Licença

Este projeto está licenciado sob a Licença MIT - veja o arquivo `LICENSE.md` para detalhes