package sa.mhmd.nabd.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        Info info = new Info()
                .title("Nabd API")
                .version("1.0")
                .description("Content Management System & Discovery Systems API")
                .contact(new Contact()
                        .name("Nabd Team")
                        .email("dev@nabd.sa"));

        return new OpenAPI().info(info);
    }
}