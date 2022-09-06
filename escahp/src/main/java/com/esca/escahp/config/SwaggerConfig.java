package com.esca.escahp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import java.net.URI;

import static org.springframework.web.servlet.function.RequestPredicates.GET;
import static org.springframework.web.servlet.function.RouterFunctions.route;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	@Bean
	public Docket restAPI(){
		final ApiInfo apiInfo = new ApiInfoBuilder()
			.title("ESCA 홈페이지 게시판 API")
			.version("1.0")
			.build();

		return new Docket(DocumentationType.SWAGGER_2)
			.useDefaultResponseMessages(false)
			.apiInfo(apiInfo)
			.select()
			.apis(RequestHandlerSelectors.basePackage("com.esca.escahp"))
			.paths(PathSelectors.any())
			.build();
	}
	@Bean
	RouterFunction<ServerResponse> routerFunction() {
		return route(GET("/docs"), req ->
				ServerResponse.temporaryRedirect(URI.create("swagger-ui.html")).build());
	}
}
