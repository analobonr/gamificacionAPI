package aplicacion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;


@SpringBootApplication
public class GamificacionAPIApplication extends SpringBootServletInitializer {

	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(GamificacionAPIApplication.class);
    }
	
	public static void main(String[] args) {
		SpringApplication.run(GamificacionAPIApplication.class, args);
	}	
}
