package com.vinhonotas.vinho.configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfiguration {

    @Bean
    public OpenAPI openApiInformation() {
        Server localServer =
                new Server()
                        .url("http://localhost:8081")
                        .description("Localhost Server URL");
        Contact contact = new Contact()
                .email("vanderlei.master@gmail.com")
                .name("Vanderlei Kleinschmidt")
                .url("https://www.linkedin.com/in/vanderlei-kleinschmidt-a1557731/");
        Info info = new Info()
                .contact(contact)
                .description("Api de cadastro de vinhos")
                .title("Api de vinho").version("V1.0.0-ALPHA")
                .license(new License()
                        .name("Apache 2.0")
                        .url("http://springdoc.org"));

        return new OpenAPI()
                .info(info)
                .addServersItem(localServer)
                .externalDocs(new ExternalDocumentation().description("Springdoc OpenAPI 3.0").url("https://springdoc.org/"));
    }
}
