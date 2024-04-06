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

### wine-controller

GET
- `/api/v1/wines/{id}`:

PUT
- `/api/v1/wines/{id}`:

DELETE
- `/api/v1/wines/{id}`:

GET
- `/api/v1/wines`:

POST
- `/api/v1/wines`:

### visual-inspection-controller

GET
- `/api/v1/visual-inspection/{id}`:

PUT
- `/api/v1/visual-inspection/{id}`:

DELETE
- `/api/v1/visual-inspection/{id}`:

GET
- `/api/v1/visual-inspection`:

POST
- `/api/v1/visual-inspection`:

### user-controller

GET
- `/api/v1/users/{id}`:

PUT
- `/api/v1/users/{id}`:

DELETE
- `/api/v1/users/{id}`:

GET
- `/api/v1/users`:

POST
- `/api/v1/users`:

GET
- `/api/v1/users/name/{name}`:

### tasting-controller

GET
- `/api/v1/tasting/{id}`:

PUT
- `/api/v1/tasting/{id}`:

DELETE
- `/api/v1/tasting/{id}`:

GET
- `/api/v1/tasting`:

POST
- `/api/v1/tasting`:

### tasting-card-controller

GET
- `/api/v1/tasting-card/{id}`:

PUT
- `/api/v1/tasting-card/{id}`:

DELETE
- `/api/v1/tasting-card/{id}`:

GET
- `/api/v1/tasting-card`:

POST
- `/api/v1/tasting-card`:

### point-scale-controller

GET
- `/api/v1/point-scales/{id}`:

PUT
- `/api/v1/point-scales/{id}`:

DELETE
- `/api/v1/point-scales/{id}`:

GET
- `/api/v1/point-scales`:

POST
- `/api/v1/point-scales`:

### person-controller

GET
- `/api/v1/persons/{id}`:

PUT
- `/api/v1/persons/{id}`:

DELETE
- `/api/v1/persons/{id}`:

GET
- `/api/v1/persons`:

POST
- `/api/v1/persons`:

GET
- `/api/v1/persons/name/{name}`:

### olfactory-inspection-controller

GET
- `/api/v1/olfactory-inspection/{id}`:

PUT
- `/api/v1/olfactory-inspection/{id}`:

DELETE
- `/api/v1/olfactory-inspection/{id}`:

GET
- `/api/v1/olfactory-inspection`:

POST
- `/api/v1/olfactory-inspection`:

### gustatory-inspection-controller

GET
- `/api/v1/gustatory-inspection/{id}`:

PUT
- `/api/v1/gustatory-inspection/{id}`:

DELETE
- `/api/v1/gustatory-inspection/{id}`:

GET
- `/api/v1/gustatory-inspection`:

POST
- `/api/v1/gustatory-inspection`:

### aromas-controller

GET
- `/api/v1/aromas/{id}`:

PUT
- `/api/v1/aromas/{id}`:

DELETE
- `/api/v1/aromas/{id}`:

GET
- `/api/v1/aromas`:

POST
- `/api/v1/aromas`:

### address-controller

GET
- `/api/v1/address/{id}`:

PUT
- `/api/v1/address/{id}`:

DELETE
- `/api/v1/address/{id}`:

GET
- `/api/v1/address`:

POST
- `/api/v1/address`:

### state-controller

GET
- `/api/v1/states`:

GET
- `/api/v1/states/{id}`:

GET
- `/api/v1/states/uf/{uf}`:

GET
- `/api/v1/states/name/{name}`:

### country-controller

GET
- `/api/v1/countries`:

GET
- `/api/v1/countries/{id}`:

GET
- `/api/v1/countries/name/{name}`:

## Licença

Este projeto está licenciado sob a Licença MIT - veja o arquivo `LICENSE.md` para detalhes