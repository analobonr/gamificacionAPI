package aplicacion;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.service.Tag;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.OperationsSorter;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger.web.UiConfigurationBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	@Bean
    public Docket api() {
			
                return new Docket(DocumentationType.SWAGGER_2)
                            .select()
                            .apis(
                                    RequestHandlerSelectors
                                    .basePackage("aplicacion"))
                            .paths(PathSelectors.any())
                            .build()
                            .tags(new Tag("Configuración de Partidas", "Recursos para la gestión de Plantillas"),
                            		new Tag("Usuarios", "Recursos para la gestión de ususarios"))
                            .apiInfo(new ApiInfoBuilder().version("1.0").title("Gamificación API").description("Documentation Gamificación API v1.0").build());
        }
	
	@Bean
	UiConfiguration uiConfig() {
	    return UiConfigurationBuilder
	            .builder()
	            .operationsSorter(OperationsSorter.METHOD)
	            .build();
	}
}
