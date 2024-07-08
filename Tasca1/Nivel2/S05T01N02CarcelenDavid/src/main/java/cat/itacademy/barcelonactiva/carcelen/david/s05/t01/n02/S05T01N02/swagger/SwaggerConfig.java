package cat.itacademy.barcelonactiva.carcelen.david.s05.t01.n02.S05T01N02.swagger;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Flower API")
                        .version("1.0")
                        .description("API for managing flowers"));
    }
}
