package com.vinhonotas.bff.configuration;

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
                        .url("http://localhost:8084")
                        .description("Localhost Server URL");
        Contact contact = new Contact()
                .email("vanderlei.master@gmail.com")
                .name("Vanderlei Kleinschmidt");
        Info info = new Info()
                .contact(contact)
                .description("BFF - Back end For Front end")
                .title("BFF").version("V0.0.1")
                .license(new License()
                        .name("Apache 2.0")
                        .url("http://springdoc.org"));

        return new OpenAPI()
                .info(info)
                .addServersItem(localServer);
    }
}
