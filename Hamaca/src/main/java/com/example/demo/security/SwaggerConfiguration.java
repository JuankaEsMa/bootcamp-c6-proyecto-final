package com.example.demo.security;

import java.util.Properties;

import org.springframework.boot.context.event.ApplicationPreparedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertiesPropertySource;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class SwaggerConfiguration implements ApplicationListener<ApplicationPreparedEvent>{

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI().addSecurityItem(new SecurityRequirement().addList("Bearer Authentication"))
            .components(new Components().addSecuritySchemes("Bearer Authentication", createAPIKeyScheme()))
            .info(new Info().title("Hamaca")
                .description("description of API.")
                .version("1.0")
                .license(new License().name("License of API")
                    .url("API license URL")));
    }
    private SecurityScheme createAPIKeyScheme() {
        return new SecurityScheme().type(SecurityScheme.Type.HTTP)
            .bearerFormat("JWT")
            .scheme("bearer")
            .description("Authorize Client");
    }
	@Override
	public void onApplicationEvent(ApplicationPreparedEvent event) {
		ConfigurableEnvironment environment = event.getApplicationContext().getEnvironment();
        Properties props = new Properties();
        props.put("springdoc.swagger-ui.path", "/");
        environment.getPropertySources()
          .addFirst(new PropertiesPropertySource("programmatically", props));
	}
		
}
