# Usando uma imagem base com Java e Maven
FROM maven:3.8.4-openjdk-17-slim AS build

# Informações sobre o mantenedor
LABEL maintainer="Vanderlei Kleinschmidt <vanderleik@yahoo.com.br>"

# Copiando os arquivos do projeto
COPY src /app/src

# Copiando o arquivo pom.xml
COPY pom.xml /app

# Definindo o diretório de trabalho. É aqui que o Maven irá buildar o projeto
WORKDIR /app

# Baixe as dependências do Maven
RUN mvn clean install

FROM openjdk:17-jdk-slim

# Copie o arquivo JAR do aplicativo
COPY --from=build /app/target/bff-1.0.0-alpha.jar /app/app.jar

WORKDIR /app

# Defina o endereço do banco de dados
#ENV DATABASE_URL jdbc:postgresql://postgresql-vinho:5432/vinho-notas-vinho

EXPOSE 8084
CMD ["java", "-jar", "app.jar"]
